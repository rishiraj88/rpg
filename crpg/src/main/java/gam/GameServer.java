package gam;

import gam.provider.FlyweightProvider;
import gam.provider.NewGameProvider;

import java.io.Console;

public final class GameServer {
    private static String activeGames = "000000"; // multiple simultaneous games not supported for now, may be supported with this map in future
    private static Console console = System.console();

    public void initGamePlay() {
        if (activeGames.contains("0")) { //in savegame map: binary "000000".."111111"
            System.out.println("Select your previously saved game out of [1 to 6] to load, else 9 to start a new game: ");
            if(8 == loadGame(console.readLine())) initGamePlay();

            //advertise the server
            //openPassivelyForClients();//extension possibility
        }
    }

    private int loadGame(String savegameOrdinal) {
        if ("9".equals(savegameOrdinal)) {
            System.out.println("How about naming your player character by your wish? ¯\\_( ͡° ͜ʖ ͡°)_/¯");
            System.out.println("Name your player as: "); String playerName = console.readLine();
            if (9 == startNewGame(playerName)) {System.out.println("New game has been loaded. Survive the adventure! ᕕ(⌐■_■)ᕗ ♪♬");return 9;}
        } else if (savegameOrdinal.length() == 1 && "123456".contains(savegameOrdinal)) {
            return loadSavegame(Integer.valueOf(savegameOrdinal));
        } else {
            throw new IllegalStateException("Please make a choice out of: [1, 2, 3, 4, 5, 6, 9]");
        }return 8; // 8 is sentinel for exception and motivates for a retry
    }

    private int startNewGame(String playerName) {
        return new NewGameProvider().startNewGame(playerName);
    }


    private  int loadSavegame(int savegameOrdinal) {
        return FlyweightProvider.getSavegameProvider().loadGame(savegameOrdinal);
    }
}
