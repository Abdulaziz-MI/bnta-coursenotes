console.log("js loaded");

const pokemonList = document.querySelector("#pokemon-list");

const loadPokemonData = () => {

    let pokemonNames = [];

    const allPromises = [];

    for (let i = 0; i < 13; i++) {
        allPromises.push(
            fetch(`https://pokeapi.co/api/v2/pokemon?offset=${100 * i}&limit=100`)
                .then(response => response.json())
        )
    }

    Promise.all(allPromises)
        .then((allResponses) => {
            const allPokemon = allResponses.map(response => response.results).flat()
            pokemonNames = allPokemon.map(pokemon => pokemon.name)
        })

    return pokemonNames;
}

const addPokemonToDOM = (pokemon) => {
    pokemon.forEach(pokemonName => {
        const nameElement = document.createElement("h3")
        nameElement.innerText = pokemonName;
        const pokemonElement = document.createElement("div");
        pokemonElement.appendChild(nameElement);
        pokemonList.appendChild(pokemonElement);
    })
    
}

const loadedData = loadPokemonData();
addPokemonToDOM(loadedData);