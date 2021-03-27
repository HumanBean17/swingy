package com.swingy.db;

import com.swingy.Main;
import com.swingy.controller.MainController;
import com.swingy.exception.ConnectionFailedException;
import com.swingy.exception.PlayerNotFoundException;
import com.swingy.map.Coordinates;
import com.swingy.model.armor.*;
import com.swingy.model.cclasses.Archer;
import com.swingy.model.cclasses.CharacterClass;
import com.swingy.model.cclasses.Warrior;
import com.swingy.model.cclasses.Wizard;
import com.swingy.model.characters.*;
import com.swingy.model.helm.*;
import com.swingy.model.weapon.*;

import java.sql.*;
import java.util.*;

public class GameDb {

    private final Connection connection = connectToDb();

    public static Connection connectToDb() {
        Properties properties = new Properties();

        String user = "postgres";
        properties.setProperty("user", user);

        String password = "admin";
        properties.setProperty("password", password);

        Connection connection = null;
        try {
            String url = "jdbc:postgresql://localhost/postgres";
            connection = DriverManager.getConnection(url, properties);
        } catch (SQLException ex) {
            Main.gui.printErrorMessage("Connection to database failed. Game progress won't be saved", true);
            Main.gui.printErrorMessage(ex.getMessage(), true);
        }
        return connection;
    }

