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
    //private static WieldConfig wieldConfig = new WieldConfig();//??
    private final SavegameProvider savegameProvider = null;

    //??
    Flyweight(String configFileName) {
        //read config file, load data
        String filePath = System.getProperty("user.dir") + configFileName;
        System.out.println("filepath: " + filePath);
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            defaultConfig = (ConcurrentHashMap<String, Object>) ois.readObject();
            // PlayerConfig??
        } catch (IOException e) {
            System.out.println("IOException: Missing some config objects in file 'game.config'");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        //defaultConfig.put("FULL_GAME_MAP", new GameMap());//??
    }

    public Flyweight() {//?? for heavy load conditions
        //read config file, load data out of pre-set config file.
        this("\\game.config");
    }

    public static <T extends Config> T getDefaultConfig(String configKey) {
        return (T) defaultConfig.get(configKey);
    }

    public static Object get(String key) {
        return defaultConfig.get(key);
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
