const Cake = ({specificCake}) => {

  const mappedIngredients = specificCake.ingredients.map((item, id)=> {
    return <li key={id}>{item}</li>
  })

  return (
    <article>
        <h2>{specificCake.cakeName}</h2>
        <p>{specificCake.rating}/5</p>
        <p>£{specificCake.price}.00</p>
        <ul>
          {mappedIngredients}
        </ul>
    </article>
  )
}

export default Cake;