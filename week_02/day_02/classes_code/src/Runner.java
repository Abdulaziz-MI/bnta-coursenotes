import java.util.ArrayList;

public class Runner {

    public static void main(String[] args) {

        Person person = new Person("Sandra", "Morelia");
        Person person2 = new Person("Rohaib", "London");

        String person1Greeting = person.greet("morning");
        String person2Greeting = person2.greet("evening");

        System.out.println(person1Greeting);

        person.setName("Colin");
        System.out.println(person.getName());

    }

}
