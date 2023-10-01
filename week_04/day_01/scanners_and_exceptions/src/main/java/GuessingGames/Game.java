package GuessingGames;

import java.util.Scanner;

public class Game {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        IPlay game = new WordGuesser();

        System.out.println(game.start());

        while (game.isRunning()){
            System.out.println(game.promptForGuess());

            String guess = scanner.nextLine();

            try {
                System.out.println(game.processGuess(guess));
            } catch (Exception exception){
                System.out.println("That's not a valid guess, try again.");
            }
        }

        System.out.println("Well done, thanks for playing!");

    }

}
