package com.swingy.model.helm;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Getter
@Setter
@Entity
@AllArgsConstructor
@Table(name = "HELM")
public abstract class Helm {

    @Id
    @Column(name = "ID")
    protected UUID id;
    @Column(name = "HIT_POINTS")
    protected Integer hitPoints;
    @Column(name = "NAME")
    protected String name = "";

    public Helm() {
        this.id = UUID.randomUUID();
    }

}
