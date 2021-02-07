package com.swingy;

import com.swingy.controller.ActionOnMove;
import com.swingy.controller.MainController;
import com.swingy.controller.MoveDirection;
import com.swingy.gui.Coordinates;
import com.swingy.gui.Map;
import com.swingy.model.characters.Villain;
import com.swingy.view.TermGui;
import com.swingy.model.characters.Hero;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Game {

    private static final Scanner scanner = new Scanner(System.in);
    private TermGui gui;

    private Map map;
    private static List<Villain> enemies = new LinkedList<>();

    private static Coordinates heroLastPos = null;

    public static void createVillain(Coordinates coordinates) {
        enemies.add(new Villain(coordinates));
    }

    public void gameCycle() {
        map.createMap();
        MoveDirection direction;
        ActionOnMove action;
        Villain villain;
        while (Hero.getHero().isAlive()) {
            gui.writeMap();
            direction = MainController.pickMovement();
            villain = checkBattle();
            if (villain != null) {
                if (MainController.battleHandler(villain))
                    enemies.remove(villain);
                else
                    Main.restartTheGame();
            }
            if (direction == MoveDirection.BORDER && checkNextLevel()) {
                System.out.println("Level up! You're now level " + Hero.getHero().getLevel() + 1);
                if (Hero.getHero().getLevel() >= 7) {
                    System.out.println("Congratulations! You've reached game level 7 and completed the game.");
                    Main.restartTheGame();
                } else {
                    Hero.getHero().increaseLevel();
                    Hero.getHero().setMaxHp(Hero.getHero().getLevel() * 50 + 100);
                    Hero.getHero().setHp(Hero.getHero().getMaxHp());
                    Map.getMap().nextLevelMap();
                }
            } else if (direction == MoveDirection.BORDER) {
                Map.getMap().resetHeroPos(Hero.getHero());
                Map.getMap().generateMap();
            }
        }
        Main.restartTheGame();
    }

    public void run() {
        if (MainController.pickHero().equals(MainController.HeroPick.CREATE)) {
            this.gui = TermGui.createShellGui();
            Hero.createHero();
            Hero.getHero().pickClass();
            Hero.getHero().pickName();
            heroLastPos = Hero.getHero().getCoordinates();
            map = Map.getMap();
            gameCycle();
        }
    }

    public Villain checkBattle() {
        List<Villain> villains = Game.getEnemies();
        Villain result = null;
        for (Villain villain : villains) {
            if (villain.getCoordinates().getX().equals(Hero.getHero().getCoordinates().getX()) &&
                    villain.getCoordinates().getY().equals(Hero.getHero().getCoordinates().getY())) {
                result = villain;
                break;
            }
        }
        return result;
    }

    public static boolean checkNextLevel() {
        return Hero.getHero().getExperience() >= getNextLevelExperience();
    }

    public static int getNextLevelExperience() {
        return ((Hero.getHero().getLevel() + 1) * 1000) +
                (int)Math.pow(Hero.getHero().getLevel(), 2) * 450;
    }

    public static List<Villain> getEnemies() {
        return enemies;
    }

    public Map getMap() {
        return map;
    }

    public TermGui getGui() {
        return gui;
    }

    public static Coordinates getHeroLastPos() {
        return heroLastPos;
    }

    public static void setHeroLastPos(Coordinates heroLastPos) {
        Game.heroLastPos = heroLastPos;
    }
}
