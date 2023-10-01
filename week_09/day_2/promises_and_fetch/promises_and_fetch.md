# Promises and fetch

### Duration: 60mins

Lesson objectives
- to understand how promises resolve problems associated with 'callback hell'
- to learn what a promise is
- use of the `async` and `await` keywords
- how to use `fetch()`

> Make sure the JSON Formatter is installed in Chrome for the students!

Before we start, let's create a project to work with:

```
# Terminal

mkdir promises_and_fetch
cd promises_and_fetch
touch index.html app.js
code .

```

Use the `html:5` snippet to generate boiler code in `index.html`.

## Asynchronity

We've done synchronous JS so far - every line of code is getting executed in order, and each function called will be finished before the next one is called. The only way to do multiple tasks at the same time within a language, is to use multi-threading.

However, on the user side, synchronous programming has huge drawbacks - once code is being executed, the user cannot interact with the page anymore

>[Show MDN synchronous prime generator](https://developer.mozilla.org/en-US/docs/Learn/JavaScript/Asynchronous/Introducing)

The solution is asynchronous programming - with callbacks! Going into the details would be a bit too in-depth right now, but just know that the way JavaScript operates in the browser and in Node, we can execute certain functions asynchonously. When we execute one of these functions, the rest of the code can keep running, other functions can be called - and when our asynchronous function finishes, it jumps to the top of the order of executables, and invokes the callback prepared for this occasion! This idea applies to most event listeners, some functions that have intentional delays, but most importantly - to functions that we do not know when they will finish executing - think of API calls, database connections, etc.

## Callback Hell

But therein lies the problem - if we have a function that we do not know how long it's going to take, what can we do? Add a callback to execute when the function finishes. However, that function might rely on something else which may take an unspecified amount of time - so we add another callback! And we keep going, adding callbacks within callbacks, thus reaching the dreaded *callback hell*. 

Let's say you hear about Promises in JavaScript - Sounds *promising*, right?! After spending some time online, you might happen across some articles, forums or videos talking about it, and the first thing those articles will talk about is probably the problem Promises were meant to solve - the Callback Hell! But what is the callback hell? Let's check out an example!

```js
const getBaguette = function(user, nextStep){
	console.log("Getting baguette from " + user);
	nextStep("baguette");
}
const sliceBaguette = function(baguette, nextStep){
	console.log("Slicing baguette in half");
	nextStep("butter", "baguette");
}
const butterBaguette = function(butter, baguette, nextStep){
	console.log("Buttering the baguette");
	nextStep("toppings", "buttered baguette");
}
const addToppingsToBaguette = function(butteredBaguette, toppings, nextStep){
	console.log("Adding toppings to buttered baguette");
}

```

Having these 4 functions, how would we call them in order, considering that all `nextStep` arguments are callbacks?

```js
getBaguette("Phil", (baguette) => {
	sliceBaguette(baguette, (slicedBaguette) => {
		butterBaguette(butter, slicedBaguette (butteredBaguette) => {
			addToppingsToBaguette(butteredBaguette, toppings, (finishdBaguette) => {
				console.log(Serving finishedBaguette);
			})
		})
	})
})
```

How easy does this look to understand, dissect, extend and modify? The process itself would take a long time, and there's no telling where the pyramid of callbacks would finish!

I can offer you a solution - and it looks *promising*. (sorry)

## Promises

Now that we understand that simply using callbacks is not a scale-able solution to creating asynchronous code, we can appreciate an alternative Javascript provides us with: Promises.

### What is a Promise?
A promise is an object that represents something that will happen in the future. This can be the completion or failure of the operation.

Promises allow us to write cleaner code; fewer callbacks are being passed from function to function, meaning our code looks more like synchronous code (much easier to read).

When we create a Promise object it will be pending until the asynchronous operations that we are wrapping has completed.

### Fetch
The `fetch()` method that we're going to use wraps the asynchronous functionality of a request inside a Promise. This means we can write code to handle the result of our request to our API without needing to worry about when our request will finish executing.

First, add a script tag to your `index.html`.

```html
// index.html
<head>
	<!--as before-->
    <script src="js/app.js" defer></script> //ADDED
</head>
```

Open `index.html` in your browser and open Chrome Dev Tools.

Let us start by looking at the API we are going to be using for this lesson: [RandomDog API](https://dog.ceo/dog-api/).

> short demo of API for students

Now we are familiar with the documentation, we can start building our app - we want to get some random dog images through the API, and it will need a JS file to be loaded in with!

We can now write a function that uses `fetch()`, our request wrapped in a Promise.

```js
// app.js
const fetchDogImage = () => { // ADDED 
    const request = fetch("https://dog.ceo/api/breeds/image/random");
    console.log(request);
}

fetchDogImage(); // ADDED 
```

Refresh the browser and look at the console.

We use the `fetch()` to make a request to the DogAPI which returns a promise. Promises are nothing magical - just another datatype, which starts off pending, but as soon as the request comes back with a response, it will have a `resolved` or `rejected` status.

We can now think about interacting with the Promise through the `then()` method. The `then()` method registers callbacks to receive a Promise's eventual value (or reason for failure). We can think of it like the following:

* We make a request
* JS promises it will come back with something
* When it does, `.then()` we can execute some other code, with the values returned.

```js
// app.js
const fetchDogImage = () => { 
    fetch("https://dog.ceo/api/breeds/image/random") // MODIFIED
    // console.log(request) // REMOVED
    .then(response => console.log(response)) // ADDED
    
}

fetchDogImage(); 
```

If we refresh and view our console again, we can now see the response object being logged out. This contains the JSON body we're looking for, but to access it, we need to call the `json()` method on it. `json()` is also asynchronous! This means we are returned ANOTHER Promise. 

Chaining another `then()` allows us to continue to work with the object until we have what we want.

```js
// app.js
const fetchDogImage = () => { 
    fetch("https://dog.ceo/api/breeds/image/random")
    .then(response => response.json()) // MODIFIED
    .then(data => console.log(data)) // ADDED
}

fetchDogImage(); 
```

Once you inspect the log, we can see that the response finally takes the shape of the JSON data that we were expecting!

Let us add a `<button>` and an `<img>` tag to our `index.html`. The button's job is going to be used to call the method, and the `img` tag will get its `src` attribute populated.

```html
// index.html
<body>
    <h1>Promises and Fetch</h1>

    <button>Click for an image of a random dog!</button>

    <img src="" alt="A dog"> //ADDED
</body>
```

Check your browser to see if these items are rendering. 

**TASK:** modify your code in `app.js` to see if you can render a new random dog image every time you click the button. Think carefully about what you want your 'end_code' to look like before you start.

Possible solution:

```js
// app.js
const fetchDogImage = () => { 
    fetch("https://dog.ceo/api/breeds/image/random")
    .then(response => response.json()) 
    .then(data => document.querySelector("img").src = data.message) // MODIFIED
}

document.querySelector("button").addEventListener("click", fetchDogImage); //ADDED
```

### Async/Await

As we can see, we can chain together multiple `.then()` method calls. After a while, this can unfortunately also become quite hard to read, regardless of how much better this is then the already dreaded "callback hell". 

Luckily, we have a tool to modify our code just a little bit in order to make it even more developer-friendly and readable! Instead of using `.then()`, as long as we know there is an async method being called, we can use the `async/await` keywords! They work like this:

* The method/function which will use promises, must have the `async` keyword added before its method definition
* The promises themselves will need the `await` keyword added before the function call.

This way we can save the return values of the successful execution of the promises into a variable with clean and easy to read code. Consider the following:

```js
// app.js
const fetchDogImage = async () => { 
    const response = await fetch("https://dog.ceo/api/breeds/image/random");
    const jsonData = await response.json();
    document.querySelector("img").src = data.message
}

document.querySelector("button").addEventListener("click", fetchDogImage);
```
Much better!

### Loading in multiple results

APIs are not only useful for single sets of data or objects. More often than not your app will interact with a collection. Let's see how we can get multiple images to load in our app using another endpoint from the Dog API.

[RandomDog API images by breed - corgis](https://dog.ceo/api/breed/corgi/images)

We can see in the browser that we ended up with a bunch of results as an array under the `message` key. Let's go through our steps on how can we display these!

* On the buttonclick, fetch the breed data, store it in a variable
* Create a `div` element, append it to the html
* Loop through each image
* Create an `img` element in the loop, set the `.url` property to be the actual URL
* Append each image to the `div`
* (Optional) style with flex if time allows

Let's write our code!

```js
// app.js
const fetchDogImage = async () => { 
    const response = await fetch("https://dog.ceo/api/breeds/image/random");
    const jsonData = await response.json();
    document.querySelector("img").src = data.message

    const breedResponse = await fetch("https://dog.ceo/api/breed/corgi/images"); //ADDED
    const breedJsonData = await breedResponse.json();                            //ADDED
    
    const imagesContainer = document.createElement("div");                       //ADDED

    breedJsonData.message.forEach((url) => {                                     //ADDED
      const dogImage = document.createElement("img");                            //ADDED
      dogImage.src = url;                                                        //ADDED
      imagesContainer.appendChild(dogImage);                                     //ADDED
    })

    document.querySelector("body").appendChild(imagesContainer);                 //ADDED
}

document.querySelector("button").addEventListener("click", fetchDogImage);
```
