package com.swingy.model.helm;

public class LightHelm extends Helm {

    private Integer hitPoints = 2;
    private final String name = "Light helmet";

    public Integer getHitPoints() {
        return hitPoints;
    }

    public void setHitPoints(Integer hitPoints) {
        this.hitPoints = hitPoints;
    }

}
