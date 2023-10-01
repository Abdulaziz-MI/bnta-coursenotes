public class Person {

    private String name;
    private String town;
    private boolean completedCourse;

    public Person(String inputName, String inputTown){
        this.name = inputName;
        this.town = inputTown;
        this.completedCourse = false;
    }

    public String getName(){
        return this.name;
    }

    public void setName(String updatedName){
        this.name = updatedName;
    }

    public String greet(String timeOfDay){
        return "Good " + timeOfDay + ", my name is " + this.name + ".";
    }

    public void finishCourse(){
        this.completedCourse = true;
    }

}
