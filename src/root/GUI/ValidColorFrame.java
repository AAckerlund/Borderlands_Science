package root.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ValidColorFrame implements ActionListener
{
    GUI gui;
    JPanel mainPanel;
    
    ArrayList<JButton[]> bonusButtons;
    ImageIcon empty;
    
    public ValidColorFrame(GUI gui)
    {
        this.gui = gui;
        mainPanel = new JPanel();

        empty = new ImageIcon("images/blank.png");

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
        Object action = e.getSource();
        for(JButton[] bonusButton : bonusButtons)
        {
            for(JButton jButton : bonusButton)
            {
                if(action == jButton)
                {
                    jButton.setIcon(gui.buttonChange(jButton.getIcon()));
                    if(jButton.getIcon().toString().equals("images/empty.png"))
                    {
                        jButton.setIcon(empty);
                    }
                }
            }
        }
    }

    public void sizeChange(int k)
    {
        getBonusButtons().add(new JButton[2]);
        for(int l = 0; l < 2; l++)
        {
            getBonusButtons().get(k)[l] = new JButton();
            getBonusButtons().get(k)[l].addActionListener(this);
            getBonusButtons().get(k)[l].setIcon(empty);
            getBonusButtons().get(k)[l].setBackground(Color.black);
        }
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
