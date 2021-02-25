package com.swingy.model.helm;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "HELM")
@FieldDefaults(level = AccessLevel.PROTECTED)
public abstract class Helm {

    @Id
    @Column(name = "ID")
    UUID id;
    @Column(name = "HIT_POINTS")
    Integer hitPoints;
    @Column(name = "NAME")
    String name;

    public UUID getId() {
        throw new RuntimeException();
    }

    public void setId(UUID id) {
        throw new RuntimeException();
    }

    public Integer getHitPoints() {
        throw new RuntimeException();
    }

    public void setHitPoints(Integer hitPoints) {
        throw new RuntimeException();
    }

    public String getName() {
        throw new RuntimeException();
    }

    public void setName(String name) {
        throw new RuntimeException();
    }

}
