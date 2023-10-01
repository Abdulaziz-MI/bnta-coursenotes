# React: Handling API Calls

## Learning Objectives

* Understand how to make API calls within a React applications via a `fetch` request within a `useEffect` block
* Be able to make use of an API's response to initialise an application state variable
* Appreciate the purpose behind conditional rendering and the problems it solves
* Understand the syntax of a ternary operator in JavaScript
* Be able to introduce conditional rendering to a React component, dependant on some application state

## Introduction

Before we round off the React chapters, there is another use-case of the `useEffect` hook which we want to introduce. The below pattern relates to how we can use a `useEffect` hook to make an API call, which then sets (part of) the gained response to a state variable.

## Dog API
Before we begin coding, let's examine the API we plan to use: https://dog.ceo/dog-api

As you can see, hitting the API results in a random dog image from the database. The json response is also helpfully described to us:

```json
{
    "status": "success",
    "message": "https://images.dog.ceo/breeds/redbone/n02090379_4915.jpg"
}
```

The response includes a status message and a link to our random dog image.

Our aim will be to create our own React project what makes a call to this API and renders a random image of a dog to our webpage.


## API Call Use-Case
As with any JavaScript application, loading data into a React app isn't quite as straight-forward as it sounds. Regardless of where we're loading the data from, it will take time which means we can't make use of the response straight away. 

We also need to make sure that our app is ready to receive that data. Instead of delving into the processes of `async` and `await`, we can use the `useEffect` hook to manage both of these problems by letting us decide exactly **when** to load the data.

In the previous chapter we saw some examples of how to use the `useEffect` and `useState` hooks. The following code introduces nothing new, it simply makes use of these same examples in addition to a `fetch` request. Let's begin by creating a project to demonstrate how to do this.

```terminal
npx create-react-app dogAPI
cd dog_api
npm start
```
Remove all the boiler plate code in `App.js` and instead return a (yet to be written) component: `DogContainer`.

```jsx
//  App.js

const App = () => {
    
    return (
      <DogContainer />
    )
}

export default App;

```
Create a new component called `DogContainer` within a `containers` directory. You will need to import this file into App.js.

```jsx
//  App.js

import DogContainer from './containers/DogContainer';  // ADDED

const App = () => {
    
    return (
      <DogContainer />
    )
}

export default App;

```

We can now create our `DogContainer` and set up some state. We will also need to import `useState` from React. We also want to create and render a `Dog.js` component, which will be responsible for rendering the image of our dog.

We can set up a state variable `dog`, as after all we are expecting an image of a dog from the API. Its initial value can be null (a better option for an expected object than an empty string).

`DogContainer` will be responsible for collecting our data (more on which shortly), `Dog` will be responsible for rendering the image. `DogContainer` should render `Dog`.

```jsx
// containers/DogContainer.js

import Dog from '../components/Dog';
import { useState, useEffect } from "react";

const DogContainer = () => {
  const [dog, setDog] = useState(null);
  }

  return (
    <Dog />
    );
};

export default DogContainer;
```

```jsx 
// components/Dog.js

const Dog = () => {
  
  return (
    <p>Hello from Dog!</p>
  )
}

export default Dog;

```

Check the browser to see that your components are hooked up correctly. You should see 'Hello from Dog'.

Now we have a couple of working components, we can think about how to fetch our image from the API. The answer is we can use the `useEffect` hook to house our `fetch` request. We should fetch our data in a container where our state lives (i.e. `DogContainer`).

By using the `useEffect` hook we are telling React that the component needs to do something after render. What that something is, is described in a callback.

`useEffect` also accepts a second, optional, argument: a dependency array. Whenever any of the variables specified in the dependency array change, the callback (in our case, the fetch) is re-executed. Note that the dependency array is empty and hence this functionality runs once each time the component loads (i.e. we are not watching any variables for changes). 

```jsx
// containers/DogContainer.js

import { useState, useEffect } from "react";
import Dog from '../components/Dog';

const DogContainer = () => {
  const [dog, setDog] = useState(null);

  useEffect(() => {         // ADDED
    fetch("https://dog.ceo/api/breeds/image/random")    // fetches the data
      .then((response) => response.json())              // converts the response to json
      .then((response) => setDog(response.message));    // uses the setter to update state to equal the url contained in 'message'
  }, []);
  
// ALTERNATIVE SYNTAX
  useEffect(() => {
    const fetchData = async () => {
        const data = await fetch("https://dog.ceo/api/breeds/image/random");
        const json = await data.json();
        setDog(json.message);
    }
    fetchData()
  }, []);
// alternative syntax ends

  return (
    <Dog />
   );
};

export default DogContainer;
```

