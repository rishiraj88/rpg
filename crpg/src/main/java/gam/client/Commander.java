package gam.client;

import gam.config.PlayerConfig;
import gam.util.IOUtil;

import java.io.IOException;

public class Commander {
    private volatile GameClient gameClient;

    void init(GameClient gameClient, PlayerConfig playerConfig) {
        // config, if required
        this.gameClient = gameClient;
        respondToCommands(playerConfig);
    }

    private void respondToCommands(PlayerConfig playerConfig) {
        while (true) {
            try { //WASD keys for gaming
                char c = (char) System.in.readNBytes(1)[0];
                switch (c) {
                    case 'W', 'w' -> playerConfig.moveNorthSouthAndEastWest((short) 1, (short) 0);
                    case 'D', 'd' -> playerConfig.moveNorthSouthAndEastWest((short) 0, (short) 1);
                    case 'S', 's' -> playerConfig.moveNorthSouthAndEastWest((short) -1, (short) 0);
                    case 'A', 'a' -> playerConfig.moveNorthSouthAndEastWest((short) 0, (short) -1);
                    case 'F', 'f' -> playerConfig.fight();
                    case 'X', 'x' -> gameClient.getSavegameProvider().saveGame(playerConfig);
                    case '\n' -> {
                    }
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
