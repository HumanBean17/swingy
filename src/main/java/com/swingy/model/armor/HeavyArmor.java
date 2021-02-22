package com.swingy.model.armor;

public class HeavyArmor extends Armor {

    private Integer defense = 30;
    private final String name = "Heavy armor";

    public Integer getDefense() {
        return this.defense;
    }

    public void setDefense(Integer defense) {
        this.defense = defense;
    }
}
