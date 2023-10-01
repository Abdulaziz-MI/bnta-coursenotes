const Film = ({film}) => {

    const castMembers = film.roles.filter(role => {
        return role.voice
    }).map(role => {
        return <li key={role.id}>{role.voice}</li>
    })

    return(
        <>
            <h2>{film.title}</h2>
            <p>Release date: {film.released}</p>
            <ul>
                {castMembers}
            </ul>
        </>
    )

}

export default Film;