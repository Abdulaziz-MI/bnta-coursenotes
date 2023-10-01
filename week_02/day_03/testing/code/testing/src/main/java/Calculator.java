public class Calculator {

    public int add(int a, int b){
        return a + b;
    }

    public int subtract(int a, int b){
        return a - b;
    }

    public int multiply(int a, int b){
        return a * b;
    }

    public boolean isDivisibleBy(int a, int b){
        return a % b == 0;
    }

    public int doubleIfDivisibleBy(int a, int b){
        if (isDivisibleBy(a, b)){
            return multiply(a, 2);
        } else {
            return a;
        }
    }

}
