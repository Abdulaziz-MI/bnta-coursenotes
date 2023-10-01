const Car = require("./Car");

let car;
beforeEach(() => {
  car = new Car("BMW", 20000, "V8");
});

describe("Accessing car properties", () => {
  test("Can access manufacturer property", () => {
    const expected = "BMW";
    const actual = car.manufacturer;
    expect(actual).toBe(expected);
  });

  test("Can access price property", () => {
    const expected = 20000;
    const actual = car.price;
    expect(actual).toBe(expected);
  });

  test("Can access manufacturer property", () => {
    const expected = "V8";
    const actual = car.engine;
    expect(actual).toBe(expected);
  });
});
