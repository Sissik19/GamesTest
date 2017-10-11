public enum Text {
    GOWHERE("Go where?"),
    GOODBYE("Thank you for playing.  Good bye."),
    WELCOME("Welcome to the World of Adventure!\n"+
            "World of Adventure is a new, incredibly boring adventure game.\n"+
            "Type "+CommandWord.HELP.toString()+" if you need help.\n"),
    IDK("I don't know what you mean..."),
    HELP("You are lost. You are alone. You wander\n"+
        "around at the university.\n "),
    NODOOR("There is no door!"),
    QUITWHAT("Quit what?"),
    YOURCOMMAND("Your command are : \n"),
    YOURARE("You are "),
    EXITS("Exits : ");

    private String mot ="";

    Text(String mot){
        this.mot = mot;
    }

    public String toString(){
        return mot;
    }
}
