package duke.commons.core;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;

public class Config {
    public static final Path DEFAULT_CONFIG_FILE = Paths.get("config.json");

    private Level logLevel = Level.INFO;

    public Level getLogLevel() {
        return logLevel;
    }

    public void setLogLevel(Level logLevel) {
        this.logLevel = logLevel;
    }

}
