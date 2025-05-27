package gam.client;

import gam.model.PlayerCharacter;

public class MenuBoard {

    public static void display(short serverPort, PlayerCharacter player) {
        System.out.println(String.format("%s, you are in World A, Ground %d%d. You are facing due North.",player.getName(),player.getScene().getNsIndex(),player.getScene().getEwIndex()));
        System.out.println("W: Move forward");
        System.out.println("S: Move backward");
        System.out.println("A: Move left");
        System.out.println("D: Move right");
        System.out.println("F: Fight");
    }

}