    public void closeStatement(PreparedStatement statement) {
        try {
            if (statement != null && !statement.isClosed()) {
                statement.close();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void closeResultSet(ResultSet resultSet) {
        try {
            if (resultSet != null && !resultSet.isClosed()) {
                resultSet.close();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void closeConnection(Connection connection) {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (Exception ex) {
            //ex.printStackTrace();
        }
    }

    public boolean updateTables() {
        PreparedStatement statement = null;
        boolean isSuccess = true;
        try {
            if (connection == null)
                throw new ConnectionFailedException();

            statement = connection.prepareStatement("CREATE TABLE IF NOT EXISTS COORDINATES(" +
                    "    ID UUID, " +
                    "    X INTEGER, " +
                    "    Y INTEGER, " +
                    "    CONSTRAINT ID_COORDINATES_PK PRIMARY KEY (ID))");
            statement.executeUpdate();

            statement = connection.prepareStatement("CREATE TABLE IF NOT EXISTS WEAPON( " +
                    "    ID UUID, " +
                    "    ATTACK INTEGER, " +
                    "    NAME VARCHAR, " +
                    "    CONSTRAINT ID_WEAPON_PK PRIMARY KEY (ID))");
            statement.executeUpdate();

            statement = connection.prepareStatement("CREATE TABLE IF NOT EXISTS HELM( " +
                    "    ID UUID, " +
                    "    HIT_POINTS INTEGER, " +
                    "    NAME VARCHAR, " +
                    "    CONSTRAINT ID_HELM_PK PRIMARY KEY (ID))");
            statement.executeUpdate();

            statement = connection.prepareStatement("CREATE TABLE IF NOT EXISTS CHARACTER_CLASS( " +
                    "    ID UUID, " +
                    "    CLASS_NAME VARCHAR, " +
                    "    SPECIAL_TALENT VARCHAR, " +
                    "    CONSTRAINT ID_CHARACTER_CLASS_PK PRIMARY KEY (ID))");
            statement.executeUpdate();

            statement = connection.prepareStatement("CREATE TABLE IF NOT EXISTS ARMOR( " +
                    "    ID UUID, " +
                    "    DEFENSE INTEGER, " +
                    "    NAME VARCHAR, " +
                    "    CONSTRAINT ID_ARMOR_PK PRIMARY KEY (ID))");
            statement.executeUpdate();

            statement = connection.prepareStatement("CREATE TABLE IF NOT EXISTS HERO( " +
                    "    ID UUID, " +
                    "    COORDINATES UUID, " +
                    "    CHARACTER_CLASS UUID, " +
                    "    WEAPON UUID, " +
                    "    ARMOR UUID, " +
                    "    HELM UUID, " +
                    "    LEVEL INTEGER, " +
                    "    EXPERIENCE INTEGER, " +
                    "    ATTACK INTEGER, " +
                    "    DEFENSE INTEGER, " +
                    "    HIT_POINTS INTEGER, " +
                    "    MAX_HP INTEGER, " +
                    "    HP INTEGER, " +
                    "    MANA INTEGER, " +
                    "    NAME VARCHAR, " +
                    "    CONSTRAINT COORDINATES_FK FOREIGN KEY (COORDINATES) REFERENCES COORDINATES(ID), " +
                    "    CONSTRAINT CHARACTER_CLASS_FK FOREIGN KEY (CHARACTER_CLASS) REFERENCES CHARACTER_CLASS(ID), " +
                    "    CONSTRAINT WEAPON_FK FOREIGN KEY (WEAPON) REFERENCES WEAPON(ID), " +
                    "    CONSTRAINT ARMOR_FK FOREIGN KEY (ARMOR) REFERENCES ARMOR(ID), " +
                    "    CONSTRAINT HELM_FK FOREIGN KEY (HELM) REFERENCES HELM(ID), " +
                    "    CONSTRAINT NAME_UC UNIQUE (NAME), " +
                    "    CONSTRAINT ID_NAME_HERO_PK PRIMARY KEY (ID))");
            statement.executeUpdate();

        } catch (NullPointerException | SQLException | ConnectionFailedException ex) {
            //Main.gui.printErrorMessage("Connection to database failed. Game progress won't be saved", true);
            Main.gui.printErrorMessage(ex.getMessage(), true);
            isSuccess = false;
        } finally {
            closeStatement(statement);
        }
        return isSuccess;
    }

    public boolean insertHero(Hero hero) {
        PreparedStatement statement = null;
        boolean isSuccess = true;
        try {
            if (connection == null)
                throw new ConnectionFailedException();

            statement = connection.prepareStatement(
                    "INSERT INTO HERO " +
                            "(id, coordinates, character_class, weapon, armor, helm, level, experience, attack, defense, hit_points, max_hp, hp, mana, name) " +
                            "VALUES (?::UUID, ?::UUID, ?::UUID, ?::UUID, ?::UUID, ?::UUID, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            statement.setString(1, hero.getId().toString());
            statement.setString(2, createCoordinates(hero.getCoordinates()).toString());
            statement.setString(3, createCharacterClass(hero, hero.getCharacterClass()).toString());
            statement.setString(4, createWeapon(hero.getWeapon()).toString());
            statement.setString(5, createArmor(hero.getArmor()).toString());
            statement.setString(6, createHelm(hero.getHelm()).toString());
            statement.setInt(7, hero.getLevel());
            statement.setInt(8, hero.getExperience());
            statement.setInt(9, hero.getAttack());
            statement.setInt(10, hero.getDefense());
            statement.setInt(11, hero.getHitPoints());
            statement.setInt(12, hero.getMaxHp());
            statement.setInt(13, hero.getHp());
            statement.setInt(14, hero.getMana());
            statement.setString(15, hero.getName());
            statement.executeUpdate();
        } catch (NullPointerException | SQLException | ConnectionFailedException ex) {
            Main.gui.printErrorMessage(ex.getMessage(), true);
            isSuccess = false;
        } finally {
            closeStatement(statement);
        }
        return isSuccess;
    }

    public boolean updateHero(Hero hero) {
        PreparedStatement statement = null;
        boolean isSuccess = true;
        try {
            if (connection == null)
                throw new ConnectionFailedException();

            statement = connection.prepareStatement(
                    "UPDATE HERO SET " +
                            "weapon = ?::UUID, " +
                            "armor = ?::UUID, " +
                            "helm = ?::UUID, " +
                            "level = ?, " + //6
                            "experience = ?, " + //7
                            "attack = ?, " + //8
                            "defense = ?, " + //9
                            "hit_points = ?, " + //10
                            "max_hp = ?, " + //11
                            "hp = ?, " + //12
                            "mana = ? " + //13
                        "WHERE name = ?"); //14
            updateCoordinates(hero.getCoordinates());
            statement.setString(1, updateWeapon(hero.getWeapon()).toString());
            statement.setString(2, updateArmor(hero.getArmor()).toString());
            statement.setString(3, updateHelm(hero.getHelm()).toString());
            statement.setInt(4, hero.getLevel());
            statement.setInt(5, hero.getExperience());
            statement.setInt(6, hero.getAttack());
            statement.setInt(7, hero.getDefense());
            statement.setInt(8, hero.getHitPoints());
            statement.setInt(9, hero.getMaxHp());
            statement.setInt(10, hero.getHp());
            statement.setInt(11, hero.getMana());
            statement.setString(12, hero.getName());
            statement.executeUpdate();
        } catch (NullPointerException | SQLException | ConnectionFailedException ex) {
            Main.gui.printErrorMessage(ex.getMessage(), true);
            isSuccess = false;
        } finally {
            closeStatement(statement);
        }
        return isSuccess;
    }

    public List<Hero> getHeroes() {
        List<Hero> heroes = new LinkedList<>();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Hero hero;
        try {
            if (connection == null)
                throw new ConnectionFailedException();

            statement = connection.prepareStatement(
                    "SELECT h.id, " +
                            "c.x x, c.y y, " +
                            "w.name w_name, " +
                            "a.name a_name, " +
                            "cc.class_name cc_name, " +
                            "h2.name h_name, " +
                            "h.level, h.experience, h.attack, h.defense, h.hit_points, h.max_hp, h.hp, h.mana, h.name " +
                            "FROM hero h " +
                            "JOIN coordinates c on h.coordinates = c.id " +
                            "JOIN character_class cc on h.character_class = cc.id " +
                            "JOIN weapon w on h.weapon = w.id " +
                            "JOIN armor a on h.armor = a.id " +
                            "JOIN helm h2 on h.helm = h2.id");
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                hero = new Hero();
                hero.setCoordinates(new Coordinates(resultSet.getInt("x"), resultSet.getInt("y")));
                hero.setWeapon(retrieveWeapon(resultSet));
                hero.setArmor(retrieveArmor(resultSet));
                hero.setCharacterClass(retrieveCharacterClass(resultSet));
                hero.setHelm(retrieveHelm(resultSet));
                hero.setName(resultSet.getString("name"));
                hero.setLevel(resultSet.getInt("level"));
                hero.setExperience(resultSet.getInt("experience"));
                hero.setAttack(resultSet.getInt("attack"));
                hero.setDefense(resultSet.getInt("defense"));
                hero.setHitPoints(resultSet.getInt("hit_points"));
                hero.setMaxHp(resultSet.getInt("max_hp"));
                hero.setHp(resultSet.getInt("hp"));
                hero.setMana(resultSet.getInt("mana"));
                heroes.add(hero);
            }
        } catch (SQLException | NullPointerException | ConnectionFailedException ex) {
            Main.gui.printErrorMessage(ex.getMessage(), false);
        } finally {
            closeResultSet(resultSet);
            closeStatement(statement);
        }
        return heroes;
    }

    public void deleteHero() {
        PreparedStatement statement = null;
        try {
            if (connection == null)
                throw new ConnectionFailedException();

            statement = connection.prepareStatement(
                    "DELETE FROM hero " +
                            "WHERE name = ?");
            statement.setString(1, Hero.getHero().getName());
            statement.executeUpdate();

        } catch (SQLException | NullPointerException | ConnectionFailedException ex) {
            Main.gui.printErrorMessage(ex.getMessage(), true);
        } finally {
            closeStatement(statement);
        }
    }

    private UUID updateHelm(Helm helm) throws SQLException {
        PreparedStatement statement;
        statement = connection.prepareStatement(
                "SELECT * FROM HELM WHERE ID = ?::UUID");
        statement.setString(1, helm.getId().toString());
        if (!statement.executeQuery().next()) {
            return createHelm(helm);
        } /*else {
            statement = connection.prepareStatement(
                    "UPDATE HELM " +
                            "SET hit_points = ?, " +
                            "name = ? " +
                            "WHERE id = ?::UUID");
            statement.setInt(1, helm.getHitPoints());
            statement.setString(2, helm.getName());
            statement.setString(3, helm.getId().toString());
            statement.executeUpdate();
        }
        return helm.getId();*/
        return helm.getId();
    }

    private UUID updateArmor(Armor armor) throws SQLException {
        PreparedStatement statement;
        statement = connection.prepareStatement(
                "SELECT * FROM ARMOR WHERE ID = ?::UUID");
        statement.setString(1, armor.getId().toString());
        if (!statement.executeQuery().next()) {
            return createArmor(armor);
        }
        /*PreparedStatement statement = connection.prepareStatement(
                "UPDATE ARMOR " +
                        "SET defense = ?, " +
                        "name = ? " +
                        "WHERE id = ?::UUID");
        statement.setInt(1, armor.getDefense());
        statement.setString(2, armor.getName());
        statement.setString(3, armor.getId().toString());
        statement.executeUpdate();*/
        return armor.getId();
    }

    private UUID updateWeapon(Weapon weapon) throws SQLException {
        PreparedStatement statement;
        statement = connection.prepareStatement(
                "SELECT * FROM WEAPON WHERE ID = ?::UUID");
        statement.setString(1, weapon.getId().toString());
        if (!statement.executeQuery().next()) {
            return createWeapon(weapon);
        }
        /*PreparedStatement statement = connection.prepareStatement(
                "UPDATE WEAPON " +
                        "SET attack = ?, " +
                        "name = ? " +
                        "WHERE id = ?::UUID");
        statement.setInt(1, weapon.getAttack());
        statement.setString(2, weapon.getName());
        statement.setString(3, weapon.getId().toString());
        statement.executeUpdate();*/
        return weapon.getId();
    }

    private UUID updateCoordinates(Coordinates coordinates) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(
                "UPDATE COORDINATES SET " +
                        "x = ?, " +
                        "y = ? " +
                     "WHERE id = ?::UUID");
        statement.setInt(1, coordinates.getX());
        statement.setInt(2, coordinates.getY());
        statement.setString(3, coordinates.getId().toString());
        statement.executeUpdate();
        return coordinates.getId();
    }

    private UUID createHelm(Helm helm) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO HELM " +
                        "(id, hit_points, name) " +
                        "VALUES (?::UUID, ?, ?)");
        statement.setString(1, helm.getId().toString());
        statement.setInt(2, helm.getHitPoints());
        statement.setString(3, helm.getName());
        statement.executeUpdate();
        return helm.getId();
    }

    private UUID createArmor(Armor armor) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO ARMOR " +
                        "(id, defense, name) VALUES " +
                        "(?::UUID, ?, ?)");
        statement.setString(1, armor.getId().toString());
        statement.setInt(2, armor.getDefense());
        statement.setString(3, armor.getName());
        statement.executeUpdate();
        return armor.getId();
    }

    private UUID createCoordinates(Coordinates coordinates) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO COORDINATES " +
                "(id, x, y) " +
                "VALUES (?::UUID, ?, ?)");
        statement.setString(1, coordinates.getId().toString());
        statement.setInt(2, coordinates.getX());
        statement.setInt(3, coordinates.getY());
        statement.executeUpdate();
        return coordinates.getId();
    }

    private UUID createCharacterClass(Hero hero, CharacterClass characterClass) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO CHARACTER_CLASS " +
                        "(id, class_name, special_talent) " +
                        "VALUES (?::UUID, ? , ?)");
        statement.setString(1, characterClass.getId().toString());
        statement.setString(2, characterClass.getGameClass().toString());
        statement.setString(3, characterClass.getSpecialTalent(hero).toString());
        statement.executeUpdate();
        return characterClass.getId();
    }

    private UUID createWeapon(Weapon weapon) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO WEAPON " +
                        "(id, attack, name) " +
                        "VALUES (?::UUID, ?, ?)");
        statement.setString(1, weapon.getId().toString());
        statement.setInt(2, weapon.getAttack());
        statement.setString(3, weapon.getName());
        statement.executeUpdate();
        return weapon.getId();
    }

    private Weapon retrieveWeapon(ResultSet resultSet) throws SQLException {
        String weaponName = "w_name";
        String result = resultSet.getString(weaponName);
        switch (result.toLowerCase()) {
            case "axe":
                return new Axe();
            case "claymore":
                return new Claymore();
            case "knife":
                return new Knife();
            case "fists":
                return new Fists();
            case "diamond staff":
                return new DiamondStaff();
            case "staff":
                return new Staff();
            case "staff with enchanted glass":
                return new StaffWithEnchantedGlass();
            case "staff with enchanted stone":
                return new StaffWithEnchantedStone();
            case "small bow":
                return new SmallBow();
            case "medium bow":
                return new MediumBow();
            case "large bow":
                return new LargeBow();
            case "slingshot":
                return new Slingshot();
            default:
                throw new SQLException("Unknown weapon provided " + result);
        }

    }

    private Armor retrieveArmor(ResultSet resultSet) throws SQLException {
        String armorName = "a_name";
        String result = resultSet.getString(armorName);
        switch (result.toLowerCase()) {
            case "cloth armor":
                return new ClothArmor();
            case "wooden armor":
                return new WoodenArmor();
            case "cooper armor":
                return new CooperArmor();
            case "iron armor":
                return new IronArmor();
            default:
                throw new SQLException("Unknown armor provided " + result);
        }

    }

    private CharacterClass retrieveCharacterClass(ResultSet resultSet) throws SQLException {
        String characterClassName = "cc_name";
        String result = resultSet.getString(characterClassName);
        switch (result.toLowerCase()) {
            case "wizard":
                return new Wizard();
            case "warrior":
                return new Warrior();
            case "archer":
                return new Archer();
            default:
                throw new SQLException("Unknown character class provided " + result);
        }
    }

    private Helm retrieveHelm(ResultSet resultSet) throws SQLException {
        String helmName = "h_name";
        String result = resultSet.getString(helmName);
        switch (result.toLowerCase()) {
            case "cloth helmet":
                return new ClothHelm();
            case "iron helmet":
                return new IronHelm();
            case "cooper helmet":
                return new CooperHelm();
            case "wooden helmet":
                return new WoodenHelm();
            default:
                throw new SQLException("Unknown helmet provided " + result);
        }
    }


}
