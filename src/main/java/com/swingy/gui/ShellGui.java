package com.swingy.gui;

public class ShellGui {

    public static Map map = new Map();

    public static void drawMap() {
        System.out.print("\033[H\033[2J");
        System.out.print("┍");
        for (int i = 0; i < map.getSize() * 2; ++i)
            System.out.print("━");
        System.out.println("┑");
        for (int i = 0; i < map.getSize(); ++i) {
            System.out.print("│");
            for (int j = 0; j < map.getSize(); ++j) {
                if (player.getPosX() == j && player.getPosY() == i)
                    System.out.print("P ");
                else
                    System.out.print(map.getMapCell(i, j) + " ");
            }
            System.out.println("│");
        }
        System.out.print("┕");
        for (int i = 0; i < map.getSize() * 2; ++i)
            System.out.print("━");
        System.out.println("┙");
        System.out.println("Type commands to move: 'right', 'left', 'up', 'down', or 'info' for player info.");
    }
}
