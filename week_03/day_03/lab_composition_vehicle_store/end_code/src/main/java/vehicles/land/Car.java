package vehicles.land;

import products.IProduct;
import vehicles.IMotorised;
import vehicles.Vehicle;

public class Car extends Vehicle implements ILandVehicle, IMotorised {
    private int wheelCount;
    private IMotorised engine;

    public Car(float weight, int maxSpeed, IProduct baseProduct, IMotorised engine) {
        super(weight, maxSpeed, baseProduct);
        this.wheelCount = 4;
        this.engine = engine;
    }

    public IMotorised getEngine() {
        return engine;
    }

    public void setEngine(IMotorised engine) {
        this.engine = engine;
    }

    public void startEngine() {
        this.engine.startEngine();
    }

    public void stopEngine() {
        this.engine.stopEngine();
    }

    public int getHp() {
        return this.engine.getHp();
    }

    public void setHp(int hp) {
        this.engine.setHp(hp);
    }

    public int getFuel() {
        return this.engine.getFuel();
    }

    public void setFuel(int fuel) {
        this.engine.setFuel(fuel);
    }

    public int getWheelCount() {
        return this.wheelCount;
    }

    public void setWheelCount(int count) throws Exception {
        throw new Exception("Cannot change wheelCount for Car");
    }
}
