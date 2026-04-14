import java.util.Stack;
/**
 *  This class is the main class of the "World of Zuul" application. 
 *  "World of Zuul" is a very simple, text based adventure game.  Users 
 *  can walk around some scenery. That's all. It should really be extended 
 *  to make it more interesting!
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @author Andrew Wright
 * @version 2026.04.13
 */

public class Game 
{
    private Parser parser;
    private Room currentRoom;
    private Room previousRoom;    
    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        createRooms();
        parser = new Parser();
    }

    /**
     * Create all the rooms and link their exits together.
     * Added Rooms Library,Cafeteria,dorm
     */
    private void createRooms()
    {
    Room outside, theater, pub, lab, office;
    Room library, cafeteria, dorm; // new rooms
  
    // create the rooms
    outside = new Room("outside the main entrance of the university");
    theater = new Room("in a lecture theater");
    pub = new Room("in the campus pub");
    lab = new Room("in a computing lab");
    office = new Room("in the computing admin office");
    library = new Room("in the university library");
    cafeteria = new Room("in the campus cafeteria");
    dorm = new Room("in the student dormitory");
    
    // initialise room exits
    outside.setExit("east", theater);
    outside.setExit("south", lab);
    outside.setExit("west", pub);
    outside.setExit("north", library);

    theater.setExit("west", outside);
    theater.setExit("south", cafeteria);

    pub.setExit("east", outside);
    pub.setExit("north", dorm);

    lab.setExit("north", outside);
    lab.setExit("east", office);

    office.setExit("west", lab);
    office.setExit("north", cafeteria);

    library.setExit("south", outside);

    cafeteria.setExit("north", theater);
    cafeteria.setExit("south", office);

    dorm.setExit("south", pub);
    // Addition of Items to rooms
    outside.setItem(new Item("a welcome sign", 1));

    theater.setItem(new Item("a projector", 2));

    pub.setItem(new Item("a pint of beer", 1));

    lab.setItem(new Item("a laptop", 3));

    office.setItem(new Item("a stack of papers", 2));

    library.setItem(new Item("a dusty book", 2));

    cafeteria.setItem(new Item("a sandwich", 1));

    dorm.setItem(new Item("a backpack", 2));

    // start game outside
    currentRoom = outside;

    currentRoom = outside;  // start game outside
    }

    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {            
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.
                
        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("World of Zuul is a new, incredibly boring adventure game.");
        System.out.println("Type '" + CommandWord.HELP + "' if you need help.");
        System.out.println();
        System.out.println(currentRoom.getLongDescription());
    }

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        CommandWord commandWord = command.getCommandWord();

        switch (commandWord) {
            case UNKNOWN:
                System.out.println("I don't know what you mean...");
                break;

            case HELP:
                printHelp();
                break;

            case GO:
                goRoom(command);
                break;

            case QUIT:
                wantToQuit = quit(command);
                break;
            case BACK:
                goBack();
                break;
        }
        return wantToQuit;
    }

    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the university.");
        System.out.println();
        System.out.println("Your command words are:");
        parser.showCommands();
    }

    /** 
     * Try to go in one direction. If there is an exit, enter the new
     * room, otherwise print an error message.
     */
    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            System.out.println("There is no door!");
        }
        else {
        previousRoom = currentRoom;   // save where you are now
        currentRoom = nextRoom;       // move to next room
        System.out.println(currentRoom.getLongDescription());
    }
    }
    /**
    * Go back to the previous room.
    * 
    * If there is a previously visited room, the player returns to it.
    * If there is no previous room (i.e., this is the first move),
    * a message is printed indicating that going back is not possible.
    */
    private void goBack()
    {
    if(previousRoom == null) {
        System.out.println("You can't go back!");
        return;
    }

    Room temp = currentRoom;
    currentRoom = previousRoom;
    previousRoom = temp;

    System.out.println(currentRoom.getLongDescription());
    }

    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }
    }
