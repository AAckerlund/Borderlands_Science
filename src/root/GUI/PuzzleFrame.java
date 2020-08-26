package root.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PuzzleFrame implements ActionListener
{
    GUI gui;
    JPanel mainPanel;

    private ArrayList<ArrayList<JButton>> colorButtons;

    ImageIcon empty, spacer, amara, zane, flak, moze;

    public PuzzleFrame(GUI gui)
    {
        this.gui = gui;
        empty = new ImageIcon("./images/empty.png");
        spacer = new ImageIcon("./images/spacer.png");
        amara = new ImageIcon("./images/amara.png");
        zane = new ImageIcon("./images/zane.png");
        flak = new ImageIcon("./images/flak.png");
        moze = new ImageIcon("./images/moze.png");

        //initialize panels
        mainPanel = new JPanel();

        //initialize main buttons
        colorButtons = new ArrayList<>();

        //initialize the color buttons
        for(int i = 0; i < 4; i++)
        {
            colorButtons.add(new ArrayList<>());
            for(int j = 0; j < 5; j++)
            {
                colorButtons.get(i).add(new JButton());
                colorButtons.get(i).get(j).addActionListener(this);
                colorButtons.get(i).get(j).setIcon(empty);
                colorButtons.get(i).get(j).setBackground(Color.black);
                mainPanel.add(colorButtons.get(i).get(j));
            }
        }

        //set the layout for the color buttons to a grid with height and width equal to the dimensions of the 2D arraylist
        mainPanel.setLayout(new GridLayout(colorButtons.size(), colorButtons.get(0).size()));

        mainPanel.setBackground(Color.black);//makes the gui look more professional
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        Object action = e.getSource();
        for(ArrayList<JButton> colorButton : colorButtons)
        {
            for(JButton jButton : colorButton)
            {
                if(action == jButton)
                {
                    jButton.setIcon(gui.buttonChange(jButton.getIcon()));
                }
            }
        }
    }

    /**
     * changes the size of the color buttons array
     * @param i used to determine if we are increasing the height or width
     * @param numInc how much to increase (or decrease) by
     */
    public void sizeChange(int i, int numInc)
    {
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
                        gui.getVCf().getBonusButtons().remove(colorButtons.size()-1);
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

                gui.getVCf().sizeChange(k);

            }
            gui.getInf().getHeightLabel().setText("Height (" + colorButtons.size() + ")");
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
            gui.getInf().getWidthLabel().setText("Height (" + colorButtons.get(0).size() + ")");
        }
        else
        {
            System.out.println("I should not be here");
        }
        resetFrame();
    }

    /**
     * refreshes the gui to reflect updates made elsewhere in the code
     */
    public void resetFrame()
    {
        addButtons();
        mainPanel.setLayout(new GridLayout(colorButtons.size(), colorButtons.get(0).size()));
        gui.getVCf().getMainPanel().setLayout(new GridLayout(colorButtons.size(), 2));
        gui.resetFrame();
    }

    /**
     * refreshes all PuzzleFrame buttons by removing and re-adding them to the frame
     */
    public void addButtons()
    {
        mainPanel.removeAll();
        for(ArrayList<JButton> colorButton : colorButtons)//add the "scorable" buttons onto the frame
        {
            for(JButton jButton : colorButton)
            {
                mainPanel.add(jButton);
            }
        }
        gui.getVCf().getMainPanel().removeAll();
        for(int i = 0; i < colorButtons.size(); i++)//add the valid color buttons back onto the frame
        {
            for(int j = 0; j < 2; j++)
            {
                gui.getVCf().getMainPanel().add(gui.getVCf().getBonusButtons().get(i)[j]);
            }
        }
    }

    public JPanel getMainPanel()
    {
        return mainPanel;
    }

    public ArrayList<ArrayList<JButton>> getColorButtons()
    {
        return colorButtons;
    }

    public void setColorButtons(ArrayList<ArrayList<JButton>> buttons)
    {
        for(int i = 0; i < colorButtons.size(); i++)
        {
            for(int j = 0; j < colorButtons.get(i).size(); j++)
            {
                colorButtons.get(i).get(j).setIcon(buttons.get(j).get(i).getIcon());
            }
        }
    }

    public ImageIcon getEmpty()
    {
        return empty;
    }

    public ImageIcon getSpacer()
    {
        return spacer;
    }

    public ImageIcon getAmara()
    {
        return amara;
    }

    public ImageIcon getZane()
    {
        return zane;
    }

    public ImageIcon getFlak()
    {
        return flak;
    }

    public ImageIcon getMoze()
    {
        return moze;
    }
}
