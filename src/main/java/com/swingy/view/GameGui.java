package com.swingy.view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameGui extends JFrame implements Gui {

    private static final int WIDTH = 500;
    private static final int HEIGHT = 400;

    public GameGui() {
        super("SWINGY");
        this.setSize(WIDTH, HEIGHT);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLayout(null);
    }

    public void drawMenu() {
        JButton continueButton = new JButton("Continue");
        continueButton.addActionListener(new ContinueButtonListener());
        continueButton.setBounds(WIDTH / 2 - 100, HEIGHT - 100, 200, 30);

        this.add(continueButton);
        this.setVisible(true);
    }

    private class ContinueButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {

        }
    }
}
