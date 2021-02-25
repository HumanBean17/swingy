package com.swingy.controller;

import com.swingy.Game;
import com.swingy.gui.Map;
import com.swingy.model.cclasses.CharacterClass;
import com.swingy.model.characters.Hero;
import com.swingy.model.characters.Villain;
import com.swingy.view.TermGui;

import java.util.Scanner;

public class MainController {

    private static final Scanner scanner = new Scanner(System.in);

    public static boolean battleHandler(Villain villain) {
        String userInput;
        boolean isHeroWon = true;
        while (true) {
            TermGui.startBattle();
            userInput = scanner.next().toLowerCase();
            if (userInput.startsWith("f") || userInput.startsWith("r"))
                break;
        }
        if (userInput.startsWith("f") || (userInput.startsWith("r") && !Battle.run()))
            isHeroWon = Battle.startBattle(villain);
        if (!isHeroWon)
            TermGui.playerDied();
        return isHeroWon;
    }

    public static MoveDirection pickMovement() {
        Hero hero = Hero.getHero();
        TermGui.pickMovement();
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
            TermGui.info(hero);
        } else if (userInput.startsWith("e")) {

        }
        return direction;
    }

    public static HeroPick pickHero() {
        while (true) {
            TermGui.pickHero();
            String userInput = scanner.next().toLowerCase();
            if (userInput.startsWith("c"))
                return HeroPick.CREATE;
            else if (userInput.startsWith("s"))
                return HeroPick.SELECT;
        }
    }

    public static String pickName() {
        TermGui.pickName();
        return scanner.next();
    }

    public static CharacterClass.GameClass pickClass() {
        while (true) {
            TermGui.pickClass();
            String userInput = scanner.next().toLowerCase();
            if (userInput.startsWith("wa")) {
                return CharacterClass.GameClass.WARRIOR;
            } else if (userInput.startsWith("wi")) {
                return CharacterClass.GameClass.WIZARD;
            } else if (userInput.startsWith("a")) {
                return CharacterClass.GameClass.ARCHER;
            }
        }
    }

    public static boolean pickPrize(String prizeName) {
        while (true) {
            TermGui.pickPrize(prizeName);
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
