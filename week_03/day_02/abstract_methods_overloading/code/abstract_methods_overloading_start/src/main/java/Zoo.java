import java.util.ArrayList;

public class Zoo {

    private ArrayList<Animal> animals;

    public Zoo() {
        this.animals = new ArrayList<>();
    }

    public int countAnimals(){
        return this.animals.size();
    }

    public void addAnimal(Animal animal){
        this.animals.add(animal);
    }
}
