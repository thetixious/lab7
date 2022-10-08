import commands.CommandResult;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class SendManager {
    private DatagramSocket client;
    private DatagramPacket packet;

    private final int buffSize = 906;

    public SendManager(DatagramSocket client) {
        this.client = client;
    }

    public void sendMessage(CommandResult result, Integer port, InetAddress addr) throws IOException {
        System.out.println(result);
        byte[] buf = serialize(result);
        packet = new DatagramPacket(buf,buf.length,addr,port);
        client.send(packet);

    }

    public byte[] serialize(Serializable mess) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ObjectOutputStream os = new ObjectOutputStream(out);
        os.writeObject(mess);
        return out.toByteArray();
    }

}
