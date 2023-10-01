public class GuessingGame {

    public static void main(String[] args) {

        int secretNumber = 5;
        int guess = 5;

        if (guess == secretNumber){
            System.out.println("Congratulations, you guessed correctly!");
        } else if (guess > secretNumber) {
            System.out.println("Unlucky, your guess is too high.");
        } else {
            System.out.println("Unlucky, your guess is too low.");
        }

    }

}
