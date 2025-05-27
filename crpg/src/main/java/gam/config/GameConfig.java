package gam.config;

import gam.config.base.Config;
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

            // potential extension point //??
            /*configMap.load(fileInputStream);
            String[] loadedKeyValuePair = FlyweightProvider.get(Constants.GAME_CONFIG).toString().split(":");//TODO use file for default config
            configMap.put(loadedKeyValuePair[0], loadedKeyValuePair[1]);*/

        } catch (FileNotFoundException e) {
            System.out.println("Failed to locate the default configuration file.");
        } catch (IOException e) {
            System.out.println("Could not read the default configurations out of the pre-set file.");
        }
    }

    /*public static GameMap getGameMap() {
        return (GameMap) FlyweightProvider.get(Constants.FULL_GAME_MAP); //flyweight
    }*/ //??

    public static PlayerConfig getPlayerConfig() {
        return FlyweightProvider.getPlayerConfig();
    }
    public static GameConfig getConfig() {
        return _INSTANCE;
    }

    /*public GameConfig add(String key, String value) {
        configMap.put(key, value);
        return _INSTANCE;
    }*/ //?? for loading useful key-value pairs to config from files

    public Object get(String key) {
        return configMap.getProperty(key);
    }
}
