# Components & Props

## Learning Objectives

* Be able to create a custom, reusable React component
* Be able to break down a design into reusable components
* Be able to build a multi-component React application
* Understand the difference between a "container" and a "component"
* Understand how props are used to pass data and functionality from one component to another
* Recognise how provided props can be destructured for easier use within a component

## Introduction

The app we built in the last lesson gave us an introduction to using React but wasn't a great example of a production-level project. The apps we produce in the real world will be much more complex and handle much more data. This has the potential to cause a problem for us if `App.js` has to get really big to handle everything.

The component-based structure mentioned in the previous lesson can help us out here. We will design a (slightly) more complex app and split it into smaller components to make it easier to maintain. That will introduce a potential difficulty in letting the components talk to each other, but React has tools to help us with that as well.


## Designing Our App

We are going to build an app which will track how many people are signing up for different outdoor activities. For now we will restrict ourselves to simple counters with functionality to increase or decrease a count, plus a guard to ensure we can't have a negative number of people signed up to an activity. We'll also track the total number of signups across all activities.

The place to start, as with any web app, is a wireframe. We're concentrating on structure over style here so we'll keep it pretty basic.

![Wireframe diagram](../../../assets/react/components_and_props/wireframe.png)

The HTML required to implement this wouldn't be particularly challenging: all we need is a couple of headings, some paragraphs and a few buttons. It would, however, need many lines of code and be quite repetitive. We've got the same combination of heading followed by paragraph followed by buttons three times over and putting all that in the same file wouldn't be very DRY.

![Wireframe with repeated HTML blocks highlighted](../../../assets/react/components_and_props/annotated_wireframe.png)

If we were writing some business logic rather than HTML this would be an immediate red flag to us and we would be trying to refactor the code in some way, probably by abstracting the offending code out to a function and calling it where necessary. React enables us to take a similar approach here.

Note the structure of the file we were working in earlier:

```js

function App(){

	// JavaScript code
	
	return (
		// JSX
	)

}

```

**We are already writing our JSX in functions!** Inside that function we do some setup, such as declaring state, then return JSX which is rendered on the page. We have already seen that we can call functions from inside other functions in JavaScript, so could this help us with our repetitive JSX problem?

The answer is yes! By abstracting part of the JSX away into another function we can call it as many times as we need to with the returned JSX being added into the DOM. When we start to structure our React applications in this way we are breaking them down into **components**.


## Setting Up the Components

We can't fire straight in to creating components, we need to add another layer of planning to the process. Exactly what should go into a component needs thought and is very easy to overthink. For example, in our wireframe above we might decide that since we have multiple buttons we should have a `Button` component. Does that actually save us any work, though? We wouldn't be cutting back the amount of code needed, in fact we'd be adding more by creating a whole separate file. The red-highlighted sections, on the other hand, do make good candidates for components. Abstracting that away would cut several lines from our app, even allowing for the extra file.

We also need to think about what is responsible for rendering what. Recall from the previous lesson that we can only return a single JSX element from each component, meaning we can't throw a bunch of components into a file and hope for the best. We need to place them inside a parent component, which may have its own parent in turn. We can start to visualise the relationships by drawing out a component diagram.

![The basic component diagram](../../../assets/react/components_and_props/basic_component_diagram.png)

We aren't worried about exactly how many of each component we have, only that `SignupContainer` will be responsible for rendering _at least one_ instance of `Counter`. We also aren't concerned with what the JSX looks like inside each component, that will be dependent on the wireframe.

The components we create will generally fall into one of two categories:

- **Containers** are components which hold state or contain complex business logic.
- **Components** are presentational and do not contain complex logic.

There are grey areas between the two (as we will see in the next lesson) but the majority can be categorised like this. 

We will be creating two components in this app and we need to decided which category each will fall into. To help us decide we need to consider what (if any) state our app will need and where it needs to be kept. We have three different activities to track, so we need three different pieces of state - one for each.

Deciding where the state should be kept is always a challenge when designing a React app but as a rule of thumb it should be *as high in the component tree as it needs to be*. Recall that React has **one-way** data flow - data can move from a parent to a child but *never* back up the way or across branches in the tree. Here we have the option of letting each `Counter` instance have its own piece of state but we also need to consider the cumulative total which will depend on the three counts. That value will be calculated in `SignupContainer`, which means the state needs to be _at least_ that high in the tree. We will annotate our diagram to show the state being kept in each component.

