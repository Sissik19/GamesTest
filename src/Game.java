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
    private Parser parser2;
    private Room currentRoom;
    private Inventory inventory;
    private String north = Direction.NORTH.toString();
    private String south = Direction.SOUTH.toString();
    private String west = Direction.WEST.toString();
    private String east = Direction.EAST.toString();
    private String up = Direction.UP.toString();
    private String down = Direction.DOWN.toString();
    private String close = StateDoor.CLOSE.toString();
    private String open = StateDoor.OPEN.toString();
    private String dclose = StateDoor.DCLOSE.toString();
    private String hide = StateDoor.HIDE.toString();
    private String code = StateDoor.CODE.toString();
    private Key orange, pink, red, blue, grey, black, yellow, code1234,code5678,white, code5432, violet;
    /**
     * Create the game and initialise its internal map.
     */
    public Game()  {
        createKey();
        createInventory();
        createRooms();
        parser = new Parser();
        parser2 = new Parser();
    }

    private void createKey(){
        orange = new Key("orange");
        pink = new Key("pink");
        red = new Key("red");
        blue = new Key("blue");
        grey = new Key("grey");
        black = new Key("black");
        yellow = new Key("yellow");
        code1234 = new Key("1234");
        code5678 = new Key("5678");
        white = new Key("white");
        code5432 = new Key("5432");
        violet = new Key("violet");

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
                attic, atticCorridor, secretRoom1, secretRoom2;

        // create the rooms
        outside = new Room("outside the main entrance of the university.", null);
        hall = new Room("in the hall",null);
        theater = new Room("in a lecture theater", null);
        backTheater = new Room("in back of the theater", black);
        pub = new Room("in the campus pub", orange);
        musicClass = new Room("in the music class", code1234);
        downCorridor = new Room("in a corridor at the first floor", null);
        computingLab = new Room("in a computing lab", null);
        reserve = new Room("in the reserve", red);

        upHall = new Room("in the hall on the second floor", null);
        balcony = new Room("on the theater's balcony", blue);
        suspendedGarden = new Room("in the suspended garden", null);
        classOne = new Room("in the class one", code5678);
        classTwo = new Room("in the class two", grey);
        upCorridor = new Room("in the corridor on the second floor", null);
        secretariat = new Room("in the secretariat",white);
        directory = new Room("in the directory", violet);

        attic = new Room("in the attic", null);
        secretRoom1 = new Room("in a secret room of the university", yellow);
        secretRoom2 = new Room("in a secret room of the university", pink);
        atticCorridor = new Room("in one corridor", null);


        // initialise room exits
        hall.setExit(west, pub, close, black);
        hall.setExit(south, downCorridor);
        hall.setExit(east, theater, close, grey);
        hall.setExit(north, outside, dclose, yellow, pink);
        hall.setExit(up, upHall);

        theater.setExit(west, hall);
        theater.setExit(up, balcony);
        theater.setExit(east, backTheater, code,code5678);

        backTheater.setExit(west, theater);

        downCorridor.setExit(north, hall);
        downCorridor.setExit(west, musicClass, close, blue);
        downCorridor.setExit(east, computingLab);

        computingLab.setExit(west, downCorridor);
        computingLab.setExit(east, reserve, code, code1234);

        musicClass.setExit(east, downCorridor);

        reserve.setExit(west, computingLab);

        pub.setExit(east, hall);

        outside.setExit(south, hall);

        suspendedGarden.setExit(south, upHall);

        upHall.setExit(north, suspendedGarden);
        upHall.setExit(west, classOne);
        upHall.setExit(south, upCorridor);
        upHall.setExit(up, attic);
        upHall.setExit(down, hall);

        classOne.setExit(east, upHall);

        balcony.setExit(down, theater);

        upCorridor.setExit(north, upHall);
        upCorridor.setExit(west, classTwo);
        upCorridor.setExit(east, secretariat);

        classTwo.setExit(east, upCorridor);

        secretariat.setExit(east, directory, dclose, orange, white);
        secretariat.setExit(west, upCorridor);

        directory.setExit(west, secretariat);

        attic.setExit(down, upHall);
        attic.setExit(west, atticCorridor);

        atticCorridor.setExit(north, secretRoom1, hide,violet);
        atticCorridor.setExit(south, secretRoom2, hide, red);
        atticCorridor.setExit(east, attic);

        secretRoom1.setExit(south, atticCorridor);

        secretRoom2.setExit(north, atticCorridor);

        currentRoom = hall;  // start game outside
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
        printHelp();
        System.out.println();
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
        else if(CommandWord.UNLOCK.equals(commandWords))
            unlock(command);
        else if(CommandWord.CODE.equals(commandWords))
            code(command);
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

    private void unlock(Command command){
        if(!command.hasSecondWord()){
            System.out.println(Text.UNLOCKWHAT.toString());
            return;
        }

        String direction = command.getSecondWord();
        Room lockRoom = null;

        if(direction.equals(north)) {
            lockRoom = currentRoom.getExist(north);
        }
        if(direction.equals(east)) {
            lockRoom = currentRoom.getExist(east);
        }
        if(direction.equals(south)) {
            lockRoom = currentRoom.getExist(south);
        }
        if(direction.equals(west)) {
            lockRoom = currentRoom.getExist(west);
        }
        if(direction.equals(up)) {
            lockRoom = currentRoom.getExist(up);
        }
        if(direction.equals(down)) {
            lockRoom = currentRoom.getExist(down);
        }

        if (lockRoom == null) {
            System.out.println(Text.ROOMOPEN.toString());
        }
        else {
            Door door = currentRoom.getDoor(direction);
            if(door.getOpen().equals(StateDoor.CLOSE.toString()) || door.getOpen().equals(StateDoor.HIDE.toString())){
                if(inventory.existKey(door.getKey1().getCouleur())) {
                    door.setOpen(open);
                    inventory.removeKey(door.getKey1());
                    printLocationInfo();
                }
                else{
                    System.out.println(Text.NOKEY.toString());
                    printLocationInfo();
                }

            }
            else if(door.getOpen().equals(StateDoor.DCLOSE.toString())){
                if(inventory.existKey(door.getKey1().getCouleur()) && inventory.existKey(door.getKey2().getCouleur())) {
                    door.setOpen(open);
                    inventory.removeKey(door.getKey1());
                    inventory.removeKey(door.getKey2());
                    printLocationInfo();
                }
                else if(inventory.existKey(door.getKey1().getCouleur())){
                    System.out.println(Text.NOKEY1.toString());
                    printLocationInfo();
                }
                else if(inventory.existKey(door.getKey2().getCouleur())){
                    System.out.println(Text.NOKEY2.toString());
                    printLocationInfo();
                }
                else{
                    System.out.println(Text.NOKEY.toString());
                    printLocationInfo();
                }
            }
            else{
                System.out.println(Text.NODOOR.toString());
            }
        }
    }

    private void code(Command command) {
        if (!command.hasSecondWord()) {
            System.out.println(Text.UNLOCKWHAT.toString());
            return;
        }

        String direction = command.getSecondWord();
        Room lockRoom = null;

        if (direction.equals(north)) {
            lockRoom = currentRoom.getExist(north);
        }
        if (direction.equals(east)) {
            lockRoom = currentRoom.getExist(east);
        }
        if (direction.equals(south)) {
            lockRoom = currentRoom.getExist(south);
        }
        if (direction.equals(west)) {
            lockRoom = currentRoom.getExist(west);
        }
        if (direction.equals(up)) {
            lockRoom = currentRoom.getExist(up);
        }
        if (direction.equals(down)) {
            lockRoom = currentRoom.getExist(down);
        }

        if (lockRoom == null) {
            System.out.println(Text.ROOMOPEN.toString());
        } else {
            Door door = currentRoom.getDoor(direction);
            if (door.getOpen().equals(StateDoor.CODE.toString())) {
                System.out.println("Enter code + number");
                Command command2 = parser2.getCommand();
                String sWord = command2.getCommandWord();
                CommandWord commandWords = new CommandWords().getCommandWords(sWord);
                if (CommandWord.CODE.equals(commandWords)) {
                    if (isNumeric(command2.getSecondWord())) {
                        if (command2.getSecondWord().equals(door.getKey1().getCouleur())){
                            door.setOpen(StateDoor.OPEN.toString());
                            inventory.removeKey(door.getKey1());
                            printLocationInfo();
                        }
                        else{
                            System.out.println(Text.ERRROR.toString());
                        }
                    }
                    else{
                        System.out.println(Text.ERRROR.toString());
                    }
                }
            }
        }
    }

    public static boolean isNumeric(String str)
    {
        try
        {
            double d = Double.parseDouble(str);
        }
        catch(NumberFormatException nfe)
        {
            return false;
        }
        return true;
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
            Door door = currentRoom.getDoor(direction);
            if(door.getOpen().equals(StateDoor.OPEN.toString())) {
                currentRoom = nextRoom;
                printLocationInfo();
            }
            else{
                System.out.println(Text.NODOOR.toString());
            }
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
        if(currentRoom.getKey()!=null){
            inventory.addKey(currentRoom.getKey());
        }
        System.out.println("You take : "+currentRoom.getKey().getKeyName());
        currentRoom.setKey();
        printLocationInfo();
    }



    public static void main(String[] args) {
        Game jeu = new Game();
        jeu.play();
    }

}
