package ExceptionsDemo;

import java.util.ArrayList;

public class ExceptionPractice {

    private ArrayList<Integer> numberList;

    public ExceptionPractice(){
        this.numberList = new ArrayList<>();
    }

    public boolean checkEven(int number) throws Exception{
        if (number % 2 == 0){
            return true;
        }
        throw new Exception("Number is not even.");
    }

    public void addToList(Integer number){
        this.numberList.add(number);
    }

}
