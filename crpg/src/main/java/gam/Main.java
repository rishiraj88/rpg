package gam;

import gam.client.GameClient;

public class Main {
    public static void main(String[] args) {
        //1. Initialize game server.
        Thread gameServerThread = new Thread(() -> new GameServer().initGamePlay());
        gameServerThread.start();

        //2. Start game client and connect to server.
        Thread gameClientThread = new Thread(() -> {
            try {
                gameServerThread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            GameClient.connectToServer((short) 1);});
        gameClientThread.start();
    }
}