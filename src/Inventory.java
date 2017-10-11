import java.util.ArrayList;

public class Inventory {
    ArrayList<Key> bag;

    Inventory(){
        bag = new ArrayList<>();
    }

    public Key getBag(Key Key) {
        Key find = null;
        int i = 0;
        for(Key lookFor : bag )
            if(lookFor.equals(Key)){
                find = Key;
                i=1;
            }
            else if(i==0){
                find = null;
            }
        return find;
    }

    public String seeBag(){
        String see = "In your bag : ";
        if(!bag.isEmpty()){
            for(Key lookFor : bag ){
                if(isNumeric(lookFor.getCouleur())) {
                    see += "code "+lookFor.getCouleur()+"\n";
                }
                else{
                    see += "key "+lookFor.getCouleur()+"\n";
                }
            }
        }
        else{
            see += "nothing";
        }
        return see;
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

    public void addKey(Key key) {
        bag.add(key);
    }

    public void removeKey(Key key){
        bag.remove(key);
    }

    public boolean existKey(String key){
        for(Key lookFor : bag ) {
            if (lookFor.getCouleur().equals(key)) {
                return true;
            }
        }
        return false;
    }

    public int bagSize(){
        return bag.size();
    }


}
