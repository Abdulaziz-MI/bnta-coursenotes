const ColourViewer = ({rgbValue}) => {

    return(
        <h1 style={{backgroundColor: rgbValue}}>{rgbValue}</h1>
    )

}

export default ColourViewer;