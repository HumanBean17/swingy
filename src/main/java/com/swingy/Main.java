package com.swingy;

import com.swingy.controller.MainController;
import com.swingy.model.characters.Hero;
import com.swingy.view.GameGui;
import com.swingy.view.Gui;
import com.swingy.view.TermGui;

import java.util.LinkedList;

public class Main {

    public static Gui gui;
    public static MainController controller = new MainController();
    private static String[] args;

    public static void restartTheGame() {
        Hero.deleteHero();
        try {
            gui.finalize();
        } catch (Throwable ignored) {}
        gui = null;
        Game.setEnemies(new LinkedList<>());
        controller = new MainController();
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
        Game game = new Game();
        game.run();
    }
}
