package GuessingGames;

public interface IPlay {

    boolean isRunning();
    String start();
    String promptForGuess();
    String processGuess(String guess) throws Exception;

}
