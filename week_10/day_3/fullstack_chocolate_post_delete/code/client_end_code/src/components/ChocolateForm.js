import { useState } from "react";

const ChocolateForm = ({ estates, postChocolate }) => {

  const [stateChocolate, setStateChocolate] = useState(
    {
        name: "",
        cocoaPercentage: 0,
        estateId: null
    }
  );
  
const handleChange = (event) => {
    let propertyName = event.target.name;
    let copiedChocolate = {...stateChocolate};
    copiedChocolate[propertyName] = event.target.value;
    setStateChocolate(copiedChocolate)
}

const estateOptions = estates.map((estate) => {
    return <option key={estate.id} value={estate.id}>{estate.name}</option>
})

const handleEstate = (event) => {
    const estateId = parseInt(event.target.value)
    let copiedChocolate = {...stateChocolate}
    copiedChocolate.estateId = estateId // MODIFIED
    setStateChocolate(copiedChocolate)
}

const handleFormSubmit = (event) => {
    event.preventDefault();
    postChocolate(stateChocolate);
    setStateChocolate({
        name: "",
        cocoaPercentage: 0,
        estateId: null
    })
};

return(
    <form onSubmit={handleFormSubmit}>
        <h3>Add a new chocolate</h3>

        <input 
            type="text"
            placeholder="chocolate name"
            name="name"
            value={stateChocolate.name}
            onChange={handleChange}/>

        <input 
            type="text"
            placeholder="cocoa percentage"
            name="cocoaPercentage"
            value={stateChocolate.cocoaPercentage}
            onChange={handleChange}/>

        <select 
            defaultValue="select-estate"
            name="estate"
            onChange={handleEstate}>
            <option disabled-value="select-estate">Choose an estate</option>
            {estateOptions}
        </select>

        <button type="submit">OK</button>

    </form>
  ) 
  
}

export default ChocolateForm;
