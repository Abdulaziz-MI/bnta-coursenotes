const Car = require("./Car");
const Dealership = require("./Dealership");
const Customer = require("./Customer");

let customer;
let dealership;
let newCar;
beforeEach(() => {
  dealership = new Dealership("Deals on Wheels Dealership", 4);

  newCar = new Car("Fiat", 15000, "1248 CC");

  dealership.addCar(new Car("BMW", 20000, "V10"));
  dealership.addCar(new Car("Porche", 50000, "GT3"));
  dealership.addCar(newCar);

  customer = new Customer("Anna", 20000);
  dealership.sellCar(newCar, customer);
});

describe("Testing customer buying a car", () => {
  test("Customer has a new car", () => {
    const expected = newCar;
    const actual = customer.car;
    expect(actual).toBe(expected);
  });

  test("Dealership has two cars left", () => {
    const expected = 2;
    const actual = dealership.carsInStock();
    expect(actual).toBe(expected);
  });
});
