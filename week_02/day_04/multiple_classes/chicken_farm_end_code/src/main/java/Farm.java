import java.util.ArrayList;

public class Farm {

    private String name;
    private ArrayList<Chicken> chickens; //this arraylist will start empty
    private int eggsForSale; // all new Farm objects will start with no eggs for sale

    public Farm(String name){
        this.name = name;
        this.chickens = new ArrayList<Chicken>();
        this.eggsForSale = 0;
    }

    public String getName(){
        return this.name;
    }

    public int getChickenCount(){
        return this.chickens.size();
    }

    public int getEggsForSale(){
        return this.eggsForSale;
    }

    public void setEggsForSale(int amount){
        this.eggsForSale = amount;
    }

    public void addChicken(Chicken chicken){
        this.chickens.add(chicken);

    }

    public ArrayList<Chicken> getChickens(){
        return this.chickens;
    }



}
