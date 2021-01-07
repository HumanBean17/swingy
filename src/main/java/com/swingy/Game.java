package com.swingy;

import com.swingy.controller.ActionOnMove;
import com.swingy.controller.MainController;
import com.swingy.controller.MoveDirection;
import com.swingy.gui.Coordinates;
import com.swingy.gui.Map;
import com.swingy.model.characters.Villain;
import com.swingy.view.ShellGui;
import com.swingy.model.characters.Hero;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Game {

    private static final Scanner scanner = new Scanner(System.in);
    private ShellGui gui;

    private Map map;
    private static List<Villain> enemies = new LinkedList<>();

    public static void createVillain(Coordinates coordinates) {
        enemies.add(new Villain(coordinates));
    }

    public void gameCycle() {
        map.createMap();
        MoveDirection direction;
        ActionOnMove action;
        while (Hero.getHero().isAlive()) {
            gui.writeMap();
            direction = MainController.pickMovement(Hero.getHero()); // border
            Hero.getHero().setExperience(1200);
            checkFight();
            checkNextLevel();
        }
    }

    public void run() {
        MainController.pickGameMode();
        if (MainController.pickHero() == 1) {
            this.gui = ShellGui.createShellGui();
            Hero.createHero();
            Hero.getHero().pickClass();
            Hero.getHero().pickName();
            map = Map.getMap();
            gameCycle();
        }
    }

    public ActionOnMove checkFight() {
        List<Villain> villains = Game.getEnemies();
        ActionOnMove action = ActionOnMove.EMPTY_CELL;
        for (Villain villain : villains) {
            if (villain.getCoordinates().getX().equals(Hero.getHero().getCoordinates().getX()) &&
                    villain.getCoordinates().getY().equals(Hero.getHero().getCoordinates().getY())) {
                action = ActionOnMove.VILLAIN;
                System.out.println("ACTION ON VILLAIN");
            }
        }
        return action;
    }

    public void checkNextLevel() {
        if (Hero.getHero().getExperience() >= ((Hero.getHero().getLevel() + 1) * 1000) +
                (int)Math.pow(Hero.getHero().getLevel(), 2) * 450) {
            Hero.getHero().increaseLevel();
            Hero.getHero().setMaxHp(Hero.getHero().getLevel() * 50 + 100);
            Hero.getHero().setHp(Hero.getHero().getMaxHp());
            map.nextLevelMap();
        }
    }

    public static List<Villain> getEnemies() {
        return enemies;
    }

    public Map getMap() {
        return map;
    }

    public ShellGui getGui() {
        return gui;
    }
}
