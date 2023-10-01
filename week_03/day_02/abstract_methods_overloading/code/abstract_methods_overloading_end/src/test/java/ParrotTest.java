import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ParrotTest {

    Parrot parrot;

    @BeforeEach
    void setUp(){
        parrot = new Parrot("Zazu");
    }

    @Test
    void canMakeNoise(){
        assertThat(parrot.makeNoise()).isEqualTo("Squawk!");
    }

    @Test
    void canEat(){
        assertThat(parrot.eat()).isEqualTo("Mmm, that was tasty!");
    }

    @Test
    void canLayEgg(){
        assertThat(parrot.layEgg()).isEqualTo("I laid an egg!");
    }

}
