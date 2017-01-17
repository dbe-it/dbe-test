package wiring.auto;

import org.springframework.stereotype.Component;


@Component
public class Stilbruch implements CompactDisc {

    String artist = "Stilbruch.tv";
    String title = "Nimm mich mit";

    @Override
    public String play(){
        return "Playing '" + title + "' by " + artist;
    }
}
