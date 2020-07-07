package root;

import root.GUI.GUI;

import java.util.ArrayList;

public class Driver
{
    GUI gui;
    public Driver()
    {
        gui = new GUI(this);
        //best = solve(best, gui.getInf().getSpacerNum(), Integer.MIN_VALUE, 0, 0);
        //print(best);
    }
    public Graph solve(Graph graph, int spacersLeft, int highScore, int row, int col)//Will attempt to brute-force the best (highest scoring) solution to the problem.
    {
        Graph best = new Graph(graph.getGraph(), gui);
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
                        int tmpScore = score(graph);
                        if(high < tmpScore)//if the new score is higher than the current best
                        {
                            //store the new high score and associated graph
                            high = tmpScore;
                            best = new Graph(graph.getGraph(), gui);
                        }
                        Graph tmp = solve(graph, spacersLeft -= 1, high, i, j);
                        if(score(tmp) > score(best))
                        {
                            high = score(tmp);
                            best = new Graph(tmp.getGraph(), gui);
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
    public int score(Graph graph)
    {
        int score = 0;
        ArrayList<ArrayList<Node>> data = graph.getGraph();
        for(int i = 0; i < data.size(); i++)
        {
            for(int j = 0; j < data.get(i).size(); j++)
            {
                /*System.out.println(i + " " + j);
                System.out.println(data.get(i).get(j).getColor());
                System.out.println(graph.getValidColor()[j][0].getColor());
                System.out.println(graph.getValidColor()[j][1].getColor());*/
                if((data.get(i).get(j).getColor() == graph.getValidColor()[j][0].getColor() || data.get(i).get(j).getColor() == graph.getValidColor()[j][1].getColor()) && data.get(i).get(j).getColor() != 0)//(button image == 1st OR 2nd valid color) AND button image is not blank
                {
                    score++;
                }
            }
        }
        for(int i = 0; i < data.get(0).size(); i++)
        {
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
    public boolean checkRow(int row, Graph graph)
    {
        for(ArrayList<Node> nodes : graph.getGraph())
        {
            if((nodes.get(row).getColor() != graph.getValidColor()[row][0].getColor() && nodes.get(row).getColor() != graph.getValidColor()[row][1].getColor()) || nodes.get(row).getColor() == 0)
            {
                return false;
            }
        }
        return true;
    }

    /**
     * prints out a given graph
     * @param graph the graph to be printed
     */
    public void print(Graph graph)
    {
        System.out.println("The best possible score is: " + score(graph));
        System.out.println("It can be achieved using the following graph:");
        for(int i = 0; i < graph.getValidColor().length; i++)
        {
            System.out.print(graph.getValidColor()[i][0].printColor() + " |");
            System.out.println(graph.getValidColor()[i][1].printColor() + " |");
        }
        for(int i = 0; i < graph.getGraph().get(0).size(); i++)
        {
            for(int j = 0; j < graph.getGraph().size(); j++)
            {
                System.out.print(graph.getGraph().get(j).get(i).printColor() + " |");
            }
            System.out.println();
        }
    }

    public static void main(String[] args)
    {
        new Driver();
    }
}
