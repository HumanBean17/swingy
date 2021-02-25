package com.swingy.model.weapon;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "WEAPON")
@FieldDefaults(level = AccessLevel.PROTECTED)
public abstract class Weapon {

    @Id
    @Column(name = "ID")
    UUID id;
    @Column(name = "ATTACK")
    Integer attack;
    @Column(name = "NAME")
    String name;

    public UUID getId() {
        throw new RuntimeException();
    }

    public void setId(UUID id) {
        throw new RuntimeException();
    }

    public Integer getAttack() {
        throw new RuntimeException();
    }

    public void setAttack(Integer attack) {
        throw new RuntimeException();
    }

    public String getName() {
        throw new RuntimeException();
    }

    public void setName(String name) {
        throw new RuntimeException();
    }

}
