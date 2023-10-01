# Fullstack Chocolate (Post & Delete)

## Learning Objectives

* Be able to run a back-end project for interfacing with a front-end
* Understand how to link our frontend to our backend
* Be able to use a form in React to send a new object to an API via a `POST` request
* Be able to use a form in React to delete an item from the backend API via a `DELETE` request
* Be able to handle a form ahead of submission when a one-to-many relationship exists in the database

## Introduction

In this lesson, we will use what we have learned about React to create a frontend for an API made in a previous lab.

> Note to trainer: ensure you have pushed `chocolate_api_start_code` and `cors.md` to `coursenotes`.

Let's begin by creating a folder to hold our fullstack application.

```shell
mkdir chocolate_fullstack
```

## Revisiting Single-Origin Chocolate lab
The API we will re-visit is: Single Origin Chocolate. A copy of this project has been provided in the `code` folder as start_code. If you remember, we created an API using Java and Spring Boot to return data on chocolates and their associated estates of origin. 

Head to `coursenotes` and copy over `chocolate_api_start_code` into your `chocolate_fullstack` folder.

> Ask students to get their API up and running, talk them through if needed.

- Look in `application.properties` and take a note of the database name (hint: `chocolate_db`)
- check you have created a psql database by that name (you can list all local databases in `psql` in Terminal with the command `\l` or you can look to see if it exists in Postico). If not:

```sh
# Terminal

createdb chocolate_db
```

- Run your application via `ChocolateApplication`

Check a route or two to see that your API is working. You could use Postman or your browser. The endpoint we will be using in this lesson will be `localhost:8080/chocolates` so take a little time to examine it.

### Configuring CORS

Before we can think about how to connect and create a frontend to our app, we need to add a coniguration step to deal with CORS.

Let's suppose we have already created our frontend app and we're good to go. We could run both our applications locally on our machine no problem, but as soon as we ask the two applications to speak to each other, we will encounter what is known as a Cross-Origin Resource Sharing (CORS). 

If the origin of the request is just us typing something in a browser, the server considers the request to come from the system (which is hosting the server). No problem.

When we make a request from a React app, it's coming from something running on `localhost:3000`...a *different* origin, which the server doesn't like.

The default set up (which does not allow CORS) has its purpose: to prevent a script running (say) in one tab from talking to one in another tab.

### Task (15mins)
Read through the [CORS lesson](../cors/cors.md) and reconfigure your Spring app, so that it can accept requests from our (soon-to-be-created) frontend/client.

## Wireframing the app
Now that we have configured for CORs, our API is ready for consumption.

Our first step in tackling the front-end is to draw the UI we want our application to have. Once we have our wireframe, we can then break it down further into possible components.

Handily, here's one we made earlier! [Chocolate Wireframe](./wireframes/chocolate_wireframe.png). As you can see, our page will display our chocolates in a grid, with the option to see further information about that chocolate (`SHOW`), or to delete the chocolate (`DELETE`).
Above the list of chocolates, there is form (with a dropdown) that allows a user to add a new chocolate.

### Breaking the wireframe down into components
If we look again at the wireframe, we can break down all the elements in the UI into components. Here's a suggestion of how this could be done: [Chocolate Wireframe - Components](./wireframes/chocolate_wireframe_components.png).

It can also be helpful to write down our components in a hierarchy, to help us figure out which components are rendered where:

- `ChocolateContainer`
    - `ChocolateForm`
    - `ChocolateList`
        - `Chocolate`

The process described above is very like the steps outlined in the React docs: Thinking in React.

## Our React App
We are now ready to begin coding. Let's start by navigating into our `chocolate_fullstack` folder and creating our React project. We could also use this opportunity to re-name our backend to the more conventional `server`.

As for our frontend, the convention is to call this folder `client`.

```shell
cd chocolate_fullstack
npx create-react-app client
```
Remove boilerplate (logos, svgs, import statements and the code with the `return` statement in `App.js`).


### Chocolate Container
We know from the planning we have done with our wireframe, that the first component we wish to create is the `ChocolateContainer`, rendered by `App`. Create 2 packages, `components` and `containers`. Within your `containers` package, create a new file called `ChocolateContainer`. We can set up our new container to return a `<p>` to check all is working as we expect. Don't forget to ensure that `App.js` renders `ChocolateContainer` also.

