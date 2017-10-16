import com.sun.org.apache.xpath.internal.SourceTree;

/**
 * This class is part of the "World of Advenrture" application. 
 *
 * This class holds an enumeration of all command words known to the game.
 * It is used to recognise commands as they are typed in.
 *
 */

public class CommandWords {

    /**
     * Constructor - initialise the command words.
     */
    public CommandWords()   {
        // nothing to do at the moment...
    }

    public CommandWord getCommandWords(String command){
        CommandWord commandWord = null;
        int i = 0;
        for (CommandWord word : CommandWord.values()) {
            if(word.toString().equals(command)){
                commandWord = word;
                i=1;
            }
            else if(i==0){
                commandWord = CommandWord.UNKNOWN;
            }
        }
        return commandWord;
    }
    /**
     * Check whether a given String is a valid command word. 
     * @return true if a given string is a valid command,
     * false if it isn't.
     */
    public boolean isCommand(String aString)  {
        for (CommandWord word : CommandWord.values()) {
            if(word.toString().equals(aString)){
                return true;
            }
        }
        if(aString != null && Command.isNumeric(aString)){
            return true;
        }
        // if we get here, the string was not found in the commands
        return false;
    }

    /**
     * Permit to have all possible commands
     * @return the String with all commands
     */
    public String allCommand(){
        String listCommand = "";
        listCommand+=Text.YOURCOMMAND.toString();
        for (CommandWord word : CommandWord.values()) {
            if(!word.equals(CommandWord.UNKNOWN)) {
                listCommand += word.toString() + " ";
            }
        }
        return listCommand;
    }


}
