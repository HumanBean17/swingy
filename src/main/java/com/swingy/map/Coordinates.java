package com.swingy.map;

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
@Table(name = "COORDINATES")
public class Coordinates {

    @Id
    @Column(name = "ID", columnDefinition = "UUID")
    protected UUID id;
    @Column(name = "X")
    private Integer x;
    @Column(name = "Y")
    private Integer y;

    public Coordinates(int x, int y) {
        this.id = UUID.randomUUID();
        this.x = x;
        this.y = y;
    }

    public Coordinates() {
        this.id = UUID.randomUUID();
        this.x = 0;
        this.y = 0;
    }

    public void incX() {
        x += 1;
    }

    public void decX() {
        x -= 1;
    }

    public void incY() {
        y -= 1;
    }

    public void decY() {
        y += 1;
    }

}
