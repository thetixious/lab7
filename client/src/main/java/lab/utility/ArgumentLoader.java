package lab.utility;

import java.util.Objects;

public class ArgumentLoader {
    private final String[] arguments;

    public ArgumentLoader(String[] arguments) {
        this.arguments = arguments;
    }

    public void validateCount(int count) {

        if (!Objects.equals(arguments.length, count))
            throw new IllegalArgumentException();

    }

    public String[] getStrArguments() {
        return arguments;
    }
}




