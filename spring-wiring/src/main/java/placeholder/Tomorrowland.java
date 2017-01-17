package placeholder;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
public class Tomorrowland implements CompactDisc{

    String artist;
    String title;

    public Tomorrowland(@Value("${title.sdfsdfsf}") String title, @Value("${artist.fsdfsdf}") String artist) {
        this.title = title;
        this.artist = artist;
    }

    @Override
    public String play() {
        return "Playing '" + title + "' by " + artist;
    }
}
