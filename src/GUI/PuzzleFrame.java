package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PuzzleFrame implements ActionListener
{
    GUI gui;
    JPanel spacerPanel, mainPanel, validColorPanel, inputPanel;

    ArrayList<ArrayList<JButton>> colorButtons;

    JButton[][] incDecrButtons;
    JLabel heightLabel, widthLabel;

    ImageIcon empty, spacer, amara, zane, flak, moze;

    public PuzzleFrame(GUI gui)
    {
        this.gui = gui;
        empty = new ImageIcon("images/empty.png");
        spacer = new ImageIcon("images/spacer.png");
        amara = new ImageIcon("images/amara.png");
        zane = new ImageIcon("images/zane.png");
        flak = new ImageIcon("images/flak.png");
        moze = new ImageIcon("images/moze.png");

        //initialize panels
        spacerPanel = new JPanel();
        mainPanel = new JPanel();
        validColorPanel = new JPanel();
        inputPanel = new JPanel();

        //initialize main buttons
        colorButtons = new ArrayList<>();

        for(int i = 0; i < 4; i++)
        {
            colorButtons.add(new ArrayList<>());
            for(int j = 0; j < 5; j++)
            {
                colorButtons.get(i).add(new JButton());
                //colorButtons.get(i).get(j).setSize(80, 80);
                colorButtons.get(i).get(j).addActionListener(this);
                colorButtons.get(i).get(j).setIcon(empty);
                colorButtons.get(i).get(j).setBackground(Color.black);
                mainPanel.add(colorButtons.get(i).get(j));
            }
        }

        mainPanel.setLayout(new GridLayout(colorButtons.size(), colorButtons.get(0).size()));

        //set up the increment and decrement buttons
        incDecrButtons = new JButton[2][4];

        heightLabel = new JLabel("Height (" + colorButtons.size() + "):");
        widthLabel = new JLabel("Width (" + colorButtons.get(0).size() + "):");
        inputPanel.add(heightLabel);
        for(int i = 0; i < incDecrButtons.length; i++)
        {
            if(i == 1)
            {
                inputPanel.add(widthLabel);
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
                inputPanel.add(incDecrButtons[i][j]);
            }

        }

        inputPanel.setLayout(new GridLayout(incDecrButtons.length, incDecrButtons[0].length+1));
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
                    sizeChange(i, j);
                }
            }
        }
        for(int i = 0; i < colorButtons.size(); i++)
        {
            for(int j = 0; j < colorButtons.get(i).size(); j++)
            {
                if(action == colorButtons.get(i).get(j))//changing the value of a button
                {
                    buttonChange(i, j);
                }
            }
        }
    }

    public void sizeChange(int i, int j)
    {
        int numInc = Integer.parseInt(incDecrButtons[i][j].getText());

        if(i == 0)//height (outer arraylist)
        {
            if(numInc < 0)//removing rows
            {
                if(colorButtons.size() + numInc < 1)//if we remove numInc number of rows will there still be a row left?
                {
                    System.out.println("This will remove all rows and is not allowed. Try something else.");
                }
                else//it is safe to remove the rows
                {
                    for(int k = 0; k < Math.abs(numInc); k++)
                    {
                        colorButtons.remove(colorButtons.size()-1);
                    }
                }
            }
            int newSize = colorButtons.size() + numInc;
            for(int k = colorButtons.size(); k < newSize; k++)
            {
                colorButtons.add(new ArrayList<>());
                for(int l = 0; l < colorButtons.get(0).size(); l++)
                {
                    colorButtons.get(k).add(new JButton());
                    colorButtons.get(k).get(l).addActionListener(this);
                    colorButtons.get(k).get(l).setIcon(empty);
                    colorButtons.get(k).get(l).setBackground(Color.black);
                }
            }
            heightLabel.setText("Height (" + colorButtons.size() + "):");
        }
        else if(i == 1)//width
        {
            if(numInc < 0)//removing cols
            {
                if(colorButtons.get(0).size() + numInc < 1)//if we remove numInc number of cols will there still be a col left?
                {
                    System.out.println("This will remove all rows and is not allowed. Try something else.");
                }
                else//it is safe to remove the cols
                {
                    for(ArrayList<JButton> colorButton : colorButtons)
                    {
                        for(int l = 0; l < Math.abs(numInc); l++)
                        {
                            colorButton.remove(colorButton.size() - 1);
                        }
                    }
                }
            }
            for(ArrayList<JButton> colorButton : colorButtons)
            {
                int oldSize = colorButton.size() - 1;
                for(int l = oldSize; l < oldSize + numInc; l++)
                {
                    colorButton.add(new JButton());
                    colorButton.get(l + 1).addActionListener(this);
                    colorButton.get(l + 1).setIcon(empty);
                    colorButton.get(l + 1).setBackground(Color.black);
                }
            }
            widthLabel.setText("Height (" + colorButtons.get(0).size() + "):");
        }
        else
        {
            System.out.println("I should not be here");
        }
        resetFrame();
    }

    public void buttonChange(int i, int j)//cycles through the possible images in the following order: empty -> amara -> zane -> flak -> moze -> empty
    {
        Icon img= colorButtons.get(i).get(j).getIcon();
        if(img == empty)
        {
            img = amara;
        }
        else if(img == amara)
        {
            img = zane;
        }
        else if(img == zane)
        {
            img = flak;
        }
        else if(img == flak)
        {
            img = moze;
        }
        else if(img == moze)
        {
            img = empty;
        }
        else//something got messed up
        {
            img = empty;
        }
        colorButtons.get(i).get(j).setIcon(img);
    }

    public void resetFrame()
    {
        addButtons();
        mainPanel.setLayout(new GridLayout(colorButtons.size(), colorButtons.get(0).size()));
        gui.resetFrame();
    }

    public void addButtons()
    {
        mainPanel.removeAll();
        for(ArrayList<JButton> colorButton : colorButtons)
        {
            for(JButton jButton : colorButton)
            {
                mainPanel.add(jButton);
            }
        }
    }

    public JPanel getMainPanel()
    {
        return mainPanel;
    }

    public JPanel getSpacerPanel()
    {
        return spacerPanel;
    }

    public JPanel getValidColorPanel()
    {
        return validColorPanel;
    }

    public JPanel getInputPanel()
    {
        return inputPanel;
    }

    public ArrayList<ArrayList<JButton>> getColorButtons()
    {
        return colorButtons;
    }
}
