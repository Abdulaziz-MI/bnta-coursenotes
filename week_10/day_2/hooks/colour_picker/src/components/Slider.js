const Slider = ({colour, value, onValueChange}) => {

    const handleValueChange = (event) => {
        onValueChange(parseInt(event.target.value));
    }

    return(
        <>
            <label htmlFor={colour}>{colour}</label>
            <input 
                type="range" 
                id={colour}
                value={value}
                onChange={handleValueChange}
            />
            <p>{colour}: {value}</p>
        </>
    )

}

export default Slider;