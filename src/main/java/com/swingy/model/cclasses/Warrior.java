package com.swingy.model.cclasses;

import com.swingy.model.characters.Character;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

public class Warrior extends CharacterClass {

    public Warrior() {
        this.id = UUID.randomUUID();
        this.className = "Warrior";
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
    public String getClassName() {
        return this.className;
    }

    @Override
    public void setClassName(String className) {
        this.className = className;
    }

    @Override
    public void setSpecialTalent(Talent talent) {
        this.specialTalent = talent;
    }

}
