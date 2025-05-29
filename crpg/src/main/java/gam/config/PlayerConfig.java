package gam.config;

import gam.config.base.Config;
import gam.model.PlayerCharacter;
import gam.util.Factory;

public final class PlayerConfig implements Config {
    private static final PlayerConfig _DEFAULT_PLAYER_CONFIG = new PlayerConfig();
    private static volatile PlayerConfig activePlayerConfig = _DEFAULT_PLAYER_CONFIG;// (PlayerConfig) Factory.get("gam.config.PlayerConfig", PlayerCharacter.class);
    private final PlayerCharacter prototypePlayer = (PlayerCharacter) Factory.get("gam.model.PlayerCharacter");
    private volatile PlayerCharacter activePlayer = null;

    public static PlayerConfig getActivePlayerConfig() {
        return activePlayerConfig;
    }

    public static PlayerConfig getDefaultConfig() {
        return _DEFAULT_PLAYER_CONFIG;
    }

    public final void createNewPlayer(String newPlayerName) {
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
            activePlayer = player;
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    public PlayerConfig getConfig() {
        return activePlayerConfig;
    }

    public String getPlayerName() {
        return activePlayer.getName();
    }

    public void setPlayerName(String playerName) {
        activePlayer.setName(playerName);
    }

    public PlayerCharacter getPlayer() {
        return activePlayer;
    }
    // private WieldConfig wieldConfig = WieldConfig.getConfig(); //?? extension point
}