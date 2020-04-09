public class Node {
    /*
    0: empty node
    1-4: scorable nodes
    9: spacer node
     */
    private int color;
    public Node(int color)
    {
        this.color = color;
    }

    public String printColor()
    {
        switch(color)
        {
            case 1:
                return "Flak |";
            case 2:
                return "Zane |";
            case 3:
                return "Amar |";
            case 4:
                return "Moze |";
            case 9:
                return "Spac |";
            default:
                return "     |";
        }
    }

    public int getColor()
    {
        return color;
    }
}
