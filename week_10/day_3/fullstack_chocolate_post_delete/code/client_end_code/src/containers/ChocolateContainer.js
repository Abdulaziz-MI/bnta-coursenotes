import { useEffect, useState } from "react";
import ChocolateList from "../components/ChocolateList";
import ChocolateForm from "../components/ChocolateForm";

const SERVER_URL = "http://localhost:8080";

const ChocolateContainer = () => {
  const [chocolates, setChocolates] = useState([]);
  const [estates, setEstates] = useState([]);

  const fetchChocolates = async () => {
    const response = await fetch(`${SERVER_URL}/chocolates`);
    const data = await response.json();
    setChocolates(data)
  }

  const fetchEstates = async () => {
    const response = await fetch(`${SERVER_URL}/estates`);
    const data = await response.json();
    setEstates(data)
  }

  useEffect(() => {
    fetchChocolates()
    fetchEstates()
  }, []);

  const postChocolate = async (newChocolate) => {
    // create in db
    const response = await fetch(`${SERVER_URL}/chocolates`, {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(newChocolate),
    })
    const savedChocolate = await response.json();
    setChocolates([...chocolates, savedChocolate])
  }

  const deleteChocolate = (id) => {
    // delete from db
    fetch(`${SERVER_URL}/chocolates/${id}`, {
      method: "DELETE",
      headers: { "Content-Type": "application/json" },
    });

    // delete locally
    const newChocolates = chocolates.filter((chocolate) => chocolate.id !== id);
    setChocolates(newChocolates);
  };

  return (
    <>
      <h1>Single Origin Chocolate</h1>
      <p><em>A resource for chocoholics</em></p>
      <ChocolateForm 
        estates={estates} 
        postChocolate={postChocolate} />
      <ChocolateList
        chocolates={chocolates}
        deleteChocolate={deleteChocolate}
      />
    </>
  );
};

export default ChocolateContainer;
