package gam.provider;

import gam.provider.base.GameProvider;

public final class NewGameProvider extends GameProvider {
    public int startNewGame(String playerName) {
        initScene(null); // default config for scene
        initPlayer(null, playerName); // default config for player character
        return 9; // sentinel for new game
    }
}