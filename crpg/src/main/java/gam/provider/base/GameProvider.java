package gam.provider.base;

import gam.config.PlayerConfig;

public abstract class GameProvider {

    public void initScene(String gameMapInfo) {
        //Objects and stories may be configured for scene
        //?? gameMapInfo to load a custom ground (location)
        System.out.println("Welcome to the rampage! Scene will be presented soon...");
    }

    public void initPlayer(PlayerConfig playerConfig, String playerName) {
        // Config with respect to player may be pulled in
        if (null == playerConfig) {
           playerConfig = PlayerConfig.getConfig();
           playerConfig.setCustomPlayerName(playerName);
        } else{ ; //?? to load custom config out of configuration file
        }
    }
}