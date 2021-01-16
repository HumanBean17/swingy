package com.swingy.controller;

import com.swingy.gui.Map;
import com.swingy.model.characters.Hero;
import com.swingy.model.characters.Villain;
import com.swingy.view.ShellGui;

import java.util.Scanner;

public class MainController {

    private static final Scanner scanner = new Scanner(System.in);

    public static void battleHandler(Villain villain) {
        System.out.println("You've met a villain. What are you going to do?");
        System.out.print("[1] FIGHT\n[2] RUN\n");
        System.out.print("> ");
        int userInput = Integer.parseInt(scanner.next().toLowerCase());
        boolean isHeroWon = true;
        if (userInput == 1) {
            isHeroWon = Battle.startBattle(villain);
        } else if (userInput == 2) {

        } else {
            ShellGui.error();
        }
        if (!isHeroWon) {
            System.out.println("You died. Game over on level " + Hero.getHero().getLevel());
        }
    }

    public static MoveDirection pickMovement() {
        Hero hero = Hero.getHero();
        System.out.println("Where to move? : 'east/E', 'west/W', 'north/N', 'south/S', 'info'");
        System.out.print("> ");
        String userInput = scanner.next().toLowerCase();
        System.out.println(hero.getCoordinates().getX() + " " + Map.getMap().getSize());
        MoveDirection direction = MoveDirection.BORDER;
        if ((userInput.equals("east") ||
                userInput.equals("e")) && ((hero.getCoordinates().getX() + 1) < Map.getMap().getSize())) {
            hero.moveRight();
            direction = MoveDirection.EAST;
        } else if ((userInput.equals("west") ||
                userInput.equals("w")) && ((hero.getCoordinates().getX() - 1) >= 0)) {
            hero.moveLeft();
            direction = MoveDirection.WEST;
        } else if ((userInput.equals("north") ||
                userInput.equals("n")) && ((hero.getCoordinates().getY() - 1) >= 0)) {
            hero.moveUp();
            direction = MoveDirection.NORTH;
        } else if ((userInput.equals("south") ||
                userInput.equals("s")) && ((hero.getCoordinates().getY() + 1) < Map.getMap().getSize())) {
            hero.moveDown();
            direction = MoveDirection.SOUTH;
        } else if (userInput.equals("info")) {

        }
        return direction;
    }

    public static int pickGameMode() {
        while (true) {
            System.out.print("[1] CONSOLE\n[2] GUI\n> ");
            int userInput = Integer.parseInt(scanner.next());
            if (userInput == 1 || userInput == 2) {
                return userInput;
            }
        }
    }

    public static int pickHero() {
        while (true) {
            System.out.print("[1] CREATE HERO\n[2] SELECT CREATED HERO\n> ");
            int userInput = Integer.parseInt(scanner.next());
            if (userInput == 1 || userInput == 2) {
                return userInput;
            }
        }
    }

    public static String pickName() {
        System.out.print("What's your name, stranger?\n> ");
        String heroName = scanner.next();
        System.out.println("Hi," + heroName);
        return heroName;
    }

    public static String pickClass() {
        while (true) {
            ShellGui.pickClass();
            System.out.print("[1] WARRIOR(chance of critical damage)\n" +
                    "[2] WIZARD(chance of freeze enemy)\n" +
                    "[3] ARCHER(chance of miss, but enemy skips first move)\n> ");
            int userInput = Integer.parseInt(scanner.next());
            if (userInput == 1) {
                return "WARRIOR";
            } else if (userInput == 2) {
                //heroClass = HeroClass.WIZARD;
                throw new UnsupportedOperationException();
                //return userInput;
            } else if (userInput == 3) {
                //heroClass = HeroClass.ARCHER;
                throw new UnsupportedOperationException();
                //return userInput;
            } else {
                ShellGui.error();
            }
        }
    }
}
