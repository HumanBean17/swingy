package com.swingy.model.armor;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

public class HeavyArmor extends Armor {

    public HeavyArmor() {
        this.id = UUID.randomUUID();
        this.defense = 30;
        this.name = "Heavy armor";
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
    public Integer getDefense() {
        return this.defense;
    }

    @Override
    public void setDefense(Integer defense) {
        this.defense = defense;
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
