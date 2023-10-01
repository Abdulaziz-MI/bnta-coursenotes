import { useState, useEffect } from "react";
import FilmList from "../components/FilmList";

const FilmContainer = () => {

    const [films, setFilms] = useState(null);

    const loadData = async () => {
        const response = await fetch("https://raw.githubusercontent.com/annahndr/annahndr.github.io/master/aardman_data/aardman.json");
        const jsonData = await response.json();
        setFilms(jsonData);
    }

    useEffect(() => {
        console.log("loading data");
        loadData();
    },[]);

    return(
        <>
            <h1>Aardman Animations</h1>
            { films ? <FilmList films={films}/> : <p>loading...</p> }
        </>
    )

}

export default FilmContainer;