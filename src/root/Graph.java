package root;

import root.GUI.ValidColorFrame;
import root.GUI.GUI;
import root.GUI.PuzzleFrame;

import javax.swing.*;
import java.util.ArrayList;

public class Graph
{
    private ArrayList<ArrayList<Node>> graph;
    private Node[][] validColor;

    public Graph(GUI gui)
    {
        graph = readGraph(gui.getPf());
        validColor = readValidColors(gui.getVCf());
    }

    public Graph(ArrayList<ArrayList<Node>> graph, GUI gui)
    {
        this.graph = new ArrayList<>();
        for(int k = 0; k < graph.size(); k++)
        {
            this.graph.add(new ArrayList<>());
            for(int l = 0; l < graph.get(k).size(); l++)
            {
                this.graph.get(k).add(l, graph.get(k).get(l));
            }
        }
        validColor = readValidColors(gui.getVCf());
    }

    /**
     * insert a Node at the given location
     * @param row the row we want to insert the node into
     * @param col the column we want to insert the node into
     * @return true if we successfully inserted the node, false otherwise
     */
    public boolean insertNode(int row, int col)
    {
        try
        {
            if(graph.get(row).get(0).getColor() != 0)//row is already filled
                return false;
            if(graph.get(row).get(col).getColor() == 0)//node being replaced is empty (inserting a node above the row of scoreable blocks).
                return false;
            graph.get(row).remove(0);//remove an empty spot from the top of the graph
            graph.get(row).add(col, new Node(9));
        }
        catch(Exception ignored)
        {
            System.out.println(row + " " + col);
            return false;
        }
        return true;
    }

    /**
     * removes a given Node
     * @param row the row of the node we want to remove
     * @param col the column of the node we want to remove
     */
    public void removeNode(int row, int col)
    {
        graph.get(row).remove(col);
        graph.get(row).add(0, new Node(0));
    }

    /**
     * reads in graph from the gui
     * @return a ready to use graph
     */
    public ArrayList<ArrayList<Node>> readGraph(PuzzleFrame pf)
    {
        ArrayList<ArrayList<Node>> nodes = new ArrayList<>();
        for(int i = 0; i < pf.getColorButtons().size(); i++)
        {
            nodes.add(new ArrayList<>());
            for(int j = 0; j < pf.getColorButtons().get(0).size(); j++)
            {
                nodes.get(i).add(new Node(iconToInt((ImageIcon) pf.getColorButtons().get(i).get(j).getIcon())));
            }
        }
        return nodes;
    }

    /**
     * reads in the valid colors for the graph
     * @return a list of valid colors for each row of the graph
     */
    public Node[][] readValidColors(ValidColorFrame vcf)
    {
        Node[][] valid = new Node[graph.get(0).size()][2];
        for(int i = 0; i < valid.length; i++)
        {
            for(int j = 0; j < 2; j++)
            {
                valid[i][j] = new Node(iconToInt((ImageIcon) vcf.getBonusButtons().get(i)[j].getIcon()));
            }
        }
        return valid;
    }

    /**
     * converts icons to ints for easy manipulation
     * @param icon the icon we want to convert to its corresponding int
     * @return the integer value of the associated icon
     */
    public int iconToInt(ImageIcon icon)
    {
        switch(icon.getDescription())
        {
            case "./images/spacer.png":
                return 9;
            case "./images/flak.png":
                return 1;
            case "./images/zane.png":
                return 2;
            case "./images/amara.png":
                return 3;
            case "./images/moze.png":
                return 4;
            default:
                return 0;
        }
    }

    public ArrayList<ArrayList<Node>> getGraph()
    {
        return graph;
    }

    public Node[][] getValidColor()
    {
        return validColor;
    }
}