```javascript
// containers/ChocolateContainer.js

const ChocolateContainer = () => {
    
    return (
        <p>Hello from ChocolateContainer</p>
    );
 }

 export default ChocolateContainer;
```

```javascript
// App.js

import ChocolateContainer from './containers/ChocolateContainer';   // ADDED

function App() {
  return (
    <ChocolateContainer />  // ADDED
  );
}

export default App;
```

Run `npm start` and ensure your browser is displaying `Hello from ChocolateContainer`.

### Components
We also know from our planning that we have three more components to make: `ChocolateList.js`, `ChocolateForm.js` and `Chocolate.js`. We expect `ChocolateList` and `ChocolateForm` to be rendered by `ChocolateContainer`; in turn, `Chocolate` should be rendered by `ChocolateList`.

#### Task (15mins)
Right-click on your `components` folder and create all three missing components. For now, include a `<p>` tag in each so that we can see evidence of them being rendered in the browser (you do not need to include anything other than this in your components for now). Review your component hierarchy in order to set them up correctly (remember, you will need to modify `ChocolateContainer` also). 

```javascript
// components/ChocolateForm

const ChocolateForm = () => {

    return (
        <p>Hello from Chocolate Form</p>
    );

}

export default ChocolateForm;
```

```javascript
// components/ChocolateList.js

import Chocolate from './Chocolate';

const ChocolateList = () => {

    return (
        <>
            <p>Hello from ChocolateList</p>
            <Chocolate />
            <Chocolate />
        </>
    );
}

export default ChocolateList
```

```javascript
// components/Chocolate.js

const Chocolate = () => {

    return (
        <>
            <p>I'm a Chocolate!</p>
        </>
    );
}

export default Chocolate;
```

```javascript
// containers/ChocolateContainer.js


const ChocolateContainer = () => {
    
    return (
        <>  // ADDED
            <p>Hello from ChocolateContainer</p>
            <ChocolateForm />   // ADDED
            <ChocolateList />   // ADDED
        </> // ADDED
    );
 }

 export default ChocolateContainer;

```

You should see something like the following in your browser:

```
Hello from ChocolateContainer

Hello from ChocolateForm

Hello from ChocolateList

I'm a Chocolate!
I'm a Chocolate!
```
Additionally, you could also open React DevTools to see the hierarchy of your components there.

### Fetching data

Once again, we know from our planning that we expect to display a list of chocolates from our API. To do this, we will need to fetch data from the API, typically done from a container. We know we will be dealing with `state` and for this we will need the `useState` hook. We can begin by importing the hook from React. 

Now we have the hook available, we can create a variable and a function for use (eventually) in our fetch. We want to set up our incoming data (i.e. the array of chocolates) as state as we will want the app to re-render everytime a new `fetch` occurs.  We can also replace the placeholder `<p>` tag with more meaningful text.

```javascript
// containers/ChocolateContainer.js

import { useState } from 'react';       // ADDED

const ChocolateContainer = () => {

    const [chocolates, setChocolates] = useState([]);   // ADDED

    return (
        <>
            <h1>Single Origin Chocolate</h1>    // ADDED
            <p><em>A resource for chocoholics</em></p>  // MODIFIED
            <ChocolateForm />   
            <ChocolateList />   
        </>
        
    );
}

```

We're now ready to fetch data from the API. Let's review what we are trying to achieve:
- every time the app loads, we want React to fetch the data from the chocolates API. We can use React's `useEffect` hook to trigger this.
- when we receive our data, we want to assign that data to the variable `chocolates`, currently assigned to state (via the `useState` hook)
- we also know that the actual data will be displayed by the `ChocolateList` (and then `Chocolate`) components, so we will need to pass `chocolates` to `ChocolateList` as a prop

```javascript
// containers/ChocolateContainer.js

import { useState } from 'react';       

const ChocolateContainer = () => {

    const [chocolates, setChocolates] = useState([]);   
    
    useEffect(() => {                   // ADDED
        const fetchChocolates = async () => {
            const response = await fetch("http://localhost:8080/chocolates");
            const data = await response.json();
            setChocolates(data);
        }
        fetchChocolates();
    }, [])

    return (
        <>
            <h1>Single Origin Chocolate</h1>    
            <p><em>A resource for chocoholics</em></p>  
            <ChocolateForm />   
            <ChocolateList chocolates = {chocolates} />   // MODIFIED
        </>
        
    );
}

```
> It can be helpful at this point to view our app in the browser and ensure that React DevTools are open. If we click on the `ChocolateList` component in DevTools, we should see our array of `chocolates` as a prop.

