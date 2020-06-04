package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InputFrame implements ActionListener
{
    GUI gui;
    JPanel mainPanel, puzzleInputPanel, spacerPanel, solvePanel;

    JButton[][] incDecrButtons;
    JLabel heightLabel, widthLabel, spacerLabel;

    JButton[] spacerButtons;
    int spacerNum;

    JButton check, solve;

    public InputFrame(GUI gui)
    {
        this.gui = gui;
        mainPanel = new JPanel();
        puzzleInputPanel = new JPanel();

        //set up the increment and decrement buttons
        incDecrButtons = new JButton[2][4];

        heightLabel = new JLabel("Height (" + gui.getPf().getColorButtons().size() + ")");
        widthLabel = new JLabel("Width (" + gui.getPf().getColorButtons().get(0).size() + ")");
        puzzleInputPanel.add(heightLabel);
        for(int i = 0; i < incDecrButtons.length; i++)
        {
            if(i == 1)
            {
                puzzleInputPanel.add(widthLabel);
            }
            for(int j = 0; j < incDecrButtons[i].length; j++)
            {
                switch(j)
                {
                    case 0:
                        incDecrButtons[i][j] = new JButton("-5");
                        break;
                    case 1:
                        incDecrButtons[i][j] = new JButton("-1");
                        break;
                    case 2:
                        incDecrButtons[i][j] = new JButton("+1");
                        break;
                    case 3:
                        incDecrButtons[i][j] = new JButton("+5");
                        break;
                }
                incDecrButtons[i][j].addActionListener(this);
                puzzleInputPanel.add(incDecrButtons[i][j]);
            }

        }
        puzzleInputPanel.setLayout(new GridLayout(incDecrButtons.length, incDecrButtons[0].length+1));
        mainPanel.add(puzzleInputPanel, BorderLayout.NORTH);

        spacerNum = 0;
        spacerLabel = new JLabel("Spacer Count: " + spacerNum);

        spacerButtons = new JButton[4];
        spacerButtons[0] = new JButton("-5");
        spacerButtons[1] = new JButton("-1");
        spacerButtons[2] = new JButton("+1");
        spacerButtons[3] = new JButton("+5");

        spacerPanel = new JPanel();
        spacerPanel.add(spacerLabel);
        for(JButton spacerButton : spacerButtons)
        {
            spacerPanel.add(spacerButton);
            spacerButton.addActionListener(this);
        }
        spacerPanel.setLayout(new GridLayout(1,5));
        mainPanel.add(spacerPanel, BorderLayout.SOUTH);

        solve = new JButton("Solve");
        check = new JButton("check");
        solvePanel = new JPanel();
        solvePanel.add(check);
        solvePanel.add(solve);
        mainPanel.add(solvePanel, BorderLayout.EAST);
        solve.addActionListener(this);
        check.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        Object action = e.getSource();

        for(int i = 0; i < incDecrButtons.length; i++)
        {
            for(int j = 0; j < incDecrButtons[i].length; j++)
            {
                if(action == incDecrButtons[i][j])//changing the size of the board
                {
                    gui.getPf().sizeChange(i, j, Integer.parseInt(incDecrButtons[i][j].getText()));
                }
            }
        }
        for(JButton spacerButton : spacerButtons)
        {
            if(action == spacerButton)
            {
                spacerNum += Integer.parseInt(spacerButton.getText());
                if(spacerNum < 0)
                {
                    spacerNum = 0;
                }
                spacerLabel.setText("Spacer Count: " + spacerNum);
            }
        }

        if(action == check)
        {
            check();
        }
        if(action == solve)
        {
            if(check())
            {
                solve();
            }
        }
    }

    public boolean check()//returns true if the input is valid
    {
        //TODO
        return false;
    }

    public void solve()
    {
        //TODO
    }

    public JPanel getMainPanel()
    {
        return mainPanel;
    }

    public JLabel getHeightLabel()
    {
        return heightLabel;
    }

    public JLabel getWidthLabel()
    {
        return widthLabel;
    }
}
