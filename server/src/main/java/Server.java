import exeptions.EmptyElement;
import exeptions.IncorrectData;
import utility.*;
import java.io.File;
import java.io.IOException;


public class Server {

    private static final Integer PORT = 4587;
    private static final VarSetter setter = new VarSetter();
    public static final Integer COUNT_OF_TREADS = 4;

    public static void main(String[] args) throws IOException, EmptyElement, IncorrectData, ClassNotFoundException {
        IOManager ioManager = new IOManager();
        File parsFile = new File(setter.getEnv("pars","parsPath"));
        CollectionSerializer serializer = new CollectionSerializer(ioManager, parsFile);
        CommandPool commandPool = new CommandPool();
        CollectionManager collectionManager = new CollectionManager(commandPool,serializer,serializer.collectionDeserializer());
        ServerManager serverManager = new ServerManager(setter.getHost() ,PORT, collectionManager,COUNT_OF_TREADS);
        serverManager.run();
    }


}
