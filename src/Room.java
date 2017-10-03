
import java.util.HashMap;
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

    public String description;
    /*public Room northExit;
    public Room southExit;
    public Room eastExit;
    public Room westExit;*/
    private Map<String, Room> hm ;

    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Room(String description)    {
        this.description = description;
        hm = new HashMap<>();
    }



    //TODO Verifier
    //Ajouter Room getExit  (String  direction), retourne un  objet Room(la sortie dans cette direction).

    public Room getExist(String direction){
        if(hm.containsKey(direction)){
            return hm.get(direction);
        }
        else return null;
    }

    //TODO vérifier
    /*Ajouter  une  methode
    setExit(String   direction,  Room   neighbor)
    qui  prend  deux
    parametres : une chaine de caracteres pour la direction et un objet de type
    Room (neighbor)qui est la piece voisine dans cette direction et qui ajoute à
    l'objet exits de type HashMap une entree
     */
    public void setExit(String direction, Room neighbor){
        hm.put(direction,neighbor);
    }

    //TODO
    // On veut que la classe Room soit responsable de preparer les informations concernant les
    //sorties et qui sont ensuite imprimees dans la classe Game par la methode printLocation.
    //Donc on va definir une methode dont la signature est :
    //public  String  getExitString()
    //qui renvoie une chaine de caracteres decrivant les sorties
    // de la piece, par exemple, “Sorties : nord ouest”.

    public String getExitString(){
        return "A ecrire";
    }

    /**
     * @return The description of the room.
     */
    public String getDescription() {
        return description;
    }

}
