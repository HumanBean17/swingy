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

    private Hero hero;
    private Map map;
    private static List<Villain> enemies = new LinkedList<>();

    public static void createVillain(Coordinates coordinates) {
        enemies.add(new Villain(coordinates));
    }

    public void gameCycle() {
        map.createMap(hero);
        MoveDirection direction;
        ActionOnMove action;
        while (hero.isAlive()) {
            //if (checkActionOnHeroPos().equals(ActionOnMove.EMPTY_CELL))
            gui.writeMap();
            direction = MainController.pickMovement(hero);
            checkActionOnHeroPos(direction);
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

    public ActionOnMove checkActionOnHeroPos(MoveDirection direction) {
        List<Villain> villains = Game.getEnemies();
        ActionOnMove action = ActionOnMove.EMPTY_CELL;
//        int heroX = Hero.getHero().getCoordinates().getX();
//        int heroY = Hero.getHero().getCoordinates().getY();
//        int mapSize = Map.getMap().getSize();
//        if (Hero.getHero().getCoordinates().getX() >= Map.getMap().getSize() ||
//                Hero.getHero().getCoordinates().getX() <= 0 ||
//                Hero.getHero().getCoordinates().getY() >= Map.getMap().getSize() ||
//                Hero.getHero().getCoordinates().getY() <= 0) {
//            action = ActionOnMove.NEXT_LEVEL;
//            System.out.println("ACTION ON NEXT LVL");
//        }
        if (direction.equals(MoveDirection.BORDER)) {
            checkNextLevel();
        } else {
            for (Villain villain : villains) {
                if (villain.getCoordinates().getX().equals(Hero.getHero().getCoordinates().getX()) &&
                        villain.getCoordinates().getY().equals(Hero.getHero().getCoordinates().getY())) {
                    action = ActionOnMove.VILLAIN;
                    System.out.println("ACTION ON VILLAIN");
                }
            }
        }
        return action;
    }

    public void checkNextLevel() {

    }

    public static List<Villain> getEnemies() {
        return enemies;
    }

    public Map getMap() {
        return map;
    }

    public Hero getHero() {
        return hero;
    }

    public ShellGui getGui() {
        return gui;
    }
}
