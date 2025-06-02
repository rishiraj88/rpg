package gam.provider;

import gam.config.GameConfig;
import gam.config.PlayerConfig;
import gam.provider.base.GameProvider;
import gam.util.IOUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Path;

public final class SavegameProvider extends GameProvider {
    private final String saveGameDirectory = "."; // for custom values in future, when needed

    /*public static GameConfig loadSavedGameConfig(FileInputStream fileInputStream) {
        String[] loadedKeyValuePair = FlyweightProvider.get(Constants.GAME_CONFIG).toString().split(":");//TODO use file for saved config
        return FlyweightProvider.getGameConfig().add(loadedKeyValuePair[0], loadedKeyValuePair[1]);
    }*/

    //used in Game Server
    public int loadGame(int savegameOrdinal) {
        String filePath = String.format(System.getProperty("user.dir") + "\\" + saveGameDirectory + "\\savegame%d.dat", savegameOrdinal);//memento is among best practices for this
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            PlayerConfig playerConfig = (PlayerConfig) ois.readObject();
            initScene(playerConfig.getPlayerPosition());
            initPlayer(GameConfig.getConfig().getPlayerConfig()); // activeGameConfig.playerChar
            return savegameOrdinal;
        } catch (IOException e) {
            IOUtil.display("Failed to load the SAVEGAME " + savegameOrdinal + ".");
            e.printStackTrace();
            return 8;
        } catch (ClassNotFoundException e) {
            IOUtil.display("There is a mismatch between savegame data and game models. Use another savegame or start a new game.");
            e.printStackTrace();
            return 8;
        }
    }

    //used in Game Client
    public int saveGame(PlayerConfig playerConfig) {//TODO to implement with PlayerConfig parameter type
        //Map "111100" may also be used, alternatively.
        IOUtil.display("Your wish to save the warrior history as SAVEGAME [1, 2, 3, 4, 5 or 6]: ");
        //int savegameOrdinal = Integer.parseInt(IOUtil.readLine());// valid range: [1,6] //TODO
        int savegameOrdinal = 1;// valid range: [1,6]
        GameConfig.getConfig().set("playerName", playerConfig.getPlayer().getName());
        return saveGameToFile(savegameOrdinal, playerConfig);
    }

    private int saveGameToFile(int ordinal, PlayerConfig playerConfig) {
        String filePath = String.format(System.getProperty("user.dir") + "\\" + saveGameDirectory + "\\savegame%d.dat", ordinal);//memento
        File file = Path.of(filePath).toFile();
        boolean createNewFile = !file.exists();
        if (ordinal < 7 && createNewFile) {
            trySavingGame(filePath, playerConfig);
            return ordinal;
        } else if (ordinal < 7) {
            saveGameToFile(++ordinal, playerConfig);
        } else {
            filePath = System.getProperty("user.dir") + "\\" + saveGameDirectory + "\\savegame1.dat";//memento
            trySavingGame(filePath, playerConfig);
            return ordinal;
        }
        return ordinal;
    }

    private void trySavingGame(String filePath, PlayerConfig playerConfig) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath));
            oos.writeObject(playerConfig);
            IOUtil.display("Your glory has been recorded as history, Meister.");
        } catch (IOException e) {
            e.printStackTrace();
            IOUtil.display("Could not save the game file with a victory, Meister.");
        }
    }
}
