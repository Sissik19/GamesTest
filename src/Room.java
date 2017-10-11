
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Class Room - a room in an adventure game.
 *
 * A "Room" represents one location in the scenery of the game.  It is
 * connected to other rooms via exits.  The exits are labelled north,
 * east, south, west.  For each direction, the room stores a reference
 * to the neighboring room, or null if there is no exit in that direction.
 *
 */
public class Room {

    private String description;
    private Key key;
    private ArrayList<Door> doors;

    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Room(String description,Key key)    {
        this.description = description;
        this.key = key;
        doors = new ArrayList<>();
    }

    public Door getDoor(String direction){
        Door toReturn = null;
        int i = 0;
        for(Door door : doors) {
            if (door.getDirection().equals(direction)){
                    toReturn = door;
                    i = 1;
            }
            else if(i==0){
                toReturn = null;
            }
        }
        return toReturn;

    }
    /**
     * Test if the direction choose is exist for the room where
     * the person is
     * @param direction
     * @return
     */
    public Room getExist(String direction){
        Room toReturn = null;
        int i = 0;
        for(Door door : doors) {
            if (door.getDirection().equals(direction)) {
                    toReturn = door.getRoom();
                    i = 1;
            }
            else if(i==0){
                toReturn = null;
            }
        }
        return toReturn;

    }


    /**
     * Create an exist for the room
     * @param direction : the direction of the exist
     * @param neighbor : the neighbor when the person go in this direction
     */
    public void setExit(String direction, Room neighbor){
        Door door = new Door(direction, neighbor, StateDoor.OPEN.toString(),null);
        doors.add(door);
    }

    public void setExit(String direction, Room neighbor, String close, Key key){
        Door door = new Door(direction, neighbor, close, key);
        doors.add(door);
    }

    public void setExit(String direction, Room neighbor, String close, Key key1, Key key2){
        Door door = new Door(direction, neighbor, close, key1, key2);
        doors.add(door);
    }

    /**
     * Write all exists for the current room
     * @return the exits
     */
    public String getExitString(){
        String exits = Text.EXITS.toString();
        for(Door door : doors){
            if(!door.getOpen().equals(StateDoor.HIDE.toString())) {
                exits += door.getDirection() + " - " + door.getOpen() + "\n";
            }
        }
        return exits;
    }

    /**
     * @return The description of the room.
     */
    public String getLongDescription(){
        String testkey = "nothing";
        if(key != null){
            if(isNumeric(key.getCouleur())) {
                testkey = "Code "+key.getCouleur();
            }
            else{
                testkey = "Key "+key.getCouleur();
            }
        }
        return Text.YOURARE.toString() + description + " \n" +
                Text.LISTOBJECT.toString()+ testkey +"\n"+ getExitString();
    }

    public Key getKey(){
        return key;
    }

    public void setKey(){
        key = null;
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
}

