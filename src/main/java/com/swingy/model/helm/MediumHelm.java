package com.swingy.model.helm;

public class MediumHelm extends Helm {

    protected Integer hitPoints = 20;
    protected final String name = "Medium helmet";

    public Integer getHitPoints() {
        return hitPoints;
    }

    public void setHitPoints(Integer hitPoints) {
        this.hitPoints = hitPoints;
    }

}
