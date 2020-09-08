package com.swingy.model.characters;

public abstract class ClassCharacter {

    protected static Integer bankPoint = 10;

    protected Integer attack;
    protected Integer defense;
    protected Integer hitPoints;

    private static Integer level = 1;
    private static Integer experience = 0;

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
