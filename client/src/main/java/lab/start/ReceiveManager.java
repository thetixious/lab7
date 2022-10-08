package lab.start;

import commands.CommandResult;
import utility.MessageSerializer;

import java.io.IOException;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.util.Objects;

public class ReceiveManager {
    private final DatagramChannel channel;
    private SocketAddress addr;
    private CommandResult result;
    private final MessageSerializer messageSerializer;
    private Integer cringeTimeCounter = 0;


    public ReceiveManager(DatagramChannel channel, MessageSerializer messageSerializer) {
        this.channel = channel;
        this.messageSerializer = messageSerializer;
    }

    public CommandResult receiveMessage() throws IOException, ClassNotFoundException {
        ByteBuffer buf = ByteBuffer.allocate(10000);
        while (buf.position() == 0) {
            addr = channel.receive(buf);
            cringeTimeCounter+=1;
            if (cringeTimeCounter>100000 && Objects.equals(addr,null))
                return new CommandResult("error","Сервер не отвечает" + cringeTimeCounter,false);
        }
        result = (CommandResult) messageSerializer.deserialize(buf.array());
        return result;

    }



}
