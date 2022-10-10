import commands.CommandResult;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.*;

public class SendManager {
    private DatagramSocket client;
    private DatagramPacket packet;
    private SocketAddress address;

    private final int buffSize = 906;

    public SendManager(DatagramSocket client, InetSocketAddress addres) {
        this.client = client;
        this.address = addres;
    }

    public void sendMessage(CommandResult result)  {
        try{
        System.out.println(result);
        byte[] buf = serialize(result);
        packet = new DatagramPacket(buf,buf.length,address);
        client.send(packet);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public byte[] serialize(Serializable mess) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ObjectOutputStream os = new ObjectOutputStream(out);
        os.writeObject(mess);
        return out.toByteArray();
    }

}
