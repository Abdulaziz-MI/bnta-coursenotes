import java.util.ArrayList;

public class ShoppingTrolley {

    private ArrayList<String> items;

    public ShoppingTrolley(){
        this.items = new ArrayList<>();
    }

    public void addItem(String newItem){
        this.items.add(newItem);
    }

    public int countItemsInTrolley(){
        return this.items.size();
    }

    public boolean checkIfItemInTrolley(String item){
        return this.items.contains(item);
    }

}
