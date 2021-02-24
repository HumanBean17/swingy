package com.swingy.model.weapon;

import java.util.UUID;

public class Axe implements Weapon {

    public UUID id;
    protected Integer attack = 35;
    protected String name = "Axe";

    public Axe() {
        this.id = UUID.randomUUID();
    }

    @Override
    public UUID getId() {
        return id;
    }

    @Override
    public void setId(UUID id) {
        this.id = id;
    }

    @Override
    public Integer getAttack() {
        return attack;
    }

    @Override
    public void setAttack(Integer attack) {
        this.attack = attack;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

}
