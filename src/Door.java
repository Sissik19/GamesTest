public class Door {

    private String direction;
    private Room neighbor;
    private String open;
    private Key key1;
    private Key key2;

    Door(String direction, Room neighbor, String open, Key key1){
        this.direction = direction;
        this.neighbor = neighbor;
        this.open = open;
        this.key1 = key1;
        this.key2 = null;
    }

    Door(String direction, Room neighbor, String open, Key key1, Key key2){
        this.direction = direction;
        this.neighbor = neighbor;
        this.open = open;
        this.key1 = key1;
        this.key2 = key2;
    }

    public String getDirection() {
        return direction;
    }

    public Room getRoom() {
        return neighbor;
    }

    public String getOpen() {
        return open;
    }

    public void setOpen(String open){
        this.open = open;
    }

    public Key getKey1() {
        return key1;
    }

    public Key getKey2() {
        return key2;
    }

    public void setKey1(Key key1) {
        this.key1 = key1;
    }

    public void setKey2(Key key2) {
        this.key2 = key2;
    }
}
