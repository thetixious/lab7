package utility;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public final class TableCreator {
    private TableCreator(){}

    public static void creatUserTable(Connection connection) throws SQLException {
        String userQuery = "CREATE TABLE IF NOT EXISTS users (" +
                "login VARCHAR (50) PRIMARY KEY," +
                "password VARCHAR NOT NULL," +
                "master VARCHAR (50) NOT NULL)";
        Statement statement = connection.createStatement();
        statement.execute(userQuery);
    }

    public static void creatCoordinatesTable(Connection connection) throws SQLException {
        String coordinatesQuery = "CREATE TABLE IF NOT EXISTS coordinates (" +
                "id SERIAL PRIMARY KEY," +
                "x INT NOT NULL," +
                "y INT NOT NULL CHECK(y > -84))";
        Statement statement = connection.createStatement();
        statement.execute(coordinatesQuery);
    }

    public static void creatChapterTable(Connection connection) throws SQLException {
        String chapterQuery = "CREATE TABLE IF NOT EXISTS chapter (" +
                "id SERIAL PRIMARY KEY," +
                "name VARCHAR NOT NULL,  " +
                "parent_Legion VARCHAR(30)" +
                ")";
        Statement statement = connection.createStatement();
        statement.execute(chapterQuery);
    }

    public static void creatSpaceMarineTable(Connection connection) throws SQLException {
        String marineQuery = "CREATE TABLE IF NOT EXISTS SpaceMarine (" +
                "id SERIAL PRIMARY KEY," +
                "name VARCHAR(50) NOT NULL, " +
                "coordinates BIGINT NOT NULL, " +
                "creation_date TIMESTAMP NOT NULL," +
                "health REAL NOT NULL CHECK (health >= 0)," +
                "achievements VARCHAR (50) NOT NULL ," +
                "astartes_category  varchar(50) NOT NULL, " +
                "melee_weapon varchar(50) NOT NULL ," +
                "chapter BIGINT NOT NULL," +
                "master VARCHAR(50) NOT NULL, " +
                "FOREIGN KEY (coordinates) REFERENCES coordinates(id)," +
                "FOREIGN KEY (chapter) REFERENCES chapter(id)," +
                "FOREIGN KEY (master) REFERENCES users(login));";
        Statement statement = connection.createStatement();
        statement.execute(marineQuery);
    }

}