Open developer tools in Chrome and you should be able to see the state in your `DogContainer` update.

So we now know that our useEffect is firing our callback and that a successful fetch occurs, we can now pass that data to the `Dog` component which will be responsible for rendering our random dog image. Let's pass this data to the `Dog` component as a prop.

The component houses the structural information. Here, this is simply an `img` whose `src` attribute is defined by the `dog` state above. Remember that our URL will be accessed via 'message'.

```jsx
// containers/DogContainer.js

import { useState, useEffect } from "react";
import Dog from '../components/Dog';

const DogContainer = () => {
  const [dog, setDog] = useState(null);

// ...

  return (
    <Dog dog={dog}/>    // MODIFIED
   );
};

export default DogContainer;
```

```jsx
// components/Dog.js

const DogComponent = ({dog}) => {
  return (
    <img src={dog.message} alt="Randomly selected dog" />
    );
};

export default DogComponent;
```

### Conditional Rendering

This code block works okay for this example as the `<img>` is fine having it's `src` attribute equal to `null` in the interim before the result from the `fetch()` request is received. This does mean though, that for a brief moment, dependent on the internet speed of the user, a broken image is displayed. Unless we strategically assign empty space using CSS, it is likely that our page will suddenly change length on load of the image.

There is, however, a more significant problem with our component than a brief broken image icon. Say we wanted to save the entirety of the API response to our state variable, rather than just the `message`. We can do this easily by simply changing the last line of our `fetch()` request to pass the entire `response` to our `setDog` function. 

```jsx
// containers/DogContainer.js
// ...
    
    .then(data => setDog(data))     // MODIFIED

// ...
```

```jsx
// components/Dog.js

const Dog = ({dog}) => {
    
    return (
        <img src={dog.message} alt="Randomly selected dog"/>    // MODIFIED
    );
};
```

But then when we next change the `src` attribute in our component file to instead make use of `dog.message`, we run into an issue:

![](../../../assets/react/hooks_api/dog_message_null_error.png)

**"Cannot read properties of null (reading 'message')"** - What's this?

Remember: fetch requests take time to return their response.

When our container code loads, every line including the return function within the then-nested component file is run significantly before our API response is recieved. So what's happening here, is that our `dog` state has a default value of `null` from which the `message` property is trying to be read. This value is only going to be returned on the front-end for a brief moment, however because we are attempting to access a value which does not exist, we have created an application-blocking error.

Ignoring the jarring extension of our page on image load (as this can be solved by using CSS), and instead on this more significant error, we can introduce some **conditional rendering** to solve our issue. For large loads, such as objects gained from APIs or a selection of many, high-quality images, it is common practise to add in a component which tells the user that we are still waiting on some information, rather than displaying a section which seems to be broken.

This **conditional** piece of information also allows us to introduce a gate which ensures our application isn't, for instance, looking to grab the `message` property of a `null` value, hence ensuring again our application failing.

The way we introduce some conditional rendering is as simple as adding in some logic to our JSX. Most commonly, this logic is a ternary operator conditional to a state variable:

```jsx
// DogContainer.js

const DogContainer = () => {
  // ...

  return dog ? <DogComponent dog={dog} /> : <p>Loading dog picture...</p>;
};
```

Since we've broken up this example code onto a few lines here which might look odd, as a refresher, please see the general form of a ternary operator below:

`condition ? exprIfTrue : exprIfFalse`

Instead of simply asking React to render our component we are adding in some sort of **condition** to our code which must be satisfied first. Putting it into words, our `DogContainer` is returning a paragraph element if the `dog` state doesn't have a value (is `null`), and the `DogComponent` if it does.

## Passing Functions (optional)

Before we wrap up the React notes, we're going to look at one last example which takes our previous dog image component one step further. Let's see how we could add some user input for requesting another dog image from the same API. We _could_ add the functionality to an existing component, but that would mean _every_ instance of that component would have the functionality associated with it, which we may not want. For instance, we may want this component to be interactable on an "About" page but not on the "Home" page of a site. So instead, let's create `NewDogButton` as a separate component of our site.

