import commands.*;
import exeptions.EmptyElement;
import exeptions.IncorrectData;
import utility.CollectionManager;
import utility.CollectionSerializer;
import utility.CommandPool;
import utility.IOManager;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Objects;

public class Server {

    private static final Integer PORT = 4587;
    private static InetAddress addr;

    public static void main(String[] args) throws IOException, EmptyElement, IncorrectData, ClassNotFoundException {
        IOManager ioManager = new IOManager();
        File pfile = new File(getEnv("pars","parsPath"));
        CollectionSerializer serializer = new CollectionSerializer(ioManager, pfile);
        CommandPool commandPool = new CommandPool();
        CollectionManager collectionManager = new CollectionManager(commandPool,serializer,serializer.collectionDeserializer());
        ServerManager serverManager = new ServerManager(commandPool,getHost() ,PORT,serializer, collectionManager);
        serverManager.run();
    }

    public static InetAddress getHost() throws UnknownHostException {
        try {
            if (Objects.equals(System.getenv("host"), null)) {
                return InetAddress.getLocalHost();
            } else
                return InetAddress.getByName(System.getenv("host"));
        } catch (UnknownHostException e) {
            return InetAddress.getLocalHost();
        }
    }

    public static String getEnv(String filename, String defaultParam){
        String var = System.getenv(filename);
        if (Objects.equals(var,null))
            return System.getenv(defaultParam);
        return var;

    }

}
