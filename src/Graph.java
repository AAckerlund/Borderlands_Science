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
        validColor = readValidColors(graph.size());
        print();
    }

    public boolean insertNode(int row, int col)
    {
        if(graph.get(row).get(0).getColor() != 0)//row is already filled
            return false;
        for (int i = col+1 ; i < graph.size(); i++)
        {
            if(graph.get(row).get(i).getColor() == 0)//cannot insert a block above an empty block
            {
                return false;
            }
        }
        graph.get(row).add(col, new Node(9));
        if(col != 0)
            graph.get(row).remove(0);
        return true;
    }

    /**
     * reads in graph from file
     * @return a ready to use graph
     */
    public ArrayList<ArrayList<Node>> readGraph()
    {
//        System.out.println("readGraph");
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
        int counter = 0;
        while(in.hasNext())
        {
            String line = in.nextLine();
//            System.out.println(line);
            for (int i = 0; i < line.length(); i++)
            {
//                System.out.println(line.charAt(i));
                if(i == 0)
                {
//                    System.out.println("New list");
                    tmp.add(new ArrayList<>());
                }
                tmp.get(counter).add(new Node(Integer.parseInt(String.valueOf(line.charAt(i)))));
            }
            counter++;
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
//        System.out.println("readValidColors");
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
        for (int i = 0; i < graph.size(); i++)
        {
            System.out.print(validColor[i][0].printColor());
            System.out.print(validColor[i][1].printColor());
            System.out.print("|");

            for (int j = 0; j < graph.get(i).size(); j++)
            {
                System.out.print(graph.get(i).get(j).printColor());
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