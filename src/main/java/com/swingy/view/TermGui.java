package com.swingy.view;

import com.swingy.gui.Map;
import com.swingy.model.characters.Hero;

public class TermGui implements Gui {

    public static TermGui shell;

    public static TermGui createShellGui() {
        shell = new TermGui();
        return shell;
    }

    public static void error() {

    }

    public static void pickClass() {

    }

    public void writeMap() {
        flush();
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
                else {
                    System.out.print(Map.getMap().getMapCell(i, j) + " ");
                }
            }
            System.out.println("│");
        }
        System.out.print("┕");
        for (int i = 0; i < Map.getMap().getSize() * 2; ++i)
            System.out.print("━");
        System.out.println("┙");
    }

    public static void flush() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
