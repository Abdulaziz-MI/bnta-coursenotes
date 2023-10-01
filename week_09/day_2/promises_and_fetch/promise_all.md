# Promise.all (optional)

## Learning Objectives

* Understand how `promise.all` relates to single promises as seen when creating asynchronous functionality with `fetch`
* Be able to fetch a complete set of information from a paginated API with `promise.all`

## Introduction

Let's imagine that we want to go absolutely crazy with our newfound powers - let's try to display on our screen every Disney character ever!
Luckily I've got just the API for you!

>[Demonstrate the Disney API](https://api.disneyapi.dev/characters)

At the very bottom of the response, we can see the problem at hand: Each and every response returns us 50 characters, and there are 149 extra pages, each with 50 further characters. This is called pagination.

WHat would be a reasonable solution to return all of the characters? We can write 149 fetch requests, but that's not realistic or scalable. We should still use promises, but I don't want to keep refreshing the screen every time a new request finishs - ideally we would wait until the last request finishes, collate the results, then generate let's say `<li>` elements with character names in one go.

Enter `Promises.all()`.

`Promises.all()` is a special method - it's first argument needs to be an Array of promises. When each and every promise finished and resolved, it will actually give us back a promise, for which we can call a single callback, with all responses from all callbacks available for us.

Let's start writing this in our `app.js`!

First we create an array of promises (let's go for only 10 pages now, to not keep hitting the API thousands of times). We need to get the second promise from our fetch, the one coming back from our `.json()` call:

```js

document.addEventListener("DOMContentLoaded", ()=>{
	
	//as before

    const allPromises = [];
    for (let i = 1; i < 11; i++) {
	    allPromises.push(
		    fetch(`https://api.disneyapi.dev/characters?page={i}`)
		    .then((response) => response.json())
	    )
    }
})

```

Now we can call `Promise.all()`, with our list of promises. This will be able to handle all responses with a single callback in a `.then()` call.

```js
document.addEventListener("DOMContentLoaded", ()=>{
	
	//as before

    const allPromises = [];
    for (let i = 1; i < 11; i++) {
	    allPromises.push(
		    fetch(`https://api.disneyapi.dev/characters?page={i}`)
		    .then((response) => response.json())
	    )
    }
    Promise.all(allPromises)
    .then((allResults) => {
        const allCharacters = allResults.map((result) => result.data).flat();
        console.log(allCharacters);
    })    
})
```

In the callbacks first line, we are only getting back 10 objects. The value we need is inside the `data` key of each object. Mapping through the results we end up with an array of arrays - each array containing 50 different characters' details, including their names. We first must flatten the array to end up with an array of 500 objects.

Once we have this, we can map through these objects, only accessing their names - like we did in the last week!

```js
document.addEventListener("DOMContentLoaded", ()=>{
	
	//as before

    const allPromises = [];
    for (let i = 1; i < 11; i++) {
	    allPromises.push(
		    fetch(`https://api.disneyapi.dev/characters?page={i}`)
		    .then((response) => response.json())
	    )
    }
    Promise.all(allPromises)
    .then((allResults) => {
        const allCharacters = allResults.map((result) => result.data).flat();
        const allCharacterNames = allCharacters.map((characterObject) => characterObject.name) //added
        console.log(allCharacters);
    })    
})
```

Fantastic, 500 character names!

**TASK:** (optional) modify your code in `app.js` to see if you can render a `ul` element, and an `li` element for each character name!

Possible solution:

```js
document.addEventListener("DOMContentLoaded", ()=>{
	
	//as before

    const allPromises = [];
    for (let i = 1; i < 11; i++) {
	    allPromises.push(
		    fetch(`https://api.disneyapi.dev/characters?page={i}`)
		    .then((response) => response.json())
	    )
    }
    Promise.all(allPromises)
    .then((allResults) => {
        const allCharacters = allResults.map((result) => result.data).flat();
        const allCharacterNames = allCharacters.map((characterObject) => characterObject.name)
        
        const ulElement = document.createElement("ul");
        allCharacterNames.forEach((name) => {
            const liElement = document.createElement("li");
            liElement.textContent = name;
            ulElement.appendChild(liElement);
        })
        document.querySelector("body").appendChild(ulElement);
    })    
})
```
