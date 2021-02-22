package com.swingy.model.weapon;

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
@Table(name = "WEAPON")
public abstract class Weapon {

    @Id
    @Column(name = "ID")
    protected UUID id;

    @Column(name = "ATTACK")
    protected Integer attack = 0;
    @Column(name = "NAME")
    protected String name = "";

    public Weapon() {
        this.id = UUID.randomUUID();
    }

}
