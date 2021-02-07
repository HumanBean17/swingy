package com.swingy;

public class Main {

    private static String[] args;

    public static void restartTheGame() {
        main(args);
    }

    public static void main(String[] args) {
        Main.args = args;
        Game game = new Game();
        game.run();
    }
}
