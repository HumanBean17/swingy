package com.swingy.controller;

import com.swingy.gui.Map;
import com.swingy.model.characters.Hero;
import com.swingy.view.ShellGui;

import java.util.Scanner;

public class MainController {

    private static final Scanner scanner = new Scanner(System.in);

    public static boolean pickMovement(Hero hero) {
        System.out.print("> ");
        String userInput = scanner.next();
        System.out.println(hero.getCoordinates().getX() + " " + Map.getMap().getSize());
        if (userInput.toLowerCase().equals("right") && ((hero.getCoordinates().getX() + 1) < Map.getMap().getSize())) {
            hero.moveRight();
        } else if (userInput.toLowerCase().equals("left") && ((hero.getCoordinates().getX() - 1) > 0)) {
            hero.moveLeft();
        } else if (userInput.toLowerCase().equals("up") && ((hero.getCoordinates().getY() + 1) < Map.getMap().getSize())) {
            hero.moveUp();
        } else if (userInput.toLowerCase().equals("down") && ((hero.getCoordinates().getY() - 1) > 0)) {
            hero.moveDown();
        } else {
            return false;
        }
        return true;
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
            System.out.print("[1] WARRIOR\n[2] WIZARD\n[3] ARCHER\n> ");
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
