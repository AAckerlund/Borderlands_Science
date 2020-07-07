package root;

import root.GUI.GUI;

import java.util.ArrayList;

public class Driver
{
    GUI gui;

    public Driver()
    {
        gui = new GUI(this);
    }

    /**
     * performs a brute force algorithm to find the optimal solution
     * @param graph the current instance of the graph that the algorithm is working on
     * @param spacersLeft the number of spacer blocks the algorithm has to work with
     * @param highScore current highest achieved score
     * @param row the current row the algorithm is on (part of an attempt to decrease runtime)
     * @param col the current column the algorithm is on (part of an attempt to decrease run time)
     * @return the most optimal (highest scoring) graph the algorithm has found
     */
    public Graph solve(Graph graph, int spacersLeft, int highScore, int row, int col)
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
        for(ArrayList<Node> datum : data)
        {
            for(int j = 0; j < datum.size(); j++)
            {
                if((datum.get(j).getColor() == graph.getValidColor()[j][0].getColor() || datum.get(j).getColor() == graph.getValidColor()[j][1].getColor()) && datum.get(j).getColor() != 0)//(button image == 1st OR 2nd valid color) AND button image is not blank
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

    public static void main(String[] args)
    {
        new Driver();
    }
}
