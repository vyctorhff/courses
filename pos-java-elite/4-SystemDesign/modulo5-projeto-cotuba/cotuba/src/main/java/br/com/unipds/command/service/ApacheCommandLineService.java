package br.com.unipds.command.service;

import br.com.unipds.command.domain.CommandOutput;
import br.com.unipds.command.domain.CommandOutputList;
import br.com.unipds.command.exceptions.CommandExeception;
import br.com.unipds.command.options.AvailableOption;
import br.com.unipds.command.options.OptionsFactory;
import org.apache.commons.cli.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.stream.Stream;

public class ApacheCommandLineService {

    private final Logger logger = LoggerFactory.getLogger(ApacheCommandLineService.class);

    private final OptionsFactory factory;

    public ApacheCommandLineService(OptionsFactory factory) {
        this.factory = factory;
    }

    public CommandOutputList execute(String[] args) {
        var options = factory.getOptions();

        try {
            CommandLine cmd = new DefaultParser().parse(options, args);
            return createOutputList(cmd);
        } catch (ParseException e) {
            logger.error(e.getMessage());
            printHelp(options);

            throw new CommandExeception(e.getMessage());
        }
    }

    private static CommandOutputList createOutputList(CommandLine cmd) {
        CommandOutputList outputList = CommandOutputList.create();
        Stream.of(AvailableOption.values())
                .forEach(option -> {
                    String sourceDir = cmd.getOptionValue(option.getLongOption());
                    outputList.add(new CommandOutput(option, sourceDir));
                });
        return outputList;
    }

    private void printHelp(Options options) {
        new HelpFormatter()
                .printHelp("cotuba", options);
    }
}
