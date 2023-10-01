const serverUrl = "http://localhost:8080";

export const postGame = async () => {
  try {
    const response = await fetch(`${serverUrl}/games`, {
      method: "POST",
    });
    return response.json();
  } catch (err) {
    return console.log(err);
  }
};

export const patchGuess = async (body) => {
  try {
    const response = await fetch(`${serverUrl}/games`, {
      headers: { "Content-Type": "application/json" },
      method: "PATCH",
      body: JSON.stringify(body),
    });
    return response.json();
  } catch (err) {
    return console.log(err);
  }
};

export const getStatus = async () => {
  try {
    const response = await fetch(`${serverUrl}/games`);
    return response.json();
  } catch (err) {
    return console.log(err);
  }
};

export const getGuesses = async () => {
  try {
    const response = await fetch(`${serverUrl}/games/guessed`);
    return response.json();
  } catch (err) {
    return console.log(err);
  }
};
