const paragraph = document.querySelector(".paragraph");
console.log("paragraph :", paragraph);

const heading = document.querySelector('#main-heading');
console.log("heading: ", heading);
heading.innerText = "Cool stuff I've learned about the DOM!";

const paragraphs = document.querySelectorAll('.paragraph');
console.log(paragraphs);
paragraphs[0].innerText = "The DOM is a hierarchical representation of the HTML elements in your document.";
paragraphs[1].innerText = "It provides us with an API which allows us to traverse our HTML document."

document.querySelector("h2").innerHTML = "Important Methods & Properties";

// createElement()
const listItem1 = document.createElement("li");
listItem1.innerText = "querySelector() - select the first element that matches a query";

const listItem2 = document.createElement("li");
listItem2.innerText = "querySelectorAll() - select all elements that match a query";

const listItem3 = document.createElement("li");
listItem3.innerText = "convertToSpaceMonkey() - use your space monkey ray-gun to convert all elements to space-monkeys";

// appendChild
const list = document.querySelector('.list');

list.appendChild(listItem1); 
list.appendChild(listItem2); 
list.appendChild(listItem3); 

list.removeChild(list.lastChild);
