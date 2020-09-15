package com.swingy.model.characters;

import com.swingy.controller.MainController;
import com.swingy.gui.Coordinates;
import com.swingy.gui.Map;
import com.swingy.model.armor.LightArmor;
import com.swingy.model.helm.LightHelm;
import com.swingy.model.weapon.Claymore;
import com.swingy.model.weapon.Weapon;
import com.swingy.model.armor.Armor;
import com.swingy.model.helm.Helm;

public class Hero {

    private ClassCharacter classCharacter;
    private static String heroName;

    private Weapon weapon;
    private Armor armor;
    private Helm helm;

    private boolean isAlive = true;

    private Integer level = 1;
    private Integer experience = 0;

    private Coordinates coordinates;

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
        classCharacter = new Warrior();
        weapon = new Claymore();
        armor = new LightArmor();
        helm = new LightHelm();
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

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public void moveRight() {
        coordinates.incX();
    }

    public void moveLeft() {
        coordinates.decX();
    }

    public void moveUp() {
        coordinates.incY();
    }

    public void moveDown() {
        coordinates.decY();
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setDead() {
        isAlive = false;
    }
}
