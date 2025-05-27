package gam.client;

import gam.config.GameConfig;
import gam.config.PlayerConfig;
import gam.model.PlayerCharacter;

import java.io.Console;

public class GameClient {
    private static PlayerCharacter player = null;
    private static final Console console = System.console();

    // A Parameter 'short serverPort' may allow for connecting to one of many geme servers running.
    public static void connectToServer() {
        // create new player on request
        System.out.println("Would you like to create your character now? (˵ ͡° ͜ʖ ͡°˵)");
        System.out.println("Y/n");
        if ("y".equalsIgnoreCase(console.readLine().substring(0, 1))) {
            createNewPlayer();
        } else {
            try {
                Thread.sleep(5000);
                System.out.println("Would you like to play soon? (˵ ͡° ͜ʖ ͡°˵)");
                System.out.println("Y/n");
                if ("y".equalsIgnoreCase(console.readLine().substring(0, 1))) {
                    createNewPlayer();
                } else {
                    System.exit(0);
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        //present menus (scene info and available actions)
        MenuBoard.display(player);
        // Respond to input commands
        Commander.init(player);
    }

    //to spawn a player token so that a player may start playing
    public static void createNewPlayer() {
        SpawnNewPlayer(GameConfig.getPlayerConfig());
        System.out.printf(String.format("%s, a new character has been created for a win.",player.getName()));
    }

    private static void SpawnNewPlayer(PlayerConfig config) {
        player = PlayerConfig.createNewPlayer(config.getCustomPlayerName());
    }
}
