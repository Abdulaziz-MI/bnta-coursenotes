import java.util.ArrayList;
import java.util.List;

public class Loops {

    public static void main(String[] args){

        List<String> colours = new ArrayList<>();
        colours.add("red");
        colours.add("silver");
        colours.add("green");
        colours.add("raspberry");

//        System.out.println(colours);
//        System.out.println(colours.get(0));
//        System.out.println(colours.get(1));

        
//        ENHANCED (or FOR EACH) FOR LOOP
        System.out.println("----Using enhanced for loop----");

        for (String colour : colours){
            System.out.println(colour.toUpperCase());
        }

        List<String> filteredColours = new ArrayList<>();

        for (String colour : colours){
            if (colour.charAt(0) == 'r'){
                filteredColours.add(colour);
            }
        }

        System.out.println("filteredColours: " + filteredColours);


//        CLASSIC FOR LOOP
        System.out.println("---Using Classic For Loop---");

        for (int i = 0; i < colours.size(); i++){
            String colour = colours.get(i);
            System.out.println(colour);
        }

        List<Integer> numbers = new ArrayList<>();

        for (int i = 1; i <= 10; i+=2){ // i = i + 2
            numbers.add(i);
        }

        System.out.println("numbers: " + numbers);

//        BREAK
        System.out.println("----break----");
        for (int i = 0; i < 10; i++){
            if (i == 4){
                break;
            }
            System.out.println(i);
        }

//        CONTINUE
        System.out.println("----continue----");
        for (int i = 0; i < 10; i++){
            if (i == 4) {
                continue;
            }
            System.out.println(i);
        }


    }

}
