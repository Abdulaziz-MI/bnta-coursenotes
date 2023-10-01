import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class LionTest {

    Lion lion;

    @BeforeEach
    void setUp(){
        lion = new Lion("Simba", false);
    }

    @Test
    void canMakeNoise__noArgument(){
        String expected = "ROAR!";
        String actual = lion.makeNoise();
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void canMakeNoise__withArgument(){
        String expected = "In my opinion, Toy Story was overrated.";
        String actual = lion.makeNoise("Toy Story was overrated");
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void canEat(){
        assertThat(lion.eat()).isEqualTo("Mmm, that was tasty!");
    }



}
