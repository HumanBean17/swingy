package com.swingy.gui;

import com.swingy.model.characters.ClassCharacter;

import java.util.Random;

public class Map {

    private Integer size;
    private char[][] map;

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public void createMap(ClassCharacter classCharacter) {
        size = (classCharacter.getLevel() - 1) * 5 + 10 - (classCharacter.getLevel() % 2);
        map = new char[size][size];
        generateMap();
        classCharacter.setPosX(size / 2);
        creature.setPosY(size / 2);
    }

    public void generateMap() {
        Random random = new Random();
        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++)
                if (random.nextInt(size * 5) == 0 && i != size / 2 && j != size / 2)
                    map[i][j] = 'E';
                else
                    map[i][j] = '.';
        //checkEmptyMap();
    }
}
