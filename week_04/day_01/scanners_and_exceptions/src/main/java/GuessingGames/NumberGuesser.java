package GuessingGames;

import java.util.Random;

public class NumberGuesser implements IPlay{

    private boolean running;
    private int secretNumber;

    public NumberGuesser(){
        this.running = false;
    }

    public boolean isRunning(){
        return this.running;
    }

    public String start(){
        Random rand = new Random();
        this.secretNumber = rand.nextInt(1, 101);
        this.running = true;
        return "Welcome to the number guessing game!";
    }

    public String promptForGuess(){
        return "Enter a number between 1 and 100";
    }

    public String processGuess(String guess) throws NumberFormatException{

        int parsedGuess = Integer.parseInt(guess);

        if (this.secretNumber == parsedGuess){
            this.running = false;
            return "Congratulations, you guessed correctly!";
        }
        if (this.secretNumber > parsedGuess){
            return "Sorry, your guess was too low.";
        }
        return "Sorry, your guess was too high.";

    }

}
