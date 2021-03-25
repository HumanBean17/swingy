package com.swingy.view;

import com.swingy.Game;
import com.swingy.controller.MainController;
import com.swingy.map.Map;
import com.swingy.model.cclasses.CharacterClass;
import com.swingy.model.characters.Character;
import com.swingy.model.characters.Hero;
import com.swingy.model.characters.Villain;

import java.util.List;

public class TermGui implements Gui {

    public final boolean isGui = false;

    @Override
    public boolean isGui() {
        return isGui;
    }

    @Override
    public void finalize() throws Throwable {
        throw new RuntimeException();
    }

    @Override
    public Hero getSelectedHero() {
        throw new RuntimeException();
    }

    @Override
    public void battleInfoFrame() {

    }

    public void validationError(String message) {
        this.printMessage(message, true);
    }

    public void takeDamage(CharacterClass characterClass, String name, int takenDamage, int hp) {
        this.printMessage(characterClass.getGameClass() + " " + name + " takes damage " +
                takenDamage + " and has " + hp + " health points\n\n", false);
    }

    public void attack(CharacterClass characterClass, String name, Character enemy, int damage) {
        this.printMessage(characterClass.getGameClass() + " '" + name + "' attacks " +
                enemy.getCharacterClass().getGameClass() + " " + enemy.getName() + " with damage " + damage + "\n\n", true);
    }

    public void criticalDamage() {
        this.printMessage("CRITICAL DAMAGE", false);
    }

    public void miss(CharacterClass characterClass, String name) {
        this.printMessage(characterClass.getGameClass() + " '" + name + "' misses", false);
    }

    public void enemyFreeze(CharacterClass characterClass, String name) {
        this.printMessage("ENEMY WAS FROZEN BY " + characterClass.getGameClass() + " '" + name + "'", false);
    }

    public void battleLost(Villain villain) {
        this.printMessage("Villain has " + villain.getHp() + " health points", true);
         MainController.enterForContinue();
    }

    public void battleWin() {
        this.printMessage("You've won the battle!", true);
         MainController.enterForContinue();
    }

    public void printErrorMessage(String message, boolean flush) {
        this.printMessage(message, true);
    }

    public void levelUpMessage() {
        this.printMessage("Level up! You're now level " + ((Hero.getHero().getLevel()) + 1), true);
         MainController.enterForContinue();
    }

    public void gameFinishedMessage() {
        this.printMessage("Congratulations! You've reached game level 7 and completed the game.", true);
        MainController.enterForContinue();
    }

    public void startBattle() {
        this.printMessage("YOU'VE MET A VILLAIN. WHAT ARE YOU GOING TO DO?\n* FIGHT\n* RUN\n\n> ", true);
    }

    public void playerDied() {
        this.printMessage("YOU DIED. GAME OVER ON LEVEL " + (Hero.getHero().getLevel() + 1), true);
        MainController.enterForContinue();
    }

    public void pickMovement() {
        this.printMessage("* WHERE TO MOVE? : 'RIGHT/R', 'LEFT/L', 'UP/U', 'DOWN/D', 'INFO'\n\n> ", false);
    }

    public void pickHero(List<Hero> heroes) {
        this.printMessage("{ ", true);
        for (Hero hero : heroes) {
            this.printMessage(hero.getName() + ", ", false);
        }
        this.printMessage("}\n* WHAT HERO WOULD YOU LIKE TO CHOOSE?\n\n> ", false);
    }

    public void pickName() {
        this.printMessage("* WHAT'S YOUR NAME, STRANGER?\n\n> ", true);
    }

    public void pickPrize(String prizeName) {
        this.printMessage(prizeName + " HAS DROPPED FROM KILLED ENEMY. WOULD YOU LIKE TO TAKE IT? (YES/NO)\n\n> ", true);
    }

    public void info(Hero hero) {
        this.printMessage("NAME:                " + hero.getName().toUpperCase() + "\n", true);
        this.printMessage("CLASS:               " + hero.getCharacterClass().getGameClass() + "\n", false);
        this.printMessage("HEALTH POINTS:       " + hero.getHp() + "/" + Hero.getHero().getMaxHp() + "\n", false);
        this.printMessage("LEVEL:               " + hero.getLevel() + "\n", false);
        this.printMessage("EXPERIENCE:          " + hero.getExperience() + "/" + Game.getNextLevelExperience() + "\n", false);
        this.printMessage("ATTACK:              " + hero.getAttack() + "\n", false);
        this.printMessage("DEFENCE:             " + hero.getDefense() + "\n", false);
        this.printMessage("HIT POINTS:          " + hero.getHitPoints() + "\n", false);
        if (hero.getWeapon() != null)
            this.printMessage("WEAPON:          " + hero.getWeapon().getName() + "\n", false);
        if (hero.getArmor() != null)
            this.printMessage("ARMOR:           " + hero.getArmor().getName() + "\n", false);
        if (hero.getHelm() != null)
            this.printMessage("HELMET:          " + hero.getHelm().getName() + "\n", false);
        MainController.enterForContinue();
    }

    public void pickClass() {
        this.printMessage("* WARRIOR (chance of critical damage)\n" +
                "* WIZARD (chance to freeze enemy)\n" +
                "* ARCHER (chance of miss, but more damage)\n\n> ", true);
    }

    @Override
    public void printMessage(String message, boolean flush) {
        if (flush)
            flush();
        System.out.print(message);
    }

    @Override
    public void drawMenu() {
        printMessage("* CREATE HERO\n* SELECT CREATED HERO\n\n> ", true);
    }

    @Override
    public void drawHello() {
        printMessage("\n\n\n", true);
        printMessage(
                   "           _______.____    __    ____  __  .__   __.   ___________    ____          \n"
                          + "          /       |\\   \\  /  \\  /   / |  | |  \\ |  |  /  _____\\   \\  /   /    \n"
                          + "         |   (----` \\   \\/    \\/   /  |  | |   \\|  | |  |  __  \\   \\/   /     \n"
                          + "          \\   \\      \\            /   |  | |  . `  | |  | |_ |  \\_    _/        \n"
                          + "      .----)   |      \\    /\\    /    |  | |  |\\   | |  |__| |    |  |           \n"
                          + "      |_______/        \\__/  \\__/     |__| |__| \\__|  \\______|    |__|          \n",
                false);
        MainController.enterForContinue();
    }

    public void writeMap() {
        flush();
        System.out.print("┍");
        for (int i = 0; i < Map.getMap().getSize() * 2; ++i)
            System.out.print("━");
        System.out.println("┑");
        for (int i = 0; i < Map.getMap().getSize(); ++i) {
            System.out.print("│");
            for (int j = 0; j < Map.getMap().getSize(); ++j) {
                if (Hero.getHero().getCoordinates().getY() == i &&
                        Hero.getHero().getCoordinates().getX() == j) {
                    System.out.print("H ");
                }
                else {
                    System.out.print(Map.getMap().getMapCell(i, j) + " ");
                }
            }
            System.out.println("│");
        }
        System.out.print("┕");
        for (int i = 0; i < Map.getMap().getSize() * 2; ++i)
            System.out.print("━");
        System.out.println("┙");
    }

    public void flush() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
