package com.swingy.model.characters;

import com.swingy.gui.Coordinates;

import java.util.Random;

public class Villain extends Character {

    public Villain(Coordinates coordinates) {
        super();
        CharacterClass[] classes = new CharacterClass[1];
        classes[0] = new Warrior();
        //classes[1] =
        this.characterClass = classes[new Random().nextInt(1)];
        this.level = (new Random().nextBoolean() ? 1 : 0) + Hero.getHero().getLevel();
        this.hp = this.getLevel() * 50 + 100;
        this.maxHp = this.hp;
        this.attack = new Random().nextInt(3) + 2 * this.getLevel();
        this.hitPoints = new Random().nextInt(3) * this.getLevel();
        this.name = "villain";
        setCoordinates(coordinates);
    }
}
