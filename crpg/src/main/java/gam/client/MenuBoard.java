package gam.client;

import gam.model.PlayerCharacter;

public class MenuBoard {
    public static void display(PlayerCharacter player) {
        System.out.printf("%s, you are in World A, Ground %d%d. You are facing due North.%n", player.getName(), player.getScene().getNsIndex(), player.getScene().getEwIndex());
        System.out.println("W: Move forward");
        System.out.println("S: Move backward");
        System.out.println("A: Move left");
        System.out.println("D: Move right");
        System.out.println("F: Fight");
        System.out.println("X: Save this game");
    }
}
