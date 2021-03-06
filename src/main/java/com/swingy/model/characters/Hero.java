package com.swingy.model.characters;

import com.swingy.Main;
import com.swingy.map.Coordinates;
import com.swingy.map.Map;
import com.swingy.model.armor.ClothArmor;
import com.swingy.model.cclasses.Archer;
import com.swingy.model.cclasses.CharacterClass;
import com.swingy.model.cclasses.Warrior;
import com.swingy.model.cclasses.Wizard;
import com.swingy.model.helm.ClothHelm;
import com.swingy.model.weapon.Fists;
import com.swingy.model.weapon.Slingshot;
import com.swingy.model.weapon.Staff;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "HERO")
@ToString(callSuper = true)
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
        hero.maxHp = 100;
        hero.hp = 100;
        hero.pickName();
        if (!validate(true)) {
            hero = null;
            return null;
        }
        hero.pickClass();
        return hero;
    }

    public static void deleteHero() {
        hero = null;
    }

    private void createWarrior() {
        hero.characterClass = new Warrior();
        setWeapon(new Fists());
        setArmor(new ClothArmor());
        setHelm(new ClothHelm());
    }

    private void createWizard() {
        hero.characterClass = new Wizard();
        setWeapon(new Staff());
        setArmor(new ClothArmor());
        setHelm(new ClothHelm());
    }

    private void createArcher() {
        hero.characterClass = new Archer();
        setWeapon(new Slingshot());
        setArmor(new ClothArmor());
        setHelm(new ClothHelm());
    }

    public void pickName() {
        this.name = Main.controller.pickName();
    }

    public void pickClass() {
        CharacterClass.GameClass chosenClass = Main.controller.pickClass();
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
