package com.swingy.gui;

public class Coordinates {

    private int x;
    private int y;

    public Coordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Coordinates() {
        x = 0;
        y = 0;
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

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }
}
