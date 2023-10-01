# Updating via React with Reusable Forms

Updating is one of the core CRUD actions - most apps involved with an API will need a way to edit existing records/entities. We've already seen how we can do an update through Spring - now we just need to connect it to our React front end. 

## Put or Patch?

Generally speaking we have 2 distinct HTTP verbs if we want to update a record - Put and Patch. Only one of them is the correct one in most situations! So how do we decide which one to use?

Put is meant to be an update which, when sent from the client, includes and updates the entire resource, and all its fields. Think of editing your Instagram or Twitter profile, including profile pictures, name, nickname, etc.

Patch, on the other hand, includes only partial data to update a resource with. This usually is done to update only a single field in most cases. Examples include marking a task as done in a to-do list, or adding a single ingredient to a recipe.

In our case, we want to (potentially) update all attributes of a resource - so we will use a Put request!

> Hand out the start code, and set up the apps - run the Spring API, and `npm i` + `npm start` in the client folder

## Put endpoint in Spring

Just for convenience, the update route is already added to the Spring back end, but let's inspect it just as a refresher!

```java
    @PutMapping(value = "/{id}")
    public ResponseEntity<Chocolate> putChocolate(
            @RequestBody Chocolate updatedChocolate,
            @PathVariable Long id){
        chocolateRepository.save(updatedChocolate);
        return new ResponseEntity<>(updatedChocolate, HttpStatus.OK);
    }
```
We might not need the ID pathvariable depending on how we write our code, but it is conventional to keep the url like this for patch requests.

> Demonstrate updating a resource via Postman - use the following sample payload: 
```json
{
        "id": 1,
        "name": "Salted Dark",
        "cocoaPercentage": 100,
        "estate": {
            "id": 1,
            "name": "Rabot Estate",
            "country": "St Lucia"
        }
    }
```

Our back end is up and running - we now need to take a look at the client start code!

> Give the students 10 minutes to explore the start code - focus on the `ChocolateContainer.js`, and the addition of React Router!
> Talk about the way we pass down props to the components in the router.


Let's get to work!

## The edit form

One of the aspects of React's Components that we haven't talked about much yet, is the fact that they are reusable.

Our edit form is going to be fairly similar to our new form - in fact, most behaviour will be shared. This leads to the conclusion that we could try to reuse the existing new form. Some modifications are necessary, but the benefits are still definitely worth it!

Before we get started with writing the code, we should think through the process of adding this feature.

Our tasks will only get more complex as we are tackling large codebases, so making a list of tasks to accomplish our goal is recommended!

In order to be able to change the form to accomodate to both a create and an edit action, we will need the following:


1. Create a new state called chocolateToUpdate on the ChocolateContainer. This is necessary as if we want update functionality to happen, we will need to get a hold of the chocolate object we intend to update. This can start off being null or an empty object even.
2. Pass down this chocolate to the update form, so we can edit it once it's selected.
3. Create a button we can click on on each individual chocolate's details that will serve as a trigger to begin the editing. This should select the chocolate we are about to edit
4. Once the chocolate is set on the ChocolateContainer, the form should prepopulate the fields with the details of the selected chocolate, and adjust the labels and buttons on the form to make sure the user is informed what action is about to happen.
5. Once submitted, the form should update the selected chocolate by making a `PUT` API request.
6. Refactor our code

Let's get started!

### 1. Creating our chocolateToUpdate state

Let's add our state to the container that will hold the chocolate we will update. Since in the beginning no chocolate should be selected to update, we will start off with a `null`;

```js
const ChocolateContainer = () => {
  const [chocolates, setChocolates] = useState([]);
  const [estates, setEstates] = useState([]);
  const [error, setError] = useState("");
  const [chocolateToUpdate, setChocolateToUpdate] = useState(null); //ADDED
  
// as before
  
```

This is a conveninet file to store our chocolate, since our logic that is making the API calls are also coming from the ChocolateContainer.

### 2. Passing down the chocolate to the update form

In order to pass down the chocolate to the form we need to amend the rendering in ChocolateContainer, and the ChocolateForm needs to accept it as a prop.

In ChocolateContainer, let's pass down said chocolate:

```js
//as before

return (
    <>
      <ChocolateForm estates={estates} postChocolate={postChocolate} chocolate={chocolateToUpdate}/> // AMENDED

//as before
```

In ChocolateForm, let's add it to our props:

```js
//as before

import { useState } from "react";

const ChocolateForm = ({ estates, saveChocolate, chocolate }) => { // AMENDED

//as before

}
```

We will come back to the form once we are able to set our chocolate.

### 3. Creating a button that will select our chocolate

This in itself is a 2-parter. First, we need to create a button to be clicked on on our Chocolate components. Second, we need to invoke the setChocolateToUpdate setter, which we need to pass down from the Container.

