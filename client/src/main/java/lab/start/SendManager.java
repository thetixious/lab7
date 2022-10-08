package lab.start;

import utility.Message;
import utility.MessageSerializer;

import java.io.IOException;
import java.io.Serializable;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

public class SendManager implements Serializable {

    private DatagramChannel client;
    private InetSocketAddress addr;
    byte[] arr;
    ByteBuffer buf;
    MessageSerializer messageSerializer;

    public SendManager(DatagramChannel channel, InetSocketAddress serverAddr, MessageSerializer messageSerializer) {
        this.client = channel;
        addr = serverAddr;
        this.messageSerializer = messageSerializer;
    }

    public void sendMessage(Message message) throws IOException {
        arr = messageSerializer.serialize(message);
        buf = ByteBuffer.wrap(arr);
        client.send(buf, addr);
        buf.clear();

    }


}
