const CakeForm = ({listOfCakes, setListOfCakes}) => {

    function handleNewCakeFormSubmission(e) {
        e.preventDefault();

        const newCakeName = e.target["cakeName"].value;
        const newCakePrice = e.target["cakePrice"].value;
        const newCakeRating = e.target["cakeRating"].value;

        let newCakeIngredients = e.target["cakeIngredients"].value.split(",");
        // removes white space either side of a string
        newCakeIngredients = newCakeIngredients.map(item => {
            return item.trim()
        })

        const newCakeObject = {
            cakeName: newCakeName,
                ingredients: newCakeIngredients,
                price: newCakePrice,
                rating: newCakeRating
        }

        setListOfCakes([...listOfCakes, newCakeObject])
    }

  return (
    <form onSubmit={handleNewCakeFormSubmission}>
        <h2>Input a new cake:</h2>
        
        <label htmlFor="cakeName">Cake Name</label>
        <input type="text" name="cakeName"></input>
        
        <label htmlFor="cakeRating">Rating</label>
        <input type="text" name="cakeRating"></input>

        <label htmlFor="cakePrice">Price (Whole £)</label>
        <input type="text" name="cakePrice"></input>

        <label htmlFor="cakeIngredients">Ingredients (as a comma separated list)</label>
        <input type="text" name="cakeIngredients"></input>

        <input type="submit" value="Submit"></input>
    </form>
  )
}

export default CakeForm