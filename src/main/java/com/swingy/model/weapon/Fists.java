package com.swingy.model.weapon;

public class Fists extends Weapon {

    private Integer attack = 5;
    private String name = "Fists";

    @Override
    public Integer getAttack() {
        return attack;
    }

    @Override
    public void setAttack(Integer attack) {
        this.attack = attack;
    }
}
