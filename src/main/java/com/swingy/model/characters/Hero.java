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
        super();
        coordinates = new Coordinates();
    }

    public static Hero createHero() {
        hero = new Hero();
        return hero;
    }

    private void createBerserk() {
        hero.maxHp = 105;
        hero.hp = 105;
        this.characterClass = new Warrior();
        setWeapon(new Claymore());
        setArmor(new LightArmor());
        setHelm(new LightHelm());
    }

    private void createWizard() {

    }

    private void createArcher() {

    }

    public void pickName() {
        this.name = MainController.pickName();
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

    public static Hero getHero() {
        return hero;
    }

    public Integer getExperience() {
        return experience;
    }

    public void addExperience(Integer experience) {
        this.experience += experience;
    }

    public void setExperience(Integer experience) {
        this.experience = experience;
    }

}
