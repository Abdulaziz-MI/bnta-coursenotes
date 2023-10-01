public enum Weekday {

    MONDAY("Monntag", 1),
    TUESDAY("Dienstag", 2),
    WEDNESDAY("Mittwoch", 3),
    THURSDAY("Donnerstag", 4),
    FRIDAY("Freitag", 5),
    SATURDAY("Samstag", 6),
    SUNDAY("Sonntag", 7);

    private final String german;
    private final int dayNumber;

    Weekday(String german, int dayNumber){
        this.german = german;
        this.dayNumber =dayNumber;
    }

    public String getGerman(){
        return this.german;
    }

    public int getDayNumber(){
        return this.dayNumber;
    }

}
