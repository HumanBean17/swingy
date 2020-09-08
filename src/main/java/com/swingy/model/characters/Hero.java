package com.swingy.model.characters;

import com.swingy.model.armor.LightArmor;
import com.swingy.model.helm.LightHelm;
import com.swingy.model.weapon.Claymore;
import com.swingy.model.weapon.Weapon;
import com.swingy.model.armor.Armor;
import com.swingy.model.helm.Helm;

import java.util.Scanner;

public class Hero {

    private static final Scanner scanner = new Scanner(System.in);

    private ClassCharacter heroClass;
    private String heroName;

    private Weapon weapon;
    private Armor armor;
    private Helm helm;

    private void createBerserk() {
        heroClass = new Berserk();
        weapon = new Claymore();
        armor = new LightArmor();
        helm = new LightHelm();
    }

    private void createWizard() {

    }

    private void createArcher() {

    }

    private void pickName() {
        System.out.print("What's your name, stranger?\n> ");
        heroName = scanner.next();
        System.out.println("Hi," + heroName);
    }

    private int pickClass() {
        while (true) {
            System.out.print("[1] BERSERK\n[2] WIZARD\n[3] ARCHER\n> ");
            int userInput = Integer.parseInt(scanner.next());
            if (userInput == 1) {
                return userInput;
            } else if (userInput == 2) {
                //heroClass = HeroClass.WIZARD;
                return userInput;
            } else if (userInput == 3) {
                //heroClass = HeroClass.ARCHER;
                return userInput;
            }
        }
    }

    public Hero() {
        pickClass();
        pickName();
    }
}
