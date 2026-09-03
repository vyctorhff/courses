package br.com.unipds.command.options;

import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;

import java.util.stream.Stream;

public class OptionsFactory {
    
    public Options getOptions() {
        var options = new Options();

        Stream.of(AvailableOption.values())
                .forEach( availableOption -> {
            options.addOption(createByAvailableOption(availableOption));
        });

        return options;
    }

    public Option createByAvailableOption(AvailableOption option) {
        return new Option(option.getOption(), option.getLongOption(), option.isHasArg(), option.getDescription());
    }
}
