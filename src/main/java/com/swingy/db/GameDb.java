package com.swingy.db;

import com.swingy.gui.Coordinates;
import com.swingy.model.armor.Armor;
import com.swingy.model.characters.CharacterClass;
import com.swingy.model.characters.Hero;
import com.swingy.model.helm.Helm;
import com.swingy.model.weapon.Weapon;

import java.sql.*;
import java.util.Properties;
import java.util.UUID;

public class GameDb {

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
            updateTables();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return connection;
    }

    public static void closeStatement(PreparedStatement statement) {
        try {
            if (statement != null && !statement.isClosed()) {
                statement.close();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void closeResultSet(ResultSet resultSet) {
        try {
            if (resultSet != null && !resultSet.isClosed()) {
                resultSet.close();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void closeConnection(Connection connection) {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void updateTables() {

    }

    public static Hero selectHero() {

    }

    public static boolean createHero(Hero hero) {
        Connection connection = null;
        PreparedStatement statement = null;
        boolean isSuccess = true;
        try {
            connection = connectToDb();
            if (connection == null)
                throw new NullPointerException();

            updateTables();

            statement = connection.prepareStatement(
                    "INSERT INTO HERO " +
                            "(id, coordinates, character_class, weapon, armor, helm, level, experience, attack, defense, hit_points, max_hp, hp, mana, name) " +
                            "VALUES (?::UUID, ?::UUID, ?::UUID, ?::UUID, ?::UUID, ?::UUID, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            statement.setString(1, hero.getId().toString());
            statement.setString(2, createCoordinates(connection, hero.getCoordinates()).toString());
            statement.setString(3, createCharacterClass(connection, hero).toString());
            statement.setString(4, createWeapon(connection, hero.getWeapon()).toString());
            statement.setString(5, createArmor(connection, hero.getArmor()).toString());
            statement.setString(6, createHelm(connection, hero.getHelm()).toString());
            statement.setInt(7, hero.getLevel());
            statement.setInt(8, hero.getExperience());
            statement.setInt(9, hero.getAttack());
            statement.setInt(10, hero.getDefense());
            statement.setInt(11, hero.getHitPoints());
            statement.setInt(12, hero.getMaxHp());
            statement.setInt(13, hero.getHp());
            statement.setInt(14, hero.getMana());
            hero.pickName();
            statement.setString(15, hero.getName());

            statement.executeUpdate();
        } catch (SQLIntegrityConstraintViolationException ex) {
            System.out.println("Player already exists!");
            isSuccess = false;
        } catch (NullPointerException | SQLException ex) {
            ex.printStackTrace();
            isSuccess = false;
        } finally {
            closeConnection(connection);
            closeStatement(statement);
        }
        return isSuccess;
    }

    private static UUID createHelm(Connection connection, Helm helm) throws SQLException {
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

    private static UUID createArmor(Connection connection, Armor armor) throws SQLException {
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

    private static UUID createCoordinates(Connection connection, Coordinates coordinates) throws SQLException {
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

    private static UUID createCharacterClass(Connection connection, Hero hero) throws SQLException {
        hero.pickClass();
        CharacterClass characterClass = hero.getCharacterClass();

        PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO CHARACTER_CLASS " +
                        "(id, class_name, special_talent) " +
                        "VALUES (?::UUID, ? , ?)");
        statement.setString(1, hero.getId().toString());
        statement.setString(2, characterClass.getClassName());
        statement.setString(3, characterClass.getSpecialTalent());
        statement.executeUpdate();
        return hero.getId();
    }

    private static UUID createWeapon(Connection connection, Weapon weapon) throws SQLException {
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


}
