package com.swingy.view;

import com.swingy.Game;
import com.swingy.gui.Map;
import com.swingy.model.characters.Hero;

public class TermGui implements Gui {

    public static TermGui shell;

    public static TermGui createShellGui() {
        shell = new TermGui();
        return shell;
    }

    public static void startBattle() {
        TermGui.flush();
        TermGui.printMessageWithFlush("YOU'VE MET A VILLAIN. WHAT ARE YOU GOING TO DO?");
        System.out.print("> FIGHT\n> RUN\n");
        System.out.print("> ");
    }

    public static void playerDied() {
        TermGui.printError("YOU DIED. GAME OVER ON LEVEL " + Hero.getHero().getLevel());
    }

    public static void pickMovement() {
        TermGui.flush();
        TermGui.printMessageWithFlush("WHERE TO MOVE? : 'RIGHT/R', 'LEFT/L', 'UP/U', 'DOWN/D', 'INFO'");
        System.out.print("> ");
    }

    public static void pickGameMode() {
        TermGui.flush();
        System.out.print("> CONSOLE\n> GUI\n> ");
    }

    public static void pickHero() {
        TermGui.flush();
        System.out.print("> CREATE HERO\n> SELECT CREATED HERO\n> ");
    }

    public static void pickName() {
        TermGui.flush();
        System.out.print("WHAT'S YOUR NAME, STRANGER?\n> ");
    }

    public static void pickPrize(String prizeName) {
        TermGui.flush();
        System.out.print(prizeName + " HAS DROPPED FROM KILLED ENEMY. WOULD YOU LIKE TO TAKE IT? (YES/NO) > ");
    }

    public static void info(Hero hero) {
        TermGui.flush();
        System.out.format("NAME:                %s\n", hero.getName().toUpperCase());
        System.out.format("CLASS:               %s\n", hero.getCharacterClass().getGameClass());
        System.out.format("HEALTH POINTS:       %d/%d\n", hero.getHp(), Hero.getHero().getMaxHp());
        System.out.format("level:               %d\n", hero.getLevel());
        System.out.format("experience:          %d/%d\n", hero.getExperience(), Game.getNextLevelExperience());
        System.out.format("attack:              %d\n", hero.getAttack());
        System.out.format("defence:             %d\n", hero.getDefense());
        System.out.format("hit points:          %d\n", hero.getHitPoints());
        if (hero.getWeapon() != null)
            System.out.format("weapon:          %s\n", hero.getWeapon().getName());
        if (hero.getArmor() != null)
            System.out.format("armor:           %s\n", hero.getArmor().getName());
        if (hero.getHelm() != null)
            System.out.format("helmet:          %s\n", hero.getHelm().getName());
    }

    public static void printError(String message) {
        flush();
        System.out.println(message);
    }

    public static void printMessageWithoutFlash(String message) {
        System.out.println(message);
    }

    public static void printMessageWithFlush(String message) {
        flush();
        System.out.println(message);
    }

    public static void pickClass() {
        TermGui.flush();
        System.out.print("> WARRIOR (chance of critical damage)\n" +
                "> WIZARD (chance of freeze enemy)\n" +
                " ARCHER (chance of miss, but less more damage)\n> ");
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
