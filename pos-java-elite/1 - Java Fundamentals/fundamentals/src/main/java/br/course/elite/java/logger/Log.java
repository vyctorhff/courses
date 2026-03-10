package br.course.elite.java.logger;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Log {

    /**
     * Precisa definiar execucao do comando java
     * java -Djava.util.logging.config.file=/src/main/resources/logging.properties
     *
     * Se não, o java vai pegar o que está dentro da jdk em config/logging/properties
     */

    private static final Logger LOGGER = Logger.getLogger(Log.class.getName());

    public void execute() {
        LOGGER.severe("teste java util logger");
        LOGGER.warning("teste java util logger");
        LOGGER.info("teste java util logger");
        LOGGER.fine("teste java util logger");
        LOGGER.finest("teste java util logger");

        LOGGER.log(Level.FINE, () -> "logger only fine is activated ");
    }
}
