const Chocolate = ({ chocolate, deleteChocolate }) => {

  const handleChocolateDelete = () => {
    deleteChocolate(chocolate.id)
  }
  
  return (
    <div className="chocolate">
      <h3>{chocolate.name}</h3>
      <p>Estate: {chocolate.estate.name}</p>
      <p>Cocoa % {chocolate.cocoaPercentage}</p>

      <div className="chocolate-buttons">
        <button>Show</button>

        <button onClick={handleChocolateDelete}>Delete</button>
      </div>
    </div>
  );
};

export default Chocolate;
