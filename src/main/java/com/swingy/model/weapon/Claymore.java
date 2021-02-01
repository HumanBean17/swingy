package com.swingy.model.weapon;

public class Claymore extends Weapon {

    private Integer attack = 2;
    private String name = "Claymore";

    @Override
    public Integer getAttack() {
        return attack;
    }

    @Override
    public void setAttack(Integer attack) {
        this.attack = attack;
    }
}
