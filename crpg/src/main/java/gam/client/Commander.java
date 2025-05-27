package gam.client;

import gam.model.PlayerCharacter;
import gam.provider.FlyweightProvider;

import java.io.Console;
import java.io.IOException;

public class Commander {
    private static Console console = System.console();

    static void init(PlayerCharacter playerCharacter) {
        // config, if required
        respondToCommands(playerCharacter);
    }

    private static void respondToCommands(PlayerCharacter player) {
        while (true) {
           try { //WASD keys for gaming
               char c = (char) System.in.readNBytes(1)[0];
               switch (c) {
                   case 'W', 'w' -> {
                       player.moveNorthSouthAndEastWest((short) 1, (short) 0);
                   }
                   case 'D', 'd' -> {
                       player.moveNorthSouthAndEastWest((short) 0, (short) 1);
                   }
                   case 'S', 's' -> {
                       player.moveNorthSouthAndEastWest((short) -1, (short) 0);
                   }
                   case 'A', 'a' -> {
                       player.moveNorthSouthAndEastWest((short) 0, (short) -1);
                   }
                   case 'F', 'f' -> {
                       player.fight();
                   }
                   case 'X', 'x' -> {
                       FlyweightProvider.getSavegameProvider().saveGame(player);
                   }
                   case '\n' -> {
                       ;
                   }
                   default -> System.out.println("invalid command. Please press W, A, S, D or F to play. Press X to save.");
               }
           } catch (IOException e) {
               throw new RuntimeException(e);
           }
        }
    }
}
