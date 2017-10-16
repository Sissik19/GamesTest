public enum Text {
    GOWHERE(Game.messages.getString("gowhere")),
    UNLOCKWHAT(Game.messages.getString("unlockwhat")),
    GOODBYE(Game.messages.getString("goodbye")),
    WELCOME(Game.messages.getString("welcome")+CommandWord.HELP.toString()),
    IDK(Game.messages.getString("idk")),
    HELP(Game.messages.getString("helpT")),
    NODOOR(Game.messages.getString("nodoor")),
    QUITWHAT(Game.messages.getString("quitwhat")),
    YOURCOMMAND(Game.messages.getString("yourcommand")),
    YOUARE(Game.messages.getString("youare")),
    EXITS(Game.messages.getString("exits")),
    LISTOBJECT(Game.messages.getString("listobject")),
    NOKEY(Game.messages.getString("nokey")),
    NOKEYS(Game.messages.getString("nokeys")),
    NOKEY1(Game.messages.getString("nokey1")),
    NOKEY2(Game.messages.getString("nokey2")),
    ERRROR(Game.messages.getString("error")),
    ROOMOPEN(Game.messages.getString("roomopen")),
    UNLOCKDOOR(Game.messages.getString("unlockdoor")),
    UNLOCK1(Game.messages.getString("unlock1")),
    UNLOCK2(Game.messages.getString("unlock2")),
    CODENUMBER(Game.messages.getString("codenumber")),
    NOTHINGTAKE(Game.messages.getString("nothingtake")),
    NOTHING(Game.messages.getString("nothing")),
    CODE(Game.messages.getString("codeT")),
    KEY(Game.messages.getString("keyT")),
    YOUTAKE(Game.messages.getString("youtake")),
    INYOURBAG(Game.messages.getString("inyourbag"));


    private String mot ="";

    Text(String mot){
        this.mot = mot;
    }

    public String toString(){
        return mot;
    }
}
