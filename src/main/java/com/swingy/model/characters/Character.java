package com.swingy.model.characters;

import com.swingy.Game;
import com.swingy.Main;
import com.swingy.map.Coordinates;
import com.swingy.model.armor.Armor;
import com.swingy.model.cclasses.CharacterClass;
import com.swingy.model.helm.Helm;
import com.swingy.model.weapon.Weapon;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.validation.ConstraintViolation;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Random;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@ToString
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
    @NotBlank(message = "NAME IS EMPTY")
    @Size(min = 3, max = 16, message = "NAME IS TOO LONG")
    @Pattern(regexp = "[A-Za-z0-9-_]+", message = "NAME CONTAINS UNSUPPORTED CHARACTERS")
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

    public Integer attack(Character enemy) {
        int damage = 0;
        if (name.equals("villain") && Hero.getHero().getCharacterClass().getGameClass().equals(CharacterClass.GameClass.WIZARD) && specialTalentRandomizer()) {
            Main.gui.enemyFreeze(characterClass, name);
            return damage;
        }
        if (characterClass.getGameClass().equals(CharacterClass.GameClass.ARCHER) && specialTalentRandomizer()) {
            Main.gui.miss(characterClass, name);
        } else {
            if (characterClass.getGameClass().equals(CharacterClass.GameClass.WARRIOR) && specialTalentRandomizer()) {
                Main.gui.criticalDamage();
                damage = attack + (hitPoints > 0 ? new Random().nextInt(hitPoints) : 0) * 3;
            } else {
                damage = attack + (hitPoints > 0 ? new Random().nextInt(hitPoints) : 0);
            }
            Main.gui.attack(characterClass, name, enemy, damage);
            enemy.takeDamage(damage);
        }
        return damage;
    }

    public Integer takeDamage(Integer damage) {
        int takenDamage = damage;
        if (defense > 0)
            takenDamage = Math.max(damage - new Random().nextInt(defense), 0);
        hp -= takenDamage;
        Main.gui.takeDamage(characterClass, name, takenDamage, hp);
        return takenDamage;
    }

    public static boolean validate(boolean errorMessageInvoke) {
        Set<ConstraintViolation<Character>> violations = Game.validator.validate(Hero.getHero());
        boolean result = true;
        for (ConstraintViolation<Character> violation : violations) {
            result = false;
            if (errorMessageInvoke)
                Main.gui.validationError(violation.getMessage());
        }
        return result;
    }

    public boolean specialTalentRandomizer() {
        return new Random().nextInt(100) % 10 == 0;
    }

    public void increaseLevel() {
        level++;
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
