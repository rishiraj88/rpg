package gam.provider.base;

import gam.config.GameConfig;
import gam.config.PlayerConfig;
import gam.model.geo.Scene;
import gam.util.IOUtil;

public abstract class GameProvider {

    public void initScene(Scene sceneInfo) {
        //Objects and stories configured for scene
        GameConfig.getConfig().set("scene",sceneInfo); //?? Another strategy: to load a <code>Ground</code> out of <code>GameMap</code>

        IOUtil.display("Welcome to the rampage! Scene will be presented soon...");
    }

    public void initPlayer(PlayerConfig playerConfig, String playerName) {
        // Config with respect to player to pull in. Recommended to use for loading a savegame
        if (null == playerConfig) {
           playerConfig = PlayerConfig.getDefaultConfig();
           playerConfig.createNewPlayer(playerName);
        } else{ playerConfig.setPlayerName(playerName);
        }
    }
}