package com.swingy.model.cclasses;

import com.swingy.model.characters.Character;

import java.util.UUID;

public class Warrior extends CharacterClass {

    public Warrior() {
        this.id = UUID.randomUUID();
        this.gameClass = GameClass.WARRIOR;
        this.specialTalent = Talent.CRITICAL_DAMAGE;
    }

    @Override
    public Talent getSpecialTalent(Character character) {
        if (character.getMana() % 3 == 0) {
            return Talent.CRITICAL_DAMAGE;
        }
        return null;
    }

    @Override
    public UUID getId() {
        return this.id;
    }

    @Override
    public void setId(UUID id) {
        this.id = id;
    }

    @Override
    public GameClass getGameClass() {
        return this.gameClass;
    }

    @Override
    public void setGameClass(GameClass gameClass) {
        this.gameClass = gameClass;
    }

    @Override
    public void setSpecialTalent(Talent talent) {
        this.specialTalent = talent;
    }

}
