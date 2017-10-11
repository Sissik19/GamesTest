public class Key {

    private String couleur;
    Key(String couleur){
        this.couleur = couleur;
    }

    public String getCouleur() {
        return couleur;
    }

    public String getKeyName(){
        if(isNumeric(couleur)){
            return "code "+couleur;
        }
        else{
            return "key "+couleur;
        }
    }

    public static boolean isNumeric(String str)
    {
        try
        {
            double d = Double.parseDouble(str);
        }
        catch(NumberFormatException nfe)
        {
            return false;
        }
        return true;
    }
}
