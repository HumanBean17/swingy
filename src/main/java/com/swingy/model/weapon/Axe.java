package com.swingy.model.weapon;

public class Axe extends Weapon {

    private Integer attack = 35;
    private String name = "Axe";

    @Override
    public Integer getAttack() {
        return attack;
    }

    @Override
    public void setAttack(Integer attack) {
        this.attack = attack;
    }
}
