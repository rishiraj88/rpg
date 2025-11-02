package gam;

import gam.client.GameClient;
import gam.util.Factory;

public class Main {
    public static void main(String[] args) {

        //1. Initialize game server.
        Thread gameServerThread = startServer();

        //2. Start game client and connect the client to server.
        startClient(gameServerThread);
    }

    public static Thread startServer() {
        GameServer gameServer = (GameServer) Factory.get("gam.GameServer", true);
        Thread gameServerThread = new Thread(gameServer::initGamePlay);
        gameServerThread.start();
        return gameServerThread;
    }

    public static void startClient(Thread gameServerThread) {
        Thread gameClientThread = new Thread(() -> {
            try {
                gameServerThread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            GameServer gameServer = (GameServer) Factory.get("gam.GameServer", false);
            ((GameClient) Factory.get("gam.client.GameClient")).connectToServer(gameServer);
        });
        gameClientThread.start();
    }
}