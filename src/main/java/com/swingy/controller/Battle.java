package com.swingy.controller;

import com.swingy.Game;
import com.swingy.model.characters.Hero;
import com.swingy.model.characters.Villain;

import java.util.Random;

public class Battle {


    public static boolean startBattle(Villain villain) {
        boolean isHeroWon = true;
        while (true) {
            Hero.getHero().attack(villain);
            villain.attack(Hero.getHero());
            if (Hero.getHero().getHp() <= 0) {
                System.out.println("Villain has " + villain.getHp() + " health points");
                isHeroWon = false;
                break;
            } else if (villain.getHp() <= 0) {
                System.out.println("You've won the battle!");
                Hero.getHero().addExperience(villain.getLevel() * 300 + 200);
                Hero.getHero().setHp(Hero.getHero().getMaxHp());
                Game.checkNextLevel();
                break;
            }
            try {
                Thread.sleep(1L);
            } catch (InterruptedException exception) {
                exception.printStackTrace();
            }
        }
        return isHeroWon;
    }

    public static boolean run() {
        boolean isRun = true;
        if (new Random().nextInt(2) != 0) {
            Hero.getHero().setCoordinates(Game.getHeroLastPos());
        } else {
            isRun = false;
        }
        return isRun;
    }

}
