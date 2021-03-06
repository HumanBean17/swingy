package com.swingy.view;

import com.swingy.model.cclasses.CharacterClass;
import com.swingy.model.characters.Character;
import com.swingy.model.characters.Hero;
import com.swingy.model.characters.Villain;

public interface Gui {

    boolean isGui();

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

     void pickGameMode();

     void pickHero();

     void pickName();

     void pickPrize(String prizeName);

     void levelUpMessage();

     void gameFinishedMessage();

     void selectHeroError();

     void saveHeroToDbError();

     void info(Hero hero);

     void pickClass();

     void writeMap();

    void flush();

    void printMessage(String message, boolean flush);

    void drawMenu();

    void drawHello();

}
