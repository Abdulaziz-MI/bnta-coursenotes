public class Parrot extends Bird{

    public Parrot(String name){
        super(name);
    }

    @Override
    public String makeNoise(){
        return "Squawk!";
    }

    public String sayName(){
        return "My name is " + this.name + ".";
    }

}
