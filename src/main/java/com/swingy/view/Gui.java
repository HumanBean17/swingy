package com.swingy.view;

import com.swingy.model.characters.Hero;

import javax.swing.*;

public interface Gui {

    public static void startBattle() { throw new RuntimeException(); }

    public static void playerDied() {
        throw new RuntimeException();
    }

    public static void pickMovement() {
        throw new RuntimeException();
    }

    public static void pickGameMode() {
        throw new RuntimeException();
    }

    public static void pickHero() {
        throw new RuntimeException();
    }

    public static void pickName() {
        throw new RuntimeException();
    }

    public static void pickPrize(String prizeName) {
        throw new RuntimeException();
    }

    public static void info(Hero hero) {
        throw new RuntimeException();
    }

    public static void printError(String message) {
        throw new RuntimeException();
    }

    public static void printMessageWithoutFlash(String message) {
        throw new RuntimeException();
    }

    public static void printMessageWithFlush(String message) {
        throw new RuntimeException();
    }

    public static void pickClass() {
        throw new RuntimeException();
    }

    public static void writeMap() {
        throw new RuntimeException();
    }

    public static void flush() {
        throw new RuntimeException();
    }

    public void drawMenu();
}
