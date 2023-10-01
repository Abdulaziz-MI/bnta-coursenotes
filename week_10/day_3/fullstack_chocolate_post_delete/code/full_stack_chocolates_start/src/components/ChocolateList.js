import Chocolate from "./Chocolate";

const ChocolateList = ({chocolates}) => {

    const chocolateComponents = chocolates.map(chocolate => {
        return <Chocolate 
            key={chocolate.id} 
            chocolate={chocolate}
            />
    });

    return(
        <div id="chocolate-list">
            {chocolateComponents}
        </div>
    )
}

export default ChocolateList;