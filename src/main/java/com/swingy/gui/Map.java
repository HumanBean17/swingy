package com.swingy.gui;

import com.swingy.model.characters.Hero;

import java.util.Random;

public class Map {

    private int size;
    private char[][] map;

    private static Map thisMap = null;

    public static Map getMap() {
        if (thisMap == null)
            thisMap = new Map();
        return thisMap;
    }

    public void setMap(char[][] newMap) {
        map = newMap;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void createMap(Hero hero) {
        setSize((hero.getLevel() - 1) * 5 + 10 - (hero.getLevel() % 2));
        setMap(new char[size][size]);
        generateMap();
        hero.getCoordinates().setX(size / 2);
        hero.getCoordinates().setY(size / 2);
    }

    public void generateMap() {
        Random random = new Random();
        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++)
                if (random.nextInt(size * 5) == 0 && i != size / 2 && j != size / 2)
                    map[i][j] = 'E';
                else
                    map[i][j] = '.';
        checkEmptyMap();
    }

    public void checkEmptyMap() {
        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++)
                if (map[i][j] != '.')
                    return;
        generateMap();
    }
    
    public char getMapCell(int i, int j) {
        return map[i][j];
    }
}
