package com.swingy.view;

import com.swingy.Main;
import com.swingy.controller.MainController;
import com.swingy.db.GameDb;
import com.swingy.model.cclasses.CharacterClass;
import com.swingy.model.characters.Character;
import com.swingy.model.characters.Hero;
import com.swingy.model.characters.Villain;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class GameGui extends JFrame implements Gui {

    private volatile JTextField textField;
    private volatile JLabel label;

    private volatile boolean loop = false;
    public final boolean isGui = true;

    private static final int WIDTH = 500;
    private static final int HEIGHT = 400;

    public GameGui() {
        super("Swingy");
        this.setFont(new Font("Courier", Font.PLAIN, 16));
        this.setSize(WIDTH, HEIGHT);
        this.setResizable(false);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    @Override
    public void pickClass() {
        loop = true;
        flush();

        this.setLayout(new GridBagLayout());

        JButton warriorButton = new JButton("WARRIOR");
        JButton wizardButton = new JButton("WIZARD");
        JButton archerButton = new JButton("ARCHER");

        warriorButton.addActionListener(new ChooseWarriorButtonListener());
        wizardButton.addActionListener(new ChooseWizardButtonListener());
        archerButton.addActionListener(new ChooseArcherButtonListener());

        GridBagConstraints c = new GridBagConstraints();

        JLabel warriorLabel = new JLabel("critical damage");
        warriorLabel.setForeground(Color.BLUE);
        JLabel wizardLabel = new JLabel("freeze enemy");
        wizardLabel.setForeground(Color.BLUE);
        JLabel archerLabel = new JLabel("more damage");
        archerLabel.setForeground(Color.BLUE);

        c.gridx = 0;
        this.add(warriorButton, c);
        c.gridx = 1;
        this.add(Box.createHorizontalStrut(25), c);
        c.gridx = 2;
        this.add(wizardButton, c);
        c.gridx = 3;
        this.add(Box.createHorizontalStrut(25), c);
        c.gridx = 4;
        this.add(archerButton, c);

        c.gridx = 0;
        c.gridy = 1;
        this.add(warriorLabel, c);
        c.gridx = 1;
        this.add(Box.createHorizontalStrut(50), c);
        c.gridx = 2;
        this.add(wizardLabel, c);
        c.gridx = 3;
        this.add(Box.createHorizontalStrut(50), c);
        c.gridx = 4;
        this.add(archerLabel, c);

        this.setVisible(true);
        this.repaint();
        actionOnPerformedLoop();
    }

    @Override
    public void pickName() {
        flush();
        loop = true;

        JButton chooseName = new JButton("Choose name");
        chooseName.addActionListener(new ChooseNameButtonListener());
        chooseName.setBounds(WIDTH / 2 - 75, HEIGHT - 100, 150, 30);

        textField = new JTextField();
        textField.setBounds(WIDTH / 2 - 75, HEIGHT - 150, 150, 30);

        label = new JLabel();
        label.setBounds(WIDTH / 2 - 140, HEIGHT - 200, 500, 30);
        label.setForeground(Color.red);

        this.add(chooseName);
        this.add(label);
        this.add(textField);

        this.setVisible(true);
        this.repaint();
        actionOnPerformedLoop();
    }

    @Override
    public void drawMenu() {
        flush();
        loop = true;

        JComboBox<String> comboBox = new JComboBox<>();
        comboBox.setFont(new Font("Courier", Font.PLAIN, 16));
        comboBox.setBounds(WIDTH / 2 - 65, HEIGHT - 300, 130, 150);
        try {
            List<String> heroes = GameDb.getHeroes();
            for (String hero : heroes) {
                comboBox.addItem(hero);
            }
        } catch (SQLException ignored) { }

        JButton selectButton = new JButton("SELECT");
        selectButton.setFont(new Font("Courier", Font.PLAIN, 16));
        selectButton.addActionListener(new SelectHeroButtonListener());
        selectButton.setBounds(WIDTH - 400, HEIGHT - 100, 100, 30);

        JButton createButton = new JButton("CREATE");
        createButton.setFont(new Font("Courier", Font.PLAIN, 16));
        createButton.addActionListener(new CreateHeroButtonListener());
        createButton.setBounds(WIDTH - 200, HEIGHT - 100, 100, 30);

        this.add(comboBox);
        this.add(createButton);
        this.add(selectButton);

        this.setVisible(true);
        this.repaint();
        actionOnPerformedLoop();
    }

    @Override
    public void drawHello() {
        flush();
        loop = true;

        JLabel swingyLabel = new JLabel("SWINGY");
        swingyLabel.setFont(new Font("Courier", Font.PLAIN, 35));
        swingyLabel.setBounds(WIDTH / 2 - 65, HEIGHT - 300, 130, 150);

        JButton continueButton = new JButton("Continue");
        continueButton.setFont(new Font("Courier", Font.PLAIN, 16));
        continueButton.addActionListener(new ContinueButtonListener());
        continueButton.setBounds(WIDTH / 2 - 100, HEIGHT - 100, 200, 30);

        this.add(swingyLabel);
        this.add(continueButton);

        this.setVisible(true);
        this.repaint();
        actionOnPerformedLoop();
    }

    public void actionOnPerformedLoop() {
        while (isLoop()) { }
    }

    @Override
    public boolean isGui() {
        return isGui;
    }

    @Override
    public void validationError(String message) {

    }

    @Override
    public void takeDamage(CharacterClass characterClass, String name, int takenDamage, int hp) {

    }

    @Override
    public void attack(CharacterClass characterClass, String name, Character enemy, int damage) {

    }

    @Override
    public void criticalDamage() {

    }

    @Override
    public void miss(CharacterClass characterClass, String name) {

    }

    @Override
    public void enemyFreeze(CharacterClass characterClass, String name) {

    }

    @Override
    public void battleWin() {

    }

    @Override
    public void battleLost(Villain villain) {

    }

    @Override
    public void startBattle() {

    }

    @Override
    public void playerDied() {

    }

    @Override
    public void pickMovement() {

    }

    @Override
    public void pickGameMode() {

    }

    @Override
    public void pickHero() {

    }

    @Override
    public void pickPrize(String prizeName) {

    }

    @Override
    public void levelUpMessage() {

    }

    @Override
    public void gameFinishedMessage() {

    }

    @Override
    public void selectHeroError() {

    }

    @Override
    public void saveHeroToDbError() {
        JOptionPane.showMessageDialog(null,
                "ERROR WHILE SAVING HERO TO DATABASE. GAME PROGRESS WILL NOT BE SAVED AFTER EXIT THE GAME.",
                "Error info",
                JOptionPane.ERROR_MESSAGE);
    }

    @Override
    public void info(Hero hero) {

    }

    @Override
    public void writeMap() {

    }

    @Override
    public void flush() {
        this.getContentPane().removeAll();
        this.repaint();
    }

    @Override
    public void printMessage(String message, boolean flush) {

    }

    public void setLoop(boolean loop) {
        this.loop = loop;
    }

    public boolean isLoop() {
        return loop;
    }

    private class ChooseArcherButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            loop = false;
            MainController.guiActions.add("archer");
        }
    }

    private class ChooseWizardButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            loop = false;
            MainController.guiActions.add("wizard");
        }
    }

    private class ChooseWarriorButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            loop = false;
            MainController.guiActions.add("warrior");
        }
    }

    private class ChooseNameButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Hero.getHero().setName(textField.getText());
            if (Hero.validate(false)) {
                loop = false;
                MainController.guiActions.add(textField.getText());
            } else {
                label.setText("Name must be 3-16 characters A-z;0-9 symbols!");
            }
        }
    }

    private class CreateHeroButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            MainController.guiActions.add("create");
            setLoop(false);
        }
    }

    private class SelectHeroButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            MainController.guiActions.add("select");
            setLoop(false);
        }
    }

    private class ContinueButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            setLoop(false);
        }
    }
}
