import commands.Command;
import commands.CommandResult;
import data.SpaceMarine;
import exeptions.EmptyElement;
import exeptions.IncorrectData;
import utility.CollectionManager;
import utility.CollectionSerializer;
import utility.CommandPool;
import utility.Message;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Objects;

public class ServerManager {
    CollectionManager collectionManager;
    CommandPool commandPool;
    Integer port;
    InetAddress addr;
    Boolean serverState = true;
    SendManager sendManager;
    ReceiveManager receiveManager;
    Message message;
    CollectionSerializer serializer;
    CommandResult result;

    public ServerManager(CommandPool commandPool, InetAddress addr, Integer port, CollectionSerializer serializer,CollectionManager collectionManager) {
        this.commandPool = commandPool;
        this.port = port;
        this.addr = addr;
        this.serializer = serializer;
        this.collectionManager = collectionManager;
    }

    public void run() throws EmptyElement, IncorrectData, IOException, ClassNotFoundException {


        DatagramSocket server = null;
        try {
            server = new DatagramSocket(port);
            receiveManager = new ReceiveManager(server);
        } catch (SocketException e) {
            serverState = false;
        }
        sendManager = new SendManager(server);

        while (serverState) {
            message = receiveManager.receiveMessage();
            if (message.equals(null) || message.getCommand().getName().equals("exit")) {
                continue;
            }
            result = execute(message);
            if (Objects.equals(result, "error")) {
                sendManager.sendMessage(new CommandResult("error", "Что-то не так с данными", false), receiveManager.getPort(), receiveManager.getAddr());
            } else {
                sendManager.sendMessage(result, receiveManager.getPort(), receiveManager.getAddr());
            }
            collectionManager.saveCollection();
        }
    }

    public CommandResult execute(Message mes) throws EmptyElement, IncorrectData {
        Command curCommand = mes.getCommand();
        Object data = mes.getData();
        SpaceMarine item = mes.getSpaceMarine();
        return curCommand.run(collectionManager, data, item);
    }
}
