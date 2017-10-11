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
    private Inventory inventory;
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
        createInventory();
        parser = new Parser();
    }

    private void createInventory(){
        inventory = new Inventory();
    }
    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms() {
        Room outside, hall, theater, backTheater, downCorridor, musicClass, pub, computingLab, reserve,
                balcony, classOne, classTwo, suspendedGarden, upCorridor, secretariat, directory, upHall,
                attic, secretRoom;

        // create the rooms
        outside = new Room("outside the main entrance of the university.", null);
        hall = new Room("in the hall",null);
        theater = new Room("in a lecture theater", null);
        backTheater = new Room("in back of the theater", null);
        pub = new Room("in the campus pub", "orange key");
        musicClass = new Room("in the music class", "code - 1234");
        downCorridor = new Room("in a corridor at the first floor", null);
        computingLab = new Room("in a computing lab", null);
        reserve = new Room("in the reserve", "A GERER - red key close - pink key close");

        upHall = new Room("in the hall on the second floor", null);
        balcony = new Room("on the theater's balcony", "blue key");
        suspendedGarden = new Room("in the suspended garden", null);
        classOne = new Room("in the class one", "code - 5678");
        classTwo = new Room("in the class two", "grey key");
        upCorridor = new Room("in the corridor on the second floor", null);
        secretariat = new Room("in the secretariat","code - 9876");
        directory = new Room("in the directory", "violet key");

        attic = new Room("in the attic", null);
        secretRoom = new Room("in the secret room of the university", "A GERER - yellow key, close pink key");



        // initialise room exits
        outside.setExit(west, pub);
        outside.setExit(south, computingLab);
        outside.setExit(east, theater);
        theater.setExit(west, outside);
        theater.setExit(up, balcony);
        balcony.setExit(down, theater);
        pub.setExit(east, outside);
        computingLab.setExit(north, outside);
        computingLab.setExit(east, reserve);
        reserve.setExit(west, computingLab);

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
        else if(CommandWord.INVENTORY.equals(commandWords))
            inventory();
        else if(CommandWord.TAKE.equals(commandWords))
            take();

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

    private void inventory(){
        System.out.println(inventory.seeBag());
    }

    private void take(){
        if(currentRoom.getObject()!=null){
            inventory.addObject(currentRoom.getObject());
        }
        System.out.println("You take : "+currentRoom.getObject());
        currentRoom.setObject();
    }



    public static void main(String[] args) {
        Game jeu = new Game();
        jeu.play();
    }

}
