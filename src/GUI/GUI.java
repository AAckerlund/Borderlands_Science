package GUI;

import javax.swing.*;
import java.awt.*;

public class GUI extends JFrame// implements ActionListener
{
    JFrame frame;
    private PuzzleFrame pf;
    private SpacerFrame sf;
    private BonusFrame bf;

    public GUI()
    {
        //initialize frame
        frame = new JFrame("Borderlands Science Solver");
        pf = new PuzzleFrame(this);
        bf = new BonusFrame(this);
        sf = new SpacerFrame();

        //add panels to frame
        frame.add(pf.getMainPanel(), BorderLayout.CENTER);
        frame.add(pf.getInputPanel(), BorderLayout.SOUTH);

        frame.add(bf.getMainPanel(), BorderLayout.WEST);

        frame.add(sf.getMainPanel(), BorderLayout.EAST);
        //more frame stuff
        //frame.setSize(500,500);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //sf = new SpacerFrame();
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

    public BonusFrame getBf()
    {
        return bf;
    }
}
