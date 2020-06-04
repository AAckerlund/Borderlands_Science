package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class BonusFrame implements ActionListener
{
    //JFrame frame;
    JPanel mainPanel;
    
    ArrayList<JButton[]> bonusButtons;
    ImageIcon empty;
    
    public BonusFrame(GUI gui)
    {
        mainPanel = new JPanel();

        empty = new ImageIcon("images/empty.png");

        bonusButtons = new ArrayList<>();
        for(int i = 0; i < gui.getPf().getColorButtons().size(); i++)
        {
            bonusButtons.add(new JButton[2]);
            for(int j = 0; j < bonusButtons.get(i).length; j++)
            {
                bonusButtons.get(i)[j] = new JButton();
                bonusButtons.get(i)[j].addActionListener(this);
                bonusButtons.get(i)[j].setIcon(empty);
                bonusButtons.get(i)[j].setBackground(Color.black);
                mainPanel.add(bonusButtons.get(i)[j], BorderLayout.CENTER);
            }
        }
        mainPanel.setLayout(new GridLayout(gui.getPf().getColorButtons().size(), 2));

        mainPanel.setBackground(Color.black);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        //TODO make buttons change image on click
    }

    public ArrayList<JButton[]> getBonusButtons()
    {
        return bonusButtons;
    }

    public JPanel getMainPanel()
    {
        return mainPanel;
    }
}
