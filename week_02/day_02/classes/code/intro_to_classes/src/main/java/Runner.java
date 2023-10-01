public class Runner {

    public static void main(String[] args) {

        Person person = new Person("Colin", "Bathgate");
        Person person2 = new Person("Anna", "Glasgow");

//        System.out.println(person.name);
//        System.out.println(person2.name);

//        person.greet();
//        person2.greet();

//        person.greet("afternoon");
//        person2.greet("evening");

//        String personBio = person.generateBio();
//
//        System.out.println(personBio);

//        person.setName("Iain");
//
//        System.out.println(person.getName());

//        Person.staticMethodExample();

        Person.printSharedValue();

        person.incrementSharedValue();

        Person.printSharedValue();

        person2.incrementSharedValue();

        Person.printSharedValue();
    }

}
