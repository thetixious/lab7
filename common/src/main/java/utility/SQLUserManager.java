package utility;

import java.sql.*;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;

public class SQLUserManager {

    public Collection<String> loginSet;
    public Collection<String> pasSet;
    private final Connection connection;

    public SQLUserManager(Connection connection) throws SQLException {
        this.connection = connection;
        TableCreator.creatUserTable(connection);
        deSerializeLogs();
        deSerializePasswords();
    }
    public void refreshUsersData() throws  SQLException{
        deSerializePasswords();
        deSerializeLogs();
    }
    private void deSerializeLogs() throws SQLException {
        loginSet = Collections.synchronizedSet(new HashSet<>());
        Statement stat = connection.createStatement();
        ResultSet res = stat.executeQuery("SELECT login FROM users");
        while (res.next()) {
            loginSet.add(res.getString("login"));
        }
    }
    public boolean checkLogin(String login) {
        return loginSet.contains(login);
    }

    private void deSerializePasswords() throws SQLException {
        pasSet = Collections.synchronizedSet(new HashSet<>());
        Statement stat = connection.createStatement();
        ResultSet res = stat.executeQuery("SELECT password FROM users");
        while (res.next()) {
            pasSet.add(res.getString("password"));
        }
    }

    public boolean checkPassword(String password){
        return pasSet.contains(password);
    }

    public boolean isItRegistrated(String login, String password){
        return checkLogin(login) && checkPassword(password);
    }
    public void register(String login, String password) throws SQLException {
        String prp = "INSERT INTO users (login, password) VALUES (?,?)";
        PreparedStatement statement = connection.prepareStatement(prp);
        statement.setString(1, login);
        statement.setString(2, password);
        int iM = statement.executeUpdate();
        
    }

}
