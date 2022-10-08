
import utility.Message;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.net.*;

public class ReceiveManager {

    private DatagramSocket client;
    private byte[] buf;
    private Message message;
    private InetAddress addr;
    private Integer port;

    private DatagramPacket inputPacket;

    public ReceiveManager(DatagramSocket client) throws SocketException {
        this.client = client;
    }

    public Message receiveMessage() throws IOException, ClassNotFoundException {
        buf = new byte[10000];
        inputPacket = new DatagramPacket(buf, buf.length);
        client.receive(inputPacket);
        addr = inputPacket.getAddress();
        port = inputPacket.getPort();
        message = (Message) deserialize(inputPacket.getData());
        return message;
    }

    public Object deserialize(byte[] data) throws IOException, ClassNotFoundException {
        ByteArrayInputStream in = new ByteArrayInputStream(data);
        ObjectInputStream is = new ObjectInputStream(in);
        Serializable mess = (Serializable) is.readObject();
        return mess;
    }

    public InetAddress getAddr() {
        return addr;
    }

    public Integer getPort() {
        return port;
    }
}
