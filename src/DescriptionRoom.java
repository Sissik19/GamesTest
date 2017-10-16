public enum DescriptionRoom {
    OUTSIDE(Game.messages.getString("outside")),
    HALL(Game.messages.getString("hall")),
    THEATER(Game.messages.getString("theater")),
    BACKTHEATER(Game.messages.getString("backtheater")),
    DOWNCORRIDOR(Game.messages.getString("downcorridor")),
    MUSICCLASS(Game.messages.getString("musicclass")),
    PUB(Game.messages.getString("pub")),
    COMPUTINGLAB(Game.messages.getString("computinglab")),
    RESERVE(Game.messages.getString("reserve")),
    BALCONY(Game.messages.getString("balcony")),
    CLASSONE(Game.messages.getString("classone")),
    CLASSTWO(Game.messages.getString("classtwo")),
    SUSPENDEDGARDEN(Game.messages.getString("suspendedgarden")),
    UPCORRIDOR(Game.messages.getString("upcorridor")),
    SECRETARIAT(Game.messages.getString("secretariat")),
    DIRECTORY(Game.messages.getString("directory")),
    UPHALL(Game.messages.getString("uphall")),
    ATTIC(Game.messages.getString("attic")),
    ATTICCORRIDOR(Game.messages.getString("atticcorridor")),
    SECRETROOM1(Game.messages.getString("secretroom1")),
    SECRETROOM2(Game.messages.getString("secretroom2"));


    private String mot ="";

    DescriptionRoom(String mot){
        this.mot = mot;
    }

    public String toString(){
        return mot;
    }
}
