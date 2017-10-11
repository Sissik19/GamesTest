public class Game {

    /**
     *  This class is the main class, text based adventure game.  Users
     *  can walk around some scenery. That's all. It should really be extended
     *  to make it more interesting!
     *
     *  To play this adventure game, create an instance of this class and call the "play"
     *  method.
     *
     *  This main class creates and initialises all the others: it creates all
     *  rooms, creates the parser and starts the game.  It also evaluates and
     *  executes the commands that the parser returns.
     *
     */

    private Parser parser;
    private Room currentRoom;
    private String north = Direction.NORTH.toString();
    private String south = Direction.SOUTH.toString();
    private String west = Direction.WEST.toString();
    private String east = Direction.EAST.toString();
    private String up = Direction.UP.toString();
    private String down = Direction.DOWN.toString();

    /**
     * Create the game and initialise its internal map.
     */
    public Game()  {
        createRooms();
        parser = new Parser();
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms() {
        Room outside, theatre, pub, lab, office, balcony;

        // create the rooms
        outside = new Room("outside the main entrance of the university");
        theatre = new Room("in a lecture theatre");
        pub = new Room("in the campus pub");
        lab = new Room("in a computing lab");
        office = new Room("in the computing admin office");
        balcony = new Room("on the theatre's balcony");

        // initialise room exits
        outside.setExit(west, pub);
        outside.setExit(south, lab);
        outside.setExit(east, theatre);
        theatre.setExit(west, outside);
        theatre.setExit(up, balcony);
        balcony.setExit(down, theatre);
        pub.setExit(east, outside);
        lab.setExit(north, outside);
        lab.setExit(east, office);
        office.setExit(west, lab);

        currentRoom = outside;  // start game outside
    }

    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play()  {
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.

        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println(Text.GOODBYE.toString());
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome() {
        System.out.println();
        System.out.println(Text.WELCOME.toString());
        printLocationInfo();
    }

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) {
        boolean wantToQuit = false;

        String sWord = command.getCommandWord();
        CommandWord commandWords = new CommandWords().getCommandWords(sWord);
        if(CommandWord.UNKNOWN.equals(commandWords)) {
            System.out.println(Text.IDK.toString());
            return false;
        }
        else if (CommandWord.HELP.equals(commandWords))
            printHelp();
        else if (CommandWord.GO.equals(commandWords))
            goRoom(command);
        else if (CommandWord.QUIT.equals(commandWords))
            wantToQuit = quit(command);
        else if(CommandWord.LOOK.equals(commandWords))
            look();

        return wantToQuit;
    }

    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the
     * command words.
     */
    private void printHelp() {
        CommandWords commandWords = new CommandWords();
        System.out.println(Text.HELP.toString());
        System.out.println(commandWords.allCommand());
    }

    /**
     * Try to go to one direction. If there is an exit, enter
     * the new room, otherwise print an error message.
     */
    private void goRoom(Command command)  {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println(Text.GOWHERE.toString());
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = null;
        if(direction.equals(north)) {
            nextRoom = currentRoom.getExist(north);
        }
        if(direction.equals(east)) {
            nextRoom = currentRoom.getExist(east);
        }
        if(direction.equals(south)) {
            nextRoom = currentRoom.getExist(south);
        }
        if(direction.equals(west)) {
            nextRoom = currentRoom.getExist(west);
        }
        if(direction.equals(up)) {
            nextRoom = currentRoom.getExist(up);
        }
        if(direction.equals(down)) {
            nextRoom = currentRoom.getExist(down);
        }

        if (nextRoom == null) {
            System.out.println(Text.NODOOR.toString());
        }
        else {
            currentRoom = nextRoom;
            printLocationInfo();
        }
    }

    /**
     * Permit to have the long description of the room (exits and description)
     */
    private void printLocationInfo(){
        System.out.println(currentRoom.getLongDescription());
    }

    /**
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command)   {
        if(command.hasSecondWord()) {
            System.out.println(Text.QUITWHAT.toString());
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }

    /**
     * "Look" was entered. Print the location info for the room
     */
    private void look (){
        printLocationInfo();
    }

    public static void main(String[] args) {
        Game jeu = new Game();
        jeu.play();
    }

}
