import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class GUI extends JFrame implements ActionListener
{
    JFrame frame;
    JPanel spacerPanel, mainPanel, validColorPanel, inputPanel;

    ArrayList<ArrayList<JButton>> colorButtons;

    JButton[][] incDecrButtons;
    JLabel heightLabel, widthLabel;

    ImageIcon empty, spacer, amara, zane, flak, moze;

    public GUI()
    {
        empty = new ImageIcon("images/empty.png");
        spacer = new ImageIcon("images/spacer.png");
        amara = new ImageIcon("images/amara.png");
        zane = new ImageIcon("images/zane.png");
        flak = new ImageIcon("images/flak.png");
        moze = new ImageIcon("images/moze.png");
        //initialize frame
        frame = new JFrame("Borderlands Science Solver");

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

        heightLabel = new JLabel("Height:");
        widthLabel = new JLabel("Width:");
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


        //add panels to frame
        frame.add(spacerPanel, BorderLayout.EAST);
        frame.add(mainPanel, BorderLayout.CENTER);
        frame.add(validColorPanel, BorderLayout.WEST);
        frame.add(inputPanel, BorderLayout.SOUTH);

        //more frame stuff
        frame.setSize(500,500);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public int randNum()
    {
        return new Random().nextInt(4);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        Object action = e.getSource();
        /*for(ArrayList<JButton> color : colorButtons)
        {
            for(JButton jButton : color)
            {
                if(action == jButton)
                {
                    int num = Integer.parseInt(jButton.getText());
                    if(num >= 4)
                        num = 0;
                    else
                        num++;
                    jButton.setText(String.valueOf(num));
                }
            }
        }*/

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
        System.out.println(numInc + " " + i);

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
        }
        else
        {
            System.out.println("I should not be here");
        }
        resetFrame();

        System.out.println("done");
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
        frame.pack();
        frame.invalidate();
        frame.validate();
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
}
