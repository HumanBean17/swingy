package com.swingy.model;

import java.util.Scanner;

public class Hero {

    private static final Scanner scanner = new Scanner(System.in);

    enum HeroClass {
        BERSERK("BERSERK"),
        WIZARD("WIZARD"),
        ARCHER("ARCHER");
        HeroClass(String s) {}
    }

    public HeroClass heroClass;

    private int pickClass() {
        while (true) {
            System.out.print("[1] BERSERK\n[2] WIZARD\n[3] ARCHER\n> ");
            int userInput = Integer.parseInt(scanner.next());
            if (userInput == 1) {
                heroClass = HeroClass.BERSERK;
                return userInput;
            } else if (userInput == 2) {
                heroClass = HeroClass.WIZARD;
                return userInput;
            } else if (userInput == 3) {
                heroClass = HeroClass.ARCHER;
                return userInput;
            }
        }
    }

    public Hero() {
        pickClass();
    }
}
