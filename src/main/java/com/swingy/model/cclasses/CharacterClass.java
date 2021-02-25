package com.swingy.model.cclasses;

import com.swingy.model.characters.Character;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "CHARACTER_CLASS")
@FieldDefaults(level = AccessLevel.PROTECTED)
public abstract class CharacterClass {

    @Id
    @Column(name = "ID")
    UUID id;
    @Column(name = "SPECIAL_TALENT")
    Talent specialTalent;
    @Column(name = "CLASS_NAME")
    GameClass gameClass;

    public enum Talent {
        CRITICAL_DAMAGE {
            @Override
            public String toString() {
                return "CRITICAL DAMAGE";
            }
        },
        FREEZE {
            @Override
            public String toString() {
                return "FREEZE";
            }
        },
        SKIP_MOVE {
            @Override
            public String toString() {
                return "SKIP MOVE";
            }
        };

        Talent() { }
    }

    public enum GameClass {
        WARRIOR {
            @Override
            public String toString() {
                return "WARRIOR";
            }
        },
        WIZARD {
            @Override
            public String toString() {
                return "WIZARD";
            }
        },
        ARCHER {
            @Override
            public String toString() {
                return "ARCHER";
            }
        };

        GameClass() { }
    }

    public UUID getId() {
        throw new RuntimeException();
    }

    public void setId(UUID id) {
        throw new RuntimeException();
    }

    public Talent getSpecialTalent(Character character) {
        throw new RuntimeException();
    }

    public void setSpecialTalent(Talent specialTalent) {
        throw new RuntimeException();
    }

    public GameClass getGameClass() {
        throw new RuntimeException();
    }

    public void setGameClass(GameClass gameClass) {
        throw new RuntimeException();
    }

}
