const Chocolate = ({ chocolate, deleteChocolate, setChocolateToUpdate }) => {

  const handleEditClick = () => {
    setChocolateToUpdate(chocolate);
    const chocolateForm = document.querySelector("form");
    chocolateForm.scrollIntoView( {behavior: "smooth"} )
  }

  return (
    <div className="chocolate">
      <h3>{chocolate.name}</h3>
      <p>Estate: {chocolate.estate.name}</p>
      <p>Cocoa % {chocolate.cocoaPercentage}</p>

      <div className="chocolate-buttons">
        <button onClick={handleEditClick}>Edit</button>

        <button onClick={() => deleteChocolate(chocolate.id)}>Delete</button>
      </div>
    </div>
  );
};

export default Chocolate;
