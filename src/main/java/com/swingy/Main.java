package com.swingy;

import com.swingy.controller.MainController;
import com.swingy.db.GameDb;
import com.swingy.model.characters.Hero;
import com.swingy.view.GameGui;
import com.swingy.view.Gui;
import com.swingy.view.TermGui;

import java.util.LinkedList;

public class Main {

    public static GameDb gameDb;
    public static Gui gui;
    public static Game game;
    public static MainController controller;

    private static String[] args;

    public static void restartTheGame() {
        Hero.deleteHero();
        try {
            gui.finalize();
        } catch (Throwable ignored) {}
        gui = null;
        game = null;
        gameDb = null;
        controller = null;
        main(args);
    }

    public static void printUsage() {
        System.out.println("Usage :\n" +
                "  java -jar swingy.jar [term/gui]");
    }

    public static Gui validateArgs() {
        if (args.length != 1) {
            printUsage();
            return null;
        } else if (args[0].equals("term")) {
            return new TermGui();
        } else if (args[0].equals("gui")) {
            return new GameGui();
        }
        printUsage();
        return null;
    }

    public static void main(String[] args) {
        Main.args = args;

        gui = validateArgs();
        if (gui == null)
            return;

        gameDb = new GameDb();
        gameDb.updateTables();

        game = new Game();
        controller = new MainController();
        game.run();
    }
}
