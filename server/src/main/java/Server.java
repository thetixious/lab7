import exeptions.EmptyElement;
import exeptions.IncorrectData;
import utility.*;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Server {

    private static final Integer PORT = 4587;
    private static final VarSetter setter = new VarSetter();

    public static void main(String[] args) throws IOException, EmptyElement, IncorrectData, ClassNotFoundException, SQLException {
        SQLUserManager sqlUserManager = null;
        SQLCollectionManager sqlCollectionManager = null;
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "qwerty");
            sqlUserManager = new SQLUserManager(connection);
            sqlCollectionManager = new SQLCollectionManager(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        File parsFile = new File(setter.getEnv("pars", "parsPath"));
        ServerManager serverManager = new ServerManager(setter.getHost(), PORT, sqlUserManager, sqlCollectionManager, connection);
        serverManager.run();
    }


}
