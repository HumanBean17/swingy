package com.swingy.controller;

import com.swingy.Game;
import com.swingy.model.armor.Armor;
import com.swingy.model.armor.IronArmor;
import com.swingy.model.armor.WoodenArmor;
import com.swingy.model.armor.CooperArmor;
import com.swingy.model.cclasses.CharacterClass;
import com.swingy.model.characters.Character;
import com.swingy.model.characters.Hero;
import com.swingy.model.characters.Villain;
import com.swingy.model.helm.IronHelm;
import com.swingy.model.helm.Helm;
import com.swingy.model.helm.WoodenHelm;
import com.swingy.model.helm.CooperHelm;
import com.swingy.model.weapon.Axe;
import com.swingy.model.weapon.Claymore;
import com.swingy.model.weapon.Knife;
import com.swingy.model.weapon.Weapon;
import com.swingy.view.TermGui;

import java.util.Random;

public class Battle {

    public static boolean startBattle(Villain villain) {
        boolean isHeroWon = true;
        while (true) {
            Hero.getHero().attack(villain);
            villain.attack(Hero.getHero());
            if (Hero.getHero().getHp() <= 0) {
                TermGui.printMessage("Villain has " + villain.getHp() + " health points");
                isHeroWon = false;
                break;
            } else if (villain.getHp() <= 0) {
                TermGui.printMessage("You've won the battle!");
                Hero.getHero().addExperience(villain.getLevel() * 300 + 200);
                Hero.getHero().setHp(Hero.getHero().getMaxHp());
                getPrize(villain, Hero.getHero());
                Game.deleteVillain(villain);
                Game.checkNextLevel();
                break;
            }
            try {
                Thread.sleep(250L);
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

    public static void getPrize(Character enemy, Character hero) {
        int rand = new Random().nextInt(100);
        System.out.println(rand);
        if (rand % 5 == 0) {
            rand = new Random().nextInt(6);
            Weapon weapon = null;
            Armor armor = null;
            Helm helm = null;
            switch (enemy.getLevel()) {
                case 0:
                    if (rand == 0)
                        weapon = getWeaponPrize(hero.getCharacterClass(), 0);
                    break;
                case 1:
                    if (rand == 0)
                        weapon = getWeaponPrize(hero.getCharacterClass(), 1);
                    else if (rand == 1)
                        armor = new WoodenArmor();
                    else
                        helm = new WoodenHelm();
                    break;
                case 2:
                    if (rand == 0)
                        weapon = getWeaponPrize(hero.getCharacterClass(), 2);
                    else if (rand == 1)
                        armor = new WoodenArmor();
                    else
                        helm = new WoodenHelm();
                    break;
                case 3:
                    if (rand == 0)
                        weapon = getWeaponPrize(hero.getCharacterClass(), 3);
                    else if (rand == 1)
                        armor = new CooperArmor();
                    else
                        helm = new CooperHelm();
                    break;
                case 4:
                    if (rand == 0)
                        weapon = getWeaponPrize(hero.getCharacterClass(), 4);
                    else if (rand == 1)
                        armor = new CooperArmor();
                    else
                        helm = new CooperHelm();
                    break;
                case 5:
                    if (rand == 0)
                        weapon = getWeaponPrize(hero.getCharacterClass(), 5);
                    else if (rand == 1)
                        armor = new IronArmor();
                    else
                        helm = new IronHelm();
                    break;
            }
            if (weapon != null || armor != null || helm != null) {
                if (weapon != null) {
                    if (MainController.pickPrize(weapon.getName())) {
                        hero.setWeapon(weapon);
                    }
                } else if (armor != null) {
                    if (MainController.pickPrize(armor.getName())) {
                        hero.setArmor(armor);
                    }
                } else {
                    if (MainController.pickPrize(helm.getName())) {
                        hero.setHelm(helm);
                    }
                }
            }
        }
    }

    private static Weapon getWeaponPrize(CharacterClass characterClass, int level) {
        if (characterClass.getGameClass().equals(CharacterClass.GameClass.WARRIOR)) {
            switch (level) {
                case 0:
                    break;
                case 1:
                    return new Knife();
                case 2:
                    break;
                case 3:
                    return new Claymore();
                case 4:
                    break;
                case 5:
                    return new Axe();
                default:
                    break;
            }
        }
        return null;
    }

}
