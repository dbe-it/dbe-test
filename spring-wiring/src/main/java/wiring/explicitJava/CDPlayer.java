package wiring.explicitJava;

import wiring.auto.CompactDisc;


public class CDPlayer implements Player{

    private CompactDisc compactDisc;

    public CDPlayer(CompactDisc compactDisc){
        this.compactDisc = compactDisc;
    }

    @Override
    public CompactDisc getCompactDisc() {
        return compactDisc;
    }
}
