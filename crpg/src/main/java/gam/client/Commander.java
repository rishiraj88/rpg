package gam.client;

import gam.model.PlayerCharacter;
import gam.util.IOUtil;

import java.io.IOException;

public class Commander {
    private volatile GameClient gameClient;

    void init(GameClient gameClient, PlayerCharacter playerCharacter) {
        // config, if required
        this.gameClient = gameClient;
        respondToCommands(playerCharacter);
    }

    private void respondToCommands(PlayerCharacter player) {
        while (true) {
            try { //WASD keys for gaming
                char c = (char) System.in.readNBytes(1)[0];
                switch (c) {
                    case 'W', 'w' -> player.moveNorthSouthAndEastWest((short) 1, (short) 0);
                    case 'D', 'd' -> player.moveNorthSouthAndEastWest((short) 0, (short) 1);
                    case 'S', 's' -> player.moveNorthSouthAndEastWest((short) -1, (short) 0);
                    case 'A', 'a' -> player.moveNorthSouthAndEastWest((short) 0, (short) -1);
                    case 'F', 'f' -> player.fight();
                    case 'X', 'x' -> gameClient.getSavegameProvider().saveGame(player);
                    case '\n' -> {}
                    case 'q' -> System.exit(0);
                    default ->
                            IOUtil.display("invalid command. Please press W, A, S, D or F to play. Press X to save game.");
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
