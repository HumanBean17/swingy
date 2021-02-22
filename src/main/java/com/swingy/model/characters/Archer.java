package com.swingy.model.characters;

public class Archer extends CharacterClass {

    private final static String className = "Archer";

    @Override
    public Talent specialTalent(Character character) {
        if (character.getMana() % 3 == 0) {
            return Talent.SKIP_MOVE;
        }
        return null;
    }

    @Override
    public String getClassName() {
        return className;
    }
}
