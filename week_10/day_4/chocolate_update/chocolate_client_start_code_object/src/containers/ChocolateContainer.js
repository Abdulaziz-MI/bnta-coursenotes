import { useEffect, useState } from "react";
import ChocolateList from "../components/ChocolateList";
import ChocolateForm from "../components/ChocolateForm";

const SERVER_URL = "http://localhost:8080";

const ChocolateContainer = () => {
  const [chocolates, setChocolates] = useState([]);
  const [estates, setEstates] = useState([]);
  const [error, setError] = useState("");

  useEffect(() => {
    fetch(`${SERVER_URL}/chocolates`)
      .then((response) => response.json())
      .then((response) => setChocolates(response))
      .catch((err) => setError(err.message));

    fetch(`${SERVER_URL}/estates`)
      .then((response) => response.json())
      .then((response) => setEstates(response))
      .catch((err) => setError(err.message));
  }, []);

  const postChocolate = (newChocolate) => {
    // create in db
    fetch(`${SERVER_URL}/chocolates`, {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(newChocolate),
    })
      .then((response) => response.json())
      .then((response) => {
        setChocolates([...chocolates, response]);
      });
  };

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

  // Display an error to the user if we cannot fetch chocolates
  if (error !== "") return <p>Error! {error}</p>;
  // Otherwise display the form and chocolates
  return (
    <>
      <ChocolateForm estates={estates} postChocolate={postChocolate} />
      <ChocolateList
        chocolates={chocolates}
        deleteChocolate={deleteChocolate}
      />
    </>
  );
};

export default ChocolateContainer;
