package com.swingy.model.weapon;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

public class Fists extends Weapon {

    public Fists() {
        this.id = UUID.randomUUID();
        this.attack = 5;
        this.name = "Fists";
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
    public Integer getAttack() {
        return this.attack;
    }

    @Override
    public void setAttack(Integer attack) {
        this.attack = attack;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

}
