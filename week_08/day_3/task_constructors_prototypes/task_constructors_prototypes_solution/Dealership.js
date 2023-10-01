const Dealership = function (name, maxCars) {
  this.name = name;
  this.maxCars = maxCars;
  this.carStock = [];
};

Dealership.prototype.carsInStock = function () {
  return this.carStock.length;
};

Dealership.prototype.addCar = function (car) {
  if (this.carStock.length < this.maxCars) {
    this.carStock.push(car);
  }
};

Dealership.prototype.allManufacturers = function () {
  return this.carStock.map((car) => {
    return car.manufacturer;
  });
};

Dealership.prototype.findCarsFromManufacturer = function (manufacturer) {
  return this.carStock.filter((car) => car.manufacturer === manufacturer);
};

Dealership.prototype.searchCars = function (query, property) {
  return this.carStock.filter((car) => car[property] === query);
};

Dealership.prototype.totalCarValue = function () {
  return this.carStock.reduce((reducer, car) => reducer + car.price, 0);
};

Dealership.prototype.sellCar = function (car, customer) {
  if (customer.wallet > car.price) {
    this.carStock = this.carStock.filter(
      (dealershipCar) => dealershipCar !== car
    );
    customer.buyCar(car);
  }
};

module.exports = Dealership;
