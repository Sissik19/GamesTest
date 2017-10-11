public enum Text {
    GOWHERE("Go where?"),
    UNLOCKWHAT("What do you waant unlock ?"),
    GOODBYE("Thank you for playing.  Good bye."),
    WELCOME("Welcome to the World of Adventure!\n"+
            "World of Adventure is a new, incredibly boring adventure game.\n"+
            "Your goal is to go out of the university. \n"+
            "Of course it's difficult. You have to find\n"+
            "keys or code to unlock door. The outside is \n"+
            "at the north of the hall. Good Luck! \n"+
            "Type "+CommandWord.HELP.toString()+" if you need help.\n"),
    IDK("I don't know what you mean..."),
    HELP("You are lost. You are alone. You wander\n"+
        "around at the university.\n "),
    NODOOR("You can't do that !"),
    QUITWHAT("Quit what?"),
    YOURCOMMAND("Your command are : \n"),
    YOURARE("You are "),
    EXITS("Exits : \n"),
    LISTOBJECT("In this room, there is : "),
    NOKEY("You don't have the key to open this door"),
    NOKEY1("You don't have the first key"),
    NOKEY2("You don't have the second key"),
    ERRROR("ERROR!!!"),
    ROOMOPEN("The door is already open");

    private String mot ="";

    Text(String mot){
        this.mot = mot;
    }

    public String toString(){
        return mot;
    }
}
