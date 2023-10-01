package vehicles;

import products.IProduct;

public interface IVehicle extends IProduct {
    public float getWeight();
    public int getMaxSpeed();
    public void setWeight(float weight);
    public void setMaxSpeed(int speed);

}
