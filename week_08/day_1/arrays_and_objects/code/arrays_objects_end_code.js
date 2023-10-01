// ARRAYS
item1 = "milk";
item2 = "eggs";
item3 = "bread";
//..
item94 = "eggs";

// Array: an ordered list of values which can be of any type
emptyArray = [];
numbers = [1, 2, 3, 4, 5];
letters = ["a", "b", "c", "d", "e"];
badIdea = [0, "a", true, numbers, Math.PI];

shoppingList = ["milk", "eggs", "bread"];

// Arrays are mutable
// Adding an element to the end of the array (PUSH):
shopppingListLength = shoppingList.push("apples");
console.log("shoppingList: ", shoppingList);
console.log("shoppingList length: ", shopppingListLength);
console.log("shopingList length property: ", shoppingList.length);

// removing an element form the end of the array (POP):
returnedValue = shoppingList.pop();
console.log("returnedValue: ", returnedValue);
console.log("shoppingList after pop(): ", shoppingList);

// adding element to START of array (UNSHIFT):
shoppingList.unshift("coffee");
console.log("shoppingList after unshift: ", shoppingList);

// removing element to END fo array (SHIFT):
shoppingList.shift();
console.log("shoppingList after shift: ", shoppingList);

// square bracket notation
item1 = shoppingList[0];
console.log("item at index 0: ", item1);

item3 = shoppingList[2];
console.log("item at index 2: ", item2);

// OBJECTS
// sometimes ordering is less important that knowing about what it represents
// key-value pairs
alice = {
  name: "Alice",
  age: 25,
  favouriteColour: "blue",
};

bob = {
  age: 28,
  name: "Bob",
  favouriteColour: "yellow",
};

// square notation
aliceFaveColour = alice["favouriteColour"];
console.log("Alice favourite colour: ", aliceFaveColour);

// dot notation
bobFaveColour = bob.favouriteColour;
console.log("Bob favourite colour: ", bobFaveColour);

// adding a new value to an object
alice.occupation = "developer";
console.log("alice object: ", alice);

// ARRAY DESTRUCTURING

const numbersArray = [10, 20, 30, 40];

// const ten = numbersArray[0];
// const twenty = numbersArray[1];

// console.log("ten: ", ten);
// console.log("twenty: ", twenty);

// ^^can be replaced with:
const [ten, twenty, ...rest] = numbersArray;
// ^^create an array using the values in the numbersArray. Assgn the first two elements to the variable names 'ten' and 'twenty'.

console.log("ten: ", ten);
console.log("twenty: ", twenty);
console.log("numbersArray: ", numbersArray);

// object literal destructuring assignment
let a, b;
({ a, b } = { a: 10, b: 20 });
console.log("a: ", a);
console.log("b: ", b);

// OBJECT DESTRUCTURING
const person = {
  name: "Sally",
  age: 25,
};

// const name = person.name;
// const age = person.age;

// ^^ can replace the above with:
const { name, age } = person;

console.log("name: ", name);
console.log("age: ", age);

// COMBINING OBJECT & ARRAY DESTRUCTURING

// extract name property of the third element in the array

const props = [
  { id: 1, item: "Fizz" },
  { id: 2, item: "Buzz" },
  { id: 3, item: "FizzBuzz" },
];

// const item = props[2].item;

// ^^ can replace the above with:
const [, , { item }] = props;

console.log("item: ", item);
