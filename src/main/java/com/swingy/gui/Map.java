package com.swingy.gui;

import com.swingy.Game;
import com.swingy.model.characters.Hero;

import java.util.Random;

public class Map {

    private int size;
    private char[][] map;

    private static Map thisMap = null;

    public void generateMap() {
        Random random = new Random();
        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++)
                if (j != size / 2 && i != size / 2
                        && random.nextInt(size * 5) == 0) {
                    map[i][j] = 'V';
                    Game.createVillain(new Coordinates(j, i));
                }
                else
                    map[i][j] = '.';
        checkIsMapEmpty();
    }

    public void checkIsMapEmpty() {
        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++)
                if (map[i][j] != '.')
                    return;
        generateMap();
    }

    public Map createMap(boolean resetPos) {
        Hero hero = Hero.getHero();
        setSize((hero.getLevel() - 1) * 5 + 10 - (hero.getLevel() % 2));
        setMap(new char[size][size]);
        generateMap();
        if (resetPos)
            hero.resetHeroPos();
        return thisMap;
    }

    public void nextLevelMap() {
        createMap(true);
    }
    
    public char getMapCell(int i, int j) {
        return map[i][j];
    }

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
}
