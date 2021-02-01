package com.swingy.model.armor;

import com.swingy.model.armor.Armor;

public class LightArmor extends Armor {

    private Integer defense = 2;
    private final String name = "Light armor";

    public Integer getDefense() {
        return this.defense;
    }

    public void setDefense(Integer defense) {
        this.defense = defense;
    }
}
