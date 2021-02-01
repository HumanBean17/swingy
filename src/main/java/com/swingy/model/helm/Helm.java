package com.swingy.model.helm;

public abstract class Helm {

    protected Integer hitPoints;
    protected String name = "";

    public Integer getHitPoints() {
        return hitPoints;
    }

    public String getName() {
        return this.name;
    }

    public void setHitPoints(Integer hitPoints) {
        this.hitPoints = hitPoints;
    }

}
