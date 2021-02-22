package com.swingy.model.characters;

public class Wizard extends CharacterClass {

    private final static String className = "Wizard";

    @Override
    public Talent specialTalent(Character character) {
        if (character.getMana() % 3 == 0) {
            return Talent.FREEZE;
        }
        return null;
    }

    @Override
    public String getClassName() {
        return className;
    }
}
