package gam.config;

import gam.config.base.Config;
import gam.model.PlayerCharacter;

public final class PlayerConfig implements Config {
    private static final PlayerConfig _INSTANCE = new PlayerConfig();
    private static final PlayerCharacter prototypePlayer = new PlayerCharacter();
    private String customPlayerName = null;

    public static PlayerCharacter createNewPlayer(String playerName) {
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
            player.setName(playerName);
            return player;
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    public static PlayerConfig getConfig() {
        return _INSTANCE;
    }
    // private WieldConfig wieldConfig = WieldConfig.getConfig(); //?? extension point

    public String getCustomPlayerName() {
        return customPlayerName;
    }

    public void setCustomPlayerName(String customPlayerName) {
        this.customPlayerName = customPlayerName;
    }
}