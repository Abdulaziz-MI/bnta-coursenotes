import Chocolate from "./Chocolate";

const ChocolateList = ({ chocolates, deleteChocolate, setChocolateToUpdate }) => {
  const chocolateComponents = chocolates.map((chocolate) => {
    return (
      <Chocolate
        key={chocolate.id}
        chocolate={chocolate}
        deleteChocolate={deleteChocolate}
        setChocolateToUpdate={setChocolateToUpdate}
      />
    );
  });

  return (
    <>
      <h2>Chocolates</h2>
      <hr />
      {chocolateComponents}
    </>
  );
};

export default ChocolateList;
