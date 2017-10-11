import java.util.ArrayList;

public class Inventory {
    ArrayList<String> bag;

    Inventory(){
        bag = new ArrayList<>();
    }

    public String getBag(String object) {
        String find = null;
        int i = 0;
        for(String lookFor : bag )
            if(lookFor.equals(object)){
                find = object;
                i=1;
            }
            else if(i==0){
                find = null;
            }
        return find;
    }

    public void addObject(String object) {
        bag.add(object);
    }

    public void removeObject(String object){
        bag.remove(object);
    }

    public int bagSize(){
        return bag.size();
    }


}
