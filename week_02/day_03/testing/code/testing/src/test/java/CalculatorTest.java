import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class CalculatorTest {

    private Calculator calculator;

    @BeforeEach
    public void setUp(){
        calculator = new Calculator();
    }

    @Test
    public void canAddNumbers(){
        int expected = 5;
        int actual = calculator.add(3, 2);
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void canSubtractNumbers(){
        int expected = 1;
        int actual = calculator.subtract(3,2);
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void fourDivisibleByTwo(){
        boolean actual = calculator.isDivisibleBy(4, 2);
        assertThat(actual).isTrue();
    }

    @Test
    public void twoNotDivisibleByFour(){
        boolean actual = calculator.isDivisibleBy(2, 4);
        assertThat(actual).isFalse();
    }

    @Test
    public void canDoubleIfDivisible(){
        int expected = 8;
        int actual = calculator.doubleIfDivisibleBy(4, 2);
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void canMultiply(){
        int expected = 8;
        int actual = calculator.multiply(4, 2);
        assertThat(actual).isEqualTo(expected);
    }
}
