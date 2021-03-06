package root.GUI;
import root.Driver;
import root.Graph;
import root.Node;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class InputFrame implements ActionListener
{
    Driver driver;
    GUI gui;
    JPanel mainPanel, puzzleInputPanel, spacerPanel, solvePanel;

    JButton[][] incDecrButtons;
    JLabel heightLabel, widthLabel, spacerLabel;

    JButton[] spacerButtons;
    private int spacerNum;

    JButton check, solve;

    public InputFrame(GUI gui, Driver driver)
    {
        this.driver = driver;
        this.gui = gui;
        mainPanel = new JPanel();
        puzzleInputPanel = new JPanel();

        //set up the increment and decrement buttons
        incDecrButtons = new JButton[2][4];

        heightLabel = new JLabel("Height (" + gui.getPf().getColorButtons().size() + ")");
        widthLabel = new JLabel("Width (" + gui.getPf().getColorButtons().get(0).size() + ")");
        puzzleInputPanel.add(heightLabel);
        for(int i = 0; i < incDecrButtons.length; i++)
        {
            if(i == 1)
            {
                puzzleInputPanel.add(widthLabel);
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
                puzzleInputPanel.add(incDecrButtons[i][j]);
            }

        }
        puzzleInputPanel.setLayout(new GridLayout(incDecrButtons.length, incDecrButtons[0].length+1));
        mainPanel.add(puzzleInputPanel, BorderLayout.NORTH);

        spacerNum = 0;
        spacerLabel = new JLabel("Spacer Count: " + spacerNum);

        spacerButtons = new JButton[4];
        spacerButtons[0] = new JButton("-5");
        spacerButtons[1] = new JButton("-1");
        spacerButtons[2] = new JButton("+1");
        spacerButtons[3] = new JButton("+5");

        spacerPanel = new JPanel();
        spacerPanel.add(spacerLabel);
        for(JButton spacerButton : spacerButtons)
        {
            spacerPanel.add(spacerButton);
            spacerButton.addActionListener(this);
        }
        spacerPanel.setLayout(new GridLayout(1,5));
        mainPanel.add(spacerPanel, BorderLayout.SOUTH);

        solve = new JButton("Solve");
        check = new JButton("check");
        solve.addActionListener(this);
        check.addActionListener(this);
        solvePanel = new JPanel();
        solvePanel.add(check);
        solvePanel.add(solve);
        mainPanel.add(solvePanel, BorderLayout.EAST);
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
                    gui.getPf().sizeChange(i, Integer.parseInt(incDecrButtons[i][j].getText()));
                }
            }
        }
        for(JButton spacerButton : spacerButtons)
        {
            if(action == spacerButton)
            {
                spacerNum += Integer.parseInt(spacerButton.getText());
                if(spacerNum < 0)
                {
                    spacerNum = 0;
                }
                spacerLabel.setText("Spacer Count: " + spacerNum);
            }
        }

        if(action == check)
        {
            check(gui.getPf().getColorButtons());
        }
        if(action == solve)
        {
            solve();
        }
    }

    /**
     * checks for a valid graph
     * @param data the graph to check
     * @return true if valid, false otherwise
     */
    public boolean check(ArrayList<ArrayList<JButton>> data)
    {
        ArrayList<ArrayList<Node>> graph = ButtonToNode(data);
        boolean found;
        for(ArrayList<Node> nodes : graph)
        {
            found = false;
            for(Node node : nodes)
            {
                if(node.getColor() == 0 && found)//if we have found a scorable node already and we come across an empty node then the board is invalid.
                {
                    JOptionPane.showMessageDialog(null, "Invalid Graph");
                    return false;
                }
                if(!found && node.getColor() != 0)//found the first scorable node in a column
                {
                    found = true;
                }

            }
        }
        JOptionPane.showMessageDialog(null, "This is a valid graph");
        return true;
    }

    /**
     * starts the execution of the solving algorithm
     */
    public void solve()
    {
        if(check(gui.getPf().getColorButtons()))
        {
            ArrayList<ArrayList<Node>> graph = ButtonToNode(gui.getPf().getColorButtons());
            Graph solution = driver.solve(new Graph(graph, gui), spacerNum, Integer.MIN_VALUE, 0, 0);
            display(solution);
        }
    }

    /**
     * displays a graph to the gui
     * @param g the graph to display
     */
    public void display(Graph g)
    {
        ArrayList<ArrayList<JButton>> buttons = new ArrayList<>();
        for(int i = 0; i < g.getGraph().size(); i++)
        {
            buttons.add(new ArrayList<>());
            for(int j = 0; j < g.getGraph().get(i).size(); j++)
            {
                buttons.get(i).add(new JButton());
                switch(g.getGraph().get(i).get(j).getColor())
                {
                    case 1:
                        buttons.get(i).get(j).setIcon(gui.getPf().getFlak());
                        break;
                    case 2:
                        buttons.get(i).get(j).setIcon(gui.getPf().getZane());
                        break;
                    case 3:
                        buttons.get(i).get(j).setIcon(gui.getPf().getAmara());
                        break;
                    case 4:
                        buttons.get(i).get(j).setIcon(gui.getPf().getMoze());
                        break;
                    case 9:
                        buttons.get(i).get(j).setIcon(gui.getPf().getSpacer());
                        break;
                    default://case 0
                        buttons.get(i).get(j).setIcon(gui.getPf().getEmpty());
                        break;
                }
            }
        }
        gui.getPf().setColorButtons(buttons);
    }

    /**
     * takes a 2d list of buttons and converts it to a 2d list of Nodes for easy manipulation
     * @param data the 2d list of buttons to get converted
     * @return a 2d list of nodes that correspond to the given inputf
     */
    public ArrayList<ArrayList<Node>> ButtonToNode(ArrayList<ArrayList<JButton>> data)
    {
        ArrayList<ArrayList<Node>> graph = new ArrayList<>();
        for(int i = 0; i < data.get(0).size(); i++)
        {
            graph.add(new ArrayList<>());
            for(ArrayList<JButton> datum : data)
            {
                switch(datum.get(i).getIcon().toString())
                {
                    case "./images/moze.png":
                        graph.get(i).add(new Node(4));
                        break;
                    case "./images/amara.png":
                        graph.get(i).add(new Node(3));
                        break;
                    case "./images/zane.png":
                        graph.get(i).add(new Node(2));
                        break;
                    case "./images/flak.png":
                        graph.get(i).add(new Node(1));
                        break;
                    case "./images/spacer/png":
                        graph.get(i).add(new Node(9));
                        break;
                    default://case "./images/blank.png" || "./images/empty.png" or something got messed up
                        graph.get(i).add(new Node(0));
                        break;
                }
            }
        }
        return graph;
    }

    public JPanel getMainPanel()
    {
        return mainPanel;
    }

    public JLabel getHeightLabel()
    {
        return heightLabel;
    }

    public JLabel getWidthLabel()
    {
        return widthLabel;
    }
}
