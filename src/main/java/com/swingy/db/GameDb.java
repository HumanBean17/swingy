package com.swingy.db;

import com.swingy.exception.PlayerNotFoundException;
import com.swingy.gui.Coordinates;
import com.swingy.model.armor.Armor;
import com.swingy.model.armor.HeavyArmor;
import com.swingy.model.armor.LightArmor;
import com.swingy.model.armor.MediumArmor;
import com.swingy.model.characters.*;
import com.swingy.model.helm.HeavyHelm;
import com.swingy.model.helm.Helm;
import com.swingy.model.helm.LightHelm;
import com.swingy.model.helm.MediumHelm;
import com.swingy.model.weapon.*;

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
            System.out.println(ex.getMessage());
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

    private static Weapon retrieveWeapon(ResultSet resultSet) throws SQLException {
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
            default:
                throw new SQLException("Unknown weapon provided " + result);
        }

    }

    private static Armor retrieveArmor(ResultSet resultSet) throws SQLException {
        String armorName = "a_name";
        String result = resultSet.getString(armorName);
        switch (result.toLowerCase()) {
            case "light armor":
                return new LightArmor();
            case "medium armor":
                return new MediumArmor();
            case "heavy armor":
                return new HeavyArmor();
            default:
                throw new SQLException("Unknown armor provided " + result);
        }

    }

    private static CharacterClass retrieveCharacterClass(ResultSet resultSet) throws SQLException {
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

    private static Helm retrieveHelm(ResultSet resultSet) throws SQLException {
        String helmName = "h_name";
        String result = resultSet.getString(helmName);
        switch (result.toLowerCase()) {
            case "heavy helmet":
                return new HeavyHelm();
            case "medium helmet":
                return new MediumHelm();
            case "light helmet":
                return new LightHelm();
            default:
                throw new SQLException("Unknown helmet provided " + result);
        }
    }

    public static Hero selectHero(String name) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Hero hero;
        try {
            connection = connectToDb();
            if (connection == null)
                throw new NullPointerException();

            statement = connection.prepareStatement(
                    "SELECT h.id, " +
                            "c.x x, c.y y, " +
                            "w.name w_name, " +
                            "a.name a_name, " +
                            "cc.class_name cc_name, " +
                            "h.name, " +
                            "h.level, h.experience, h.attack, h.defense, h.hit_points, h.max_hp, h.hp, h.mana, h.name " +
                         "FROM hero h " +
                            "JOIN coordinates c on h.coordinates = c.id " +
                            "JOIN character_class cc on h.character_class = cc.id " +
                            "JOIN weapon w on h.weapon = w.id " +
                            "JOIN armor a on h.armor = a.id " +
                            "JOIN helm h2 on h.helm = h2.id " +
                        "WHERE h.name = ?");
            statement.setString(1, name);
            resultSet = statement.executeQuery();
            if (!resultSet.next()) {
                throw new PlayerNotFoundException();
            }

            hero = Hero.createHero();
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
        } catch (SQLException | NullPointerException | PlayerNotFoundException ex) {
            System.out.println(ex.getMessage());
            hero = null;
        } finally {
            closeResultSet(resultSet);
            closeStatement(statement);
            closeConnection(connection);
        }
        return hero;
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
            System.out.println(ex.getMessage());
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
