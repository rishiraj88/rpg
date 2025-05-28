package gam.provider;

import gam.model.geo.GameMap;
import gam.util.Factory;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public final class FlyweightProvider {
    private static final Properties defaultConfig = new Properties();
    //private static WieldConfig wieldConfig = new WieldConfig();//??
    private final SavegameProvider savegameProvider= (SavegameProvider) Factory.get("gam.provider.SavegameProvider");

//??
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

    public FlyweightProvider() {//?? for heavy load conditions
        //read config file, load data out of pre-set config file.
        this("\\application.properties");
    }

    public static Object get(String key) {
        return defaultConfig.get(key);
    }

    /*public static gam.config.PlayerConfig getPlayerConfig() {
        return PlayerConfig.getConfig();
    }*/

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
