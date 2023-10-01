import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ZooTest {

    Zoo zoo;

    @BeforeEach
    void setUp(){
        zoo = new Zoo();
    }

    @Test
    void canCountAnimals(){
        assertThat(zoo.countAnimals()).isEqualTo(0);
    }

    @Test
    void canAddAnimal(){
        Animal animal = new Lion("Simba", false);
        zoo.addAnimal(animal);
        assertThat(zoo.countAnimals()).isEqualTo(1);
    }

    @Test
    void canAddMultipleAnimals(){
        Lion lion = new Lion("Scar", true);
        Parrot parrot = new Parrot("Zazu");
        zoo.addAnimal(lion);
        zoo.addAnimal(parrot);
        assertThat(zoo.countAnimals()).isEqualTo(2);
    }

}
