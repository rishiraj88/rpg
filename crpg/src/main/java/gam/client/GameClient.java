package gam.client;

import gam.config.GameConfig;
import gam.config.PlayerConfig;
import gam.model.PlayerCharacter;

import java.io.Console;

public class GameClient {
    private static PlayerCharacter player = null;
    private static Commander commander = new Commander();
    private static Console console = System.console();

    public static void connectToServer(short serverPort) {
        // create new player on request
        System.out.println("Would you like to create your character now? (˵ ͡° ͜ʖ ͡°˵)");
        System.out.println("Y/n");
        if ("y".equalsIgnoreCase(console.readLine().substring(0, 1))) {
            createNewPlayer(serverPort);
        } else {
            try {
                Thread.sleep(5000);
                System.out.println("Would you like to play soon? (˵ ͡° ͜ʖ ͡°˵)");
                System.out.println("Y/n");
                if ("y".equalsIgnoreCase(console.readLine().substring(0, 1))) {
                    createNewPlayer(serverPort);
                } else {
                    System.exit(0);
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        //present menus (scene info and available actions)
        MenuBoard.display(serverPort, player);
        // Respond to input commands
        commander.init(player);
    }

    //to spawn a player token so that a player may start playing
    public static void createNewPlayer(short port) {
        SpawnNewPlayer(GameConfig.getPlayerConfig(), port);
        System.out.println(String.format("new player has been created for client connection %d.", port));
    }

    private static void SpawnNewPlayer(PlayerConfig config, short port) {
        player = PlayerConfig.createNewPlayer(config.getCustomPlayerName());
    }
}
