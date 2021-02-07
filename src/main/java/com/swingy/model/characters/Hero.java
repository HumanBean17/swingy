package com.swingy.model.characters;

import com.swingy.controller.MainController;
import com.swingy.gui.Coordinates;
import com.swingy.model.armor.LightArmor;
import com.swingy.model.helm.LightHelm;
import com.swingy.model.weapon.Claymore;

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

    public static void deleteHero() {
        hero = null;
    }

    private void createBerserk() {
        hero.maxHp = 105;
        hero.hp = 105;
        hero.characterClass = new Warrior();
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
        GameClass chosenClass = MainController.pickClass();
        if (chosenClass.equals(GameClass.WARRIOR)) {
            createBerserk();
        } else if (chosenClass.equals(GameClass.WIZARD)) {
            createWizard();
        } else if (chosenClass.equals(GameClass.ARCHER)) {
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
