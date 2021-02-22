package com.swingy.model.armor;

public class MediumArmor extends Armor {

    private Integer defense = 20;
    private final String name = "Medium armor";

    public Integer getDefense() {
        return this.defense;
    }

    public void setDefense(Integer defense) {
        this.defense = defense;
    }
}
