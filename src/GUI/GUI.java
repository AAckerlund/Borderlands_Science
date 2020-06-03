package GUI;

import javax.swing.*;
import java.awt.*;

public class GUI extends JFrame// implements ActionListener
{
    JFrame frame;
    PuzzleFrame pf;
    SpacerFrame sf;

    public GUI()
    {
        //initialize frame
        frame = new JFrame("Borderlands Science Solver");
        pf = new PuzzleFrame(this);

        //add panels to frame
        frame.add(pf.getSpacerPanel(), BorderLayout.EAST);
        frame.add(pf.getMainPanel(), BorderLayout.CENTER);
        frame.add(pf.getValidColorPanel(), BorderLayout.WEST);
        frame.add(pf.getInputPanel(), BorderLayout.SOUTH);

        //more frame stuff
        frame.setSize(500,500);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        sf = new SpacerFrame();
    }
    public void resetFrame()
    {
        frame.pack();
        frame.invalidate();
        frame.validate();
        frame.setLocationRelativeTo(null);
    }

    public PuzzleFrame getPf()
    {
        return pf;
    }
}
