package com.swingy.model.weapon;

public class Claymore extends Weapon {

    private Integer attack = 2;

    @Override
    public Integer getAttack() {
        return attack;
    }

    @Override
    public void setAttack(Integer attack) {
        this.attack = attack;
    }
}
