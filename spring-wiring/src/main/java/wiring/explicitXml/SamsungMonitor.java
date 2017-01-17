package wiring.explicitXml;

import org.springframework.stereotype.Component;

import wiring.explicitJava.Player;


@Component
public class SamsungMonitor implements Monitor {

    private Player player;

    public SamsungMonitor(Player player){
        this.player = player;
    }

    @Override
    public Player connect(Player player) {
        this.player = player;
        return this.player;
    }

    @Override
    public Player getConnectedPlayer() {
        return player;
    }
}
