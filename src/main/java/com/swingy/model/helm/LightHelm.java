package com.swingy.model.helm;

public class LightHelm extends Helm {

    protected Integer hitPoints = 10;
    protected final String name = "Light helmet";

    public Integer getHitPoints() {
        return hitPoints;
    }

    public void setHitPoints(Integer hitPoints) {
        this.hitPoints = hitPoints;
    }

}
