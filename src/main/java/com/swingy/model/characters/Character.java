package com.swingy.model.characters;

import com.swingy.gui.Coordinates;
import com.swingy.model.armor.Armor;
import com.swingy.model.cclasses.CharacterClass;
import com.swingy.model.helm.Helm;
import com.swingy.model.weapon.Weapon;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Id;
import java.util.Random;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public abstract class Character {

    @Id
    @Column(name = "ID")
    protected UUID id;

    @Column(name = "COORDINATES")
    protected Coordinates coordinates;

    @Column(name = "ATTACK")
    protected Integer attack = 0;
    @Column(name = "DEFENSE")
    protected Integer defense = 0;
    @Column(name = "HIT_POINTS")
    protected Integer hitPoints = 0;

    @Column(name = "MAX_HP")
    protected Integer maxHp = 0;
    @Column(name = "HP")
    protected Integer hp = 0;

    @Column(name = "MANA")
    protected Integer mana = 0;

    @Column(name = "CHARACTER_CLASS")
    protected CharacterClass characterClass;

    @Column(name = "NAME")
    protected String name;

    @Column(name = "WEAPON")
    protected Weapon weapon;
    @Column(name = "ARMOR")
    protected Armor armor;
    @Column(name = "HELM")
    protected Helm helm;

    @Column(name = "LEVEL")
    protected Integer level = 0;

    public Character() {
        this.id = UUID.randomUUID();
    }

    // TODO: implement special talents
    public Integer attack(Character enemy) {
        Integer damage = this.attack + (this.hitPoints > 0 ? new Random().nextInt(this.hitPoints) : 0);
        System.out.println(this.characterClass.getClassName() + " " + this.name + " attacks " +
                enemy.getCharacterClass().getClassName() + " " + enemy.getName() + " with damage " + damage);
        enemy.takeDamage(this, damage);
        return damage;
    }

    public Integer takeDamage(Character enemy, Integer damage) {
        int takenDamage = damage;
        if (this.defense > 0)
            takenDamage = Math.max(damage - new Random().nextInt(this.defense), 0);
        this.hp -= takenDamage;
        System.out.println(this.characterClass.getClassName() + " " + this.name + " takes damage " +
                takenDamage + " and has " + this.hp + " health points");
        return takenDamage;
    }

    public void increaseLevel() {
        this.level++;
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

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
        this.attack = this.weapon.getAttack();
    }

    public void setArmor(Armor armor) {
        this.armor = armor;
        this.defense = this.armor.getDefense();
    }

    public void setHelm(Helm helm) {
        this.helm = helm;
        this.hitPoints = this.helm.getHitPoints();
    }

}
