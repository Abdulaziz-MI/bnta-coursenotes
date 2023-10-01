import java.util.ArrayList;

public class Artist {

    private String name;

    public Artist(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public String talk(){
        return "Hello I'm an artist";
    }

}
