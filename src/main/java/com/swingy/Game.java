package com.swingy;

import com.swingy.controller.MainController;
import com.swingy.controller.MoveDirection;
import com.swingy.db.GameDb;
import com.swingy.map.Coordinates;
import com.swingy.map.Map;
import com.swingy.model.characters.Villain;
import com.swingy.model.characters.Hero;

import javax.validation.*;
import java.util.LinkedList;
import java.util.List;

public class Game {

    public static Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    private static List<Villain> enemies = new LinkedList<>();

    private static Coordinates heroLastPos = null;

    public static void createVillain(Coordinates coordinates) {
        enemies.add(new Villain(coordinates));
    }

    public static void deleteVillain(Villain villain) {
        Map.getMap().setMapCell(villain.getCoordinates().getY(), villain.getCoordinates().getX(), '.');
        enemies.remove(villain);
    }

    public void gameCycle() {
        heroLastPos = Hero.getHero().getCoordinates();
        MoveDirection direction;
        Villain villain;
        while (Hero.getHero().isAlive()) {
            Main.gui.writeMap();
            direction = Main.controller.pickMovement();
            villain = checkBattle();
            if (villain != null) {
                if (Main.controller.battleHandler(villain))
                    enemies.remove(villain);
                else
                    Main.restartTheGame();
            }
            if (direction == MoveDirection.BORDER && checkNextLevel()) {
                Main.gui.levelUpMessage();
                if (Hero.getHero().getLevel() >= 7) {
                    Main.gui.gameFinishedMessage();
                    Main.restartTheGame();
                } else {
                    nextLevel();
                }
            } else if (direction == MoveDirection.BORDER) {
                Hero.getHero().resetHeroPos();
                Map.getMap().generateMap();
            }
            GameDb.updateHero(Hero.getHero());
        }
        Main.restartTheGame();
    }

    public void nextLevel() {
        Hero.getHero().increaseLevel();
        Hero.getHero().setMaxHp(Hero.getHero().getLevel() * 50 + 100);
        Hero.getHero().setHp(Hero.getHero().getMaxHp());
        Map.getMap().nextLevelMap();
    }

    public void run() {
        Main.gui.drawHello();
        MainController.HeroPick heroPick = Main.controller.menu();
        while (true) {
            if (heroPick.equals(MainController.HeroPick.CREATE)) {
                Hero hero = Hero.createHero();
                if (hero == null) {
                    continue;
                }
                if (!GameDb.insertHero(hero)) {
                    Main.gui.printErrorMessage("ERROR WHILE SAVING HERO TO DATABASE. GAME PROGRESS WILL NOT BE SAVED AFTER EXIT THE GAME.", true);
                }
                Map.getMap().createMap(true);
            } else if (heroPick.equals(MainController.HeroPick.SELECT)) {
                Hero hero = GameDb.selectHero(Main.controller.pickName());
                if (hero == null) {
                    Main.gui.selectHeroError();
                    Main.restartTheGame();
                }
                Map.getMap().createMap(false);
            }
            break;
        }
        gameCycle();
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

    public static boolean isVillain(Coordinates coordinates) {
        List<Villain> villains = Game.getEnemies();
        for (Villain villain : villains) {
            if (villain.getCoordinates().getX().equals(coordinates.getX()) &&
                    villain.getCoordinates().getY().equals(coordinates.getY())) {
                return true;
            }
        }
        return false;
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

    public static Coordinates getHeroLastPos() {
        return heroLastPos;
    }

    public static void setHeroLastPos(Coordinates heroLastPos) {
        Game.heroLastPos = heroLastPos;
    }
}
