package com.swingy;

import com.swingy.controller.MainController;
import com.swingy.model.characters.Hero;
import com.swingy.view.GameGui;
import com.swingy.view.Gui;
import com.swingy.view.TermGui;

public class Main {

    public static Gui gui;
    public static final MainController controller = new MainController();
    private static String[] args;

    public static void restartTheGame() {
        Hero.deleteHero();
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
