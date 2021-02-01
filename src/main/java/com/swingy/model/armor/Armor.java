package com.swingy.model.armor;

public abstract class Armor {

    protected Integer defense = 0;
    protected String name = "";

    public Integer getDefense() {
        return this.defense;
    }

    public String getName() {
        return this.name;
    }

    public void setDefense(Integer defense) {
        this.defense = defense;
    }

}