![Component diagram with state added](../../../assets/react/components_and_props/components_state.png)

`SignupContainer` contains our application's state and so will be a *container*. `Counter` will be responsible for rendering the details of each count so will be a *component*.

### Creating the Components

Now we have decided on a structure it's time to start coding! Create a new React app then create two subdirectories inside `src` called `containers` and `components`. Create the appropriate file in each.

```sh title="Terminal"
# Terminal

npx create-react-app activity_signup
cd activity_signup
mkdir src/containers
mkdir src/components
touch src/containers/SignupContainer.js
touch src/components/Counter.js
```

We'll start by building the bare bones of our container. For now we'll add a heading and a placeholder for our combined signups count. We'll also set up our state.

```js title="src/containers/SignupContainer.js"
// src/containers/SignupContainer.js

import {useState} from 'react';

const SignupContainer = () => {

	const [archeryCount, setArcheryCount] = useState(0);
	const [zorbCount, setZorbCount] = useState(0);
	const [canoeCount, setCanoeCount] = useState(0);

	return(
		<>
			<h2>Activities:</h2>
			<p>Total Sign-ups: Coming soon!</p>
		</>
	)

}

export default SignupContainer
```

Note that we have used the arrow function syntax here, but it will work in the same way. Note also the `export default` statement at the bottom of the code block. This is needed to be able to use our component elsewhere in the app.

If we save our work and start the app we still see the default React startup page, meaning our new code isn't being executed. To change that we need to edit `App.js` as before, although this time we will use an `import` to give us access to our new container.

```js title="src/App.js"
// src/App.js

import SignupContainer from './containers/SignupContainer';

function App() {

	return(
		<>
			<h1>Activity Sign-Ups</h1>
			<SignupContainer />
		</>
	);

}

export default App;
``` 

Reloading our page shows our `h2` and `p`, but how have we managed this? We may be importing `SignupContainer` but we haven't called our function anywhere!

That function call happens for us when we use our component name inside angle brackets within the JSX. When we use `<SignupContainer />` the JSX it returns is inserted into the JSX to be returned by `App`, adding it to what is shown in the browser. It looks remarkably similar to the rest of our JSX, although React components will generally be self-closing (although there are exceptions) and will **always** be capitalised. 

Now we have our container rendering on screen we can add some `Counter`s to track our activities.

```js title="src/components/Counter.js"
// src/components/Counter.js

const Counter = () => {

	return(
		<>
			<h3>Activity name goes here</h3>
			<p>Current total: 0</p>
			<button>Add Participant</button>
			<button>Remove Participant</button>
		</>
	)

}

export default Counter;
```

```js title="src/containers/SignupContainer.js"
// src/containers/SignupContainer.js

import Counter from '../components/Counter'	// ADDED

const SignupContainer = () => {

	// ...

	return(
		<>
			<h2>Activities:</h2>
			<Counter />						// ADDED
			<Counter />						// ADDED
			<Counter />						// ADDED
			<p>Total Sign-ups: Coming soon!</p>
		</>
	)

}

export default SignupContainer
```

Our counters are on the page, but they're a bit rubbish. The buttons don't work and even if they did we wouldn't know which activity we were signing people up for. We need a way to get the right details down to each component, but also ensure that we send the right thing to the right place. React has tools to help us out here as well.


## Passing Data With Props

The one-way data flow is all well and good when discussing it in an abstract sense but is a bit more inconvenient when we need to get data from a parent component to a child. React has been built with this in mind though and includes **props** to facilitate the movement of data. Short for *properties*, when we talk about "passing props" we are talking about the process of sending data from a parent to a child.

These props could be single values, complex data structures or even functions and are defined when a parent renders the child. Deciding what gets sent where can get complicated so it's no surprise that we're going back to our component diagram to annotate it.

Unlike when adding state, where a value exists in a single location, when adding props we need to consider that we may have multiple instances of a single component in our app. That means we need to keep the naming general and consider that we are defining the structure of *every* instance of that component. In a similar vein to the Interface Segregation Principle introduced back in the Java module, we should only be passing props needed for a component to display information or for it to pass on to its own children. If a component is receiving props it has no use for then it is an indicator that the design is flawed in some way. 

