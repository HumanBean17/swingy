package com.swingy.controller;

import com.swingy.Game;
import com.swingy.Main;
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
import com.swingy.model.weapon.*;

import java.util.Random;

public class Battle {

    public static boolean startBattle(Villain villain) {
        boolean isHeroWon = true;
        while (true) {
            Hero.getHero().attack(villain);
            if (villain.getHp() <= 0) {
                villain.setHp(0);
                Main.gui.battleWin();
                Hero.getHero().addExperience(villain.getLevel() * 300 + 200);
                Hero.getHero().setHp(Hero.getHero().getMaxHp());
                getPrize(villain, Hero.getHero());
                Game.deleteVillain(villain);
                Game.checkNextLevel();
                break;
            }
            villain.attack(Hero.getHero());
            if (Hero.getHero().getHp() <= 0) {
                Hero.getHero().setHp(0);
                Main.gui.battleLost(villain);
                isHeroWon = false;
                break;
            }
            try {
                Thread.sleep(350L);
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
        if (rand % 2 == 0) {
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
                    if (Main.controller.pickPrize(weapon.getName())) {
                        hero.setWeapon(weapon);
                    }
                } else if (armor != null) {
                    if (Main.controller.pickPrize(armor.getName())) {
                        hero.setArmor(armor);
                    }
                } else {
                    if (Main.controller.pickPrize(helm.getName())) {
                        hero.setHelm(helm);
                    }
                }
            }
        }
    }

    private static Weapon getWeaponPrize(CharacterClass characterClass, int level) {
        if (characterClass.getGameClass().equals(CharacterClass.GameClass.WARRIOR)) {
            switch (level) {
                case 1:
                case 2:
                    return new Knife();
                case 3:
                case 4:
                    return new Claymore();
                case 5:
                case 6:
                    return new Axe();
                default:
                    break;
            }
        } else if (characterClass.getGameClass().equals(CharacterClass.GameClass.ARCHER)) {
            switch (level) {
                case 1:
                case 2:
                    return new SmallBow();
                case 3:
                case 4:
                    return new MediumBow();
                case 5:
                case 6:
                    return new LargeBow();
                default:
                    break;
            }
        } else if (characterClass.getGameClass().equals(CharacterClass.GameClass.WIZARD)) {
            switch (level) {
                case 1:
                case 2:
                    return new StaffWithEnchantedGlass();
                case 3:
                case 4:
                    return new StaffWithEnchantedStone();
                case 5:
                case 6:
                    return new DiamondStaff();
                default:
                    break;
            }
        }
        return null;
    }

}
