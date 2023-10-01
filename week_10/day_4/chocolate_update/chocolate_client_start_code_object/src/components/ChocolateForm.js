import { useState } from "react";

const ChocolateForm = ({ estates, postChocolate }) => {
  const [newChocolate, setNewChocolate] = useState({
    name: "",
    cocoaPercentage: 0,
    estate: null
  })

  const estateOptions = estates.map((estate) => {
    return (
      <option key={estate.id} value={estate.id}>
        {estate.name}
      </option>
    );
  });

  const handleChange = (event) => {
    const propertyName = event.target.name;
    const copiedChocolate = {...newChocolate};
    copiedChocolate[propertyName] = event.target.value;
    setNewChocolate(copiedChocolate);
  }

  const handleEstateChange = (event) => {
    const estateId = parseInt(event.target.value);
    const selectedEstate = estates.find(estate => {
        return estate.id === estateId;
    });
    const copiedChocolate = {...newChocolate};
    copiedChocolate.estate = selectedEstate;
    setNewChocolate(copiedChocolate);
  }

  const handleFormSubmit = (event) => {
    event.preventDefault();
    postChocolate(newChocolate);
    setNewChocolate({
      name: "",
      cocoaPercentage: 0,
      estate: null
    })
  }

  return (
    <form onSubmit={handleFormSubmit}>
      <h2>Create a chocolate</h2>
      <input
        type="text"
        name="name"
        placeholder="Chocolate name"
        onChange={handleChange}
      />

      <input
        type="number"
        name="cocoaPercentage"
        placeholder="Cocoa percentage"
        onChange={handleChange}
      />

      <select onChange={handleEstateChange} name="estate" defaultValue="select-estate">
        <option disabled-value="select-estate">Select an estate</option>
        {estateOptions}
      </select>

      <button type="submit">Ok</button>
    </form>
  );
};

export default ChocolateForm;
