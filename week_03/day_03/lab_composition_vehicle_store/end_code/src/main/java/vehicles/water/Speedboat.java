package vehicles.water;

import products.IProduct;
import vehicles.IMotorised;
import vehicles.Vehicle;

public class Speedboat extends Vehicle implements IMotorised, IWaterVehicle {
    private IMotorised motor;
    private String hullType;

    public Speedboat(float weight, int maxSpeed, IProduct baseProduct, IMotorised motor, String hullType) {
        super(weight, maxSpeed, baseProduct);
        this.motor = motor;
        this.hullType = hullType;
    }


    public void startEngine() {
        this.motor.startEngine();
    }

    public void stopEngine() {
        this.motor.stopEngine();
    }

    public int getHp() {
        return this.motor.getHp();
    }

    public void setHp(int hp) {
        this.motor.setHp(hp);
    }

    public int getFuel() {
        return this.getFuel();
    }

    public void setFuel(int fuel) {
        this.motor.setFuel(fuel);
    }

    public String getHullType() {
        return this.hullType;
    }

    public void setHullType(String type) {
        this.hullType = type;
    }
}
