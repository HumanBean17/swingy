package com.swingy.model.characters;

public class Warrior extends CharacterClass {

    public Warrior(Character character) {
        character.setAttack(6);
        character.setDefense(1);
        character.setHitPoints(3);
    }
}
