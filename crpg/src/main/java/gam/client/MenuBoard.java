package gam.client;

import gam.model.PlayerCharacter;
import gam.util.IOUtil;

public class MenuBoard {
    public void display(PlayerCharacter player) {
        IOUtil.display(String.format( "%s, you are in World A, Ground %d%d. You are facing due North.%n", player.getName(), player.getScene().getNsIndex(), player.getScene().getEwIndex()));
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
