package wiring.explicitXml;

import wiring.explicitJava.Player;


public interface Monitor {

    public Player connect(Player player);

    public Player getConnectedPlayer();
}
