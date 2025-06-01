package gam.client;

import gam.config.PlayerConfig;
import gam.util.IOUtil;

public class MenuBoard {
    public void display(PlayerConfig playerConfig ) {
        IOUtil.display(String.format( "%s, you are in World A, Ground %d%d. You are facing due North.%n", playerConfig.getPlayer().getName(), playerConfig.getPlayerPosition().getNsIndex(), playerConfig.getPlayerPosition().getEwIndex()));
        IOUtil.display("""
        W: Move forward
        S: Move backward
        A: Move left
        D: Move right
        F: Fight
        X: Save this game
        """);
    }
}
