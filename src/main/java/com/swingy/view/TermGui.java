package com.swingy.view;

import com.swingy.Game;
import com.swingy.map.Map;
import com.swingy.model.cclasses.CharacterClass;
import com.swingy.model.characters.Character;
import com.swingy.model.characters.Hero;
import com.swingy.model.characters.Villain;

public class TermGui implements Gui {

    public final boolean isGui = false;

    @Override
    public boolean isGui() {
        return isGui;
    }

    @Override
    public void battleInfoFrame() {

    }

    public void validationError(String message) {
        this.printMessage(message, true);
    }

    public void takeDamage(CharacterClass characterClass, String name, int takenDamage, int hp) {
        this.printMessage(characterClass.getGameClass() + " " + name + " takes damage " +
                takenDamage + " and has " + hp + " health points", true);
    }

    public void attack(CharacterClass characterClass, String name, Character enemy, int damage) {
        this.printMessage(characterClass.getGameClass() + " '" + name + "' attacks " +
                enemy.getCharacterClass().getGameClass() + " " + enemy.getName() + " with damage " + damage, false);
    }

    public void criticalDamage() {
        this.printMessage("CRITICAL DAMAGE", true);
    }

    public void miss(CharacterClass characterClass, String name) {
        this.printMessage(characterClass.getGameClass() + " '" + name + "' misses", true);
    }

    public void enemyFreeze(CharacterClass characterClass, String name) {
        this.printMessage("ENEMY WAS FROZEN BY " + characterClass.getGameClass() + " '" + name + "'", false);
    }

    public void battleLost(Villain villain) {
        this.printMessage("Villain has " + villain.getHp() + " health points", true);
    }

    public void battleWin() {
        this.printMessage("You've won the battle!", true);
    }

    public void printErrorMessage(String message, boolean flush) {
        this.printMessage("Error while saving hero to database. Game progress will not be saved after exit the game.", true);
    }

    public void selectHeroError() {
        this.printMessage("Error while selecting a hero. Probably it wasn't created.", true);
    }

    public void levelUpMessage() {
        this.printMessage("Level up! You're now level " + Hero.getHero().getLevel() + 1, true);
    }

    public void gameFinishedMessage() {
        this.printMessage("Congratulations! You've reached game level 7 and completed the game.", true);
    }

    public void startBattle() {
        this.printMessage("YOU'VE MET A VILLAIN. WHAT ARE YOU GOING TO DO?\n> FIGHT\n> RUN\n> ", true);
    }

    public void playerDied() {
        this.printMessage("YOU DIED. GAME OVER ON LEVEL " + Hero.getHero().getLevel(), true);
    }

    public void pickMovement() {
        this.printMessage("WHERE TO MOVE? : 'RIGHT/R', 'LEFT/L', 'UP/U', 'DOWN/D', 'INFO'\n> ", true);
    }

    public void pickGameMode() {
        this.printMessage("> CONSOLE\n> GUI\n> ", true);
    }

    public void pickHero() {
        this.printMessage("> CREATE HERO\n> SELECT CREATED HERO\n> ", true);
    }

    public void pickName() {
        this.printMessage("WHAT'S YOUR NAME, STRANGER?\n> ", true);
    }

    public void pickPrize(String prizeName) {
        this.printMessage(prizeName + " HAS DROPPED FROM KILLED ENEMY. WOULD YOU LIKE TO TAKE IT? (YES/NO) > ", true);
    }

    public void info(Hero hero) {
        this.printMessage("NAME:                " + hero.getName().toUpperCase(), true);
        this.printMessage("CLASS:               " + hero.getCharacterClass().getGameClass(), false);
        this.printMessage("HEALTH POINTS:       " + hero.getHp() + "/" + Hero.getHero().getMaxHp(), false);
        this.printMessage("level:               " + hero.getLevel(), false);
        this.printMessage("experience:          " + hero.getExperience() + "/" + Game.getNextLevelExperience(), false);
        this.printMessage("attack:              " + hero.getAttack(), false);
        this.printMessage("defence:             " + hero.getDefense(), false);
        this.printMessage("hit points:          " + hero.getHitPoints(), false);
        if (hero.getWeapon() != null)
            this.printMessage("weapon:          " + hero.getWeapon().getName(), false);
        if (hero.getArmor() != null)
            this.printMessage("armor:           " + hero.getArmor().getName(), false);
        if (hero.getHelm() != null)
            this.printMessage("helmet:          " + hero.getHelm().getName(), false);
    }

    public void pickClass() {
        this.printMessage("> WARRIOR (chance of critical damage)\n" +
                "> WIZARD (chance to freeze enemy)\n" +
                " ARCHER (chance of miss, but more damage)\n> ", true);
    }

    @Override
    public void printMessage(String message, boolean flush) {
        if (flush)
            flush();
        System.out.println(message);
    }

    @Override
    public void drawMenu() {
        flush();
        System.out.print("> CREATE HERO\n> SELECT CREATED HERO\n> ");
    }

    @Override
    public void drawHello() {

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
