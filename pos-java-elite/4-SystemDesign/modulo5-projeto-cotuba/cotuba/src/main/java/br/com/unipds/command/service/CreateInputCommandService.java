package br.com.unipds.command.service;

import br.com.unipds.command.domain.CommandInputValues;
import br.com.unipds.command.domain.CommandOutput;
import br.com.unipds.command.domain.CommandOutputList;
import br.com.unipds.command.options.AvailableOption;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CreateInputCommandService {

    private final Logger logger = LoggerFactory.getLogger(CreateInputCommandService.class);

    public CommandInputValues create(CommandOutputList list) {
        String fileFormat = createFormart(list);
        boolean verboseMode = list.hasOption(AvailableOption.VERBOSE);

        Path source = createSourceDirectory(list);
        Path fileName = createFileOutputName(list, fileFormat);

        logger.info("Inputs converted");
        return new CommandInputValues(source, fileFormat, fileName, verboseMode);
    }

    private static Path createFileOutputName(CommandOutputList list, String formato) {
        CommandOutput commandOutput = list.getOptionValueByLongName(AvailableOption.OUTPUT);

        if (commandOutput.hasLongNameValue()) {
            return Paths.get(commandOutput.output());
        }

        return Paths.get("book." + formato.toLowerCase());
    }

    private static String createFormart(CommandOutputList list) {
        CommandOutput commandOutput = list.getOptionValueByLongName(AvailableOption.FORMAT);

        if (commandOutput.hasLongNameValue()) {
            return commandOutput.output().toLowerCase();
        }

        return "pdf";
    }

    private static Path createSourceDirectory(CommandOutputList list) {
        CommandOutput commandOutput = list.getOptionValueByLongName(AvailableOption.DIR);

        if (!commandOutput.hasLongNameValue()) {
            return Paths.get(StringUtils.EMPTY);
        }

        Path dirMDs = commandOutput.getOutputAsPath();

        if (!Files.isDirectory(dirMDs)) {
            throw new IllegalArgumentException(commandOutput.output() + " não é um diretório.");
        }
        return dirMDs;
    }
}
