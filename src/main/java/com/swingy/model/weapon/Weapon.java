package com.swingy.model.weapon;

public abstract class Weapon {

    protected Integer attack = 0;
    protected String name = "";

    public Integer getAttack() {
        return attack;
    }

    public String getName() {
        return name;
    }

    public void setAttack(Integer attack) {
        this.attack = attack;
    }
}
