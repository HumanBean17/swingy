package com.swingy.model.helm;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

public class MediumHelm extends Helm {

    public MediumHelm() {
        this.hitPoints = 20;
        this.name = "Medium helmet";
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
