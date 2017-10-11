
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
    private String object;
    private Map<String, Room> hm ;

    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Room(String description,String object)    {
        this.description = description;
        this.object = object;
        hm = new HashMap<>();
    }

    /**
     * Test if the direction choose is exist for the room where
     * the person is
     * @param direction
     * @return
     */
    public Room getExist(String direction){
        if(hm.containsKey(direction)){
            return hm.get(direction);
        }
        else return null;
    }

    /**
     * Create an exist for the room
     * @param direction : the direction of the exist
     * @param neighbor : the neighbor when the person go in this direction
     */
    public void setExit(String direction, Room neighbor){
        hm.put(direction,neighbor);
    }

    /**
     * Write all exists for the current room
     * @return the exits
     */
    public String getExitString(){
        String exits = Text.EXITS.toString();
        Iterator iterator = hm.entrySet().iterator();
        while(iterator.hasNext()){
            Map.Entry pair = (Map.Entry)iterator.next();
            exits += pair.getKey();
            if(iterator.hasNext()){
                exits+= ", ";
            }

        }
        return exits;
    }

    /**
     * @return The description of the room.
     */
    public String getLongDescription(){
        String testObject = "nothing";
        if(this.object != null){
            testObject = this.object;
        }
        return Text.YOURARE.toString() + description + " \n" +
                Text.LISTOBJECT.toString()+ testObject +"\n"+ this.getExitString();
    }

    public String getObject(){
        return object;
    }

    public void setObject(){
        object = null;
    }
}
