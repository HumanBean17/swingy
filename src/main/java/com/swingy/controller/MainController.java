package com.swingy.controller;

import com.swingy.Game;
import com.swingy.Main;
import com.swingy.db.GameDb;
import com.swingy.map.Map;
import com.swingy.model.cclasses.CharacterClass;
import com.swingy.model.characters.Hero;
import com.swingy.model.characters.Villain;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class MainController {

    public volatile static Queue<String> guiActions = new LinkedList<>();

    private static final Scanner scanner = new Scanner(System.in);

    public boolean battleHandler(Villain villain) {
        String userInput;
        boolean isHeroWon = true;
        if (!Main.gui.isGui()) {
            while (true) {
                Main.gui.startBattle();
                userInput = scanner.next().toLowerCase();
                if (userInput.startsWith("f") || userInput.startsWith("r"))
                    break;
            }
            if (userInput.startsWith("f") || (userInput.startsWith("r") && !Battle.run()))
                isHeroWon = Battle.startBattle(villain);
            if (!isHeroWon)
                Main.gui.playerDied();
        } else {
            Main.gui.startBattle();
            System.out.println(guiActions);
            while (guiActions.isEmpty());
            String action = guiActions.remove();
            if (action.equals("fight") || action.equals("run") && !Battle.run()) {
                System.out.println("here");
                Main.gui.battleInfoFrame();
                isHeroWon = Battle.startBattle(villain);
            }
            if (!isHeroWon)
                Main.gui.playerDied();
        }
        return isHeroWon;
    }

    public static MoveDirection pickMovement() {
        Hero hero = Hero.getHero();
        String userInput;
        if (!Main.gui.isGui()) {
            Main.gui.pickMovement();
            userInput = scanner.next().toLowerCase();
        } else {
            System.out.println(guiActions);
            while (guiActions.isEmpty());
            userInput = guiActions.remove();
        }
        MoveDirection direction = MoveDirection.NULL;
        Game.setHeroLastPos(hero.getCoordinates());
        if (userInput.startsWith("r") && ((hero.getCoordinates().getX() + 1) < Map.getMap().getSize())) {
            hero.moveRight();
            direction = MoveDirection.EAST;
        } else if (userInput.startsWith("r")) {
            direction = MoveDirection.BORDER;
        } else if (userInput.startsWith("l") && ((hero.getCoordinates().getX() - 1) >= 0)) {
            hero.moveLeft();
            direction = MoveDirection.WEST;
        } else if (userInput.startsWith("l")) {
            direction = MoveDirection.BORDER;
        } else if (userInput.startsWith("u") && ((hero.getCoordinates().getY() - 1) >= 0)) {
            hero.moveUp();
            direction = MoveDirection.NORTH;
        } else if (userInput.startsWith("u")) {
            direction = MoveDirection.BORDER;
        } else if (userInput.startsWith("d") && ((hero.getCoordinates().getY() + 1) < Map.getMap().getSize())) {
            hero.moveDown();
            direction = MoveDirection.SOUTH;
        } else if (userInput.startsWith("d")) {
            direction = MoveDirection.BORDER;
        } else if (userInput.startsWith("i")) {
            Main.gui.info(hero);
        }
        return direction;
    }

    public HeroPick menu() {
        if (!Main.gui.isGui()) {
            while (true) {
                Main.gui.drawMenu();
                HeroPick action = heroPickCondition(scanner.next().toLowerCase());
                if (action != null)
                    return action;
            }
        } else {
            Main.gui.drawMenu();
            while (guiActions.isEmpty());
            return heroPickCondition(guiActions.remove());
        }
    }

    private HeroPick heroPickCondition(String userInput) {
        if (userInput.startsWith("cre"))
            return HeroPick.CREATE;
        else if (userInput.startsWith("sel"))
            return HeroPick.SELECT;
        return null;
    }

    public Hero pickHero(List<Hero> heroes) {
        if (!Main.gui.isGui()) {
            while (true) {
                Main.gui.pickHero(heroes);
                String userInput = scanner.next();
                for (Hero hero : heroes) {
                    if (hero.getName().equals(userInput)) {
                        return hero;
                    }
                }
            }
        } else {
            while (guiActions.isEmpty());
            return Main.gui.getSelectedHero();
        }
    }

    public String pickName() {
        if (!Main.gui.isGui())
            return scanner.next();
        else
            Main.gui.pickName();
        while (guiActions.isEmpty());
        return guiActions.remove();
    }

    public CharacterClass.GameClass pickClass() {
        if (!Main.gui.isGui()) {
            while (true) {
                Main.gui.pickClass();
                CharacterClass.GameClass gameClass = pickClassCondition(scanner.next().toLowerCase());
                if (gameClass != null)
                    return gameClass;
            }
        } else {
            Main.gui.pickClass();
            while (guiActions.isEmpty());
            return pickClassCondition(guiActions.remove());
        }
    }

    private CharacterClass.GameClass pickClassCondition(String userInput) {
        if (userInput.startsWith("war")) {
            return CharacterClass.GameClass.WARRIOR;
        } else if (userInput.startsWith("wiz")) {
            return CharacterClass.GameClass.WIZARD;
        } else if (userInput.startsWith("arc")) {
            return CharacterClass.GameClass.ARCHER;
        }
        return null;
    }

    public boolean pickPrize(String prizeName) {
        if (!Main.gui.isGui()) {
            while (true) {
                Main.gui.pickPrize(prizeName);
                String userInput = scanner.next().toLowerCase();
                if (userInput.startsWith("y")) {
                    return true;
                } else if (userInput.startsWith("n")) {
                    return false;
                }
            }
        } else {
            Main.gui.pickPrize(prizeName);
            while (guiActions.isEmpty());
            String userInput = MainController.guiActions.remove();
            return userInput.equals("yes");
        }
    }

    public enum HeroPick {
        CREATE,
        SELECT
    }

}
