package gam;

import gam.provider.NewgameProvider;
import gam.util.Factory;
import gam.util.Flyweight;
import gam.util.IOUtil;
import gam.util.StringUtil;

public final class GameServer {
    private final Flyweight flyweight = (Flyweight) Factory.get("gam.util.Flyweight");
    private volatile String savedGames = "111110"; // multiple simultaneous games is supported now

    public void initGamePlay() {
        // Display the list of available savegames to load
        String promptToLoadSavegames = "";
        //in savegame map: binary "000000".."111111" //present game numbers to choose out of to player
        if(savedGames.contains("1"))
        promptToLoadSavegames += "Select your previously saved game out of " + StringUtil.addDelimiter(savedGames, ',').replaceAll(",0","") + " to load ELSE ";
        IOUtil.display(promptToLoadSavegames + "Press 9 to start a new game: ");
        // 8 : sentinel for any failure while attempting to load a game
        if (8 == loadGame(IOUtil.readLine())) {
            new Thread(() -> initGamePlay()).start();
        }
    }

    private int loadGame(String savegameOrdinal) {
        if ("9".equals(savegameOrdinal)) { //9 : new game with default setup to load
            if (9 == startNewGame()) { // plot and start new game
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

    private int startNewGame() {
        return new NewgameProvider().startNewGame();
    }

    private int loadSavegame(short savegameOrdinal) {
        return flyweight.getSavegameProvider().loadGame(savegameOrdinal);
    }
}