We can now let `ChocolateList` expect to receive this prop and map over the array, extracting the `Chocolate` objects from the array and passing this to the `Chocolate.js` component, which is responsible for rendering each individual chocolate. Don't forget that React wants every object to have a key (so that it can easily compare changes), and for this we will use the `chocolate.id`.

```javascript
// components/ChocolateList.js

import Chocolate from './Chocolate';

const ChocolateList = ({chocolates}) => { // MODIFIED

    const chocolateComponents = chocolates.map(chocolate => {   // ADDED
        return <Chocolate 
                key={chocolate.id} 
                chocolate={chocolate} />
    })

    return (
        <>
            <h3>List of Chocolates</h3>
            <hr />
            {chocolateComponents}
        </>
    )
}

export default ChocolateList;

```

And finally, we can let the `Chocolate.js` component know it should also expect a prop and how to render the information. While we're here, we can also add the `Show` and `Delete` buttons that each chocolate ought to have (as shown in the wireframe).

```javascript
// components/Chocolate.js

const Chocolate = ({ chocolate }) => { //   MODIFIED

    return (
        <>      // ADDED
            <h4>{chocolate.name}</h4>
            <p>Estate: {chocolate.estate.name}</p>
            <p>Cocoa %: {chocolate.cocoaPercentage}</p>
            <button> delete </button>
            <button> show </button>
            <hr />
        </>
    )
}

export default Chocolate;

```
On returning to the browser, we should see all our chocolates (live!) from our API, rendered to the page.


## Adding a New Chocolate (Forms!)
Now we are rendering our existing chocolates, let's turn our attention to how to add a new chocolate to the list.

In your `components` folder, create a `ChocolateForm` component that renders a form to the page. Get `ChocolateContainer` to render the `ChocolateForm`. 

The React docs say that forms should be controlled components; that is, a component that contains user inputs (a form) should have state that keeps track of the values of those inputs. This is an execption to the rule that state should live 'high' in the hierachy of an app. While we're here, we can also import `useState`.

We can now consider the form we need for a new chocolate. Although the `name` and `cocoaPercentage` properties are straightforward to collect, remember that every chocolate has an `estate` object within it. Remember that within our database, we have a one-to-many relationship between `estates` and `chocolates`. In other words, when a chocolate is created, an estate object also has to be selected to form part of this new chocolate.

Therefore, it seems sensible for our form to include a dropdown which lists all the estates in our API (check the route `localhost:8080/estates` in your browswer or Postman). The user can then chose one of these existing estates to be part of the new chocolate. 

Let's start by coding the bones of our form, 

```javascript
//  components/ChocolateForm.js

import {useState} from 'react'; 

const ChocolateForm = () => {

    return(
        <form>
            <h3>Add a new chocolate</h3>
            
            <input 
                type="text"
                placeholder="Chocolate name" />
                
            <input
                type="text"
                placeholder="cocoa percentage" />
                
            <select defaultValue="select-estate">
                <option disabled-value="select-estate">Choose an estate</option>
            </select>
            
            <button type="submit">OK</button>      
        </form>
    )
}

export default ChocolateForm;
```

Because forms in React have their own state, we could also set up an object (as state) to hold the inputs that we will gather from the form. This may look a little different from what we have done previously, where the different properties are treated separately. In this project we will create an object that can handle all the inputs. Later we will be passing down `chocolate` as a prop, so let's call this object the slightly different `stateChocolate` (i.e. whatever the chocolate *is* while the form is being filled out).

We are also familiar with the code we need to handle the inputs for `chocolate name` and `cocoa percentage`. Ultimately, will need two handlers: (1) to handle changes to `name` and `cocoaPercentage` (as Strings, they can be treated in the same way) and (2) to handle changes to the `estate` (more on this shortly). For now we can just code the handler for `name` and `cocoaPercentage`. 

We should also add a `name` attribute to our `<input />`s as this will let the handler function choose what to do based on the value of `event.target.name`.

Finally, we can set a `value` attribute. Whilst not strictly necessary, but it's usually a good idea. Adding a `value` attribute is what makes the input controlled. This means the value in the `<input>` will always exactly correspond to what's in the React state, which will make the program's logic easier to reason with.

