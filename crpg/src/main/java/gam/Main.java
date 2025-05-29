package gam;

import gam.client.GameClient;
import gam.util.Factory;

public class Main {
    public static void main(String[] args) {
        //1. Initialize game server.
        GameServer gameServer = (GameServer) Factory.get("gam.GameServer");
        Thread gameServerThread = new Thread(() -> gameServer.initGamePlay());
        gameServerThread.start();

        //2. Start game client and connect to server.
        Thread gameClientThread = new Thread(() -> {
            try {
                gameServerThread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            new GameClient().connectToServer(gameServer);
        });
        gameClientThread.start();
    }
}