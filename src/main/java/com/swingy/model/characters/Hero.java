package com.swingy.model.characters;

import com.swingy.controller.MainController;
import com.swingy.gui.Coordinates;
import com.swingy.gui.Map;
import com.swingy.model.armor.LightArmor;
import com.swingy.model.cclasses.CharacterClass;
import com.swingy.model.cclasses.Warrior;
import com.swingy.model.helm.LightHelm;
import com.swingy.model.weapon.Fists;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "HERO")
public class Hero extends Character {

    @Column(name = "EXPERIENCE")
    protected Integer experience = 0;

    private static Hero hero;

    public Hero() {
        super();
        coordinates = new Coordinates(Map.getMap().getSize() / 2, Map.getMap().getSize() / 2);
    }

    public void resetHeroPos() {
        hero.getCoordinates().setX(Map.getMap().getSize() / 2);
        hero.getCoordinates().setY(Map.getMap().getSize() / 2);
    }

    public static Hero getHero() {
        if (hero == null)
            createHero();
        return hero;
    }

    public static Hero createHero() {
        hero = new Hero();
        return hero;
    }

    public static void deleteHero() {
        hero = null;
    }

    private void createWarrior() {
        hero.maxHp = 105;
        hero.hp = 105;
        hero.characterClass = new Warrior();
        setWeapon(new Fists());
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
        CharacterClass.GameClass chosenClass = MainController.pickClass();
        if (chosenClass.equals(CharacterClass.GameClass.WARRIOR)) {
            createWarrior();
        } else if (chosenClass.equals(CharacterClass.GameClass.WIZARD)) {
            createWizard();
        } else if (chosenClass.equals(CharacterClass.GameClass.ARCHER)) {
            createArcher();
        }
    }

    public void addExperience(Integer experience) {
        this.experience += experience;
    }

}
