package com.swingy.model.characters;

import com.swingy.gui.Coordinates;

public abstract class ClassCharacter {

    protected static Integer bankPoint = 10;

    protected Integer attack = 0;
    protected Integer defense = 0;
    protected Integer hitPoints = 0;

    public Integer getAttack() {
        return attack;
    }

    public void setAttack(Integer attack) {
        bankPoint -= attack - this.attack;
        this.attack = attack;
    }

    public Integer getDefense() {
        return defense;
    }

    public void setDefense(Integer defense) {
        bankPoint -= defense -= this.defense;
        this.defense = defense;
    }

    public Integer getHitPoints() {
        return hitPoints;
    }

    public void setHitPoints(Integer hitPoints) {
        bankPoint -= hitPoints -= this.hitPoints;
        this.hitPoints = hitPoints;
    }
}
