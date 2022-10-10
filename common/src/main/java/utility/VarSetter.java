package utility;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Objects;

public class VarSetter {

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
