import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Driver
{
    Graph g;
    Graph best;//stores the graph with the highest calculated score.
    int maxSpacers, spacersLeft;
    GUI gui;
    public Driver()
    {
        gui = new GUI();
        g = new Graph();
        best = g;
        spacersLeft = maxSpacers;
        best = solve(best, getNumSpacers(), Integer.MIN_VALUE, 0, 0);
        print(best);
    }
    public static void main(String[] args)
    {
        new Driver();
    }
    public Graph solve(Graph graph, int spacersLeft, int highScore, int row, int col)//Will attempt to brute-force the best (highest scoring) solution to the problem.
    {
        Graph best = new Graph(graph.getGraph());
        int high = highScore;
        //loop through each node in the graph
        for(int i = row; i < graph.getGraph().get(0).size(); i++)
        {
            for(int j = col; j < graph.getGraph().size(); j++)
            {
                if(spacersLeft > 0)//if we have a spacer to insert
                {
                    if(graph.insertNode(j, i))//if we can insert a spacer node
                    {
                        int tmpScore = score(graph.getGraph());
                        if(high < tmpScore)//if the new score is higher than the current best
                        {
                            //store the new high score and associated graph
                            high = tmpScore;
                            best = new Graph(graph.getGraph());
                        }
                        Graph tmp = solve(graph, spacersLeft -= 1, high, i, j);
                        if(score(tmp.getGraph()) > score(best.getGraph()))
                        {
                            high = score(tmp.getGraph());
                            best = new Graph(tmp.getGraph());
                        }
                        graph.removeNode(j, i);
                        ++spacersLeft;
                    }
                }
                else//no spacers left to insert
                {
                    return best;
                }
            }
        }
        return best;
    }

    /**
     * determines the score of a given graph
     * @param graph the graph being scored
     * @return the score of the given graph
     */
    public int score(ArrayList<ArrayList<Node>> graph)
    {
        int score = 0;
        for(int i = 0; i < graph.get(0).size(); i++)
        {
            for(ArrayList<Node> nodes : graph)
            {
                if((nodes.get(i).getColor() == g.getValidColor()[i][0].getColor() || nodes.get(i).getColor() == g.getValidColor()[i][1].getColor()) && nodes.get(i).getColor() != 0)
                {
                    score++;
                }
            }
            if(checkRow(i, graph))//1 bonus point for completely filling a row with valid blocks
            {
                score++;
            }
        }
        return score;
    }

    /**
     * checks for a completely filled row.
     * @param row the row of the graph that is being checked
     * @param graph the graph being checked
     * @return true if all blocks in the row are valid, false otherwise
     */
    public boolean checkRow(int row, ArrayList<ArrayList<Node>> graph)
    {
        for(ArrayList<Node> nodes : graph)
        {
            if((nodes.get(row).getColor() != g.getValidColor()[row][0].getColor() && nodes.get(row).getColor() != g.getValidColor()[row][1].getColor()) || nodes.get(row).getColor() == 0)
            {
                return false;
            }
        }
        return true;
    }

    /**
     * gets the number of spacer blocks available for use.
     */
    public int getNumSpacers()
    {
        Scanner in = new Scanner(System.in);
        int input;
        System.out.print("How many spacer blocks do I have to work with?: ");
        try
        {
            input = in.nextInt();
            if(input < 1)
            {
                System.out.println("Number is too small, please try again.");
                input = getNumSpacers();
            }
        }
        catch(InputMismatchException ex)
        {
            System.out.println("Whoa there, you need to enter a number like 5 or 10 or something.");
            input = getNumSpacers();
        }
        return input;
    }

    /**
     * prints out a given graph
     * @param graph the graph to be printed
     */
    public void print(Graph graph)
    {
        System.out.println("The best possible score is: " + score(graph.getGraph()));
        System.out.println("It can be achieved using the following graph:");
        for(int i = 0; i < graph.getGraph().get(0).size(); i++)
        {
            System.out.print(g.getValidColor()[i][0].printColor() + " |");
            System.out.print(g.getValidColor()[i][1].printColor() + " |");
            System.out.print("|");
            for(ArrayList<Node> nodes : graph.getGraph())
            {
                System.out.print(nodes.get(i).printColor() + " |");
            }
            System.out.println();
        }
    }
}
