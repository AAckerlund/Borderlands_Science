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

    public Icon buttonChange(Icon img)//cycles through the possible images in the following order: empty -> amara -> zane -> flak -> moze -> empty
    {
        if(img.toString().equals("images/empty.png"))
        {
            img = new ImageIcon("images/amara.png");
        }
        else if(img.toString().equals("images/amara.png"))
        {
            img = new ImageIcon("images/zane.png");
        }
        else if(img.toString().equals("images/zane.png"))
        {
            img = new ImageIcon("images/flak.png");
        }
        else if(img.toString().equals("images/flak.png"))
        {
            img = new ImageIcon("images/moze.png");
        }
        else if(img.toString().equals("images/moze.png"))
        {
            img = new ImageIcon("images/empty.png");
        }
        else//something got messed up
        {
            img = new ImageIcon("images/empty.png");
        }
        return img;
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
