import { useState, useEffect } from "react";
import Slider from "../components/Slider";
import ColourViewer from "../components/ColourViewer";

const ColourPickerContainer = () => {

    const [red, setRed] = useState(50);
    const [green, setGreen] = useState(50);
    const [blue, setBlue] = useState(50);
    const [rgbValue, setRgbValue] = useState("");

    useEffect(() => {
        console.log("calling useEffect");
        const red255 = Math.ceil(red * 2.55);
        const green255 = Math.ceil(green * 2.55);
        const blue255 = Math.ceil(blue * 2.55);
        setRgbValue(`rgb(${red255}, ${green255}, ${blue255})`);
    },[red, green, blue])

    return(
        <>
            <Slider colour="Red" value={red} onValueChange={setRed}/>
            <Slider colour="Green" value={green} onValueChange={setGreen} />
            <Slider colour="Blue" value={blue} onValueChange={setBlue} />
            <ColourViewer rgbValue={rgbValue}/>
        </>
    )

}

export default ColourPickerContainer;