For now the immediate concern with our `Counter` is that we can't identify which one is for which activity. Once we know that we'll also need to make sure the correct count is displayed under the correct name. Every component needs these pieces of information, it will be up to the container to ensure the right things go to the right place. We will give our props suitably generic names on the diagram.

![Component diagram with title and count props](../../../assets/react/components_and_props/components_props.png)

Passing a prop to a child component looks very similar to setting attributes in HTML. We include the name of the prop inside the angle brackets after the component name and set it equal to the value we wish to pass. The values can be hard-coded or we can take values from variables elsewhere in the code, such as our state variables. To pass props to the `Counter` components we update `SignupContainer` as follows:

```js title="src/containers/SignupContainer.js"
// src/containers/SignupContainer.js

const SignupContainer = () => {

	// ...

	return(
		<>
			<h2>Activities:</h2>
			<Counter title={"Archery"} count={archeryCount}/>		// MODIFIED
			<Counter title={"Zorbing"} count={zorbCount}/>		// MODIFIED
			<Counter title={"Canoeing"} count={canoeCount}/>		// MODIFIED
			<p>Total Sign-ups: Coming soon!</p>
		</>
	)

}

export default SignupContainer
```

That's only one side of the relationship though. We aren't doing anything with the props after we pass them yet, and that will need some configuration in `Counter`.

When we pass props to a component, regardless of how many there are, they are grouped together into an object called `props` and passed into the function defining the component. The names of the props become the object's keys and have the values passed from the parent. We can access these values inside the child using `props.propName` but it is more common to use destructuring to pull out each value. We can update `Counter` to give us access to the values passed.

```js title="src/components/Counter.js"
// src/components/Counter.js

const Counter = ({title, count}) => {

	// ...

}
```

Once they have been destructured they can be accessed like any other variable. We can include them in our JSX by wrapping them in braces as before.

```js title="src/components/Counter.js"
// src/components/Counter.js

const Counter = ({title, count}) => {

	return(
		<>
			<h3>{title} Sign-Ups</h3>
			<p>Current total: {count}</p>
			<button>Add Participant</button>
			<button>Remove Participant</button>
		</>
	)

}
```

Now we can identify which counter is which and see the correct number of sign-ups so far. By breaking our app down into components and by using props to pass data to each we have built something which could now easily be extended to cover more activities simply by adding a new counter to state and passing the value to another `Counter` instance. Every `Counter` will handle the props in exactly the same way. We're not quite done though, as we still need to make the buttons work.

### The React Developer Tools

Keeping track of which props are being passed to which components and what the current values in state are is tricky enough with a diagram, but gets even harder when the app is running. It can also be incredibly difficult to debug any errors without cluttering the console with logs.

