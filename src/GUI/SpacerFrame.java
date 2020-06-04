package GUI;

import javax.swing.*;

public class SpacerFrame extends JFrame
{
    JFrame frame;
    JPanel mainPanel;

    public SpacerFrame()
    {
        mainPanel = new JPanel();
        frame = new JFrame("Spacer Frame");
        frame.setVisible(true);
        frame.pack();
        frame.setLocationRelativeTo(null);
    }

    public JPanel getMainPanel()
    {
        return mainPanel;
    }
}
