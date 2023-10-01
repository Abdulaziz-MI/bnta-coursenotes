import { useEffect, useState } from "react";
import { getGuesses, getStatus, postGame, patchGuess } from "./api/api-game";
import { Alert, Fade, Grid, LinearProgress, Snackbar } from "@mui/material";
import EmptyState from "./components/EmptyState";
import GameState from "./components/GameState";
import styled from "@emotion/styled";

const AppContainer = styled.div`
  height: 100vh;
  display: flex;
  justify-content: center;
  align-items: center;
`;
function App() {
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState("");

  const [word, setWord] = useState("");
  const [guessedLetters, setGuessedLetters] = useState([]);

  const [message, setMessage] = useState("");

  const guesses = async () => {
    setLoading(true);
    setError("");
    try {
      const response = await getGuesses();
      setGuessedLetters(response.letters);
    } catch (err) {
      setError("Error: Could not connect to server");
    }
    setLoading(false);
  };

  const status = async () => {
    setLoading(true);
    setError("");
    try {
      const response = await getStatus();
      if (response.wordState) {
        setWord(response.wordState);
        guesses();
        setMessage(response.message);
      }
    } catch (err) {
      setError("Error: Could not connect to server");
    }
    setLoading(false);
  };

  const start = async () => {
    setLoading(true);
    setError("");
    try {
      const response = await postGame();
      setWord(response.wordState);
      setGuessedLetters([]);
      setMessage(response.message);
    } catch (err) {
      setError("Error: Could not connect to server");
    }
    setLoading(false);
  };

  const submitGuess = async (guess) => {
    setLoading(true);
    setError("");
    try {
      const response = await patchGuess(guess);
      setWord(response.wordState);
      setMessage(response.message);

      guessedLetters.push(guess);
      setGuessedLetters(guessedLetters);
    } catch (err) {
      setError("Error: Could not connect to server");
    }
    setLoading(false);
  };

  const handleSnackClose = () => {
    setMessage("");
  };

  useEffect(() => {
    status();
  }, []);

  const renderGame = () => {
    if (error !== "") {
      return (
        <EmptyState
          title={error}
          action={start}
          actionText={"Retry"}
          iconText={"🛑"}
        />
      );
    }
    if (word === "") {
      return (
        <EmptyState
          title={"Guessing Game"}
          action={start}
          actionText={"Go"}
          iconText={"🤔"}
        />
      );
    }
    if (!word.includes("*")) {
      return (
        <EmptyState
          title={`The word was ${word}`}
          subTitle={`You guessed the word in ${guessedLetters.length} tries`}
          action={start}
          actionText={"Play again"}
          iconText={"🎉"}
        />
      );
    }

    return (
      <GameState
        title={word}
        subTitle={"Guess a letter in the word!"}
        submitGuess={submitGuess}
        guessedLetters={guessedLetters}
      />
    );
  };

  return (
    <AppContainer>
      <Grid container justifyContent="center">
        <Grid item xs={8}>
          <Fade in={loading}>
            <LinearProgress />
          </Fade>
          {renderGame()}
        </Grid>
      </Grid>

      <Snackbar
        open={message !== ""}
        autoHideDuration={4000}
        onClose={handleSnackClose}
      >
        <Alert severity={message.includes("not") ? "error" : "info"}>
          {message}
        </Alert>
      </Snackbar>
    </AppContainer>
  );
}

export default App;
