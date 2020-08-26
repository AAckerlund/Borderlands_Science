package root.GUI;

import root.Driver;

import javax.swing.*;
import java.awt.*;

public class GUI extends JFrame
{
    private JFrame frame;
    private PuzzleFrame pf;
    private InputFrame inf;
    private ValidColorFrame vcf;

    public GUI(Driver driver)
    {
        //initialize frame
        frame = new JFrame("Borderlands Science Solver");
        pf = new PuzzleFrame(this);
        vcf = new ValidColorFrame(this);
        inf = new InputFrame(this, driver);

        //add panels to frame
        frame.add(pf.getMainPanel(), BorderLayout.CENTER);

        frame.add(vcf.getMainPanel(), BorderLayout.WEST);

        frame.add(inf.getMainPanel(), BorderLayout.SOUTH);

        //more frame stuff
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    /**
     * resets the gui to show updates made elsewhere in the code
     */
    public void resetFrame()
    {
        frame.pack();
        frame.invalidate();
        frame.validate();
        frame.setLocationRelativeTo(null);
    }

    /**
     * cycles through the possible images in the following order: empty -> amara -> zane -> flak -> moze -> empty
     * @param img the current Icon
     * @return the next Icon in the cycle
     */
    public Icon buttonChange(Icon img)
    {
        switch(img.toString())
        {
            case "./images/empty.png":
            case "./images/blank.png":
                img = new ImageIcon("./images/amara.png");
                break;
            case "./images/amara.png":
                img = new ImageIcon("./images/zane.png");
                break;
            case "./images/zane.png":
                img = new ImageIcon("./images/flak.png");
                break;
            case "./images/flak.png":
                img = new ImageIcon("./images/moze.png");
                break;
            default://case "./images/moze.png" or something got messed up
                img = new ImageIcon("./images/empty.png");
                break;
        }
        return img;
    }

    public PuzzleFrame getPf()
    {
        return pf;
    }

    public ValidColorFrame getVCf()
    {
        return vcf;
    }

    public InputFrame getInf()
    {
        return inf;
    }

    public JFrame getFrame()
    {
        return frame;
    }
}
