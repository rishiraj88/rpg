package gam.config;

import gam.Constants;
import gam.config.base.Config;
import gam.model.geo.GameMap;
import gam.provider.FlyweightProvider;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public final class GameConfig implements Config {
    private static final GameConfig _INSTANCE = new GameConfig();
    private static final Properties configMap = new Properties();

    static {
        String filePath = System.getProperty("user.dir") + "\\application.properties";
        try (FileInputStream fileInputStream = new FileInputStream(filePath)) {
            configMap.load(fileInputStream);
            String[] loadedKeyValuePair = new FlyweightProvider().get(Constants.GAME_CONFIG).toString().split(":");//TODO use file for default config
            configMap.put(loadedKeyValuePair[0], loadedKeyValuePair[1]);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static GameMap getGameMap() {
        return (GameMap) FlyweightProvider.get(Constants.FULL_GAME_MAP); //flyweight
    }

    public static String getGameMapInfo() {
        return "GameMap";
    }
    public static String getPlayerConfigInfo() {
        return FlyweightProvider.getPlayerConfigInfo();
    }

    public static PlayerConfig getPlayerConfig() {
        return FlyweightProvider.getPlayerConfig();
    }
    public static GameConfig getConfig() {
        return _INSTANCE;
    }

    public GameConfig add(String key, String value) {
        _INSTANCE.configMap.put(key, value);
        return _INSTANCE;
    }

    public Object get(String key) {
        return _INSTANCE.configMap.getProperty(key);
    }
}
