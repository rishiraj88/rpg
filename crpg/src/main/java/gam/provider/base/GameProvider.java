package gam.provider.base;

import gam.config.GameConfig;
import gam.config.PlayerConfig;
import gam.provider.FlyweightProvider;

public abstract class GameProvider {
    private GameConfig gameConfig = FlyweightProvider.getGameConfig().getConfig();

    public void initScene(String gameMapInfo) {
        //Objects and stories may be configured for scene
        System.out.println("Welcome to the rampage! Scene will be presented soon...");
    }

    public void initPlayer(PlayerConfig playerConfig, String playerName) {
        // Config with respect to player may be pulled in
        if (null == playerConfig) {
           playerConfig = FlyweightProvider.getPlayerConfig().getConfig();
           playerConfig.setCustomPlayerName(playerName);
        } else{
            ;
        }
    } //??
}