The [React Developer Tools](https://chrome.google.com/webstore/detail/react-developer-tools/fmkadmapgofadopljbjfkapdkoienihi?hl=en) extension for Chrome gives us access to those values as the application is running. For this course we will be using the `Components` tab which shows us the structure of the components and the values of props and state as shown in the screenshot below. There is also a `Profiler` tab with tools to assess the efficiency of the app. 

![The React developer tools for Chrome](../../../assets/react/components_and_props/devtools.png)



## Passing Functions With Props

It's time for another reminder of React's one-way data flow - a child component cannot modify anything in its parent, or indeed anything in a different branch of the tree. We only have one branch in this app but what that means for us is that `Counter` can't modify anything in `SignupContainer`. That's going to be a problem though: our state is in `SignupContainer`, but the buttons we need to attach the update functionality to are in `Counter`.

It doesn't mean we can't update the values at all, just that the functions we use to do so must be defined in `SignupContainer`. We already have access to the setters returned by `useState` so the challenge for us is finding a way for `Counter` to make use of them.

Recall that functions are first-class objects in JavaScript, we means we can treat them just like any other object. We have already seen that we can pass hard-coded values and variable names as props in our applications, so if we can handle functions in the same way as those objects can we pass them as props as well?

Once again, the answer is yes! In a similar way to passing a callback into a higher-order function we can either store the function in a variable which we pass as a prop or define the function in-line. We have our setters already defined so we will take the first option here, but not until we have updated our component diagram to show the extra prop being passed.

![Component diagram with function prop added](../../../assets/react/components_and_props/components_function_prop.png)

```js title="src/containers/SignupContainer.js"
// src/containers/SignupContainer.js

const SignupContainer = () => {

	// ...

	return(
		<>
			<h2>Activities:</h2>
			<Counter title={"Archery"} count={archeryCount} onButtonClick={setArcheryCount}/>		// MODIFIED
			<Counter title={"Zorbing"} count={zorbCount} onButtonClick={setZorbCount}/>				// MODIFIED
			<Counter title={"Canoeing"} count={canoeCount} onButtonClick={setCanoeCount}/>			// MODIFIED
			<p>Total Sign-ups: Coming soon!</p>
		</>
	)

}

export default SignupContainer
```

We can't make use of the function in `Counter` until we destructure it from the `props` object.

```js title="src/components/Counter.js"
// src/components/Counter.js

const Counter = ({title, count, onButtonClick}) => {		// MODIFIED

	// ...

}
```

Note the generic name given to the prop. Since every `Counter` instance will be passed this function we can't be too specific but we do want the name to reflect the prop's purpose. 

Once we have passed the function down the tree the receiving component can use it in whatever way it wants, including calling it from inside another function. We'll add a function to handle the "add participant" button being clicked.

```js title="src/components/Counter.js"
// src/components/Counter.js

const Counter = ({title, count, onButtonClick}) => {

	const handleAddParticipantClick = () => {			// ADDED
		onButtonClick(count + 1)
	}

	return(
		<>
			<h3>{title} Sign-Ups</h3>
			<p>Current total: {count}</p>
			<button onClick={handleAddParticipantClick}>Add Participant</button>		// MODIFIED
			<button>Remove Participant</button>
		</>
	)

}
```

Each component has exactly the same functionality defined in a broad manner but because each instance is given different values as props they will all work independently. `SignupContainer` is still responsible for managing the overall application state, the `Counter` instances just display what they're told to display and use the tools provided to them to handle the button click. Presentation components can define their own functions as we have done here but these shouldn't be as complex as those defined in the containers. That said, a little bit of logic is fine. For example, we might want to add a condition to the function for removing a participant which ensures we can't have a negative number of participants.

```js title="src/components/Counter.js"
// src/components/Counter.js

const Counter = ({title, count, onButtonClick}) => {

	const handleAddParticipantClick = () => {
		onButtonClick(count + 1);
	}
	
	const handleRemoveParticipantClick = () => {		// ADDED
		if (count > 0){
			onButtonClick(count - 1);
		}
	}

	return(
		<>
			<h3>{title} Sign-Ups</h3>
			<p>Current total: {count}</p>
			<button onClick={handleAddParticipantClick}>Add Participant</button>
			<button onClick={handleRemoveParticipantClick}>Remove Participant</button>		// MODIFIED
		</>
	)

}
```

Our three counters work independently and all the remains is to update `SignupContainer` to include the total number of participants.

```js title="src/containers/SignupContainer.js"
// src/containers/SignupContainer.js

const SignupContainer = () => {

	// ...

	return(
		<>
			<h2>Activities:</h2>
			<Counter title={"Archery"} count={archeryCount} onButtonClick={setArcheryCount}/>
			<Counter title={"Zorbing"} count={zorbCount} onButtonClick={setZorbCount}/>
			<Counter title={"Canoeing"} count={canoeCount} onButtonClick={setCanoeCount}/>
			<p>Total Sign-ups: {archeryCount + zorbCount + canoeCount}</p>
		</>
	)

}

export default SignupContainer
```

We're at a point where we could now, if we wanted to, extend our app to include another activity without too much modification needed. All we would need to do is initialise more state and pass the relevant values to another instance of `Counter`. This still isn't particularly scaleable though, we need to manually add all the `Counter`s we need. In the next lesson we will look at a way of dynamically creating as many instances of a component as necessary for our app. This will be particularly useful because we'll also look at taking user input through a form and adding it to the values we have in state, which will require the app to render more components while still running.
