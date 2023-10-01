package products;

public final class Product implements IProduct {
    private float price;
    private long inventory;
    private String title;

    public Product(String title, float price, long inventory) {
        this.title = title;
        this.price = price;
        this.inventory = inventory;
    }

    public float getPrice() {
        return this.price;
    }

    public long getInventory() {
        return this.inventory;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setInventory(long amount) {
        this.inventory = amount;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return this.title;
    }

    public void addInventory(long amount) {
        this.inventory += amount;
    }

    public void removeInventory(long amount) throws Exception {
        if(this.inventory - amount < 0) {
            throw new Exception("cannot remove more inventory than is available");
        }
        else {
            this.inventory -= amount;
        }
    }


}
