package gam.config;

import gam.client.GameClient;
import gam.client.MenuBoard;
import gam.config.base.Config;
import gam.model.PlayerCharacter;
import gam.model.geo.Scene;
import gam.util.Factory;
import gam.util.IOUtil;

import java.util.concurrent.ConcurrentHashMap;

public final class PlayerConfig implements Config {
    // player configs: player char + env + more data
    private static final PlayerConfig _DEFAULT_PLAYER_CONFIG = new PlayerConfig(); // common across game servers
    // player char
    private static final PlayerCharacter prototypePlayer = (PlayerCharacter) Factory.get("gam.model.PlayerCharacter");
    private static ConcurrentHashMap<String, PlayerConfig> activePlayerConfigs = new ConcurrentHashMap<>();// Use it for long
    private static PlayerConfig activePlayerConfig = null; //TODO remove it soon
    private volatile PlayerCharacter activePlayer = null;
    private volatile Scene playerPosition = null; //scene: Map Block (world, ground) + Geospatial Coordinates (x,y,z)
    // tools
    private transient GameClient gameClient = null;
    private transient MenuBoard menuBoard = null;

    public static PlayerConfig getDefaultConfig() {
        return _DEFAULT_PLAYER_CONFIG;
    }

    public static PlayerConfig getActivePlayerConfig(String playerCharacterName) {
        if (null == activePlayerConfigs || activePlayerConfigs.isEmpty()) return _DEFAULT_PLAYER_CONFIG;
        return activePlayerConfigs.get(playerCharacterName);
    }

    @Override
    public PlayerConfig clone() throws CloneNotSupportedException {
        if (this == _DEFAULT_PLAYER_CONFIG) {
            return (PlayerConfig) super.clone();
        } else {
            throw new CloneNotSupportedException();
        }
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
            IOUtil.display(String.format("%s, a new character has been created for a win.", player.getName()));
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    public Object getMenuBoard() {
        if (null == menuBoard) menuBoard = new MenuBoard();
        return menuBoard;
    }

    public PlayerCharacter getPlayer() {
        return activePlayer;
    }

    public Scene getPlayerPosition() {
        if (null == playerPosition) playerPosition = (Scene) Factory.get("gam.model.geo.Scene");
        return playerPosition;
    }

    public void moveNorthSouthAndEastWest(short y, short x) {
        playerPosition.moveNorthSouthAndEastWest(this, y, x);
    }

    public void fight() {
        activePlayer.incrementScore(1);
        IOUtil.display("score: " + activePlayer.getScore());
    }
    // private WieldConfig wieldConfig = WieldConfig.getConfig(); //?? extension point
}