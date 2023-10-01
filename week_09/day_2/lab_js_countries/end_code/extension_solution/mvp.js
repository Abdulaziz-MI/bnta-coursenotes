// our "state" variable
let APIdata;
const countriesList = document.getElementById("countriesList");
const filterCountriesForm = document.getElementById("filterCountriesForm");

filterCountriesForm.addEventListener("submit", (e)=>{
    e.preventDefault();
    clearCountriesList();

    const loadingMessage = document.createElement("p");
    loadingMessage.innerText = "Getting filtered results...";
    countriesList.appendChild(loadingMessage);
    
    artificialLoad(() =>
        filterCountries(e.target[0].value.toLowerCase())
    );
})

// https://restcountries.com/v3.1/all
async function getAllCountries() {
    let fetchedData = await fetch("https://restcountries.com/v3.1/all");
    if (!fetchedData.ok) {
        throw new Error(`${fetchedData.status} ${fetchedData.statusText}`);
    } else {
        return await fetchedData.json();
    }
}

function mapCountries(countriesArray) {
    for (country of countriesArray) {
        let countryElement = document.createElement("li");
        countryElement.classList.add("country");
        addCountryInformationToElement(countryElement);
        countriesList.appendChild(countryElement);
    }
}

function addCountryInformationToElement(countryElement) {
    let countryNameElement = document.createElement("h2");
    countryNameElement.innerText = country.name.common;
    countryElement.appendChild(countryNameElement)

    let countryPopulationElement = document.createElement("p");
    countryPopulationElement.innerText = `population: ${country.population}`;
    countryElement.appendChild(countryPopulationElement)
}

function clearCountriesList() {
    countriesList.innerHTML = "";
}

async function filterCountries(filterParameter) {
    clearCountriesList();
    let filteredCountriesList = await APIdata.filter(country => country.name.common.toLowerCase().includes(filterParameter));
    if (filteredCountriesList == []) {
        let noCountryFound = document.createElement("h2");
        noCountryFound.innerHTML = `No such country found, please try a different search query<br/>(filters by name displayed)`;
        countriesList.appendChild(noCountryFound)
    } else {
        mapCountries(filteredCountriesList);
    }
}

async function setUp() {
    clearCountriesList();
    try {
        APIdata = await getAllCountries();
        mapCountries(APIdata);
    } catch (error) {
        let APIError = document.createElement("h2");
        APIError.innerHTML = error;
        countriesList.appendChild(APIError);
        console.log(e);
        return;
    }
    
}

// adds an artificial load-time of 1 second (to view the loading logic)
async function artificialLoad(providedFunction) {
    setTimeout(providedFunction, 1000);
}

artificialLoad(setUp);