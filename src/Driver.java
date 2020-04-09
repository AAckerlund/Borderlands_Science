import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Driver
{
    Graph g;
    ArrayList<ArrayList<Node>> best;//stores the graph with the highest calculated score.
    int maxSpacers, spacersLeft;
    public Driver()
    {
        g = new Graph();
        getNumSpacers();
        best = g.getGraph();
        spacersLeft = maxSpacers;
        solve();
        print(best);
    }
    public static void main(String[] args)
    {
        new Driver();
    }
    public void solve()//Will attempt to brute-force the best (highest scoring) solution to the problem.
    {
        //TODO: This is gonna suck.
    }

    /**
     * determines the score of a given graph
     * @param graph the graph being scored
     * @return the score of the given graph
     */
    public int score(ArrayList<ArrayList<Node>> graph)
    {
        int score = 0;
        for (int i = 0; i < graph.size(); i++)
        {
            for(int j = 0; j < graph.get(i).size(); j++)
            {
                if((graph.get(i).get(j).getColor() == g.getValidColor()[i][0].getColor() || graph.get(i).get(j).getColor() == g.getValidColor()[i][1].getColor()) && graph.get(i).get(j).getColor() != 0)
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
        for (int i = 0; i < graph.get(row).size(); i++)
        {
            if((graph.get(row).get(i).getColor() != g.getValidColor()[row][0].getColor() && graph.get(row).get(i).getColor() != g.getValidColor()[row][1].getColor()) || graph.get(row).get(i).getColor() == 0)
            {
                return false;
            }
        }
        return true;
    }

    /**
     * gets the number of spacer blocks available for use.
     */
    public void getNumSpacers()
    {
        Scanner in = new Scanner(System.in);
        int input = -1;
        System.out.print("How many spacer blocks do I have to work with?: ");
        try
        {
            input = in.nextInt();
            if(input < 1)
            {
                System.out.println("Number is too small, please try again.");
                getNumSpacers();
            }
        }
        catch(InputMismatchException ex)
        {
            System.out.println("Whoah there, you need to enter a number like 5 or 10 or something.");
            getNumSpacers();
        }
    }

    /**
     * prints out a given graph
     * @param graph the graph to be printed
     */
    public void print(ArrayList<ArrayList<Node>> graph)
    {
        System.out.println("The best possible score is: " + score(graph));
        System.out.println("It can be achieved using the following graph:");
        for (int i = 0; i < graph.size(); i++)
        {
            for (int j = 0; j < graph.get(i).size(); j++)
            {
                System.out.print(graph.get(i).get(j).printColor());
            }
            System.out.println();
        }
    }
}
