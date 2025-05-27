package gam.provider;

import gam.Constants;
import gam.config.GameConfig;
import gam.model.PlayerCharacter;
import gam.provider.base.GameProvider;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Path;

public final class SavegameProvider extends GameProvider {
    private static String saveGameDirectory = "."; // for custom values in future, when needed
    private static String saveGameFile = "";
    private boolean forceOverwriteSavegame = false;

    /*public static GameConfig loadSavedGameConfig(FileInputStream fileInputStream) {
        String[] loadedKeyValuePair = FlyweightProvider.get(Constants.GAME_CONFIG).toString().split(":");//TODO use file for saved config
        return FlyweightProvider.getGameConfig().add(loadedKeyValuePair[0], loadedKeyValuePair[1]);
    }*/

    //used in Game Server
    public int loadGame(int savegameOrdinal) {
        String filePath = String.format(System.getProperty("user.dir") + "\\" + saveGameDirectory + "\\savegame%d.dat", savegameOrdinal);//memento
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            PlayerCharacter player = (PlayerCharacter) ois.readObject();
            initScene((String) GameConfig.getConfig().get(Constants.FULL_GAME_MAP)); //TODO activeGameConfig.sceneMap
            initPlayer(null, player.getName()); //TODO activeGameConfig.playerChar
            return savegameOrdinal;
        } catch (IOException e) {
            System.out.println("Failed to load the SAVEGAME " + savegameOrdinal + ".");e.printStackTrace();
            return 8;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    //used in Game Client
    public int saveGame(PlayerCharacter player) {
        //Map "111100" may also be used, alternatively.
        // Player may be asked for the savegame number when "X" command is issued.
        forceOverwriteSavegame = false;
        int savegameOrdinal = 1;// valid range: [1,6]
        return saveGameToFile(savegameOrdinal, player);
    }

    private int saveGameToFile(int ordinal, PlayerCharacter player) {
        String filePath = String.format(System.getProperty("user.dir") + "\\" + saveGameDirectory + "\\savegame%d.dat", ordinal);//memento
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            File file = Path.of(filePath).toFile();
            if (ordinal++ < 7 && !file.exists()) {
                oos.writeObject(player);
                //Files.writeString(Path.of(filePath), playerName, StandardOpenOption.CREATE);
                //String fileData = Files.readString(Path.of(filePath));
                //System.out.println("savegame file: " + filePath);
                //System.out.println("savegame content: " + fileData);
            } else if (ordinal < 7) {
                saveGameToFile(ordinal, player);
            } else if (ordinal == 7) {
                forceOverwriteSavegame = true;
                filePath = System.getProperty("user.dir") + "\\" + saveGameDirectory + "\\savegame1.dat";//memento
                //Files.writeString(Path.of(filePath), playerName, StandardOpenOption.WRITE);
                oos.writeObject(player);
            }
            return ordinal;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            return -1;
        }
    }
}
