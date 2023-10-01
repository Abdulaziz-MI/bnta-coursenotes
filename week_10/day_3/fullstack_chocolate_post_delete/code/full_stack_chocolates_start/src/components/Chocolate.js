const Chocolate = ({chocolate}) => {

    return(
        <div className="chocolate">
            <h4>{chocolate.name}</h4>
            <p>Estate: {chocolate.estate.name}</p>
            <p>Cocoa %: {chocolate.cocoaPercentage}</p>
            <button>Delete</button>
        </div>
    )

}

export default Chocolate;