```sh
touch src/components/NewDogButton.js
```

```jsx
// NewDogButton.js

const NewDogButton = () => {
  return <button>Fetch!</button>;
};

export default NewDogButton;
```

```jsx
// DogContainer.js

import NewDogButton from "../components/NewDogButton";

const DogContainer = () => {
  // ...

  return dog ? (
    <>
      <DogComponent dog={dog} />
      <NewDogButton />
    </>
  ) : (
    <p>Loading dog picture...</p>
  );
};
```

Above we have defined the structural information needed for our `NewDogButton` but we still need to define the behaviour we want for when the button is clicked. For now we'll simply log something to the console to confirm that the event is being picked up correctly.

```jsx
// NewDogButton.js

const NewDogButton = () => {
  const handleClick = () => {
    console.log("button clicked");
  };

  return <button onClick={handleClick}>Fetch!</button>;
};

export default NewDogButton;
```

Note that here we are passing the **name of the variable** assigned to the function to our button element.

![Browser console with 'button clicked!' message](../../../assets/react/hooks_api/console_button_clicked.png)

We can see from our image above that by clicking the button logs _"button clicked!"_ to the console, so our event handler is set up correctly. Now we need to modify it so that we get a new dog photo. We already have the functionality to update the `dog` value in state, so how do we get it down to `NewDogButton`?

Recall that functions in JavaScript are first-class objects. This means that we can have a **variable storing a function** just like one storing any other value. This in turn means that we can also pass around a function as a prop akin to any other variable. To test this make another function called `updateDogData` within our `DogContainer` file, before then passing it back down to `NewDogButton` as a prop:

```jsx
// DogContainer.js

const DogContainer = () => {
  // ...

  const updateDogData = () => {
    console.log("updating dog data from DogContainer");
  };

  return dog ? (
    <>
      <DogViewer dog={dog} />
      <NewDogButton onClick={updateDogData} />
    </>
  ) : (
    <p>Loading dog picture...</p>
  );
};
```

In `NewDogButton` we can now access the `onClick` prop and call the function within our existing event handler:

```jsx
// NewDogButton.js

const NewDogButton = ({ onClick }) => {
  const handleClick = () => {
    console.log("button clicked");
    onClick();
  };

  // ...
};
```

Now when we click the button we see both `console.log` messages being printed:

![Browser console with 'button clicked!' and 'updating dog data from DogContainer' messages](../../../assets/react/hooks_api/console_button_clicked_2.png)

Now all that remains is for us to modify the function in `DogContainer` so that it updates the state. We already have this functionality within this same file, within our `useEffect`. Let's abstract out our `fetch()` request, placing it within a function so that we can easily make use of the same functionality:

```jsx
// DogContainer.js

const DogContainer = () => {
  // ...

  const updateDogData = () => {
    console.log("updating dog data from DogContainer");
    fetch("https://dog.ceo/api/breeds/image/random")
      .then((response) => response.json())
      .then((response) => setDog(response));
  };

  useEffect(() => {
    updateDogData();
  }, []);

  // ...
};
```

Hey presto! We now have a finished React app (apart from any styling) with components which could easily be reused if we wanted to extend its functionality!


## Potential Pitfalls

Being able to use someone else's data in our apps is pretty likely to be a useful thing, but it's not guaranteed. The API's developers will have to plan around the same considerations as us when building it and think about how they present their data and how appropriate it is, but there's no guarantee they came to the same conclusions we would. 

The most obvious problem is data quality. We will notice any issues with the structure fairly quickly, although just because the data is not structured in a way that works for us it doesn't make it objectively "bad". Likewise missing data will be immediately obvious. This is bad enough if it leads to missing values in our apps but it can also cause issues in the code if an unexpected `undefined` pops up. The hardest thing for us to assess, though, is the accuracy of the data. This adds an element of risk to our projects as we are relying on that third-party - if their data is bad, our product is bad.

These are issues when working with data APIs, but when dealing with more complex systems there is even more we need to consider. If we send information to a third-party API we need to be aware of what it may do with that data. For example, if we send something to a generative AI API does our data get added to its training set? If we use a social media app's API is our user's activity being shared by them? Is it even legal for us to be sending that data in the first place? The level of risk increases substantially, as does the need to ensure we mitigate it.