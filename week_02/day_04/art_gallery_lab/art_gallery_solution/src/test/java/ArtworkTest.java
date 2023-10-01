import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ArtworkTest {

    Artist artist;
    Artwork artwork;

    @BeforeEach
    public void setUp(){
        artist = new Artist("Pablo Picasso");
        artwork = new Artwork("irises", artist, 50_000_000, 884);
    }

    @Test
    public void hasTitle(){
        assertThat(artwork.getTitle()).isEqualTo("irises");
    }

    @Test
    public void hasArtist(){
        assertThat(artwork.getArtist()).isEqualTo("Pablo Picasso");
    }

    @Test
    public void hasPrice(){
        assertThat(artwork.getPrice()).isEqualTo(50_000_000);
    }

}
