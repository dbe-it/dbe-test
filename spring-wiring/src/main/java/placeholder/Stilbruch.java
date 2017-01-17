package placeholder;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
public class Stilbruch implements CompactDisc {

    String artist;
    String title;

    public Stilbruch(@Value("${disc.title}") String title, @Value("${disc.artist}") String artist) {

        this.title = title;
        this.artist = artist;
    }

    @Override
    public String play() {
        return "Playing '" + title + "' by " + artist;
    }
}
