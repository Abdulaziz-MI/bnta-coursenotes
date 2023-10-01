# The Document Object Model (DOM)

## Learning Objectives

* Understand what the Document Object Model represents
* Understand how to use the Chrome Developer Tools (or alternative) to view the DOM of a website
* Be able to query the DOM using JavaScript to select specific HTML elements
* Be able to update the text content of an HTML element using JavaScript
* Be able to create new node elements, update their text content, and add them as a child to another HTML element, using JavaScript
* Be able to remove a specific child element from the DOM, using JavaScript

## Introduction

When writing front-end JavaScript (i.e. JavaScript in the browser), often times, we are responding to user input, or modifying the view (HTML elements) in some way. To do such things, we must access and/or manipulate the Document Object Model API, or DOM for short. 

In really plain terms, the DOM is a hierarchical, object oriented representation of the HTML elements in your document - think of it as a tree structure, stemming from the root element (html) and branching out from there:

![](https://upload.wikimedia.org/wikipedia/commons/thumb/5/5a/DOM-model.svg/800px-DOM-model.svg.png)

Why do we need to know about the DOM? Because it provides us with an API which allows us to traverse our HTML document, read data from it, and/or make changes to it, including such things as storing the text in an input field to a variable, or adding a new li element to end of a list, and so on...

## Playing with the DOM

Let's setup a little DOM sandbox for us to play around with now. Create a new project, and add two files called `index.html` and `index.js`.

```shell
mkdir the_dom
cd the_dom
touch index.html index.js
```

Open your code in VSCode and add the following contents to `index.html` (`html:5` + `tab` should start you off with some HTML boilerplate):

```html
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script src="index.js" type="text/javascript" defer></script>       // ADDED
    <title>DOM Lesson</title>       // MODIFIED
</head>

<body>      
    <h1 id="main-heading">This is some text in a heading</h1>
    <p class="paragraph">This is some text in a paragraph</p>
    <p class="paragraph">This is some text in a different paragraph</p>
    <h2>A generic list heading</h2>
    <ul class="list">
       
    </ul>
  </body>
</html>
```

This is just a basic html page, like any other you've worked with before. Open `index.html` in the browser so we can see our page working. 

We have added `class` and `id` attributes to some of the elements to make it easier for us to find them by traversing the DOM in javascript; let's do that now. Head to `index.js`, in the same folder. As you can see, we have already linked our JS file to `index.html` via a script tag in our html file; the `defer` flag tells it to load only after the document is fully loaded in the browser, to avoid errors:

```javascript
<script src="index.js" type="text/javascript" defer></script>
```

Open index.js and add the following two lines:

```javascript
const paragraph = document.querySelector(".paragraph");
console.log(paragraph);
```

As you can see, on the first line we are calling a method called `querySelector`, and passing it the class `".paragraph"`. `querySelector` will find the first element on the DOM that matches the query provided, and return it, or else return null. If we refresh our page in the browser and pay attention the the console tab, we should be able to see this working in action. We have logged out the variable `paragraph` and we should be able to see the first `p` element in the `index.html` with the `class` of `paragraph` in the console.

`querySelector` is actually part of a family of methods on the DOM API which we can use to easily traverse the node tree and find the element, or elements, that we need:

- `getElementById(id)`: returns the element with the given `id`, or null if none are found.
- `getElementsByClassName(class)`: returns a **HTMLCollection** of all elements with the given `class`. A HTMLCollection is an array-like structure.
- `getElementsByTagName(tag)`: Retrieves a HTMLCollection of all elements of the specified tag type.
- `getElementsByName(name)`: returns a HTMLCollection of elements with the specified name attribute.
- `querySelector(query)`: returns the first element on the DOM tree to match the given query, or null.
- `querySelectorAll(query)`: same as querySelector, except it returns all matches, rather than just the first one, as a static NodeList.

You will have noticed that that some of the above methods return a **HTMLCollection**, whereas others return a **NodeList**. What's the difference, you ask? Very little, actually - they're both zero-indexed, array-like collections. The key difference is that a HTMLCollection is a _live_ collection, whereas a NodeList is usually a _static_ collection. What does that mean? If you retrieve a HTMLCollection of a list, then add a list item onto it in the DOM, the new `li` will also be added to your HTMLCollection - it's a _live_ reference, instead of a static copy. NodeList, on the other hand, would not update with the new li. It is static only.

With so many methods to choose from, you may wonder which ones to start with? They all have their use cases, but by and large, `querySelector` and `querySelectorAll` cover almost all bases, so they are a great starting point. Hence, we'll focus on those 2 methods in our lesson.

## Testing with DevTools

Google Chrome is a great browser for a number of reasons. For one thing, it has the fastest JavaScript engine of all browsers, hands-down. But another great thing about Chrome are the built-in DevTools. Let's use those tools now. Open up your `index.html` file in Chrome, then press **CTRL + SHIFT + I** (**COMMAND + OPTION + I** on Mac) to launch Dev Tools (you could also right-click anywhere on the page and select `inspect`). DevTools should appear at the bottom of the screen, or to the right.

![](../../../assets/javascript/dom/devtools-1.png)

If you select the Console tab, you should see the following printout:

![](../../../assets/javascript/dom/devtools-2.png)

As you can see, this is the first element in our document with the class `paragraph` on it, so our query worked. Hooray! See the line below the printout, with the blue angle bracket and blinking cursor - this console is _interactive_! You can run JavaScript right in here, which is very useful for debugging and experimentation. Let's try it out now by running a `querySelectorAll` right inside the console:

```javascript
document.querySelectorAll(".paragraph");
```

When you run this, the result will print straight to the console - you should see a NodeList which includes both **p** elements that are marked with the `paragraph` class in your html document:

![](../../../assets/javascript/dom/devtools-3.png)

## Updating and Adding DOM Elements

So far, we've seen that we can use various methods, such as querySelector, to fetch elements of our page via the DOM. However, the DOM also allows us to add, remove and update elements. Open up `index.js` again. We can start by making an update to one of the elements on the page - the current title is very boring and generic. Let's change it from `This is some text in a heading` to `Cool Stuff I've Learned About the DOM`, by accessing the innerText property and setting it as follows:

```javascript
const heading = document.querySelector("#main-heading");
console.log(heading);
heading.innerText = "Cool Stuff I've Learned About the DOM";
```

As you can see, we are now _changing_ the value of a html element, not just _retrieving_ information like before. If you refresh the browser, you should see the heading update with the new content. Nifty!

![](../../../assets/javascript/dom/devtools-4.png)

Let's finish updating the text on the page. First, we'll finish off the two paragraphs by replacing any generic text with new content, again, using the `innerText` property. We will also want to update the content of our `h2` tag:

```javascript
const paragraphs = document.querySelectorAll(".paragraph");
paragraphs[0].innerText = "The DOM is a hierarchical representation of the HTML elements in your document.";
paragraphs[1].innerText = "It provides us with an API which allows us to traverse our HTML document.";

document.querySelector("h2").innerHTML = "Important Methods & Properties:";
```

### createElement()
We now know how to amend elements on our page, but what if we want to add entirely new elements? We can do this using the `createElement()` method, which allows us to create a new element, store it in a variable, and manipulate it's attributes and content. Let's insert a number of list elements to our unordered list (`<ul>`).

```javascript
//  index.js

const listItem1 = document.createElement("li");
listItem1.innerText = "querySelector() - select the first element that matches a query";

const listItem2 = document.createElement("li");
listItem2.innerText = "querySelectorAll() - select all elements that match a query";

const listItem3 = document.createElement("li");
listItem3.innerText = "convertToSpaceMonkey() - use your space monkey ray-gun to convert all elements to space-monkeys";
```

### appendChild()
Notice, however, that this doesn't actually add the elements to the page; as of now, they exist only in memory. Adding them to the page is the next step. First, we should grab hold of the element we want to add our `<li>`s to (`<ul class="list">`), then use another useful method `appendChild()` to attach our `<li>`s.

```javascript
//  index.js

const list = document.querySelector('.list');

list.appendChild(listItem1); 
list.appendChild(listItem2); 
list.appendChild(listItem3); 

```

Notice the new methods in use above - `createElement`, and `appendChild()`. Using these two methods, we can create and add completely new elements to the page. What raw power we now wield! Your webpage should now look something like the following:

![](../../../assets/javascript/dom/devtools-6.png)

## Removing Elements

Oh no! There's a mistake in our list of methods and properties above - you included `convertToSpaceMonkey()`, but that actually belongs to the space-monkey API you've been working on in your spare time, not the DOM API. Let's fix it now.

We already seen one method of removing elements - select the parent node, and set it's innerHTML property to `''`. However, this only works if you know that you want to remove **all** children of an element. If you want to surgically remove a specific element, you can do so with the `removeChild` method:

```javascript
// to remove a specific element, select the parent and use the removeChild method
list.removeChild(list.lastChild);
```

Since we know that we want to remove the last child of our list, we can use the `lastChild` property. Adding the above line to the end of your JS file should fix the error, and you should now see the correction reflected in the browser if you reload the page:

![](../../../assets/javascript/dom/devtools-7.png)


## Conclusion

In this lesson we learned how to **retrieve**, **update**, **add** and **delete** elements on the page using the DOM API. The DOM also includes an **events** system. We will learn how to listen and respond to HTML DOM events in the next chapter.
