import Film from "./Film";

const FilmList = ({films}) => {

    const mappedFilms = films.map(film => {
        return <Film film={film} key={film.id}/>
    })

    return(
        <>
            {mappedFilms}
        </>
    )

}

export default FilmList;