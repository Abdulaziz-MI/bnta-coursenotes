const Car = require("./Car");
const Dealership = require("./Dealership");

let dealership;
beforeEach(() => {
  dealership = new Dealership("Deals on Wheels Dealership", 4);

  dealership.addCar(new Car("BMW", 20000, "V10"));
  dealership.addCar(new Car("Porche", 50000, "GT3"));
  dealership.addCar(new Car("Fiat", 15000, "1248 CC"));
});

describe("Testing dealership methods", () => {
  test("Can count total stock", () => {
    const expected = 3;
    const actual = dealership.carsInStock();
    expect(actual).toBe(expected);
  });

  test("Can add car to stock", () => {
    dealership.addCar(new Car("Opel", 20000, "V8"));

    const expected = 4;
    const actual = dealership.carsInStock();
    expect(actual).toBe(expected);
  });

  test("Can obtain array of car manufacturers", () => {
    const expected = ["BMW", "Porche", "Fiat"];
    const actual = dealership.allManufacturers();

    expect(actual).toStrictEqual(expected);
  });

  test("Return nothing if manufacturer doesn't exist", () => {
    const expected = [];
    const actual = dealership.findCarsFromManufacturer("Volkswagen");

    expect(actual).toStrictEqual(expected);
  });

  test("Return car from BMW", () => {
    const expected = [new Car("BMW", 20000, "V10")];
    const actual = dealership.findCarsFromManufacturer("BMW");

    expect(actual).toStrictEqual(expected);
  });

  test("Total value of cars in stock", () => {
    const expected = 6000;
    const actual = dealership.totalCarValue();
    expect(actual).toStrictEqual(expected);
  });
});
