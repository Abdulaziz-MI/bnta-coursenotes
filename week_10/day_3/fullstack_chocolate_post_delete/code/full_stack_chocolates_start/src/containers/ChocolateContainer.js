import { useState } from "react";
import ChocolateList from "../components/ChocolateList";
import ChocolateForm from "../components/ChocolateForm";

const ChocolateContainer = () => {

    const [chocolates, setChocolates] = useState([]);
    const [estates, setEstates] = useState([]);

    return(
        <>
            <ChocolateForm estates={estates}/>
            <ChocolateList chocolates={chocolates}/>
        </>
    )

}

export default ChocolateContainer;