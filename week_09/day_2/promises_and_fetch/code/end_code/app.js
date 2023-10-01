const fetchDogImage = async () => { 
  const response = await fetch("https://dog.ceo/api/breeds/image/random");
  const jsonData = await response.json();
  document.querySelector("img").src = jsonData.message

  const breedResponse = await fetch("https://dog.ceo/api/breed/corgi/images");
  const breedJsonData = await breedResponse.json();
  
  const imagesContainer = document.createElement("div");

  breedJsonData.message.forEach((url) => {
    const dogImage = document.createElement("img");
    dogImage.src = url;
    imagesContainer.appendChild(dogImage);
  })

  console.log(breedJsonData)

  document.querySelector("body").appendChild(imagesContainer);
}

document.querySelector("button").addEventListener("click", fetchDogImage);