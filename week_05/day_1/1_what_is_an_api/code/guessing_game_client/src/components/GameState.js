import Guess from "./Guess";
import { Card, CardContent, CardHeader } from "@mui/material";
import styled from "@emotion/styled";

const StyledCard = styled(Card)`
  height: 250px;
  justify-content: center;
  display: flex;
  flex-direction: column;
  text-align: center;
`;

const StyledCardHeader = styled(CardHeader)`
  text-align: center;
`;

const StyledCardContent = styled(CardContent)`
  padding-bottom: 0;
  font-size: 3em;
`;

const GameState = ({ title, subTitle = "", submitGuess, guessedLetters }) => {
  return (
    <>
      <StyledCard>
        <StyledCardContent>👀</StyledCardContent>
        <StyledCardHeader title={title} subheader={subTitle} />
        <CardContent>
          <Guess submitGuess={submitGuess} guessedLetters={guessedLetters} />
        </CardContent>
      </StyledCard>
    </>
  );
};

export default GameState;
