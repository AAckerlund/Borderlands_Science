package root;

public class Node {
    /*  0: empty node
        1-4: scorable nodes
        9: spacer node */
    private int color;
    public Node(int color)
    {
        this.color = color;
    }
    public int getColor()
    {
        return color;
    }
}