In ChocolateContainer:

```js
//as before
return (
    <>
      <ChocolateForm estates={estates} saveChocolate={saveChocolate} chocolate={chocolateToUpdate}/>
      <ChocolateList
        chocolates={chocolates}
        deleteChocolate={deleteChocolate}
        setChocolateToUpdate={setChocolateToUpdate} // ADDED
      />
    </>
 );
//as before
```

In the ChocolateList, we need to pass it further down:

```js
const ChocolateList = ({ chocolates, deleteChocolate, setChocolateToUpdate }) => { // AMENDED
  const chocolateComponents = chocolates.map((chocolate) => {
    return (
      <Chocolate
        key={chocolate.id}
        chocolate={chocolate}
        deleteChocolate={deleteChocolate}
        setChocolateToUpdate={setChocolateToUpdate} // ADDED
      />
```

Finally, in our Chocolate component, we can create a button to click on which will set our selected chocolate:

```js
const Chocolate = ({ chocolate, deleteChocolate, setChocolateToUpdate }) => { // AMENDED

  const handleEditClick = () => {.   //ADDED
    setChocolateToUpdate(chocolate); //ADDED
  }										    //ADDED

  return (
    <div className="chocolate">
      <h3>{chocolate.name}</h3>
      <p>Estate: {chocolate.estate.name}</p>
      <p>Cocoa % {chocolate.cocoaPercentage}</p>

      <div className="chocolate-buttons">
        <button onClick={() => deleteChocolate(chocolate.id)}>Delete</button>
        <button onClick={handleEditClick}>Edit</button> //ADDED
      </div>
    </div>
  );
};

export default Chocolate;
```

We are using a separate handler function, because we will write some added functionality later that will bring the update form into focus on the screen.

### 4. Prepopulate the form

Fantastic, our chocolate is set on the Container, and we can inspect in the React Components tool that we indeed pass down the correct prop to our component.

> Check out the devtools

...now what?

We need to prepopulate the form with the existing details - but only if there is an actual chocolate passed down instead of null, which indicates the user wants to update a chocolate. How can we achieve this?

> Ask the students, discuss ideas

How can we guarantee that a chocolate is selected? The one guarantee we have is that only chocolates with an ID should be updateable! So we can do the following:

* Check if the chocolate has an id, if it does, we can save it in a variable.
* If an ID is present, we can use a useEffect hook to listen to changes to the passed down chocolate prop. 
* If there is a chocolate present, set the state of the form to the details of the chocolate.

To guarantee a valid ID we can check if the chocolate's id exists, and then set our variable to be it. Otherwise, keep it undefined.

```js
import { useState, useEffect } from "react";

const ChocolateForm = ({ estates, saveChocolate, chocolate }) => {

  const id = chocolate && chocolate.id ? chocolate.id : undefined; // ADDED

  useEffect(() => {                                    //ADDED
    if(id){                                            //ADDED
      setName(chocolate.name);                         //ADDED
      setCocoaPercentage(chocolate.cocoaPercentage);   //ADDED
      setEstate(chocolate.estate);                     //ADDED
    }                                                  //ADDED
  }, [chocolate])                                      //ADDED

  const [name, setName] = useState("");
  const [cocoaPercentage, setCocoaPercentage] = useState(0);
  const [estate, setEstate] = useState(null);

//as before
```

This sets everything on our edit form - except the estate! What can we do here?

We need to set the `value` attribute on the `select` tag to be the estate's `id` property - but only if it exists! Another great use case for a short and sweet ternary operator:

```js
//as before 
      <select onChange={handleEstateChange} value={estate ? estate.id : ""}> //MODIFIED
        <option disabled-value="select-estate">Select an estate</option>
        {estateOptions}
      </select>

      <button type="submit">Create</button>
      <p>{error}</p>
    </form>
  );
};

export default ChocolateForm;

```

Fantastic! Now when we select a chocolate it should properly populate the form. However, there is no indication to the user that the new form currently is being used for a potential update! This is bad UX - let's make it more obvious what is going on. We can use 2 further ternary operators to set both the label and the button to represent the appropriate action:

```js
return (
    <form onSubmit={handleFormSubmit}>
      <h2>{id ? "Update a chocolate" : "Create chocolate"}</h2> //MODIFIED
      <input
        type="text"
        placeholder="Chocolate name"
        value={name}
        onChange={(e) => setName(e.target.value)}
      />

      <input
        type="number"
        placeholder="Cocoa percentage"
        value={cocoaPercentage}
        onChange={(e) => setCocoaPercentage(e.target.value)}
      />

      <select onChange={handleEstateChange} value={estate ? estate.id : ""}>
        <option disabled-value="select-estate">Select an estate</option>
        {estateOptions}
      </select>

      <button type="submit">{id ? "Update" : "Create"}</button> //MODIFIED
      <p>{error}</p>
    </form>
  );
};

export default ChocolateForm;
```

