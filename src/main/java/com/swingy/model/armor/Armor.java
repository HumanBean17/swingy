package com.swingy.model.armor;

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
@AllArgsConstructor
@Entity
@Table(name = "ARMOR")
public abstract class Armor {

    @Id
    @Column(name = "ID")
    protected UUID id;
    @Column(name = "DEFENSE")
    protected Integer defense = 0;
    @Column(name = "NAME")
    protected String name = "";

    public Armor() {
        this.id = UUID.randomUUID();
    }

}
