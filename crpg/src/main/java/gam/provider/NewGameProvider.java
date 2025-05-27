package gam.provider;

import gam.provider.base.GameProvider;

public final class NewGameProvider extends GameProvider {
    public  int startNewGame(String playerName) {
        initScene(null); // defaultGameConfig.sceneMap
        initPlayer(null, playerName); // defaultGameConfig.playerCharacter
        return 9; // sentinel for new game
    }

}