package com.swingy.model.weapon;

public class Knife extends Weapon {

    private Integer attack = 15;
    private String name = "Knife";

    @Override
    public Integer getAttack() {
        return attack;
    }

    @Override
    public void setAttack(Integer attack) {
        this.attack = attack;
    }
}
