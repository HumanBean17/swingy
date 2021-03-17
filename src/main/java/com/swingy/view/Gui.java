package com.swingy.view;

import com.swingy.model.cclasses.CharacterClass;
import com.swingy.model.characters.Character;
import com.swingy.model.characters.Hero;
import com.swingy.model.characters.Villain;

import java.util.List;

public interface Gui {

    boolean isGui();

    void battleInfoFrame();

    void validationError(String message);

    void takeDamage(CharacterClass characterClass, String name, int takenDamage, int hp);

    void attack(CharacterClass characterClass, String name, Character enemy, int damage);

    void criticalDamage();

    void miss(CharacterClass characterClass, String name);

    void enemyFreeze(CharacterClass characterClass, String name);

    void battleWin();

    void battleLost(Villain villain);

     void startBattle();

     void playerDied();

     void pickMovement();

     void pickName();

     void pickClass();

     void pickPrize(String prizeName);

     void pickHero(List<Hero> heroes);

     void levelUpMessage();

     void gameFinishedMessage();

     void printErrorMessage(String message, boolean flush);

     void info(Hero hero);

     void writeMap();

    void flush();

    void printMessage(String message, boolean flush);

    void drawMenu();

    void drawHello();

}
