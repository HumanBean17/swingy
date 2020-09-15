package com.swingy.view;

import com.swingy.gui.Map;
import com.swingy.model.characters.ClassCharacter;
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

    public void drawMap() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
        System.out.print("┍");
        for (int i = 0; i < Map.getMap().getSize() * 2; ++i)
            System.out.print("━");
        System.out.println("┑");
        for (int i = 0; i < Map.getMap().getSize(); ++i) {
            System.out.print("│");
            for (int j = 0; j < Map.getMap().getSize(); ++j) {
                if (Hero.getHero().getCoordinates().getX() == j && Hero.getHero().getCoordinates().getY() == i)
                    System.out.print("P ");
                else
                    System.out.print(Map.getMap().getMapCell(i, j) + " ");
            }
            System.out.println("│");
        }
        System.out.print("┕");
        for (int i = 0; i < Map.getMap().getSize() * 2; ++i)
            System.out.print("━");
        System.out.println("┙");
        System.out.println("Type commands to move: 'right/R', 'left/L', 'up/U', 'down/D'.");
    }
}
