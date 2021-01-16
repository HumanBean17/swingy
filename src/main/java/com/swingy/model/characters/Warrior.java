package com.swingy.model.characters;

public class Warrior implements CharacterClass {

    private final static String className = "WARRIOR";

    @Override
    public Talent specialTalent(Character character) {
        if (character.getMana() % 3 == 0) {
            return Talent.CRITICAL_DAMAGE;
        }
        return null;
    }

    @Override
    public String getClassName() {
        return className;
    }
}
