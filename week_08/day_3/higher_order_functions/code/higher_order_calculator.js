const sum = (number1, number2) => {
  console.log(number1 + number2);
};

const subtract = (number1, number2) => {
  console.log(number1 - number2);
};

const multiply = (number1, number2) => {
  console.log(number1 * number2);
};

const doCalculation = (number1, number2, callback) => {
  callback(number1, number2);
};

doCalculation(2, 3, sum);

doCalculation(2, 3, subtract);

doCalculation(2, 3, multiply);

doCalculation(2, 3, (number1, number2) => {
  console.log(number1 / number2);
});
