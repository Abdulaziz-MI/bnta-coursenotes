public class Booking {

    private String name;
    private int weekNumber;
    private Weekday weekday;

    public Booking(String name, int weekNumber, Weekday weekday){
        this.name = name;
        this.weekNumber = weekNumber;
        this.weekday = weekday;
    }

    public Weekday getWeekday(){
        return this.weekday;
    }

}
