package com.swingy;

import com.swingy.model.characters.Hero;

import java.util.Scanner;

public class Game {

    private static final Scanner scanner = new Scanner(System.in);

    public Hero hero;

    private int pickGameMode() {
        while (true) {
            System.out.print("[1] CONSOLE\n[2] GUI\n> ");
            int userInput = Integer.parseInt(scanner.next());
            if (userInput == 1 || userInput == 2) {
                return userInput;
            }
        }
    }

    private int pickHero() {
        while (true) {
            System.out.print("[1] CREATE HERO\n[2] SELECT CREATED HERO\n> ");
            int userInput = Integer.parseInt(scanner.next());
            if (userInput == 1 || userInput == 2) {
                return userInput;
            }
        }
    }

    public void run() {
        pickGameMode();
        if (pickHero() == 1) {
            hero = new Hero();
        }
    }
}
