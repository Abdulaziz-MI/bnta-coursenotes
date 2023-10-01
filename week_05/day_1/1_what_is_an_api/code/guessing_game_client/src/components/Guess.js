import styled from "@emotion/styled";
import { Search } from "@mui/icons-material";
import { Fade, FormHelperText, IconButton, Input } from "@mui/material";
import { useState } from "react";

const GuessContainer = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  height: 80px;
`;

const StyledInput = styled(Input)`
  min-width: 30%;
`;

const Guess = ({ submitGuess, guessedLetters }) => {
  const [currentGuess, setCurrentGuess] = useState("");
  const [error, setError] = useState("");

  const handleChange = (e) => {
    if (e.target.value.length < 2) {
      setCurrentGuess(e.target.value);
    }
    if (
      guessedLetters.findIndex((letter) => letter === e.target.value) !== -1
    ) {
      return setError(`${e.target.value} has already been guessed!`);
    }

    setError("");
  };

  const submit = () => {
    if (currentGuess.length !== 1) {
      return setError("Guess cannot be blank!");
    }

    if (error === "") {
      submitGuess(currentGuess);
      setCurrentGuess("");
    }
  };

  return (
    <GuessContainer>
      <StyledInput
        type="text"
        value={currentGuess}
        onChange={handleChange}
        variant="standard"
        placeholder="Type a letter"
        error={error !== ""}
        endAdornment={
          <IconButton type="button" color="primary" onClick={submit}>
            <Search />
          </IconButton>
        }
      />
      <Fade in={error !== ""}>
        <FormHelperText error={true}>{error}</FormHelperText>
      </Fade>
    </GuessContainer>
  );
};

export default Guess;
