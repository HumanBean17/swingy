package com.swingy.view;

import com.swingy.Game;
import com.swingy.controller.MainController;
import com.swingy.db.GameDb;
import com.swingy.map.Map;
import com.swingy.model.cclasses.CharacterClass;
import com.swingy.model.characters.Character;
import com.swingy.model.characters.Hero;
import com.swingy.model.characters.Villain;

import javax.swing.*;
import javax.swing.text.DefaultCaret;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;

public class GameGui extends JFrame implements Gui {

    private static Hero selectedHero = null;

    private volatile JTextField textField;
    private volatile JLabel label;

    private volatile JFrame heroInfoFrame;
    private volatile JFrame battleInfoFrame;

    private volatile JTextArea battleTextArea;

    private final Font textFont = new Font("Courier", Font.PLAIN, 14);

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

    public void heroInfoFrame(Hero hero) {
        if (heroInfoFrame != null) {
            heroInfoFrame.dispose();
            heroInfoFrame = null;
        }

        heroInfoFrame = new JFrame();
        heroInfoFrame.setFont(textFont);
        heroInfoFrame.setPreferredSize(new Dimension(WIDTH - 50, HEIGHT - 150));
        heroInfoFrame.setResizable(false);
        heroInfoFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        heroInfoFrame.setLocationRelativeTo(this);

        String infoString = "<html><body><pre>";
        infoString += ("NAME:                " + hero.getName().toUpperCase() + "<br>");
        infoString += ("CLASS:               " + hero.getCharacterClass().getGameClass() + "<br>");
        infoString += ("HEALTH POINTS:       " + hero.getHp() + "/" + Hero.getHero().getMaxHp() + "<br>");
        infoString += ("LEVEL:               " + hero.getLevel() + "<br>");
        infoString += ("EXPERIENCE:          " + hero.getExperience() + "/" + Game.getNextLevelExperience() + "<br>");
        infoString += ("ATTACK:              " + hero.getAttack() + "<br>");
        infoString += ("DEFENCE:             " + hero.getDefense() + "<br>");
        infoString += ("HIT POINTS:          " + hero.getHitPoints() + "<br>");
        if (hero.getWeapon() != null)
            infoString += ("WEAPON:          " + hero.getWeapon().getName() + "<br>");
        if (hero.getArmor() != null)
            infoString += ("ARMOR:           " + hero.getArmor().getName() + "<br>");
        if (hero.getHelm() != null)
            infoString += ("HELMET:          " + hero.getHelm().getName() + "<br>");
        infoString += "</pre></body></html>";
        JLabel heroInfoLabel = new JLabel(infoString);
        
        JPanel panel = new JPanel();
        panel.add(heroInfoLabel);

        heroInfoFrame.getContentPane().add(panel);

        heroInfoFrame.pack();
        heroInfoFrame.setVisible(true);
    }

    @Override
    public void battleInfoFrame() {
        if (battleInfoFrame != null) {
            battleInfoFrame.dispose();
            battleInfoFrame = null;
        }

        battleInfoFrame = new JFrame();
        battleInfoFrame.setPreferredSize(new Dimension(WIDTH, HEIGHT - 200));
        battleInfoFrame.setResizable(false);
        battleInfoFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        battleInfoFrame.setLocationRelativeTo(this);

        battleTextArea = new JTextArea(9, 35);
        battleTextArea.setEditable(false);
        battleTextArea.setWrapStyleWord(true);
        battleTextArea.setLineWrap(true);
        DefaultCaret caret = (DefaultCaret)battleTextArea.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);

