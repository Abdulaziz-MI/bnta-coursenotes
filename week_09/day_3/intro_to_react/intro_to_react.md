# An Introduction to React

## Learning Objectives

* Understand React's place in the front-end ecosystem
* Appreciate the proposed strengths of utilising React, including that related to the Virtual DOM and application production
* Be able to create our first React application using `create-react-app` in the terminal
* Appreciate what is meant by React being "unopinionated" and how, for instance, this relates to a projects' file structure
* Be able to use JSX and understand the differences to HTML
* Understand the use-case of a fragment (`<>...</>`) in React
* Understand what is meant by "application state"
* Be able to manage the "state" of an application by utilising the `useState` React hook

## Introduction

JavaScript is an incredibly powerful tool when we want to add functionality to a web page, but isn't the easiest to work with when we want to add more than a couple of event listeners. Help is at hand in the form of additional libraries which add specific things to our apps (similar to Jest adding testing support) but things start to get really powerful when we add **frameworks** into the mix. 

There are a wide range of JavaScript frameworks available, each one with pros and cons. They are all designed to streamline the process of building a web app, integrating the HTML and JavaScript code much more tightly than would be possible otherwise. In this module we will be using [React](https://reactjs.org/)


## Why React?

According to the annual [State of JS survey](https://2021.stateofjs.com/en-US/libraries/front-end-frameworks) React is the most widely-used front-end framework and has one of the highest satisfaction rates among its users. There isn't anything we can do with React which we can't already achieve by loading a JavaScript file into our HTML, so why the popularity?

Efficiency is a big part of the answer, both in terms of writing and running our code. When working outside of a framework it takes a *lot* of code to set up even small pieces of functionality in our apps. Big JavaScript files being loaded into big HTML files via `<script>` tags gets difficult to manage very quickly. By using any framework (not just React) we remove many of the problems introduced by working across multiple files.

We also need to consider efficiency in terms of execution. Running JavaScript code in the browser isn't too bad (it's what JavaScript was designed for, after all) but updating the HTML and CSS is a slow process. In the apps we have made so far this hasn't been a problem but once we start making lots of changes in quick succession we will start to see thing slow down. A slow, unresponsive app makes for a terrible user experience, but React is designed to greatly reduce the overheads in updating the page.

There are three key features of React which enable this:

- Component-based structure
- One-way data flow
- A virtual DOM

#### Component-Based Structure

Complex apps mean lots of HTML, much of which will be duplicated across the code base. Depending on what the HTML represents there can also be a lot of duplicated JavaScript to ensure the correct functionality is added in the correct places. We have spent weeks ensuring our code is DRY in these scenarios by abstracting repeated blocks of code into functions, so why not do the same here?

React lets us do just this by supporting the use of **components** - blocks of HTML with associated JavaScript which can be reused throughout an application. Much like the way in which functions can be chained together to build up complex logic, groups of components can be used to build up complex UIs. They have a parent-child relationship, meaning a component can itself be made up of smaller components. We will look at how to implement this structure in a future lesson.

#### One-Way Data Flow

Breaking an app down into components means we need to think about how they will communicate. Without a framework we can declare variables in a JS file which can be accessed by everything else in that file, but this isn't practical with React. Not only does component A now need a way to get data across to component B, it potentially needs to send data to multiple instances of component B.

Managing data flow like this gets complicated, but React can help us out again. Thanks to the hierarchical component structure we can be sure that there will be one component responsible for rendering all others, meaning every component has a common ancestor. This parent component is responsible for tracking all the application's data, known as **application state**, and sending data to the appropriate children. By keeping the data high up the component tree we also facilitate the other major efficiency driver, the Virtual DOM.

#### The Virtual DOM

We have already seen the Document Object Model (DOM) and interacted with it using JavaScript. Our applications were able to modify the DOM, both upon loading a JS file and in response to an event firing. As mentioned above though, this is a slow process. A complete new version of the DOM must be built which replaces the current one for even the smallest change. Lots of changes mean lots of DOM updates, meaning a slow app.

React's approach is to make the update a two-stage process by creating a second version of the DOM which will never be rendered, the **Virtual DOM**. In a complicated app a single button click could change multiple values being stored, each of which would trigger a DOM update. When we build our app using React those changes are made to the virtual DOM first, which is *much* faster as there is no need for the time-consuming rendering to happen. Once all changes are made the browser's DOM is compared to React's virtual DOM and only the necessary changes are made.

How does the one-way data flow support this? If the only way for a component to get updated data is from its parent, then it only needs to change if its parent tells it to. Without a framework setting out the guidelines an element could in theory be changed from anywhere in the app, meaning we can't rely on it *not* changing when something is updated. When we update React's virtual DOM we can ignore entire branches of the component tree if we aren't changing the data sent down that branch.


## Our First React App

React is available from [npmjs.com](https://www.npmjs.com/package/react) and can be installed like any library through the command line, however there are a _lot_ of moving parts required to fully configure it. In order to minimise the amount of configuration (and therefore the potential for mistakes) Meta developed the [create-react-app](https://www.npmjs.com/package/create-react-app) package. In addition to the core React libraries it includes support for:

- The bundler [Webpack](https://www.npmjs.com/package/webpack) which compresses our code into a single file before serving it to the user, reducing the number of imports required
- A built-in web server
- The Jest testing framework, enabling us to run unit tests out of the box
- Hot reloading functionality, meaning we don't need to restart the application every time we make a change.

We also get a pre-made `README.md` file and a Git repository initialised for us.

Installation is done through the command line using `npx` to temporarily install `create-react-app`, use it to scaffold the app then remove the tools.

`npx create-react-app my_app_name`

> React functionality can be added to an existing webpage by including the React initiation `<script>` tags before importing a React component file at the bottom of the page's `<body>` element. See the [official React documentation](https://reactjs.org/docs/add-react-to-a-website.html) for a guide.

We'll use this tool to set up our first React app:

```sh title="Terminal"
# Terminal
npx create-react-app hello_world
```

> The first time this command is run the following prompt may appear: 
> 
> `Need to install the following packages: create-react-app OK to proceed? (y)` 
> 
> Type 'y' and proceed, this first installation may take longer than usual.

Once the setup is complete we will have a directory containing the files needed for a minimal React app. Change into the directory and open it in VSCode to inspect our new files.

```sh title="Terminal"
# Terminal
cd hello_world
code .
```

There is a lot more here than we have seen in previous JavaScript apps, in fact we're getting close to what we saw when working with Spring. Although it can look quite daunting, the two directories created actually give us a good starting point for structuring our code:

- `public` holds assets such as images which are required for our app, plus our `index.html` file. We won't be writing any code in this folder but any photos, audio, etc should be stored here.
- `src` contains the source code for our application, any CSS style sheets and any test files. We already have examples of each generated by `create-react-app`

React is an *unopinionated* framework, meaning there are very few rules aroud how we organise our code. So long as we respect the division between code and assets we can structure sub-directories however we wish, for example by keeping all our test files in the same directory.

We can start our applications by typing `npm start` in the terminal. This will usually open a new tab in our default browser and direct it to `localhost:3000`, but sometimes it may be necessary to do this manually. Regardless of how the application is started, once it is running we will see the default `create-react-app` start page with its rotating logo.


### Modifying the Default App

There are three files which are needed to render a React app in the browser:

- `index.html` - This lays out an empty page including a `div` element
- `index.js` - This inserts our code into `index.html`'s empty `div`
- `App.js` - This is where we write our code. It acts as the top of our component tree and is our "start" point.

We won't need to do anything with `index.html` or `index.js` in this course but we will do a lot with `App.js`. If we open the file we will see the HTML used to generate the spinning logo:

```js title="src/App.js"
// src/App.js

import logo from './logo.svg';
import './App.css';

function App() {
  return (
    <div className="App">
      <header className="App-header">
        <img src={logo} className="App-logo" alt="logo" />
        <p>
          Edit <code>src/App.js</code> and save to reload.
        </p>
        <a
          className="App-link"
          href="https://reactjs.org"
          target="_blank"
          rel="noopener noreferrer"
        >
          Learn React
        </a>
      </header>
    </div>
  );
}

export default App;
```

We have a couple of imports and we define a function, so far so good. Once we get to that function's return value things start getting a bit strange though - why do we have HTML in the middle of a JavaScript file? And how is it working?!

What we have here isn't actually HTML, although it's virtually identical. What we're looking at is **JSX**, or **JavaScript XML**. While we could use the DOM methods we saw previously (such as `addElement`) to add the elements it would mean a lot of code. JSX was developed as a syntactic sugar to simplify the process, enabling us to write what *looks like* HTML directly inside our JavaScript code.

All this code will have to go though if we want to write our own app. All we need to do is replace the JSX with our own, which can be as simple or as complex as we like. For example, we could replace everything on the page with a single `h1` element saying "hello world".

```js title="src/App.js"
// src/App.js

import './App.css';

function App() {
  return (
    <h1>Hello World!</h1>
  );
}

export default App;
```

Note that we also delete the `logo.svg` import since we no longer need it. All we need to do to see our changes is save the file, which will trigger the hot reloading tools provided by `create-react-app` and reload the browser window. 

We've displayed some text on the page, which is awesome, but it's only a single heading. The average website contains a bit more than that, so let's add some more text. The next logical step might be to add a `p` tag under our `h1`.

```js title="src/App.js"
// src/App.js

function App() {
  return (
    <h1>Hello World!</h1>
    <p>Welcome to my amazing React app!</p>
  );
}

export default App;
```

Saving and reloading our app doesn't have quite the same effect this time though, in fact it breaks completely. Reading our error message gives us a clue to the problem: "Adjacent JSX elements must be wrapped in an enclosing tag".

When we return JSX from a function we are limited to a maximum of **one** element. When we only had the `h1` it was fine, but now we've added a `p` and things are getting messy. There's a way around this though, as compiler doesn't care about what is inside the single element being returned. That means we can nest elements inside each other and have them all returned, for example by wrapping our `h1` and our `p` in a `div`:

```js title="src/App.js"
// src/App.js

function App() {
	return (
  		<div>  	
   			<h1>Hello World!</h1>
    		<p>Welcome to my amazing React app!</p>
   		</div>
  	);
}

export default App;
```

Since the function is only explicitly returning the `div` the compiler is happy. Note that the error message made reference to something called a **fragment**, which is a JSX element created to handle this situation. Instead of a `div` we can enclose our JSX with empty angle brackets and keep the compiler happy.

```js title="src/App.js"
// src/App.js

function App() {
	return (
  		<>  	
   			<h1>Hello World!</h1>
    		<p>Welcome to my amazing React app!</p>
   		</>
  	);
}

export default App;
```

Open the dev tools and compare the page layout for each way of writing the code. Note that the `div` is displayed in the tree whereas the fragment isn't. When working with a large number of components fragments are often preferred to prevent cluttering the layout, but since they aren't inclued in the DOM tree we can't apply any CSS to them. If we want to use CSS we need to use a `div`.

### A Note on CSS

Structuring CSS files in React can get a little complicated as the app grows larger. It is possible to keep all styling in a single file which is imported by the top-level component but it can be helpful to have a separate file for each component. Recall that React is unopinionated, so it is up to the developer to decide how to go about it.

There are a couple of small issues arising from using JSX which need to be addressed, however. Let's say we want to make our text blue. We can define a CSS class in `App.css` to set this up.

```css title="App.css"
/* App.css */

.blue{
	color: blue;
}
```

There are two things to consider when adding this to our JSX. First, we can't apply the class to a fragment; we need to use a `div` to wrap our elements here. Second, we may run into issues with overloaded syntax. Let's add the class as we would with HTML:

```js title="src/App.js"
// src/App.js

function App() {
	return (
  		<div class='blue'>  	
   			<h1>Hello World!</h1>
    		<p>Welcome to my amazing React app!</p>
   		</div>
  	);
}

export default App;
```

The code runs but we see an error in the browser console warning of an "invlaid DOM property". Because we are working in a JavaScript file we need to be careful with our terminology. Because `class` is a JavaScript keyword the interpreter is trying to treat it as such, throwing our error. Instead we need to use the property `className` when setting CSS in JSX.

```js title="src/App.js"
// src/App.js

function App() {
	return (
  		<div className='blue'>  	
   			<h1>Hello World!</h1>
    		<p>Welcome to my amazing React app!</p>
   		</div>
  	);
}

export default App;
```

Our error has cleared. Note that we have a similar issue when setting a `for` property; we need to use `htmlFor` when working in JSX


## Adding Application State

Our application is nice, but not particularly exciting. We're going to jazz it up a little by adding a button and a counter to show how many times it has been clicked. The first thing we need is a variable to track the clicks and a function to update it with. Note that both of these are defined **inside** the `App` function.

```js title="src/App.js"
// src/App.js

function App() {
	
	let counter = 0;
	
	const incrementCounter = () => {
		counter += 1;
	}
	
	// ...
	
}

export default App;
```

Next we will display the value of our variable in the JSX. Ordinarily we would only need to write the variable name in order to access the value but that won't quite be enough here. If we simply write "counter" inside a JSX tag it will be interpreted as plain text to be displayed in the browser and won't show the value. To show the number we need to wrap the variable name in braces (`{}`) inside the JSX tags.


```js title="src/App.js"
// src/App.js

function App() {

	// ...

	return (
  		<div className='blue'>  	
   			<h1>Hello World!</h1>
    		<p>Welcome to my amazing React app!</p>
    		<hr/>
    		<p>The current value of the counter is {counter}</p>
   		</div>
  	);
}

export default App;
```

We aren't limited to variables, we could write any JavaScript expression inside the brackets. In general we try to avoid making them overly complex but it's common to find simple expressions (`term1 + term2`) or in-line function declarations made in this way.

Speaking of functions, we need to add our button and set up an event listener. Previously this would have required the `addEventListener(event, callback)` method but React streamlines this process too. In JSX we can set an `onClick` property for an element (or `onSubmit`, `onChange`, etc) and pass it a callback in the same way as we used the variable above.

```js title="src/App.js"
// src/App.js

function App() {

	// ...

	return (
  		<div className='blue'>  	
   			<h1>Hello World!</h1>
    		<p>Welcome to my amazing React app!</p>
    		<hr/>
    		<p>The current value of the counter is {counter}</p>
    		<button onClick={incrementCounter}>Increment Counter</button>
   		</div>
  	);
}

export default App;
```

Everything is on the page and hooked up... but nothing happens when we click the button! A quick bit of debugging, either by adding a `console.log` to `incrementCounter` or by using the browser's debugger, shows that the callback is firing and the value in `counter` is being updated, so why is nothing changing in the DOM?

Because our application has been built using React the updates will happen on React's terms. Remember that we don't go straight to updating the DOM, we update the virtual DOM first, and React needs a trigger to start that process. Those changes are a consequence of changes in **application state** and we need to tell React which values are part of that state.

Modern React includes special functions called **hooks** which open up additional functionality for us. One such hook is the `useState` hook which lets us include things in the application's state. We include it in our application by importing it from the `react` library. Note that we use destructuring here to *only* import the hook instead of the entire library.

```js title="src/App.js"
// src/App.js

import { useState } from 'react';

function App() {

	// ...

}

export default App;
```

We initialise a value in state by calling the `useState` function and passing it an initial value then using destructuring again to capture a variable representing that piece of state.

```js title="src/App.js"
// src/App.js

import { useState } from 'react';

function App() {

	let [counter] = useState(0);

	// ...

}

export default App;
```

Our button still doesn't appear to be working though. Checking again with the debugger shows that `counter` is still incrementing correctly now we're using state to handle it, but it still isn't updating on the page. That's because using state isn't just a matter of *where* we store a value, it's about *how we update it* as well.

We're actually only capturing one of the things returned by `useState` when we do our destructuring. The first element in the array it sends back is a variable representing the piece of state, but there is a second element which is a function which can be used to update it. This function is implemented in such a way that calling it triggers an update of the virtual DOM; once all updates are complete the DOM is updated to reflect the changes. Convention is to prefix the name of the function with `set` and include the name of the variable, in this case making it `setCounter`.

```js title="src/App.js"
// src/App.js

import { useState } from 'react';

function App() {

	const [counter, setCounter] = useState(0);		// Updated to use const since we no longer directly modify counter

	const incrementCounter = () => {
		setCounter(counter + 1);
	}
	
	// ...

}

export default App;
```

Success, clicking the button updates the number on the page!

This is a very basic example of using state but the principal is the same whatever value we use: initialise the variable with `useState` then use the returned function to update it. Most React apps will have multiple values held in state and we will see how to manage data in a more complex app in the next lesson.
