
/**
 * Write a description of class Item here.
 *
 * @author Andrew Wright
 * @version 2026.04.13
 */
public class Item
{
    private String description;
    private int weight;

    public Item(String description, int weight)
    {
        this.description = description;
        this.weight = weight;
    }

    public String getDescription()
    {
        return description;
    }

    public int getWeight()
    {
        return weight;
    }

    public String toString()
    {
        return description + " (weight: " + weight + ")";
    }
}