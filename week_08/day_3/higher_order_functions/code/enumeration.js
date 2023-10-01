const numbers = [1, 2, 3, 4, 5];

const printElement = (arrayElement) => {
  console.log(arrayElement);
};

// for (let i = 0; i < numbers.length; i++) {
//     printElement(numbers[i]);
// }

numbers.forEach(printElement);

// mapping

const doubledNumbers = numbers.map((number) => number * 2);

console.log(doubledNumbers);

// filtering

const evenNumbers = numbers.filter((number) => number % 2 == 0);

console.log(evenNumbers);

// reducing

const total = numbers.reduce((reducer, number) => reducer + number, 0);

console.log(total);
