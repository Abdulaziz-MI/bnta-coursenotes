import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ArtistTest {

    Artist artist;

    @BeforeEach
    public void setUp(){
        artist = new Artist("Vincent van Gogh");
    }

//    no idea how getName() is called
    @Test
    public void hasName(){
        assertThat(artist.getName()).isEqualTo("Vincent van Gogh");
    }


}
