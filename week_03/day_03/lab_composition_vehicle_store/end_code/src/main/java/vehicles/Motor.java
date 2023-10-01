package vehicles;

public class Motor implements IMotorised {
    private int hp;
    private int fuel;

    public Motor(int hp, int fuel) {
        this.hp = hp;
        this.fuel = fuel;
    }

    public void startEngine() {
        System.out.println("Turning on motor. Let's go explore the big blue!");
    }

    public void stopEngine() {
        System.out.println("Turning off motor. Let's just enjoy the sun and sea for a while.");
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