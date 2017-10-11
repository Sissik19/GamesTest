public enum StateDoor {
    CLOSE("need one key"),
    OPEN("open"),
    DCLOSE("need two key"),
    HIDE("hide"),
    CODE("code");

    private String mot ="";

    StateDoor(String mot){
        this.mot = mot;
    }

    public String toString(){
        return mot;
    }
}
