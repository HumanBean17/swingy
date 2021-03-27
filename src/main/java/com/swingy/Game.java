package com.swingy;

import com.swingy.controller.MainController;
import com.swingy.controller.MoveDirection;
import com.swingy.db.GameDb;
import com.swingy.map.Coordinates;
import com.swingy.map.Map;
import com.swingy.model.characters.Villain;
import com.swingy.model.characters.Hero;

import javax.validation.*;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class Game {

    private Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
    private Coordinates heroLastPos = null;
    private List<Villain> enemies = new LinkedList<>();
    private List<Hero> heroes = new LinkedList<>();

    public void createVillain(Coordinates coordinates) {
        enemies.add(new Villain(coordinates));
    }

    public void deleteVillain(Villain villain) {
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
                if (Main.controller.battleHandler(villain)) {
                    enemies.remove(villain);
                }
                else {
                    Main.gameDb.deleteHero();
                    Main.restartTheGame();
                }
            }
            if (direction == MoveDirection.BORDER && checkNextLevel()) {
                Main.gui.levelUpMessage();
                if (Hero.getHero().getLevel() >= 6) {
                    Main.gui.gameFinishedMessage();
                    Main.restartTheGame();
                } else {
                    nextLevel();
                }
            } else if (direction == MoveDirection.BORDER) {
                Hero.getHero().resetHeroPos();
                Map.getMap().generateMap();
            }
            Main.gameDb.updateHero(Hero.getHero());
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
        while (true) {
            heroes.clear();
            heroes.addAll(Main.gameDb.getHeroes());
            MainController.HeroPick heroPick = Main.controller.menu();
            if (heroPick.equals(MainController.HeroPick.CREATE)) {
                Hero hero = Hero.createHero();
                if (hero == null) {
                    continue;
                }
                if (!Main.gameDb.insertHero(hero)) {
                    Main.gui.printErrorMessage("Error while saving hero", true);
                    continue;
                }
                Map.getMap().createMap(true);
                break;
            } else if (heroPick.equals(MainController.HeroPick.SELECT)) {
                Hero.setHero(Main.controller.pickHero(heroes));
                if (Hero.getHero() == null) {
                    continue;
                }
                Map.getMap().createMap(false);
                break;
            }
        }
        gameCycle();
    }

    public Villain checkBattle() {
        List<Villain> villains = getEnemies();
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

    public boolean isVillain(Coordinates coordinates) {
        List<Villain> villains = getEnemies();
        for (Villain villain : villains) {
            if (villain.getCoordinates().getX().equals(coordinates.getX()) &&
                    villain.getCoordinates().getY().equals(coordinates.getY())) {
                return true;
            }
        }
        return false;
    }

    public boolean checkNextLevel() {
        return Hero.getHero().getExperience() >= getNextLevelExperience();
    }

    public int getNextLevelExperience() {
        return ((Hero.getHero().getLevel() + 1) * 1000) +
                (int)Math.pow(Hero.getHero().getLevel(), 2) * 450;
    }

    public void setEnemies(List<Villain> enemies) {
        this.enemies = enemies;
    }

    public List<Villain> getEnemies() {
        return enemies;
    }

    public Coordinates getHeroLastPos() {
        return heroLastPos;
    }

    public void setHeroLastPos(Coordinates heroLastPos) {
        this.heroLastPos = heroLastPos;
    }

    public Validator getValidator() {
        return validator;
    }

    public void setValidator(Validator validator) {
        this.validator = validator;
    }

    public List<Hero> getHeroes() {
        return heroes;
    }

    public void setHeroes(List<Hero> heroes) {
        this.heroes = heroes;
    }
}

