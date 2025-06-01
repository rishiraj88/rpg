package gam.client;

import gam.GameServer;
import gam.config.PlayerConfig;
import gam.provider.SavegameProvider;
import gam.util.Factory;
import gam.util.IOUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class GameClient {
    private final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private PlayerConfig playerConfig = PlayerConfig.getDefaultConfig();
    //tools
    private volatile GameServer gameServer = null;
    private volatile Commander commander = (Commander) Factory.get("gam.client.Commander");
    private SavegameProvider savegameProvider;

    private static void SpawnNewPlayer(PlayerConfig playerConfig) {
    }

    //to spawn a player token so that a player may start playing
    public void createNewPlayer() {
        //PlayerCharacter player = SpawnNewPlayer(GameConfig.getConfig().getPlayerConfig());
        IOUtil.display("""
                How about naming your player character by your wish? ¯\\_( ͡° ͜ʖ ͡°)_/¯
                Name your player as: """);
        String playerName = "playername";
        playerName = IOUtil.readLine();
        //PlayerCharacter player = SpawnNewPlayer(playerName);
        playerConfig.createNewPlayer(playerName);
    }

    // A Parameter 'short serverPort' may allow for connecting to one of many game servers running.
    public void connectToServer(GameServer gameServer) {
        this.gameServer = gameServer;
        // create new player on request
        IOUtil.display("Would you like to create your character now? (˵ ͡° ͜ʖ ͡°˵)\nY/n");//TODO
        try {
            if ("y".equalsIgnoreCase(reader.readLine().substring(0, 1))) {
                createNewPlayer();
            } else {
                Thread.sleep(5000);
                IOUtil.display("Would you like to play soon? (˵ ͡° ͜ʖ ͡°˵)\nY/n");
                if ("y".equalsIgnoreCase(reader.readLine().substring(0, 1))) {
                    createNewPlayer();
                } else {
                    System.exit(0);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        //present menus (scene info and available actions)
        MenuBoard menuBoard = (MenuBoard) playerConfig.getMenuBoard();
        menuBoard.display(playerConfig);
        // Respond to input commands
        initCommander(playerConfig);
    }

    private void initCommander(PlayerConfig playerConfig) {
        new Commander().init(this, playerConfig);
    }

    public SavegameProvider getSavegameProvider() {
        return null == this.savegameProvider ? this.savegameProvider = (SavegameProvider) Factory.get("gam.provider.SavegameProvider") : this.savegameProvider;
    }
}
