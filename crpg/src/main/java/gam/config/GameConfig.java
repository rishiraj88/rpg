package gam.config;

import gam.config.base.Config;
import gam.provider.FlyweightProvider;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public final class GameConfig implements Config {
    private static volatile GameConfig _INSTANCE = (GameConfig) FlyweightProvider.get("GameConfig"); //to keep singleton or to replicate separately per GameServer instance?
    private volatile Map<String, Object> configMap = new ConcurrentHashMap<>();

    /*public static GameMap getGameMap() {
        return (GameMap) FlyweightProvider.get(Constants.FULL_GAME_MAP); //flyweight
    }*/ //??

    public static synchronized GameConfig getConfig() {
        if (null == _INSTANCE) return new GameConfig();
        return _INSTANCE;
    }

    public PlayerConfig getPlayerConfig() {
        PlayerConfig playerConfig = (PlayerConfig) this.configMap.get("PLAYER_CONFIG");
        if (null == playerConfig) playerConfig = PlayerConfig.getDefaultConfig();
        return playerConfig;
    }

    public void set(String configKey, Object configValue) {
        this.configMap.put(configKey, configValue);
    }


    /*public GameConfig add(String key, String value) {
        configMap.put(key, value);
        return _INSTANCE;
    }*/ //?? for loading useful key-value pairs to config from files

    public Object get(String key) {
        return configMap.get(key);
    }

    @Override
    public PlayerConfig clone() throws CloneNotSupportedException {
        return (PlayerConfig) super.clone();
    }
}
