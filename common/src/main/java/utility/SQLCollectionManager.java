package utility;

import commands.CommandResult;
import data.Chapter;
import data.Coordinates;
import data.MeleeWeapon;
import data.SpaceMarine;
import exeptions.EmptyElement;
import exeptions.IncorrectData;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;

public class SQLCollectionManager {
    private Collection collection;
    private CollectionManager collectionManager;
    private final Connection connection;

    public SQLCollectionManager(Connection connection) throws SQLException, EmptyElement, IncorrectData {
        this.connection = connection;
        TableCreator.creatChapterTable(connection);
        TableCreator.creatCoordinatesTable(connection);
        TableCreator.creatSpaceMarineTable(connection);


    }

    public CollectionManager getCollectionManager() throws SQLException, IncorrectData {
        deserialize();
        return collectionManager;
    }

    public void deserialize() throws SQLException, IncorrectData {
        collectionManager = new CollectionManager(new CommandPool());
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM SpaceMarine");
        while (resultSet.next()) {
            collectionManager.addMarine(getSpaceMarine(resultSet));
        }

    }

    public SpaceMarine getSpaceMarine(ResultSet result) throws SQLException, IncorrectData {
        DateTimeFormatter form = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        SpaceMarine spaceMarine = new SpaceMarine();
        spaceMarine.setId(result.getLong("id"));
        spaceMarine.setCreationDate(LocalDateTime.parse(result.getString("creation_date"), form));
        spaceMarine.setMeleeWeapon(MeleeWeapon.valueOf(result.getString("melee_weapon").toUpperCase()));
        spaceMarine.setAchievements(result.getString("achievements"));
        spaceMarine.setHealth(Double.parseDouble(result.getString("health")));
        spaceMarine.setMaster(result.getString("master"));

        PreparedStatement statement;
        result.getString("chapter");
        String getChapter = "SELECT * FROM chapter WHERE id=?";
        statement = connection.prepareStatement(getChapter);
        statement.setLong(1, result.getLong("chapter"));
        ResultSet resChapter = statement.executeQuery();
        resChapter.next();
        spaceMarine.setChapter(new Chapter(resChapter.getLong("id"),
                resChapter.getString("name"),
                resChapter.getString("parent_legion")));
        String getCoord = "SELECT * FROM coordinates WHERE id=?";
        statement = connection.prepareStatement(getCoord);
        statement.setInt(1, result.getInt("coordinates"));
        ResultSet resCoord = statement.executeQuery();
        resCoord.next();
        spaceMarine.setCoordinates(new Coordinates(resCoord.getLong("id"), resCoord.getInt("x"), resCoord.getInt("y")));
        return spaceMarine;
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
        System.out.println(spaceMarine.getCoordinates().getId());
        System.out.println(spaceMarine.getChapter().getId());
        System.out.println(spaceMarine.getCategory().toString());
        System.out.println(spaceMarine.getMeleeWeapon().toString());
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

    public void addToBD(SpaceMarine spaceMarine) {
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

    public void clearCollection() {

    }
}
