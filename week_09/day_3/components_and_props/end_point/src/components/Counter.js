const Counter = ({title, count, onButtonClick}) => {

    const handleAddParticipantClick = () => {
        onButtonClick(count + 1);
    }

    const handleRemoveParticipantClick = () => {
        if (count > 0) {
            onButtonClick(count - 1);
        }
    }

    return(
        <>
            <h3>{title} Sign-Ups</h3>
            <p>Current total: {count}</p>
            <button onClick={handleAddParticipantClick}>Add Participant</button>
            <button onClick={handleRemoveParticipantClick}>Remove Participant</button>
            <hr />
        </>
    )

}

export default Counter;