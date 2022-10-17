package commands;

import data.SpaceMarine;
import utility.CollectionManager;
import utility.SQLCollectionManager;
import utility.SQLUserManager;

public class CommandEnvironment {
    private CollectionManager collectionManager;
    private SQLCollectionManager sqlCollectionManager;
    private Object data;
    private SQLUserManager sqlUserManager;
    private String user;
    private SpaceMarine spaceMarine;

    public CommandEnvironment(CollectionManager collectionManager, SQLCollectionManager sqlCollectionManager, Object data, SQLUserManager sqlUserManager, String user, SpaceMarine spaceMarine) {
        this.collectionManager = collectionManager;
        this.sqlCollectionManager = sqlCollectionManager;
        this.data = data;
        this.sqlUserManager = sqlUserManager;
        this.user = user;
        this.spaceMarine = spaceMarine;
    }

    public CollectionManager getCollectionManager() {
        return collectionManager;
    }

    public SQLCollectionManager getSqlCollectionManager() {
        return sqlCollectionManager;
    }

    public Object getData() {
        return data;
    }

    public SQLUserManager getSqlUserManager() {
        return sqlUserManager;
    }

    public String getUser() {
        return user;
    }

    public SpaceMarine getSpaceMarine() {
        return spaceMarine;
    }
}
