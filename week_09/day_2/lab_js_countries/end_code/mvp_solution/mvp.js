// our "state" variable
let APIdata;
const countriesList = document.getElementById("countriesList");
const filterCountriesForm = document.getElementById("filterCountriesForm");

filterCountriesForm.addEventListener("submit", (e)=>{
    e.preventDefault();
    countriesList.innerHTML = "";
    filterCountries(e.target[0].value.toLowerCase())
})

async function getAllCountries() {
    const response = await fetch("https://restcountries.com/v3.1/all");
    const data = await response.json();
    return data;
}

function mapCountries(countriesArray) {
    countriesList.innerHTML = "";
    for (country of countriesArray) {
        let countryElement = document.createElement("li");
        countryElement.classList.add("country");
        addCountryInformationToElement(countryElement);
        countriesList.appendChild(countryElement);
    }
}

function addCountryInformationToElement(li_countryElement) {
    let countryNameElement = document.createElement("h2");
    countryNameElement.innerText = country.name.common;
    li_countryElement.appendChild(countryNameElement);

    let countryPopulationElement = document.createElement("p");
    countryPopulationElement.innerText = `population: ${country.population}`;
    li_countryElement.appendChild(countryPopulationElement);
}

async function filterCountries(filterParameter) {
    let filteredCountriesList = await APIdata.filter(
        country => country.name.common.toLowerCase().includes(filterParameter)
    );
    mapCountries(filteredCountriesList);
}

async function setUp() {
    APIdata = await getAllCountries();
    console.log(APIdata);
    mapCountries(APIdata);
}

setUp();