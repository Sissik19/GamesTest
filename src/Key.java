public class Key {

    private String couleur;
    Key(String couleur){
        this.couleur = couleur;
    }

    public String getCouleur() {
        return couleur;
    }

    public String getKeyName(){
        if(Command.isNumeric(couleur)){
            return "code "+couleur;
        }
        else{
            return "key "+couleur;
        }
    }

}
