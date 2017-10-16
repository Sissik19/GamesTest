public enum KeyCode {
    ORANGE(Game.messages.getString("orange")),
    PINK(Game.messages.getString("pink")),
    RED(Game.messages.getString("red")),
    BLUE(Game.messages.getString("blue")),
    GREY(Game.messages.getString("grey")),
    BLACK(Game.messages.getString("black")),
    YELLOW(Game.messages.getString("yellow")),
    CODE1(alea()),
    CODE2(alea()),
    WHITE(Game.messages.getString("white")),
    VIOLET(Game.messages.getString("violet"));


    private String mot ="";

    KeyCode(String mot){
        this.mot = mot;
    }

    public String toString(){
        return mot;
    }

    private static String alea(){
        String x = String.valueOf((int)(Math.random()*9999));
        return x;
    }
}
