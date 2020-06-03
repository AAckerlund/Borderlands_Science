import java.util.ArrayList;

public class Graph
{
    private ArrayList<ArrayList<Node>> graph;
    private Node[][] validColor;
    public Graph()
    {
        graph = readGraph();
        validColor = readValidColors();
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
        validColor = readValidColors();
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
        return null;//TODO
    }

    /**
     * reads in the valid colors for the graph
     * @return a list of valid colors for each row of the graph
     */
    public Node[][] readValidColors()
    {
        return null;//TODO
    }

    /**
     * prints the valid colors for each row
     */
    public void printValidColors()
    {
        System.out.println("printValidColors");
        for(Node[] nodes : validColor)
        {
            for(Node node : nodes)
            {
                System.out.print(node.printColor());
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
            for(ArrayList<Node> nodes : graph)
            {
                System.out.print(nodes.get(i).printColor() + " |");
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