```javascript
//  components/ChocolateForm.js

import {useState} from 'react'; 

const ChocolateForm = () => {

    const [stateChocolate, setStateChocolate] = useState(   // ADDED
        {
            name: "",
            cocoaPercentage: 0,
            estate: null
        }
    )
    
    const handleChange = (event) => {       // ADDED
        let propertyName = event.target.name; // 'name' is the name of the target that triggered the event: 'name'/'cocoaPercentage'
        let copiedChocolate = {...stateChocolate};
        copiedChocolate[propertyName] = event.target.value;
        setStateChocolate(copiedChocolate)
    }
    

    return(
        <form onSubmit={handleFormSubmit}>  // MODIFIED
            <h3>Add a new chocolate</h3>
            
            <input 
                type="text
                placeholder="chocolate name" 
                onChange={handleChange}     // ADDED
                name="name"                 // ADDED
                value={stateChocolate.name} />   // ADDED
                
            <input
                type="text"
                placeholder="cocoa percentage"
                onChange={handleChange}     // ADDED
                name="cocoaPercentage"      // ADDED
                value={stateChocolate.cocoaPercentage} />   // ADDED
                
            <select defaultValue="select-estate">
                <option disabled-value="select-estate">Choose an estate</option>
            </select>
            
            <button type="submit>OK</button>      
        </form>
    )
}

export default ChocolateForm;
```

The set-up in the `handleChange` function may look unfamiliar.
We want to take the values of the inputs and set them to the values of chocolate we have in this form's state: `stateChocolate`. Because `stateChocolate` is state, we must do this via the `setStateChocolate` function and not directly (as we might with `stateChocolate[propertyName] = event.target.value`) For this reason, we have to make a copy and set that to state.

### The `estate` property
We can now turn our attention to that tricky `estate` property. We know that this part of the object needs to represent another object: an `Estate`. Therefore, we only want our dropdown (`<select>`) to be populated with options that already exisit in the API. So, before we go any further, we can make our `ChocolateContainer` fetch and pass down our array of estates as a prop. We will call this function inside an Effect hook as we want the data as soon as the app starts up.

We will also need to create (and set as state) an `estates` variable to hold our data after the `fetch`.

```javascript
// containers/ChocolateContainer.js

import ChocolateForm from "../components/ChocolateForm";
import ChocolateList from "../components/ChocolateList";
import { useEffect, useState } from "react";

const ChocolateContainer = () => {

    const [chocolates, setChocolates] = useState([])

    useEffect(() => {
        const fetchData = async () => {
            const response = await fetch("http://localhost:8080/chocolates");
            const data = await response.json();
            setChocolates(data);
        }
        fetchData()
    }, [])

    const [estates, setEstates] = useState([])  // ADDED

    useEffect(() => {                   // ADDED
        const fetchData = async () => {
            const response = await fetch("http://localhost:8080/estates");
            const data = await response.json();
            setEstates(data)
        
        }
        fetchData()
    }, [])
    
    return(
        <>
            <h1>Single Origin Chocolate</h1>
            <p>A resource for chocoholics</p>
            <ChocolateForm 
                    estates={estates} />        // ADDED
            <ChocolateList 
                    chocolates={chocolates} />
        </>
    );
}

export default ChocolateContainer;
```
### Finishing the `<select>`
`estates` is now available in ChocolateForm and we should indicate that the component should expect this prop.
We can map over our array of estates and return another array of `<option>`s, all labelled with the names of the estates in our API. This can be inserted into our `return` within the `<select>` tags. We can also assign a `key` and `value` attribute, both of which can be the `estate.id`.

