package gam.provider;

import gam.config.GameConfig;
import gam.config.PlayerConfig;
import gam.model.geo.GameMap;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public final class FlyweightProvider {
    private static Properties defaultConfig = new Properties();
    private static gam.config.PlayerConfig playerConfig = new gam.config.PlayerConfig();
    //private static WieldConfig wieldConfig = new WieldConfig();//??
    private static SavegameProvider savegameProvider= new SavegameProvider();

    FlyweightProvider(String configFileName) {
        //read config file, load data
        try {
            String filePath = System.getProperty("user.dir") + configFileName;
            FileInputStream fileInputStream = new FileInputStream(filePath);
            defaultConfig.load(fileInputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        defaultConfig.put("FULL_GAME_MAP", new GameMap());
    }

    public FlyweightProvider() {
        //read config file, load data out of pre-set config file.
        this("\\application.properties");
    }

    public static Object get(String key) {
        return defaultConfig.get(key);
    }

    public static GameConfig getGameConfig() {
        return GameConfig.getConfig();
    }
    public static gam.config.PlayerConfig getPlayerConfig() {
        return PlayerConfig.getConfig();
    }
    public static String getPlayerConfigInfo() {
        return "playerConfig";
    }
    /*public static WieldConfig getWieldConfig() {return WieldConfig.getConfig();}*/ //??
    public static SavegameProvider getSavegameProvider() {
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
