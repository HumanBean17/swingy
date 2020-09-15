package com.swingy;

import com.swingy.controller.MainController;
import com.swingy.gui.Map;
import com.swingy.view.ShellGui;
import com.swingy.model.characters.Hero;

import java.util.Scanner;

public class Game {

    private static final Scanner scanner = new Scanner(System.in);
    private ShellGui gui;

    public Hero hero;
    public Map map;

    public void gameCycle() {
        map.createMap(hero);
        while (hero.isAlive()) {
//            map.createMap(hero);
            gui.drawMap();
            if (!MainController.pickMovement(hero)) {
                break;
            }
        }
    }

    public void run() {
        MainController.pickGameMode();
        if (MainController.pickHero() == 1) {
            this.gui = ShellGui.createShellGui();
            hero = Hero.createHero();
            map = Map.getMap();
            gameCycle();
        }
    }
}
