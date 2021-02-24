package com.swingy;

import com.swingy.model.characters.Hero;

public class Main {

    private static String[] args;

    public static void restartTheGame() {
        Hero.deleteHero();
        main(args);
    }

    public static void main(String[] args) {
        Main.args = args;
        Game game = new Game();
        game.run();
    }
}
