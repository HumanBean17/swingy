package com.swingy.model.characters;

import com.swingy.gui.Coordinates;
import com.swingy.model.armor.Armor;
import com.swingy.model.helm.Helm;
import com.swingy.model.weapon.Weapon;

public abstract class Character {

    protected static Integer bankPoint = 10;

    protected Coordinates coordinates;

    protected Integer attack = 0;
    protected Integer defense = 0;
    protected Integer hitPoints = 0;

    private CharacterClass character;
    private static String heroName;

    private Weapon weapon;
    private Armor armor;
    private Helm helm;

    private Integer level = 1;

    private boolean isAlive = true;

    public Integer getAttack() {
        return attack;
    }

    public void setAttack(Integer attack) {
        bankPoint -= attack - this.attack;
        this.attack = attack;
    }

    public Integer getDefense() {
        return defense;
    }

    public void setDefense(Integer defense) {
        bankPoint -= defense -= this.defense;
        this.defense = defense;
    }

    public Integer getHitPoints() {
        return hitPoints;
    }

    public void setHitPoints(Integer hitPoints) {
        bankPoint -= hitPoints -= this.hitPoints;
        this.hitPoints = hitPoints;
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

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

}
