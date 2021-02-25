package com.swingy.model.armor;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "ARMOR")
public abstract class Armor {

    @Id
    @Column(name = "ID")
    protected UUID id;
    @Column(name = "DEFENSE")
    protected Integer defense;
    @Column(name = "NAME")
    protected String name;

    public UUID getId() {
        throw new RuntimeException();
    }

    public void setId(UUID id) {
        throw new RuntimeException();
    }

    public Integer getDefense() {
        throw new RuntimeException();
    }

    public void setDefense(Integer defense) {
        throw new RuntimeException();
    }

    public String getName() {
        throw new RuntimeException();
    }

    public void setName(String name) {
        throw new RuntimeException();
    }

}
