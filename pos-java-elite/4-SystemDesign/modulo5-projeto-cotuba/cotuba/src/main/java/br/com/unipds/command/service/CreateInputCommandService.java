package br.com.unipds.command.service;

import br.com.unipds.command.domain.CommandInputValues;
import br.com.unipds.command.domain.CommandOutput;
import br.com.unipds.command.domain.CommandOutputList;
import br.com.unipds.command.options.AvailableOption;
import br.com.unipds.shared.exception.CotubaExeception;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CreateInputCommandService {

    public static final String DEFAULT_TYPE_FORMAT = "pdf";

    private final Logger logger = LoggerFactory.getLogger(CreateInputCommandService.class);

    public CommandInputValues create(CommandOutputList list) {
        boolean verboseMode = list.hasOption(AvailableOption.VERBOSE);
        String fileFormat = createFormat(list);

        Path source = createSourceDirectory(list);
        Path fileName = createFileOutputName(list, fileFormat);

        logger.info("Inputs converted");
        return new CommandInputValues(source, fileFormat, fileName, verboseMode);
    }

    private static Path createFileOutputName(CommandOutputList list, String format) {
        CommandOutput commandOutput = list.getOptionValueByLongName(AvailableOption.OUTPUT);

        if (commandOutput.hasLongNameValue()) {
            return commandOutput.getOutputAsPath();
        }

        return Paths.get("book." + format.toLowerCase());
    }

    private static String createFormat(CommandOutputList list) {
        CommandOutput commandOutput = list.getOptionValueByLongName(AvailableOption.FORMAT);

        if (commandOutput.hasLongNameValue()) {
            return commandOutput.output().toLowerCase();
        }

        return DEFAULT_TYPE_FORMAT;
    }

    private static Path createSourceDirectory(CommandOutputList list) {
        CommandOutput commandOutput = list.getOptionValueByLongName(AvailableOption.DIR);

        if (!commandOutput.hasLongNameValue()) {
            return Paths.get(StringUtils.EMPTY);
        }

        Path dirMDs = commandOutput.getOutputAsPath();

        if (!Files.isDirectory(dirMDs)) {
            throw new CotubaExeception(commandOutput.output() + " não é um diretório.");
        }

        return dirMDs;
    }
}
