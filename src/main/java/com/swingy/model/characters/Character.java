package com.swingy.model.characters;

import com.swingy.gui.Coordinates;
import com.swingy.model.armor.Armor;
import com.swingy.model.helm.Helm;
import com.swingy.model.weapon.Weapon;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public abstract class Character {

    protected static Integer bankPoint = 10;

    protected Coordinates coordinates;

    protected Integer attack = 0;
    protected Integer defense = 0;
    protected Integer hitPoints = 0;

    protected Integer maxHp = 0;
    protected Integer hp = 0;

    protected Integer mana = 0;

    protected CharacterClass characterClass;

    protected String name;

    protected Weapon weapon;
    protected Armor armor;
    protected Helm helm;

    protected Integer level = 0;

    public Integer attack(Character enemy) {
        Integer damage = this.attack + (this.hitPoints > 0 ? new Random().nextInt(this.hitPoints) : 0);
        System.out.println(this.characterClass.getClassName() + " " + this.name + " attacks " +
                enemy.getCharacterClass().getClassName() + " " + enemy.getName() + " with damage " + damage);
        enemy.takeDamage(this, damage);
        return damage;
    }

    public Integer takeDamage(Character enemy, Integer damage) {
        int takenDamage = Math.max(damage - this.defense, 0);
        this.hp -= takenDamage;
        System.out.println(this.characterClass.getClassName() + " " + this.name + " takes damage " +
                takenDamage + " and has " + this.hp + " health points");
        /*if (this.hp > 0 && new Random().nextInt(this.hp) % 12 == 0) {
            enemy.takeDamage(this, new Random().nextInt((this.attack / 5) + 1));
        }*/
        return takenDamage;
    }

    public void increaseLevel() {
        this.level++;
    }

    public Integer getMaxHp() {
        return maxHp;
    }

    public void setMaxHp(Integer maxHp) {
        this.maxHp = maxHp;
    }

    public Integer getHp() {
        return hp;
    }

    public void setHp(Integer hp) {
        this.hp = hp;
    }

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
        return hp > 0;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getMana() {
        return mana;
    }

    public void setMana(Integer mana) {
        this.mana = mana;
    }

    public static Integer getBankPoint() {
        return bankPoint;
    }

    public static void setBankPoint(Integer bankPoint) {
        Character.bankPoint = bankPoint;
    }

    public CharacterClass getCharacterClass() {
        return characterClass;
    }

    public void setCharacterClass(CharacterClass characterClass) {
        this.characterClass = characterClass;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
        this.attack = this.weapon.getAttack();
    }

    public Armor getArmor() {
        return armor;
    }

    public void setArmor(Armor armor) {
        this.armor = armor;
        this.defense = this.armor.getDefense();
    }

    public Helm getHelm() {
        return helm;
    }

    public void setHelm(Helm helm) {
        this.helm = helm;
        this.hitPoints = this.helm.getHitPoints();
    }

}
