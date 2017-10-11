public enum Direction {
    WEST("west"),
    EAST("east"),
    SOUTH("south"),
    NORTH("north"),
    UP("up"),
    DOWN("down");

    private String mot ="";

    Direction(String mot){
        this.mot = mot;
    }

    public String toString(){
        return mot;
    }
}
