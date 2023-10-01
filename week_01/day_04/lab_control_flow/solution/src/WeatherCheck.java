public class WeatherCheck {

    public static void main(String[] args) {
        
        boolean currentlyRaining = false;
        int temperature = 28;
        
        if (temperature >= 25 && currentlyRaining == false){
            System.out.println("Wear shorts and sunscreen.");
        } else if (temperature < 10 && currentlyRaining == false) {
            System.out.println("Get the snow shoes out.");
        } else if (temperature < 10 && currentlyRaining == true) {
            System.out.println("Maybe just stay home...");
        } else if (currentlyRaining == true) {
            System.out.println("Take an umbrella");
        } else {
            System.out.println("Wear whatever you like!");
        }
    }
    
}
