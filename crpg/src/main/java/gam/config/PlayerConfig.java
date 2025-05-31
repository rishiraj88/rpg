package gam.config;

import gam.client.GameClient;
import gam.client.MenuBoard;
import gam.config.base.Config;
import gam.model.PlayerCharacter;
import gam.provider.FlyweightProvider;
import gam.util.Factory;

public final class PlayerConfig implements Config {
    private static final PlayerConfig _DEFAULT_PLAYER_CONFIG = (PlayerConfig) Factory.get("gam.config.PlayerConfig"); // common across game servers
    private static final PlayerCharacter prototypePlayer = (PlayerCharacter) Factory.get("gam.model.PlayerCharacter");
    private static  PlayerConfig activePlayerConfig = FlyweightProvider.getDefaultConfig("PlayerConfig");// (PlayerConfig) Factory.get("gam.config.PlayerConfig", PlayerCharacter.class); //TODO
    private volatile String activePlayerName = null;
    private GameClient gameClient=null; private MenuBoard  menuBoard= null;

    public static PlayerConfig getActivePlayerConfig() {
        if (null==activePlayerConfig)  activePlayerConfig= getDefaultConfig();
        return activePlayerConfig;
    }

    public static PlayerConfig getDefaultConfig() {
        return _DEFAULT_PLAYER_CONFIG;
    }

    public PlayerConfig getActivePlayerConfig(String playerName) {
        return activePlayerConfig;
    }

    @Override
    public PlayerConfig clone() throws CloneNotSupportedException {
        return (PlayerConfig) super.clone();
    }

    public final PlayerCharacter createNewPlayer(String newPlayerName) {
        try {
            PlayerCharacter player = prototypePlayer.clone();
            //??
        /* Wield subtypes include the item types which a player may wield, such as:
        . weapon (melee weapon, long-range (long range action) weapon)
        . armour
        . controller (key, remote control, puzzle piece)
        . first aid kit (or single piece of equipment, such as syringe, rope)
        */
           /* player.addWield("dagger", (short) 5);
            player.addWield("shotgun", (short) 30);
            player.addWield("rope", (short) 2);*/ //??
            player.setName(newPlayerName);
            return player;
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    public PlayerConfig getConfig() {
        return activePlayerConfig;
    }

    public void setPlayerName(Object playerName) {
    }

    public Object getMenuBoard() {if(null==menuBoard) menuBoard = new MenuBoard(); return menuBoard;
    }
    // private WieldConfig wieldConfig = WieldConfig.getConfig(); //?? extension point
}