Amazing! There is no way for us to set it back to creating a new chocolate yet, but we will get around that later.

### 5. Writing the update on the container

In our `ChocolateContainer`, we need to create an `updateChocolate` method that will call a PUT request. This method could be passed down to the form to invoke either the update or the create methods. This doesn't sound great - leaving the logic of deciding what method to call to the component is not ideal, since we want our Container to control logic. 

We can, however, create a little helper method that will invoke either the `postChocolate` or the `updateChocolate`. Let's call this `saveChocolate` - after all, we are definitely saving the chocolate. Again, the presence of the id of the chocolate object will determine if it's a new chocolate (lacks id) or an existing being updated (has an id)

In `ChocolateContainer`: 

```js
//as before
 const saveChocolate = (chocolate) => {                                  //ADDED
    chocolate.id ? updateChocolate(chocolate) : postChocolate(chocolate);//ADDED
  }                                                                      //ADDED

  // Display an error to the user if we cannot fetch chocolates
  if (error !== "") return <p>Error! {error}</p>;
  // Otherwise display the form and chocolates
  return (
    <>
     <ChocolateForm estates={estates} saveChocolate={saveChocolate} chocolate={chocolateToUpdate}/> //MODIFIED
//as before
```

We also need to change our Form!

```js
import { useState, useEffect } from "react";

const ChocolateForm = ({ estates, saveChocolate, chocolate }) => { //MODIFIED

//as before


  const handleFormSubmit = (event) => {
    event.preventDefault();
    if (handleValidation() === true) {
      const postBody = {
        id,                               //MODIFIED
        name,
        cocoaPercentage,
        estate,
      };
      saveChocolate(postBody);            //MODIFIED
      setName("");
      setCocoaPercentage(0);
      setEstate(null);
    }
  };
  
//as before

```

> Test that `postChocolate` still works


Now we can write our `updateChocolate` in the Container:

```js
const updateChocolate = (updatedChocolate) => {
    // create in db
    fetch(`${SERVER_URL}/chocolates/` + updatedChocolate.id, {
      method: "PUT",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(updatedChocolate),
    })
    setChocolateToUpdate(null);
  };
```

We set the chocolateToUpdate to `null` so when we updated our entity, the form goes back to it's normal `create` state!

This however does not update our ChocolateList locally - we need to do the following: Loop through each chocolate, and if it NOT the one we just updated, keep it in our list of chocolates - however, if it is our updated one, replace it with the updated chocolate's details! We can use a `.map()` enumerator with a conditional here:

```js
const updateChocolate = (updatedChocolate) => {
    // create in db
    fetch(`${SERVER_URL}/chocolates/` + updatedChocolate.id, {
      method: "PUT",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(updatedChocolate),
    })
    .then((response) => response.json())                             //ADDED
    .then((responseChocolate) => {                                   //ADDED
      const updatedChocolatesList = chocolates.map((chocolate) => {  //ADDED   
        if(chocolate.id === responseChocolate.id){                   //ADDED
          return responseChocolate;                                  //ADDED
        } else {                                                     //ADDED
          return chocolate;                                          //ADDED
        }                                                            //ADDED
      })                                                             //ADDED
      setChocolates(updatedChocolatesList);                          //ADDED
    });                                                              //ADDED
    setChocolateToUpdate(null);
  };
```

> Try updating a chocolate

Success!

One final nicety: What if we have a very long list of Chocolates? It would be really nice to scroll up to the form when we click on the edit button!
Luckily, we have a solution to this too. We need only 2 things: A reference to the element we want to scroll to, and a simple vanilla JS function called `scrollIntoView()`!

We need both of these in the same place. The event we probably want to hook onto is the `onClick` of the edit button. We can grab the reference of the form here with a classic - `querySelector()`!

> Mention that if we had our form directly above our form in the component hierarchy, we should use something called `useRef` and pass it down to our destination - however, our current setup have these 2 components next to each other!

In Chocolate:

```js
const Chocolate = ({ chocolate, deleteChocolate, setChocolateToUpdate }) => {

  const handleEditClick = () => {
    setChocolateToUpdate(chocolate);
    const chocolateForm = document.querySelector("form"); //ADDED
    chocolateForm.scrollIntoView({ behavior: 'smooth' }); //ADDED
  }
  
//as before
```

Real smooth!

## Conclusion

We were able to update a chocolate entity while utilizing one of React's core principles - the reusability of the components.

You do not need to go overboard with this - if a component is similar but it would take extreme effort to conform it to another feature, you might be better off just writing a new component entirely. However, new and edit forms are similar enough that we can easily modify them to fit both use case.