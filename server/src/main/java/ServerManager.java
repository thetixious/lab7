import com.fasterxml.jackson.core.JsonProcessingException;
import commands.Command;
import commands.CommandEnvironment;
import commands.CommandResult;
import data.SpaceMarine;
import exeptions.EmptyElement;
import exeptions.IncorrectData;
import org.postgresql.util.OSUtil;
import utility.*;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Objects;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;

public class ServerManager {
    private CollectionManager collectionManager;
    private Integer port;
    private InetAddress addr;
    private Boolean serverState;
    private ReceiveManager receiveManager;
    private Message message;
    private ExecutorService responseRequest = Executors.newFixedThreadPool(4);
    private ExecutorService sendRequest = Executors.newFixedThreadPool(4);
    private SQLCollectionManager sqlCollectionManager;
    private SQLUserManager sqlUserManager;


    public ServerManager(InetAddress addr, Integer port, SQLUserManager sqlUserManager, SQLCollectionManager sqlCollectionManager, Connection connection) {
        this.port = port;
        this.addr = addr;
        serverState = true;
        this.sqlUserManager = sqlUserManager;
        this.sqlCollectionManager = sqlCollectionManager;

    }

    public void run() throws IOException, ClassNotFoundException, SQLException, IncorrectData {
        DatagramSocket server = null;
        collectionManager =  sqlCollectionManager.getCollectionManager(); // ghb r
        try {
            server = new DatagramSocket(port);
            receiveManager = new ReceiveManager(server);
        } catch (SocketException e) {
            serverState = false;
        }

        while (serverState) {
            message = receiveManager.receiveMessage();
            if (message.equals(null) || message.getCommand().getName().equals("exit")) {
                continue;
            }
            new ClientThread(message, new SendManager(server, receiveManager.getAddres())).start();

        }
    }

    public CommandResult execute(Message mes) {
        Command curCommand = mes.getCommand();
        CommandEnvironment environment = new CommandEnvironment(collectionManager, sqlCollectionManager,mes.getData(),sqlUserManager,mes.getUser(),mes.getSpaceMarine());
        try {
            sqlUserManager.refreshUsersData();
            CommandResult commandResult = curCommand.run(environment);
            return commandResult;
        } catch (EmptyElement | IncorrectData | SQLException e) {

            return new CommandResult("error", "Проблема с полученными данным", false);
        }
    }

    private class ClientThread {
        private final Message mess;
        private final SendManager sendManager;

        ClientThread(Message mess, SendManager sendManager) {
            this.mess = mess;
            this.sendManager = sendManager;
        }

        private void start() {
            responseRequest.submit(() -> {
                CommandResult commandResult = execute(mess);
                sendRequest.submit(() -> sendManager.sendMessage(commandResult));
            });
        }

    }

}
