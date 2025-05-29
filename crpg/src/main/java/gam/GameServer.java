package gam;

import gam.provider.FlyweightProvider;
import gam.provider.NewGameProvider;
import gam.util.Factory;
import gam.util.IOUtil;

import java.util.Arrays;

public final class GameServer {
    private final FlyweightProvider flyweightProvider = (FlyweightProvider) Factory.get("gam.provider.FlyweightProvider");
    private volatile String activeGames = "000000"; // multiple simultaneous games not supported for now, may be supported with this map in future

    public void initGamePlay() {
        // Display the list of available savegames to load
        if (activeGames.contains("0")) { //in savegame map: binary "000000".."111111"
            IOUtil.display("Select your previously saved game out of " + Arrays.toString(activeGames.toCharArray()) + " to load, else 9 to start a new game: ");
            // 8 : sentinel for any failure while attempting to load a game
            if (8 == loadGame(IOUtil.readLine())) {
                new Thread(() -> initGamePlay()).start();
            }
            //advertise the server
            //openPassivelyForClients();//extension possibility
        }
    }

    private int loadGame(String savegameOrdinal) {
        if ("9".equals(savegameOrdinal)) { //9 : new game with default setup to load
            IOUtil.display("""
                    How about naming your player character by your wish? ¯\\_( ͡° ͜ʖ ͡°)_/¯
                    Name your player as: """);
            String playerName = "playername"; //playerName = IOUtil.readLine();
            if (9 == startNewGame(playerName)) { // plot and start new game
                IOUtil.display("New game has been loaded. Survive the adventure! ᕕ(⌐■_■)ᕗ ♪♬");
                return 9;
            }
        } else if (savegameOrdinal.length() == 1 && "123456".contains(savegameOrdinal)) { // load a saved game
            return loadSavegame(Short.parseShort(savegameOrdinal));
        } else {
            throw new IllegalStateException("Please make a choice out of: [1, 2, 3, 4, 5, 6, 9]");//TODO
        }
        return 8; // 8 is sentinel for exception and motivates for a retry
    }

    private int startNewGame(String playerName) {
        return new NewGameProvider().startNewGame(playerName);
    }

    private int loadSavegame(short savegameOrdinal) {
        return flyweightProvider.getSavegameProvider().loadGame(savegameOrdinal);
    }
}
