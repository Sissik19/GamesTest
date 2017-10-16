public enum Direction {
    WEST(Game.messages.getString("west")),
    EAST(Game.messages.getString("east")),
    SOUTH(Game.messages.getString("south")),
    NORTH(Game.messages.getString("north")),
    UP(Game.messages.getString("up")),
    DOWN(Game.messages.getString("down"));

    private String mot ="";

    Direction(String mot){
        this.mot = mot;
    }

    public String toString(){
        return mot;
    }
}
