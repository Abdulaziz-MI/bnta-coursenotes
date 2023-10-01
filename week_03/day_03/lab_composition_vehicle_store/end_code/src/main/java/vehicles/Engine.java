package vehicles;

public class Engine implements IMotorised {
    private int fuel;
    private int hp;

    public Engine(int fuel, int hp) {
        this.fuel = fuel;
        this.hp = hp;
    }

    public void startEngine() {
        System.out.println("engine started");
    }

    public void stopEngine() {
        System.out.println("engine stopped");
    }

    public int getHp() {
        return this.hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getFuel() {
        return this.fuel;
    }

    public void setFuel(int fuel) {
        this.fuel = fuel;
    }
}