        JScrollPane scroll = new JScrollPane(battleTextArea,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

        JPanel panel = new JPanel();
        panel.add(scroll, BorderLayout.PAGE_START);

        battleInfoFrame.getContentPane().add(panel);

        battleInfoFrame.pack();
        battleInfoFrame.setVisible(true);

        this.setVisible(false);
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
        mapLabel.setFont(textFont);
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

        //this.setLayout(new GridBagLayout());

        JButton upButton = new JButton("up");
        JButton downButton = new JButton("down");
        JButton leftButton = new JButton("left");
        JButton rightButton = new JButton("right");
        JButton infoButton = new JButton("info");

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
        infoButton.addActionListener(e -> {
            heroInfoFrame(Hero.getHero());
            loop = false;
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
        c.gridy = 3;
        c.gridx = 1;
        this.add(infoButton, c);

        this.setVisible(true);
        this.repaint();
        actionOnPerformedLoop();
    }

    @Override
    public void pickClass() {
        loop = true;
        flush();

        //this.setLayout(new GridBagLayout());

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
        warriorLabel.setFont(textFont);
        warriorLabel.setForeground(Color.BLUE);
        JLabel wizardLabel = new JLabel("freeze enemy");
        wizardLabel.setFont(textFont);
        wizardLabel.setForeground(Color.BLUE);
        JLabel archerLabel = new JLabel("more damage");
        archerLabel.setFont(textFont);
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

        JButton chooseNameButton = new JButton("CHOOSE NAME");
        chooseNameButton.addActionListener(e -> {
            Hero.getHero().setName(textField.getText());
            if (Hero.validate(false)) {
                loop = false;
                MainController.guiActions.add(textField.getText());
                System.out.println(MainController.guiActions);
            } else {
                label.setText("Name must be 3-16 characters A-z;0-9 symbols!");
            }
        });

        textField = new JTextField(16);

        label = new JLabel();
        label.setFocusable(false);
        label.setMinimumSize(new Dimension(500, 30));
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

        this.setLayout(new GridBagLayout());

        JComboBox<String> comboBox = new JComboBox<>();
        comboBox.setFocusable(false);
        comboBox.setPreferredSize(new Dimension(150, 30));
        comboBox.setFont(textFont);

        List<Hero> heroes = new LinkedList<>();
        heroes = GameDb.getHeroes();
        for (Hero hero : heroes) {
            comboBox.addItem(hero.getName());
        }

        JButton selectButton = new JButton("SELECT");
        List<Hero> finalHeroes = heroes;
        selectButton.addActionListener(e -> {
            if (comboBox.getSelectedItem() != null) {
                for (Hero hero : finalHeroes) {
                    if (hero.getName().equals(comboBox.getSelectedItem())) {
                        setSelectedHero(hero);
                        MainController.guiActions.add("select");
                        break;
                    }
                }
            }
            setLoop(false);
        });

        JButton createButton = new JButton("CREATE");
        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loop = false;
                MainController.guiActions.add("create");
            }
        });

        GridBagConstraints c = new GridBagConstraints();

        c.fill = GridBagConstraints.CENTER;
        c.gridx = 2;
        c.gridy = 0;
        this.add(comboBox, c);
        c.fill = GridBagConstraints.WEST;
        c.gridy = 1;
        c.gridx = 1;
        this.add(Box.createVerticalStrut(50), c);
        this.add(createButton, c);
        c.fill = GridBagConstraints.EAST;
        c.gridx = -2;
        this.add(Box.createVerticalStrut(50), c);
        this.add(selectButton, c);

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

        JButton continueButton = new JButton("CONTINUE");
        continueButton.addActionListener(e -> loop = false);
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

    private void setSelectedHero(Hero hero) {
        Hero.setHero(hero);
        selectedHero = hero;
    }

    @Override
    public Hero getSelectedHero() {
        return selectedHero;
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
        battleTextArea.setText(characterClass.getGameClass() + " '" + name + "' attacks " +
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
        this.setVisible(true);
        battleTextArea.append("You've won the battle!\n\n");
    }

    @Override
    public void battleLost(Villain villain) {
        this.setVisible(true);
        battleTextArea.append("Villain has " + villain.getHp() + " health points\n\n");
    }

    @Override
    public void playerDied() {
        JOptionPane.showMessageDialog(null,
                "You died. game over on level " + Hero.getHero().getLevel());
    }

    @Override
    public void pickMovement() {
        throw new RuntimeException();
    }

    @Override
    public void pickPrize(String prizeName) {
        int reply = JOptionPane.showConfirmDialog(null,
                prizeName + " has dropped from killed enemy. would you like to take it?",
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
        JOptionPane.showMessageDialog(null, "Congratulations! You've reached game level 7 and completed the game.");
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
        JOptionPane.showMessageDialog(null, message);
    }

    public void setLoop(boolean loop) {
        this.loop = loop;
    }

    public boolean isLoop() {
        return loop;
    }

    @Override
    public void finalize() throws Throwable {
        super.finalize();
        battleInfoFrame.dispose();
        this.dispose();
    }
}
