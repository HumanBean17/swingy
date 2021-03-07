package com.swingy.controller;

import com.swingy.Game;
import com.swingy.Main;
import com.swingy.map.Map;
import com.swingy.model.cclasses.CharacterClass;
import com.swingy.model.characters.Hero;
import com.swingy.model.characters.Villain;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class MainController {

    public volatile static Queue<String> guiActions = new LinkedList<>();

    private static final Scanner scanner = new Scanner(System.in);

    public boolean battleHandler(Villain villain) {
        String userInput;
        boolean isHeroWon = true;
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
        return isHeroWon;
    }

    public static MoveDirection pickMovement() {
        Hero hero = Hero.getHero();
        Main.gui.pickMovement();
        String userInput = scanner.next().toLowerCase();
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

    public String pickName() {
        Main.gui.pickName();
        if (!Main.gui.isGui())
            return scanner.next();
        System.out.println(guiActions);
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
            System.out.println(guiActions);
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
        while (true) {
            Main.gui.pickPrize(prizeName);
            String userInput = scanner.next().toLowerCase();
            if (userInput.startsWith("y")) {
                return true;
            } else if (userInput.startsWith("n")) {
                return false;
            }
        }
    }

    public enum HeroPick {
        CREATE,
        SELECT
    }

}
