package com.swingy.view;

import com.swingy.Game;
import com.swingy.controller.ActionOnMove;
import com.swingy.gui.Map;
import com.swingy.model.characters.Hero;

public class ShellGui implements Gui {

    public static ShellGui shell;

    public static ShellGui createShellGui() {
        shell = new ShellGui();
        return shell;
    }

    public static void error() {

    }

    public static void pickClass() {

    }

    public void writeMap() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
        System.out.print("┍");
        for (int i = 0; i < Map.getMap().getSize() * 2; ++i)
            System.out.print("━");
        System.out.println("┑");
        for (int i = 0; i < Map.getMap().getSize(); ++i) {
            System.out.print("│");
            for (int j = 0; j < Map.getMap().getSize(); ++j) {
                if (Hero.getHero().getCoordinates().getY() == i &&
                        Hero.getHero().getCoordinates().getX() == j) {
                        System.out.print("H ");
                }
                else
                    System.out.print(Map.getMap().getMapCell(i, j) + " ");
            }
            System.out.println("│");
        }
        System.out.print("┕");
        for (int i = 0; i < Map.getMap().getSize() * 2; ++i)
            System.out.print("━");
        System.out.println("┙");
        System.out.println("Where to move? : 'east/E', 'west/W', 'north/N', 'south/S'.");
    }
}
