package gam.util;

import gam.config.base.Config;
import gam.provider.SavegameProvider;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.OutputStreamWriter;
import java.util.concurrent.ConcurrentHashMap;

public final class Flyweight {
    public static final BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
    public static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static ConcurrentHashMap<String, Object> defaultConfig = new ConcurrentHashMap<>();
    //private static WieldConfig wieldConfig = new WieldConfig();//?? an optional feature for future releases
    private final SavegameProvider savegameProvider = null;

    Flyweight(String configFileName) {
        //read config file, load data
        String filePath = System.getProperty("user.dir") + configFileName;
        IO.println("filepath: " + filePath);
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(filePath))) {
            var configFromFile = objectInputStream.readObject();
            defaultConfig = configFromFile instanceof ConcurrentHashMap ? (ConcurrentHashMap<String, Object>) configFromFile : defaultConfig; // static across gameplays and clients

            // PlayerConfig?? specific to individual gameplays and clients
        } catch (IOException e) {
            IO.println("IOException: Missing some game config objects.");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        //defaultConfig.put("FULL_GAME_MAP", new GameMap());//?? useful when a map is required
    }

    public Flyweight() {//?? for heavy load conditions
        //read config file, load data out of pre-set config file.
        this("\\game.config");
    }

    public static <T extends Config> T getDefaultConfig(String configKey) {
        return (T) defaultConfig.get(configKey);
    }

    public static Object get(String identifier) {
        return defaultConfig.get(identifier);
    }

    /*public static WieldConfig getWieldConfig() {return WieldConfig.getConfig();}*/ //??
    public SavegameProvider getSavegameProvider() {
        return savegameProvider;
    }
    /*
    public static Wield getWield(String wieldName) {
        return switch (wieldName) {
            case "dagger" -> { // Melee Weapon
                MeleeWeapon meleeWeapon = FlyweightProvider.getWieldConfig().createNewMeleeWeapon(wieldName);
                yield meleeWeapon;
            }
            case "shotgun" -> { // Melee Weapon
                RangeWeapon rangeWeapon = FlyweightProvider.getWieldConfig().createNewRangeWeapon(wieldName);
                yield rangeWeapon;
            }
            default ->  FlyweightProvider.getWieldConfig().createSyringe(wieldName);
        };
    }*/ //??
}
