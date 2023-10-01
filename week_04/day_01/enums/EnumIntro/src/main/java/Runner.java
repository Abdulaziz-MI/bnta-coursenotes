public class Runner {

    public static void main(String[] args) {
        Booking booking = new Booking("Zsolt", 23, Weekday.MONDAY);

        System.out.println( booking.getWeekday() );

        for(Weekday weekday : Weekday.values()){
            System.out.println(weekday.getGerman());
        }

        Weekday today = Weekday.MONDAY;

        if(today.equals(Weekday.MONDAY)){
            System.out.println("Oh no, it's Monday :'( ");
        }
    }
}