```javascript
//  components/ChocolateForm.js

import {useState} from 'react'; 

const ChocolateForm = ( {estates} ) => {    // MODIFIED

    const [stateChocolate, setStateChocolate] = useState(   
        {
            name: "",
            cocoaPercentage: 0,
            estate: null
        }
    )
    
    const handleChange = (event) => {       
        let propertyName = event.target.name; 
        let copiedChocolate = {...stateChocolate};
        copiedChocolate[propertyName] = event.target.value;
        setStateChocolate(copiedChocolate)
    }
    
    const estateOptions = estates.map((estate) => {     // ADDED
        return <option key={estate.id} value={estate.id}>{estate.name}</option>
    })
    

    return(
        <form onSubmit={handleFormSubmit}>  
            <h3>Add a new chocolate</h3>
            
            <input 
                type="text
                placeholder="chocolate name" 
                onChange={handleChange}     
                name="name"                 
                value={stateChocolate.name} />   
                
            <input
                type="text"
                placeholder="cocoa percentage"
                onChange={handleChange}     
                name="cocoaPercentage"      
                value={stateChocolate.cocoaPercentage} />   
                
            <select defaultValue="select-estate">
                <option disabled-value="select-estate">Choose an estate</option>
                {estateOptions}     // ADDED
            </select>
            
            <button type="submit>OK</button>      
        </form>
    )
}

export default ChocolateForm;
```
We can now write the handler that will manage the change when a user selects something from the dropdown. We will be able to use the `value` of the selected estate (which will be the `id`) to find the chosen estate from the `estates` array and assign that to our new object.

```javascript
//  components/ChocolateForm.js

import {useState} from 'react'; 

const ChocolateForm = ( {estates} ) => {    

    // ...
    
    const estateOptions = estates.map((estate) => {     
        return <option key={estate.id} value={estate.id}>{estate.name}</option>
    })
    
    const handleEstate = (event) => {       // ADDED
        const estateId = parseInt(event.target.value)
        const selectedEstate = estates.find(estate => estate.id === estateId)
        let copiedChocolate = {...stateChocolate}
        copiedChocolate.estate = selectedEstate
        setStateChocolate(copiedChocolate)
    }

    return(
        <form onSubmit={handleFormSubmit}>  
            <h3>Add a new chocolate</h3>
            
            <input 
                type="text
                placeholder="chocolate name" 
                onChange={handleChange}     
                name="name"                 
                value={stateChocolate.name} />   
                
            <input
                type="text"
                placeholder="cocoa percentage"
                onChange={handleChange}     
                name="cocoaPercentage"      
                value={stateChocolate.cocoaPercentage} />   
                
            <select 
                    defaultValue="select-estate"
                    name="estate"   // ADDED
                    onChange={handleEstate} >   // ADDED
                <option disabled-value="select-estate">Choose an estate</option>
                {estateOptions}     
            </select>
            
            <button type="submit>OK</button>      
        </form>
    )
}

export default ChocolateForm;
```

> Head to the browser, open React DevTools and check that everything is looking as it should. Type in some values in your form to check  state.

### Submitting the form
We have dealt with all the properties in our form, we can now look at how to handle the form submission. Once again, we know that on submission on our form, we want our list of chocolates to update to include our new `chocolate`. In order to do this, we need a function in `ChocolateContainer` that adds a new `chocolate` to our array of fetched `chocolates`. This function should take our new chocolate object and make post request to the API (luckily, we already have a controller in our API listening out for just this thing).

As before, we need our function to sit where state is (i.e. `ChocolateContainer`), but only have access to our new object where the information has been collected (i.e. from the inputs in `ChocolateForm`). We should now pass our new function to `ChocolateForm` as a prop so that the function can be called where we have access to the object.

```javascript
// containers/ChocolateContainer.js

const ChocolateContainer = () => {

    const [chocolates, setChocolates] = useState([])

    //...
    
    const postChocolate = async (newChocolate) => {   // ADDED
        const response = await fetch("http://localhost:8080/chocolates", {
            method: "POST",
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify(newChocolate)
        })
        const savedChocolate = await response.json();
        setChocolates([...chocolates, savedChocolate])
    }
    
    return(
        <>
            <h1>Single Origin Chocolate</h1>
            <p>A resource for chocoholics</p>
            <ChocolateForm 
                    estates={estates}
                    postChocolate={postChocolate}/>     // ADDED
            <ChocolateList 
                    chocolates={chocolates} />
        </>
    
    );
}

export default ChocolateContainer;

```

`ChocolateForm` should now expect `postChocolate` as a prop and be able to use it in a `handleFormSubmit()` function. 

