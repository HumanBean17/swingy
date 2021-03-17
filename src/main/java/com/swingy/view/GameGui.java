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

    private volatile JFrame battleInfoFrame;
    private volatile JTextArea battleTextArea;

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

    @Override
    public void battleInfoFrame() {
        battleInfoFrame = new JFrame();
        battleInfoFrame.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        battleInfoFrame.setResizable(false);
        battleInfoFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        battleInfoFrame.setLocationRelativeTo(null);

        battleTextArea = new JTextArea(20, 35);
        battleTextArea.setEditable(false);
        battleTextArea.setWrapStyleWord(true);
        battleTextArea.setLineWrap(true);

        JScrollPane scroll = new JScrollPane(battleTextArea,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

        JPanel panel = new JPanel();
        panel.add(scroll, BorderLayout.PAGE_START);

        battleInfoFrame.getContentPane().add(panel);

        battleInfoFrame.pack();
        battleInfoFrame.setVisible(true);
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
        int reply = JOptionPane.showConfirmDialog(null,
                "YOU'VE MET A VILLAIN. FIGHT?",
                "Start Battle",
                JOptionPane.YES_NO_OPTION);
        if (reply == JOptionPane.YES_OPTION) {
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

        JComboBox<Hero> comboBox = new JComboBox<>();
        comboBox.setFont(buttonFont);
        comboBox.setBounds(WIDTH / 2 - 65, HEIGHT - 300, 130, 150);
        try {
            List<Hero> heroes = GameDb.getHeroes();
            for (Hero hero : heroes) {
                comboBox.addItem(hero);
            }
        } catch (SQLException ignored) { }

        JButton selectButton = new JButton("Select");
        selectButton.setFont(buttonFont);
        selectButton.addActionListener(e -> {
            MainController.guiActions.add(((Hero) comboBox.getSelectedItem()).getName());
            MainController.guiActions.add("select");
            setLoop(false);
        });
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
        throw new RuntimeException();
    }

    @Override
    public void takeDamage(CharacterClass characterClass, String name, int takenDamage, int hp) {
        battleTextArea.append(characterClass.getGameClass() + " " + name + " takes damage " +
                takenDamage + " and has " + hp + " health points\n\n");
    }

    @Override
    public void attack(CharacterClass characterClass, String name, Character enemy, int damage) {
        battleTextArea.append(characterClass.getGameClass() + " '" + name + "' attacks " +
                enemy.getCharacterClass().getGameClass() + " " + enemy.getName() + " with damage " + damage + "\n\n");
    }

    @Override
    public void criticalDamage() {
        battleTextArea.append("CRITICAL DAMAGE\n\n");
    }

    @Override
    public void miss(CharacterClass characterClass, String name) {
        battleTextArea.append(characterClass.getGameClass() + " '" + name + "' misses\n\n");
    }

    @Override
    public void enemyFreeze(CharacterClass characterClass, String name) {
        battleTextArea.append("ENEMY WAS FROZEN BY " + characterClass.getGameClass() + " '" + name + "'\n\n");
    }

    @Override
    public void battleWin() {
        JOptionPane.showMessageDialog(null,
                "YOU'VE WON THE BATTLE!\n\n");
    }

    @Override
    public void battleLost(Villain villain) {
        JOptionPane.showMessageDialog(null,
                "VILLAIN HAS " + villain.getHp() + " HEALTH POINTS\n\n");
    }

    @Override
    public void playerDied() {
        JOptionPane.showMessageDialog(null,
                "YOU DIED. GAME OVER ON LEVEL " + Hero.getHero().getLevel());
    }

    @Override
    public void pickMovement() {
        throw new RuntimeException();
    }

    @Override
    public void pickPrize(String prizeName) {
        int reply = JOptionPane.showConfirmDialog(null,
                "HAS DROPPED FROM KILLED ENEMY. WOULD YOU LIKE TO TAKE IT?",
                        "Pick Prize",
                JOptionPane.YES_NO_OPTION);
        if (reply == JOptionPane.YES_OPTION) {
            MainController.guiActions.add("yes");
        } else {
            MainController.guiActions.add("no");
        }
    }

    @Override
    public void pickHero(List<Hero> heroes) {
        throw new RuntimeException();
    }

    @Override
    public void levelUpMessage() {
        JOptionPane.showMessageDialog(null,
                "Level up! You're now level " + Hero.getHero().getLevel() + 1);
    }

    @Override
    public void gameFinishedMessage() {
        throw new RuntimeException();
    }

    @Override
    public void printErrorMessage(String message, boolean flush) {
        JOptionPane.showMessageDialog(null,
                message,
                "Error info",
                JOptionPane.ERROR_MESSAGE);
    }

    @Override
    public void info(Hero hero) {
        throw new RuntimeException();
    }

    @Override
    public void flush() {
        this.getContentPane().removeAll();
        this.repaint();
    }

    @Override
    public void printMessage(String message, boolean flush) {
        throw new RuntimeException();
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

    private class ContinueButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            setLoop(false);
        }
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        this.dispose();
    }
}
