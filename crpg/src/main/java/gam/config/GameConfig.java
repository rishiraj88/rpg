package gam.config;

import gam.config.base.Config;
import gam.provider.FlyweightProvider;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public final class GameConfig implements Config {
    private static volatile GameConfig _INSTANCE = (GameConfig) FlyweightProvider.get("GameConfig");
    private volatile Map<String,Object> configMap = new ConcurrentHashMap<>();

    //??  potential extension point
   /* static {
           String filePath = System.getProperty("user.dir") + "\\application.properties";
        try (FileInputStream fileInputStream = new FileInputStream(filePath)) {

            configMap.load(fileInputStream);
            String[] loadedKeyValuePair = FlyweightProvider.get(Constants.GAME_CONFIG).toString().split(":");//TODO use file for default config
            configMap.put(loadedKeyValuePair[0], loadedKeyValuePair[1]);
        } catch (FileNotFoundException e) {
            IOUtil.display("Failed to locate the default configuration file.");
        } catch (IOException e) {
            IOUtil.display("Could not read the default configurations out of the pre-set file.");
        }
    }*/

    /*public static GameMap getGameMap() {
        return (GameMap) FlyweightProvider.get(Constants.FULL_GAME_MAP); //flyweight
    }*/ //??

    public PlayerConfig getPlayerConfig() {
        return (PlayerConfig) this.configMap.get("PLAYER_CONFIG");
    }
    public static GameConfig getConfig() {
        return _INSTANCE;
    }

    public void set(String configKey, Object configValue) {
        this.configMap.put(configKey,configValue);
    }


    /*public GameConfig add(String key, String value) {
        configMap.put(key, value);
        return _INSTANCE;
    }*/ //?? for loading useful key-value pairs to config from files

    public Object get(String key) {
        return configMap.get(key);
    }
}
