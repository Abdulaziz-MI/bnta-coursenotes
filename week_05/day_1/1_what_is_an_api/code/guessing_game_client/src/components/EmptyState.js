import { Button, Card, CardContent, CardHeader } from "@mui/material";

import styled from "@emotion/styled";

const StyledCard = styled(Card)`
  height: 250px;
  justify-content: space-around;
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

const StyledButton = styled(Button)`
  max-width: 150px;
  margin: 0 auto;
`;

const EmptyState = ({ title, subTitle = "", actionText, action, iconText }) => {
  return (
    <StyledCard>
      <div>
        <StyledCardContent>{iconText}</StyledCardContent>
        <StyledCardHeader title={title} subheader={subTitle} />
      </div>
      <StyledButton variant="contained" onClick={action}>
        {actionText}
      </StyledButton>
    </StyledCard>
  );
};

export default EmptyState;
