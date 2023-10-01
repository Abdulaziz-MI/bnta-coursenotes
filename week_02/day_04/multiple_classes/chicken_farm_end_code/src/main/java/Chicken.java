public class Chicken {

    private String name;
    private int eggs;

    public Chicken(String name, int eggs){
        this.name = name;
        this.eggs = eggs;
    }

    public String getName(){
        return this.name;
    }

    public int getEggs(){
        return this.eggs;
    }

    public void setEggs(int amount){
        this.eggs = amount;
    }

}
