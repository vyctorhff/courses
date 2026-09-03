package br.com.unipds.command.service;

import java.util.stream.Stream;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import br.com.unipds.command.domain.CommandOutput;
import br.com.unipds.command.domain.CommandOutputList;
import br.com.unipds.command.exceptions.CommandExeception;
import br.com.unipds.command.options.AvailableOption;
import br.com.unipds.command.options.OptionsFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CommandExecutorService {

    private final Logger logger = LoggerFactory.getLogger(CommandExecutorService.class);

    private final OptionsFactory factory;

    public CommandExecutorService(OptionsFactory factory) {
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
                    String nomeDoDiretorioDosMD = cmd.getOptionValue(option.getLongOption());
                    outputList.add(new CommandOutput(option, nomeDoDiretorioDosMD));
                });
        return outputList;
    }

    private void printHelp(Options options) {
        var ajuda = new HelpFormatter();
        ajuda.printHelp("cotuba", options);
    }
}
