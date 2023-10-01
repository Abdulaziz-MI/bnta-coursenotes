package GuessingGames;

import java.util.ArrayList;

public class WordGuesser implements IPlay {

    private boolean running;
    private String mysteryWord;
    private ArrayList<String> guessedLetters;

    public WordGuesser() {
        // Initialise a new game to not be running and have an empty list of guessed
        // letters
        this.running = false;
        this.guessedLetters = new ArrayList<>();
    }

    public boolean isRunning(){
        return this.running;
    }

    public String start(){
        // Flip the running property to be true, generate a mystery word and return a
        // welcome message.
        // In the future we could update this method to select a random word from a list
        this.running = true;
        this.mysteryWord = "hello";
        return "Welcome to WordGuesser!";
    }

    public String promptForGuess(){
        // Return the message to prompt the user for a guess
        return "Guess a letter:";
    }

    public String processGuess(String guess) throws Exception{

        // Convert the guess to lower case
        guess = guess.toLowerCase();

        // If the player has already guessed the letter throw an exception
        if (this.guessedLetters.contains(guess)){
            throw new Exception();
        }

        // Add the guess to the list of previous guesses
        this.guessedLetters.add(guess);

        // We want to show the player how much of the word they have guessed so far...

        // runningResult will be the variable we use. We'll initialise it to be the
        // same as the mystery word.
        String runningResult = this.mysteryWord;

        // We need to replace the characters in runningResult with * if they haven't
        // been guessed yet. We'll loop through the letters in the mystery word
        for (Character letter : this.mysteryWord.toCharArray()) {
            // If the letter hasn't been guessed we replace the corresponding
            // letter in runningResult with *
            if (!this.guessedLetters.contains(letter.toString())){
                runningResult = runningResult.replace(letter, '*');
            }
        }

        // If we don't have any * in runningResult then all the letters have been
        // guessed and the game is over
        if (!runningResult.contains("*")){
            this.running = false;
        }

        // If the guess isn't in the mystery word then we tell the user and let them
        // know the current state of the word
        if (!this.mysteryWord.contains(guess)){
            return String.format("%s isn't in the mystery word. So far you've revealed: %s",
                    guess,
                    runningResult);
        }

        // When we get to this point the only remaining possibility is that the user
        // guessed a letter which is included in the word, so we show them the updated
        // state of the word
        return String.format("%s is in the mystery word! So far you've revealed: %s",
                guess,
                runningResult);

    }

}

