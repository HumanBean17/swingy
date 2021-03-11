package com.swingy.view;

import com.swingy.controller.MainController;
import com.swingy.db.GameDb;
import com.swingy.map.Map;
import com.swingy.model.cclasses.CharacterClass;
import com.swingy.model.characters.Character;
import com.swingy.model.characters.Hero;
import com.swingy.model.characters.Villain;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

public class GameGui extends JFrame implements Gui {

    private volatile JTextField textField;
    private volatile JLabel label;
    private volatile String battleString;

    private final Font buttonFont = new Font("Courier", Font.PLAIN, 14);

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

    private JLabel getMapArea() {
        String mapString = "<html><body><pre>";
        mapString += ("<br>");
        for (int i = 0; i < Map.getMap().getSize(); ++i) {
            for (int j = 0; j < Map.getMap().getSize(); ++j) {
                if (Hero.getHero().getCoordinates().getY() == i &&
                        Hero.getHero().getCoordinates().getX() == j) {
                    mapString += ("H   ");
                }
                else {
                    mapString += (Map.getMap().getMapCell(i, j) + "   ");
                }
            }
            mapString += ("<br>");
        }
        mapString += ("<br></pre></body></html>");
        JLabel mapLabel = new JLabel(mapString);
        mapLabel.setFocusable(false);
        return mapLabel;
    }

    @Override
    public void startBattle() {
        if (JOptionPane.showConfirmDialog(null,
                "YOU'VE MET A VILLAIN. WHAT ARE YOU GOING TO DO?\n> FIGHT\n> RUN\n> ",
                "Start Battle",
                JOptionPane.YES_NO_OPTION) == 1) {
            MainController.guiActions.add("fight");
        } else {
            MainController.guiActions.add("run");
        }
    }

    @Override
    public void writeMap() {
        loop = true;
        flush();

        this.setLayout(new GridBagLayout());

        JButton upButton = new JButton("up");
        upButton.setFont(buttonFont);
        JButton downButton = new JButton("down");
        downButton.setFont(buttonFont);
        JButton leftButton = new JButton("left");
        leftButton.setFont(buttonFont);
        JButton rightButton = new JButton("right");
        rightButton.setFont(buttonFont);

        JLabel mapArea = getMapArea();

        upButton.addActionListener(e -> {
            loop = false;
            MainController.guiActions.add("up");
        });
        downButton.addActionListener(e -> {
            loop = false;
            MainController.guiActions.add("down");
        });
        leftButton.addActionListener(e -> {
            loop = false;
            MainController.guiActions.add("left");
        });
        rightButton.addActionListener(e -> {
            loop = false;
            MainController.guiActions.add("right");
        });

        GridBagConstraints c = new GridBagConstraints();

        c.gridx = 1;
        this.add(mapArea, c);
        c.gridy = 1;
        this.add(upButton, c);
        c.gridx = 0;
        c.gridy = 2;
        this.add(leftButton, c);
        c.gridx = 1;
        this.add(downButton, c);
        c.gridx = 2;
        this.add(rightButton, c);

        this.setVisible(true);
        this.repaint();
        actionOnPerformedLoop();
    }

    @Override
    public void pickClass() {
        loop = true;
        flush();

        this.setLayout(new GridBagLayout());

        JButton warriorButton = new JButton("WARRIOR");
        JButton wizardButton = new JButton("WIZARD");
        JButton archerButton = new JButton("ARCHER");

        warriorButton.addActionListener(e -> {
            loop = false;
            MainController.guiActions.add("warrior");
            System.out.println(MainController.guiActions);
        });
        wizardButton.addActionListener(e -> {
            loop = false;
            MainController.guiActions.add("wizard");
            System.out.println(MainController.guiActions);
        });
        archerButton.addActionListener(e -> {
            loop = false;
            MainController.guiActions.add("archer");
            System.out.println(MainController.guiActions);
        });

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

        this.setLayout(new GridBagLayout());

        JButton chooseNameButton = new JButton("Choose name");
        chooseNameButton.setFont(buttonFont);
        chooseNameButton.addActionListener(new ChooseNameButtonListener());
//        chooseName.setBounds(WIDTH / 2 - 75, HEIGHT - 100, 150, 30);

        textField = new JTextField(16);
//        textField.setSize(150, 30);
//        textField.setBounds(WIDTH / 2 - 75, HEIGHT - 150, 150, 30);

        label = new JLabel();
        label.setFocusable(false);
        label.setMinimumSize(new Dimension(500, 30));
//        label.setSize(500, 30);
//        label.setBounds(WIDTH / 2 - 140, HEIGHT - 200, 500, 30);
        label.setForeground(Color.red);

        GridBagConstraints c = new GridBagConstraints();

        c.gridx = 1;
        c.gridy = 2;
        this.add(chooseNameButton, c);
        c.gridx = 1;
        c.gridy = 0;
        this.add(label, c);
        c.gridy = 1;
        this.add(textField, c);

        this.setVisible(true);
        this.repaint();
        actionOnPerformedLoop();
    }

    @Override
    public void drawMenu() {
        flush();
        loop = true;

        JComboBox<String> comboBox = new JComboBox<>();
        comboBox.setFont(buttonFont);
        comboBox.setBounds(WIDTH / 2 - 65, HEIGHT - 300, 130, 150);
        try {
            List<String> heroes = GameDb.getHeroes();
            for (String hero : heroes) {
                comboBox.addItem(hero);
            }
        } catch (SQLException ignored) { }

        JButton selectButton = new JButton("Select");
        selectButton.setFont(buttonFont);
        selectButton.addActionListener(new SelectHeroButtonListener());
        selectButton.setBounds(WIDTH - 400, HEIGHT - 100, 100, 30);

        JButton createButton = new JButton("Create");
        createButton.setFont(buttonFont);
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
        continueButton.setFont(buttonFont);
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
        battleString += characterClass.getGameClass() + " " + name + " takes damage " +
                takenDamage + " and has " + hp + " health points";
    }

    @Override
    public void attack(CharacterClass characterClass, String name, Character enemy, int damage) {
        battleString += characterClass.getGameClass() + " '" + name + "' attacks " +
                enemy.getCharacterClass().getGameClass() + " " + enemy.getName() + " with damage " + damage;
    }

    @Override
    public void criticalDamage() {
        battleString += "CRITICAL DAMAGE";
    }

    @Override
    public void miss(CharacterClass characterClass, String name) {
        battleString += characterClass.getGameClass() + " '" + name + "' misses";
    }

    @Override
    public void enemyFreeze(CharacterClass characterClass, String name) {
        battleString += "ENEMY WAS FROZEN BY " + characterClass.getGameClass() + " '" + name + "'";
    }

    @Override
    public void battleWin() {
        battleString += "You've won the battle!";
    }

    @Override
    public void battleLost(Villain villain) {
        battleString += "Villain has " + villain.getHp() + " health points";
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
