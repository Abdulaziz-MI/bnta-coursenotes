package ExceptionsDemo;

public class ExceptionRunner {

    public static void main(String[] args) {

        ExceptionPractice exceptionPractice = new ExceptionPractice();

        try {
            exceptionPractice.checkEven(6);
        } catch (Exception exception){
            System.out.println(exception.getMessage());
            exception.printStackTrace();
        }

        exceptionPractice.addToList(5);



    }

}
