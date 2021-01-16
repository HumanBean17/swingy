package com.swingy.model.characters;

public interface CharacterClass {

    enum Talent {
        CRITICAL_DAMAGE,
        FREEZE,
        SKIP_MOVE
    }

    Talent specialTalent(Character character);

    String getClassName();
}
