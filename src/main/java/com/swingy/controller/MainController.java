package com.swingy.controller;

import com.swingy.Game;
import com.swingy.gui.Coordinates;
import com.swingy.gui.Map;
import com.swingy.model.characters.Hero;
import com.swingy.model.characters.Villain;
import com.swingy.view.TermGui;

import java.util.Scanner;

public class MainController {

    private static final Scanner scanner = new Scanner(System.in);

    public static boolean battleHandler(Villain villain) {
        System.out.println("You've met a villain. What are you going to do?");
        System.out.print("[1] FIGHT\n[2] RUN\n");
        System.out.print("> ");
        int userInput = Integer.parseInt(scanner.next().toLowerCase());
        boolean isHeroWon = true;
        if (userInput == 1 || (userInput == 2 && !Battle.run())) {
            isHeroWon = Battle.startBattle(villain);
        } else {
            TermGui.error();
        }
        if (!isHeroWon) {
            System.out.println("You died. Game over on level " + Hero.getHero().getLevel());
        }
        return isHeroWon;
    }

    public static MoveDirection pickMovement() {
        Hero hero = Hero.getHero();
        TermGui.flush();
        System.out.println("Where to move? : 'east/E', 'west/W', 'north/N', 'south/S', 'info'");
        System.out.print("> ");
        String userInput = scanner.next().toLowerCase();
        System.out.println(hero.getCoordinates().getX() + " " + Map.getMap().getSize());
        MoveDirection direction = MoveDirection.BORDER;
        Game.setHeroLastPos(hero.getCoordinates());
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
            TermGui.flush();
            System.out.format("name: %30s\n", hero.getName());
            System.out.format("class: %30s\n", hero.getCharacterClass().getClassName());
            System.out.format("health points: %30d/%d\n", hero.getHp(), Hero.getHero().getMaxHp());
            System.out.format("level: %30d\n", hero.getLevel());
            System.out.format("experience: %30d/%d\n", hero.getExperience(), Game.getNextLevelExperience());
            System.out.format("attack: %30d\n", hero.getAttack());
            System.out.format("defence: %30d\n", hero.getDefense());
            System.out.format("hit points: %30d\n", hero.getHitPoints());
            if (hero.getWeapon() != null)
                System.out.format("weapon: %30s\n", hero.getWeapon().getName());
            if (hero.getArmor() != null)
                System.out.format("armor: %30s\n", hero.getArmor().getName());
            if (hero.getHelm() != null)
                System.out.format("helmet: %30s\n", hero.getHelm().getName());
        }
        return direction;
    }

    public static int pickGameMode() {
        while (true) {
            TermGui.flush();
            System.out.print("[1] CONSOLE\n[2] GUI\n> ");
            int userInput = Integer.parseInt(scanner.next());
            if (userInput == 1 || userInput == 2) {
                return userInput;
            }
        }
    }

    public static int pickHero() {
        while (true) {
            TermGui.flush();
            System.out.print("[1] CREATE HERO\n[2] SELECT CREATED HERO\n> ");
            int userInput = Integer.parseInt(scanner.next());
            if (userInput == 1 || userInput == 2) {
                return userInput;
            }
        }
    }

    public static String pickName() {
        TermGui.flush();
        System.out.print("What's your name, stranger?\n> ");
        String heroName = scanner.next();
        System.out.println("Hi," + heroName);
        return heroName;
    }

    public static String pickClass() {
        while (true) {
            TermGui.flush();
            //TermGui.pickClass();
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
                TermGui.error();
            }
        }
    }

}
