import com.fasterxml.jackson.core.JsonProcessingException;
import commands.Command;
import commands.CommandResult;
import data.SpaceMarine;
import exeptions.EmptyElement;
import exeptions.IncorrectData;
import utility.*;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
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
    private SendManager sendManager;
    private ReceiveManager receiveManager;
    private Message message;
    private CommandResult result;
    private ExecutorService responseRequest = Executors.newFixedThreadPool(4);
    private ExecutorService sendRequest = Executors.newFixedThreadPool(4);
    private SQLCollectionManager sqlCollectionManager;



    public ServerManager(InetAddress addr, Integer port, CollectionManager collectionManager, Integer COUNT_OF_TREADS) {
        this.port = port;
        this.addr = addr;
        this.collectionManager = collectionManager;
        serverState = true;

    }

    public void run() throws IOException, ClassNotFoundException {

        DatagramSocket server = null;

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
        Object data = mes.getData();
        SpaceMarine item = mes.getSpaceMarine();

        try {
            CommandResult commandResult = curCommand.run(collectionManager, data, item);
            collectionManager.saveCollection();
            return commandResult;
        } catch (EmptyElement | IncorrectData | JsonProcessingException e) {
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
