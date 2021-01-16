package com.swingy.model.armor;

import com.swingy.model.armor.Armor;

public class LightArmor extends Armor {

    protected Integer defense = 2;

    public Integer getDefense() {
        return this.defense;
    }

    public void setDefense(Integer defense) {
        this.defense = defense;
    }
}
