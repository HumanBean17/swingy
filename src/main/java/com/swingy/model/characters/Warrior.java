package com.swingy.model.characters;

public class Warrior extends CharacterClass {

    private final static String className = "Warrior";

    @Override
    public Talent specialTalent(Character character) {
        if (character.getMana() % 3 == 0) {
            return Talent.CRITICAL_DAMAGE;
        }
        return null;
    }

    @Override
    public String getSpecialTalent() {
        return "CRITICAL DAMAGE";
    }

    @Override
    public String getClassName() {
        return className;
    }
}
