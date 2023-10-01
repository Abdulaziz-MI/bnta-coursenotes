import { useEffect, useState } from "react";

const ChocolateForm = ({ estates, saveChocolate, chocolate }) => {
  const [newChocolate, setNewChocolate] = useState({
    name: "",
    cocoaPercentage: 0,
    estate: null
  })

  const id = chocolate && chocolate.id ? chocolate.id : undefined;

  useEffect(() => {
    if(id){
      setNewChocolate(chocolate)
    }
  }, [chocolate])

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
    saveChocolate(newChocolate);
    setNewChocolate({
      name: "",
      cocoaPercentage: 0,
      estate: null
    })
  }

  return (
    <form onSubmit={handleFormSubmit}>
      <h2>{id ? "Update a chocolate" : "Create a chocolate"}</h2>
      <input
        type="text"
        name="name"
        placeholder="Chocolate name"
        value={newChocolate.name}
        onChange={handleChange}
      />

      <input
        type="number"
        name="cocoaPercentage"
        placeholder="Cocoa percentage"
        value={newChocolate.cocoaPercentage}
        onChange={handleChange}
      />

      <select 
        onChange={handleEstateChange} 
        name="estate" 
        value={newChocolate.estate ? newChocolate.estate.id : "select-estate"}
      >
        <option value="select-estate">Select an estate</option>
        {estateOptions}
      </select>

      <button type="submit">{id ? "Update" : "Create"}</button>
    </form>
  );
};

export default ChocolateForm;
