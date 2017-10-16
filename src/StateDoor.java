public enum StateDoor {
    CLOSE(Game.messages.getString("close")),
    OPEN(Game.messages.getString("open")),
    DCLOSE(Game.messages.getString("dclose")),
    HIDE(Game.messages.getString("hide")),
    CODE(Game.messages.getString("code"));

    private String mot ="";

    StateDoor(String mot){
        this.mot = mot;
    }

    public String toString(){
        return mot;
    }
}
