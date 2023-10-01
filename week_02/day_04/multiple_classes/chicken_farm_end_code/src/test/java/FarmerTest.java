import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class FarmerTest {

    Farmer farmer;
    Farm farm;
    Chicken ginger;
    Chicken edwina;
    Chicken bunty;

    @BeforeEach
    public void setUp(){
        farmer = new Farmer("Helen");
        farm = new Farm("Tweedsdale");
        ginger = new Chicken("Ginger", 4);
        edwina = new Chicken("Edwina", 1);
        bunty = new Chicken("Bunty", 10);
        farm.addChicken(ginger);
        farm.addChicken(edwina);
        farm.addChicken(bunty);
    }

    @Test
    public void hasName(){
        assertThat(farmer.getName()).isEqualTo("Helen");
    }

    @Test
    public void collectedEggsStartAtZero(){
        assertThat(farmer.getCollectedEggs()).isEqualTo(0);
    }

    @Test
    public void farmerCanCollectEggs(){
        farmer.collectEggs(farm);
        assertThat(farmer.getCollectedEggs()).isEqualTo(15);
    }

    @Test
    public void farmerCanAssignEggsForSale(){
        farmer.collectEggs(farm);
        farmer.assignEggsForSale(farm);
        assertThat(farm.getEggsForSale()).isEqualTo(13);
        assertThat(farmer.getCollectedEggs()).isEqualTo(2);
    }

}
