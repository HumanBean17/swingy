package com.swingy.model.helm;

import java.util.UUID;

public class WoodenHelm extends Helm {

    public WoodenHelm() {
        this.id = UUID.randomUUID();
        this.hitPoints = 10;
        this.name = "Wooden helmet";
    }

    @Override
    public UUID getId() {
        return this.id;
    }

    @Override
    public void setId(UUID id) {
        this.id = id;
    }

    @Override
    public Integer getHitPoints() {
        return this.hitPoints;
    }

    @Override
    public void setHitPoints(Integer hitPoints) {
        this.hitPoints = hitPoints;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

}
