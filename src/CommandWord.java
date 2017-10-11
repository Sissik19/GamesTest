import org.omg.CORBA.UNKNOWN;

public enum CommandWord {
    GO ("go"),
    QUIT("quit"),
    HELP("help"),
    LOOK("look"),
    INVENTORY("inventory"),
    UNKNOWN("unknown");

    private String mot ="";

    CommandWord(String mot){
        this.mot = mot;
    }

    /**
     * Getter to have the word associate to an enum word
     * @return a word
     */
    public String toString(){
        return mot;
    }

}
