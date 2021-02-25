package com.swingy.model.cclasses;

import com.swingy.model.characters.Character;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Id;
import java.util.UUID;

@Getter
@Setter
public class Wizard extends CharacterClass {

    public Wizard() {
        this.id = UUID.randomUUID();
        this.className = "Wizard";
        this.specialTalent = Talent.FREEZE;
    }

    @Override
    public Talent getSpecialTalent(Character character) {
        if (character.getMana() % 3 == 0) {
            return Talent.FREEZE;
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
