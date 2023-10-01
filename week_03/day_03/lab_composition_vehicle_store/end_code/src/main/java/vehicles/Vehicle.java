package vehicles;

import products.IProduct;

public abstract class Vehicle implements IVehicle {

    private float weight;
    private int maxSpeed;
    private IProduct baseProduct;

    public Vehicle(float weight, int maxSpeed, IProduct baseProduct) {
        this.weight = weight;
        this.maxSpeed = maxSpeed;
        this.baseProduct = baseProduct;
    }

    public float getPrice() {
        return this.baseProduct.getPrice();
    }

    public long getInventory() {
        return this.baseProduct.getInventory();
    }

    public void setPrice(float price) {
        this.baseProduct.setPrice(price);
    }

    public void setInventory(long amount) {
        this.baseProduct.setInventory(amount);
    }

    public void setTitle(String title) {
        this.baseProduct.setTitle(title);
    }

    public String getTitle() {
        return this.baseProduct.getTitle();
    }

    public void addInventory(long amount) {
        this.baseProduct.addInventory(amount);
    }

    public void removeInventory(long amount) throws Exception {
        this.baseProduct.removeInventory(amount);
    }

    public float getWeight() {
        return this.weight;
    }

    public int getMaxSpeed() {
        return this.maxSpeed;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public void setMaxSpeed(int speed) {
        this.maxSpeed = speed;
    }
}
