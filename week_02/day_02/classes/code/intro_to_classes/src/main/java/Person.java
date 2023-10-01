public class Person {

    private String name;
    private String town;
    private int age;
    private static int sharedValue = 0;

    public Person(String name, String town) {
        this.name = name;
        this.town = town;
        this.age = 0;
    }

    public void greet(String timeOfDay){
        System.out.println("Good " + timeOfDay + "!");
    }

    public String generateBio(){
        return "My name is " + this.name + " and I live in " + this.town + ".";
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTown() {
        return this.town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public static void staticMethodExample(){
        System.out.println("I'm a static method in Person!");
    }

    public void incrementSharedValue(){
        sharedValue += 1;
    }

    public static void printSharedValue(){
        System.out.println("sharedValue is currently " + sharedValue);
    }
}