```javascript
//  components/ChocolateForm.js

import {useState} from 'react'; 

const ChocolateForm = ( {estates, postChocolate} ) => {    // MODIFIED

    // ...
    
    const handleFormSubmit = (event) => {   // ADDED
        event.preventDefault()
        postChocolate(stateChocolate)
        setStateChocolate({
            name: "",
            cocoaPercentage: 0,
            estate: null
        })
    }

    return(
        <form onSubmit={handleFormSubmit}>  // MODIFIED
            <h3>Add a new chocolate</h3>
            
            <input 
                type="text
                placeholder="chocolate name" 
                onChange={handleChange}     
                name="name"                 
                value={stateChocolate.name} />   
                
            <input
                type="text"
                placeholder="cocoa percentage"
                onChange={handleChange}     
                name="cocoaPercentage"      
                value={stateChocolate.cocoaPercentage} />   
                
            <select 
                    defaultValue="select-estate"
                    name="estate"   
                    onChange={handleEstate} > 
                <option disabled-value="select-estate">Choose an estate</option>
                {estateOptions}     
            </select>
            
            <button type="submit>OK</button>      
        </form>
    )
}

export default ChocolateForm;
```
> Head to the browser and check all is working as it should. You could also look at the API and be able to see any newly created chocolates listed.

## Deleting a chocolate
Our final task is to allow a user to delete a chocolate.

Helpfully, every individual chocolate rendered by the `Chocolate.js` component, already has access to `chocolate.id` (see `map` in `ChocolateList`. We can use this data to identify which chocolate to delete in the API.

Our first step could be to create a function that allows us to delete a chocolate from our array of chocolates. This `deleteChocolate` function should live in `ChocolateContainer`, as this is where we can access state. After all, on deletion of a chocolate, we want our app to re-render as `chocolates` will now have one less chocolate in it.

We need to delete our chocolate both in our API, and locally.

Before we start, we should check that our API has a delete route (it does).

```javascript
// containers/ChocolateContainer.js

const ChocolateContainer = () => {

    const [chocolates, setChocolates] = useState([])

    //...
    
    const deleteChocolate = async (id) => {
        // delete from db
        await fetch("http://localhost:8080/chocolates/" + id, {
            method: "DELETE",
            headers: {'Content-Type': 'application/json'}
        })
        // delete locally
        setChocolates(chocolates.filter(chocolate => chocolate.id !== id))
    }
    
    return(
        <>
            <h1>Single Origin Chocolate</h1>
            <p>A resource for chocoholics</p>
            <ChocolateForm 
                    estates={estates}
                    postChocolate={postChocolate}/>    
            <ChocolateList 
                    chocolates={chocolates} />
        </>
    
    );
}

export default ChocolateContainer;

```

Now we have our function `deleteChocolate()`, where do we need to call it? The answer is in `Chocolate.js`. This is where our button is and where the event listener will need to be. 

`Chocolate.js` is a little distance from `ChocolateContainer' (where our `deleteChocolate()` sits). We will need to pass `deleteChocolate` as a prop to `ChocolateList` and then onto `Chocolate.js` to make it accessible where we need it.

```javascript
// containers/ChocolateContainer.js

const ChocolateContainer = () => {

// ...

    return(
        <>
            <h1>Single Origin Chocolate</h1>
            <p>A resource for chocoholics</p>
            <ChocolateForm 
                    estates={estates}
                    postChocolate={postChocolate}/>
            <ChocolateList 
                    chocolates={chocolates}
                    deleteChocolate={deleteChocolate}   // ADDED
                    />
        </>
    );

}
```

```javascript
// components/ChocolateList.js

const ChocolateList = ({chocolates, deleteChocolate}) => {  // MODIFIED

const chocolateComponents = chocolates.map(chocolate => {
        return <Chocolate 
                key={chocolate.id} 
                chocolate={chocolate} 
                deleteChocolate={deleteChocolate} />    // ADDED
    })

// ...

```
Now that `deleteChocolate` is available in `Chocolate.js` we can call it in a handler.

```javascript
const Chocolate = ({chocolate, deleteChocolate}) => {   // MODIFIED


    const handleDeleteChocolate = () => {   // ADDED
        deleteChocolate(chocolate.id);
    }

    return (
        <>
            <h4>{chocolate.name}</h4>
            <p>Estate: {chocolate.estate.name}</p>
            <p>Cocoa %: {chocolate.cocoaPercentage}</p>
            <button onClick={handleDeleteChocolate}>    // MODIFIED
                delete
            </button>
            <button>
                show
            </button>
            <hr />
        </>
    )
}

export default Chocolate;
```
> check browser to see if your `delete` button now works
