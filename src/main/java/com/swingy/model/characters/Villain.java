package com.swingy.model.characters;

import com.swingy.map.Coordinates;
import com.swingy.model.cclasses.Archer;
import com.swingy.model.cclasses.CharacterClass;
import com.swingy.model.cclasses.Warrior;
import com.swingy.model.cclasses.Wizard;

import java.util.Random;

public class Villain extends Character {

    public Villain(Coordinates coordinates) {
        super();
        CharacterClass[] classes = new CharacterClass[3];
        classes[0] = new Warrior();
        classes[1] = new Archer();
        classes[2] = new Wizard();
        this.characterClass = classes[new Random().nextInt(3)];
        this.level = (new Random().nextBoolean() ? 1 : 0) + Hero.getHero().getLevel();
        this.hp = this.getLevel() * 50 + 80;
        this.maxHp = this.hp;
        this.attack = Hero.getHero().getAttack() +
                new Random().nextInt(Hero.getHero().getDefense());//new Random().nextInt(3) + new Random().nextInt(5) * this.getLevel();
        this.hitPoints = new Random().nextInt(3) +
                new Random().nextInt(2) * this.getLevel();
        //System.out.println("villain attack " + this.attack);
        this.name = "villain";
        setCoordinates(coordinates);
    }
}
