package utility;

import data.Chapter;
import data.Coordinates;
import data.SpaceMarine;
import exeptions.EmptyElement;
import exeptions.IncorrectData;

import java.io.PrintWriter;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SQLCollectionManager {

    public SQLCollectionManager() throws SQLException, EmptyElement, IncorrectData {

        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "qwerty");
        TableCreator.creatUserTable(connection);
        String prp = "INSERT INTO users (login, password) VALUES (?,?,?)";
        PreparedStatement com = connection.prepareStatement(prp);
        TableCreator.creatChapterTable(connection);
        TableCreator.creatCoordinatesTable(connection);
        TableCreator.creatSpaceMarineTable(connection);


    }

    public static void loaderChapter(PreparedStatement statement, Chapter chapter) throws SQLException {
        final int indexName = 1;
        final int indexParentLegion = 2;
        statement.setString(indexName, chapter.getName());
        statement.setString(indexParentLegion, chapter.getParentLegion());
    }

    public static void loaderCoordinates(PreparedStatement statement, Coordinates coordinates) throws SQLException {
        final int indexX = 1;
        final int indexY = 2;
        statement.setDouble(indexX, coordinates.getX());
        statement.setDouble(indexY, coordinates.getY());
    }

    public static void loaderSpaceMarine(PreparedStatement statement, SpaceMarine spaceMarine) throws SQLException {
        DateTimeFormatter form = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        statement.setString(1, spaceMarine.getName());
        statement.setLong(2, spaceMarine.getCoordinates().getId());
        statement.setTimestamp(3, Timestamp.valueOf(spaceMarine.getCreationDate().format(form)));
        statement.setDouble(4, spaceMarine.getHealth());
        statement.setString(5, spaceMarine.getAchievements());
        statement.setString(6, spaceMarine.getCategory().toString());
        statement.setString(7, spaceMarine.getMeleeWeapon().toString());
        statement.setLong(8, spaceMarine.getChapter().getId());
        statement.setString(9, spaceMarine.getMaster());
    }

    public static void addToBD(SpaceMarine spaceMarine, Connection connection) {
        final String addSpaceMarine = "INSERT INTO SpaceMarine VALUES (" +
                "default,?,?,?,?,?,?,?,?,?) RETURNING id";
        final String addCoordinates = "INSERT INTO Coordinates VALUES (" +
                "default,?,?) RETURNING id";
        final String addChapter = "INSERT INTO Chapter VALUES (" +
                "default,?,?) RETURNING id";

        try (PreparedStatement statementChapter = connection.prepareStatement(addChapter);
             PreparedStatement statementCoordinates = connection.prepareStatement(addCoordinates);
             PreparedStatement statementSpaceMarine = connection.prepareStatement(addSpaceMarine)) {
            loaderChapter(statementChapter, spaceMarine.getChapter());
            loaderCoordinates(statementCoordinates, spaceMarine.getCoordinates());
            ResultSet chapterResult = statementChapter.executeQuery();
            ResultSet coordinatesResult = statementCoordinates.executeQuery();
            chapterResult.next();
            coordinatesResult.next();
            spaceMarine.getChapter().setId(chapterResult.getLong("id"));
            spaceMarine.getCoordinates().setId(coordinatesResult.getLong("id"));
            loaderSpaceMarine(statementSpaceMarine, spaceMarine);
            ResultSet marineResult = statementSpaceMarine.executeQuery();
            marineResult.next();
            spaceMarine.setId(marineResult.getLong("id"));

        } catch (SQLException | IncorrectData e) {
            e.printStackTrace();
        }
    }

}
