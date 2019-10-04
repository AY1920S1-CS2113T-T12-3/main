package duke.commons;

import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public interface LogsCenter {



    /**
     * Creates a logger with the given name.
     */
    Logger getLogger(String name);

    /**
     * Creates a Logger for the given class name.
     */
    static <T> Logger getLogger(Class<T> clazz);

}
