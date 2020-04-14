import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Graph
{
    private ArrayList<ArrayList<Node>> graph;
    private Node[][] validColor;
    public Graph()
    {
        graph = readGraph();
        validColor = readValidColors(graph.get(0).size());
        print();
    }

    public Graph(ArrayList<ArrayList<Node>> graph)
    {
        this.graph = new ArrayList<ArrayList<Node>>();
        for(int k = 0; k < graph.size(); k++)
        {
            this.graph.add(new ArrayList<Node>());
            for(int l = 0; l < graph.get(k).size(); l++)
            {
                this.graph.get(k).add(l, graph.get(k).get(l));
            }
        }
        validColor = readValidColors(graph.get(0).size());
    }
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

    public void removeNode(int row, int col)
    {
        graph.get(row).remove(col);
        graph.get(row).add(0, new Node(0));
    }

    /**
     * reads in graph from file
     * @return a ready to use graph
     */
    public ArrayList<ArrayList<Node>> readGraph()
    {
        File f = new File("graph.txt");
        Scanner in = null;
        try
        {
            in = new Scanner(f);
        }
        catch(FileNotFoundException ex)//if there is a problem reading in the file return null.
        {
            System.out.println("No file: graph");
            System.exit(1);
        }

        ArrayList<ArrayList<Node>> tmp = new ArrayList<>();
        String line = in.nextLine();
        int lineLength = line.length();
        while(in.hasNext())
        {
            line = line + in.nextLine();
        }

        for(int i = 0; i < lineLength; i++)
        {
            tmp.add(new ArrayList<>());
            for(int j = i; j < line.length(); j+=lineLength)
            {
                tmp.get(i).add(new Node(Integer.parseInt(String.valueOf(line.charAt(j)))));
            }
        }
        return tmp;
    }

    /**
     * prints out the current state of the graph
     */
    public void printGraph()
    {
        System.out.println("printGraph");
        for (int i = 0; i < graph.size(); i++)
        {
            for (int j = 0; j < graph.get(i).size(); j++)
            {
                System.out.print(graph.get(i).get(j).printColor());
            }
            System.out.println();
        }
    }

    /**
     * reads in the valid colors for the graph
     * @param height the height of the graph
     * @return a list of valid colors for each row of the graph
     */
    public Node[][] readValidColors(int height)
    {
        File f = new File("validColors.txt");
        Scanner in = null;
        try
        {
            in = new Scanner(f);
        }
        catch(FileNotFoundException ex)
        {
            System.out.println("No file: validColors");
            System.exit(1);
        }

        Node[][] tmp = new Node[height][2];
        int counter = 0;
        while(in.hasNext())
        {
            String line = in.nextLine();
            tmp[counter][0] = new Node(Integer.parseInt(String.valueOf(line.charAt(0))));
            tmp[counter][1] = new Node(Integer.parseInt(String.valueOf(line.charAt(1))));
            counter++;
        }
        return tmp;
    }

    /**
     * prints the valid colors for each row
     */
    public void printValidColors()
    {
        System.out.println("printValidColors");
        for (int i = 0; i < validColor.length; i++)
        {
            for (int j = 0; j < validColor[i].length; j++)
            {
                System.out.print(validColor[i][j].printColor());
            }
            System.out.println();
        }
    }

    /**
     * prints all data for the graph as it appears in the graph.
     */
    public void print()
    {
        System.out.println("Starting Graph");

        for(int i = 0; i < graph.get(0).size(); i++)
        {
            System.out.print(validColor[i][0].printColor() + " |");
            System.out.print(validColor[i][1].printColor() + " |");
            System.out.print("|");
            for(int j = 0; j < graph.size(); j++)
            {
                System.out.print(graph.get(j).get(i).printColor() + " |");
            }
            System.out.println();
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