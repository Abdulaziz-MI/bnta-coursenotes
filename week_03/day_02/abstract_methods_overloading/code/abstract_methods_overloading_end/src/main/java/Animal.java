public abstract class Animal {

    protected String name;

    public Animal(String name) {
        this.name = name;
    }

    public abstract String makeNoise();

    public String eat(){
        return "Mmm, that was tasty!";
    }
}
