import products.Product;
import vehicles.*;
import vehicles.land.Bicycle;
import vehicles.land.Car;
import vehicles.water.Kayak;
import vehicles.water.Speedboat;

import java.util.ArrayList;
import java.util.List;

public class VehicleStore {
    private static List<IVehicle> vehicles;

    public static void main(String[] args) {
        vehicles = new ArrayList<>();
        // create a bike using composition, and call methods from its components
        Bicycle bike1 = new Bicycle(10, 20, new Product("BMX", 350, 6));
        vehicles.add(bike1);

        System.out.println(bike1.getWheelCount());
        System.out.println(bike1.getTitle());
        System.out.println(bike1.getInventory());

        // create a car using composition, and call methods from its multiple components
        Car car1 = new Car(200, 120, new Product("2015 Dacia Duster", 10000, 3),
        new Engine(100, 150));
        vehicles.add(car1);

        car1.startEngine();
        System.out.println(car1.getFuel());
        System.out.println(car1.getPrice());

        // lab tests (water vehicle related)
        Speedboat boat = new Speedboat(300, 120, new Product("Wally 48 tender X", 1200000, 2),
                new Motor(450, 80),"Carbon Fibre");
        boat.startEngine();


        Kayak kayak = new Kayak(
                30,
                20,
                new Product(
                        "9ft Sit On Kayak",
                        400,
                        8),
                "Polyethylene Planing Hull");



        kayak.addInventory(4);
        System.out.println(kayak.getMaxSpeed());
        System.out.println(kayak.getInventory());

    }
}
