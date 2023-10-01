# Lab - The BNTA Bakery


In this exercise we're going to start building an app we'll develop further as we learn more about React. Ultimately the user will be able to see a range of cake recipes and be able to add their own, but for now we're going to display three pre-selected recipes on the screen.

## MVP

The details of the cakes are below:

```json
// Victora Sponge
{
	cakeName: "Victoria Sponge",
   	ingredients: [
   		"eggs",
      	"butter",
      	"sugar",
      	"self-raising flour",
      	"baking powder",
      	"milk"
  	],
  	price: 5
   	rating: 5
}

// Tea Loaf
{
 	cakeName: "Tea Loaf",
   	ingredients: [
   		"eggs",
      	"oil",
      	"dried fruit",
      	"sugar",
      	"self-raising flour",
      	"strong tea",
  	],
  	price: 2,
  	rating: 3
}

// Carrot Cake
{
 	cakeName: "Carrot Cake",
   	ingredients: [
    	"carrots",
      	"walnuts",
      	"oil",
      	"cream cheese",
      	"flour",
      	"sugar",
   	],
   	price: 8,
   	rating: 5
} 
```

- Each cake has a name, a list of ingredients, a price and a rating. The app should also include the average rating of all cakes. Draw a wireframe diagram to show how this information could be displayed on the page.
- Draw a component diagram and consider where the state should be and what props need to be passed to which component.
- Build a React app according to your design


## Extensions

- Add CSS to your app.
- Add a piece of state to track the total value of the cakes sold. Add a "sell cake" button to each cake - when the button is clicked the total sale value should update appropriately.
- There are two ways we can go about listing the ingredients. One would be to individually access each element in the array (`cake.ingredients[0]`, etc.) but that's not very dynamic - what happens if we get a cake with only five ingredients? If you haven't already, try to build out the ingredient list dynamically.