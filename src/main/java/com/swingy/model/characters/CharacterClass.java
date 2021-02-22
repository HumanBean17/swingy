package com.swingy.model.characters;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Getter
@Setter
@Entity
@AllArgsConstructor
@Table(name = "CHARACTER_CLASS")
public abstract class CharacterClass {

    @Id
    @Column(name = "ID")
    protected UUID id;
    @Column(name = "SPECIAL_TALENT")
    protected Talent specialTalent;
    @Column(name = "CLASS_NAME")
    protected String className;

    enum Talent {
        CRITICAL_DAMAGE,
        FREEZE,
        SKIP_MOVE
    }

    public CharacterClass() {
        this.id = UUID.randomUUID();
    }

    public Talent specialTalent(Character character) {
        throw new RuntimeException("method must be overrided");
    }

    public String getClassName() {
        throw new RuntimeException("method must be overrided");
    }

    public String getSpecialTalent() {
        throw new RuntimeException("method must be overrided");
    }
}
