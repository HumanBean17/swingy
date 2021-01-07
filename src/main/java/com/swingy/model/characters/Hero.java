package com.swingy.model.characters;

import com.swingy.controller.MainController;
import com.swingy.gui.Coordinates;
import com.swingy.model.armor.LightArmor;
import com.swingy.model.helm.LightHelm;
import com.swingy.model.weapon.Claymore;
import com.swingy.model.weapon.Weapon;
import com.swingy.model.armor.Armor;
import com.swingy.model.helm.Helm;

public class Hero extends Character {

    private Integer experience = 0;

    private static Hero hero;

    public Hero() {
        coordinates = new Coordinates();
        pickClass();
        pickName();
    }

    public static Hero createHero() {
        hero = new Hero();
        return hero;
    }

    public static Hero getHero() {
        return hero;
    }

    private void createBerserk() {
        CharacterClass character = new Warrior(this);
        Weapon weapon = new Claymore();
        Armor armor = new LightArmor();
        Helm helm = new LightHelm();
    }

    private void createWizard() {

    }

    private void createArcher() {

    }

    public void pickName() {
        MainController.pickName();
    }

    public void pickClass() {
        String chosenClass = MainController.pickClass();
        if (chosenClass.equals("WARRIOR")) {
            createBerserk();
        } else if (chosenClass.equals("WIZARD")) {
            createWizard();
        } else if (chosenClass.equals("ARCHER")) {
            createArcher();
        }
    }

    public Integer getExperience() {
        return experience;
    }

    public void setExperience(Integer experience) {
        this.experience = experience;
    }

}
