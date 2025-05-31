package gam.provider;

import gam.provider.base.GameProvider;

public final class NewgameProvider extends GameProvider {
    public int startNewGame() {
        initScene(null); // default config for scene
        initPlayer(null); // default config for player character
        return 9; // sentinel for new game
    }
}