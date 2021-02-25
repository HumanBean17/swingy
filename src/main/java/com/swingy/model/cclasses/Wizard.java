package com.swingy.model.cclasses;

import com.swingy.model.characters.Character;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class Wizard extends CharacterClass {

    public Wizard() {
        this.id = UUID.randomUUID();
        this.gameClass = GameClass.WIZARD;
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
