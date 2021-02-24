package com.swingy.model.helm;

public class HeavyHelm extends Helm {

    protected Integer hitPoints = 30;
    protected final String name = "Heavy helmet";

    public Integer getHitPoints() {
        return hitPoints;
    }

    public void setHitPoints(Integer hitPoints) {
        this.hitPoints = hitPoints;
    }

}
