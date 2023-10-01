const ChocolateForm = () => {

    return(
        <form id="chocolate-form">
            <h3>Add a new chocolate</h3>

            <label htmlFor="chocolate-name">Chocolate Name:</label>
            <input
                id="chocolate-name"
                name="name"
                type="text"
                placeholder="enter chocolate name" 
            />

            <label htmlFor="cocoa-percentage">Cocoa Percentage:</label>
            <input
                id="cocoa-percentage"
                name="cocoaPercentage"
                type="number"
                min={1}
                max={100}
            />

            <label htmlFor="estate">Estate</label>
            <select 
                id="estate" 
                name="estate"
                defaultValue="select-estate"
            >
                <option disabled-value="select-estate">Choose an estate</option>
            </select>

            <input type="submit" value="Add Chocolate"/>          
        </form>
    )

}

export default ChocolateForm;