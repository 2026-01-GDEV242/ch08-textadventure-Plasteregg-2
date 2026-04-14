import java.util.ArrayList;
/**
 * The Player class represents the user playing the game. It keeps track of the player's current location (room) and the items the player is carrying in their inventory.
 *
 *
 * @author Andrew Wright
 * @version 2026.04.13
 */
public class Player
{
    private Room currentRoom;
    private ArrayList<Item> inventory;

    /**
     * Create a player starting in a room.
     */
    public Player(Room startingRoom)
    {
        currentRoom = startingRoom;
        inventory = new ArrayList<>();
    }

    public Room getCurrentRoom()
    {
        return currentRoom;
    }

    public void setCurrentRoom(Room room)
    {
        currentRoom = room;
    }

    // INVENTORY METHODS

    public void takeItem(Item item)
    {
        inventory.add(item);
    }

    public Item dropItem(int index)
    {
        return inventory.remove(index);
    }

    public ArrayList<Item> getInventory()
    {
        return inventory;
    }

    public String showInventory()
    {
    if(inventory.isEmpty()) {
        return "You are carrying nothing.";
    }

    String result = "You are carrying:\n";

    for(Item item : inventory) {
        result += "- " + item + "\n";
    }

    return result;
    }
}