package com.swingy.model.weapon;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "WEAPON")
public interface Weapon {

    @Id
    @Column(name = "ID")
    UUID id = null;
    @Column(name = "ATTACK")
    Integer attack = 0;
    @Column(name = "NAME")
    String name = "";

    UUID getId();

    void setId(UUID id);

    Integer getAttack();

    void setAttack(Integer attack);

    String getName();

    void setName(String name);

}
