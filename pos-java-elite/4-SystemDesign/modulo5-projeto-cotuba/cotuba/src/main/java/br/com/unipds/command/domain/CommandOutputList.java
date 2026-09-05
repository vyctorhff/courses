package br.com.unipds.command.domain;

import br.com.unipds.command.options.AvailableOption;

import java.util.ArrayList;
import java.util.List;

public record CommandOutputList(
    List<CommandOutput> outputs
) {

    public static CommandOutputList create() {
        return new CommandOutputList(new ArrayList<>());
    }

    public void add(CommandOutput commandOutput) {
        this.outputs.add(commandOutput);
    }

    public CommandOutput getOptionValueByLongName(AvailableOption option) {
        return outputs.stream()
            .filter(op -> op.option().getLongOption().equals(option.getLongOption()))
            .findFirst()
            .orElse(new CommandOutput(null, null));
    }

    public boolean hasOption(AvailableOption option) {
        return outputs.stream()
            .filter(op -> op.option().getLongOption().equals(option.getLongOption()))
            .findAny()
            .map(op -> op.option().isHasArg())
            .orElse(Boolean.FALSE);
    }

    public boolean hasItems() {
        return !outputs.isEmpty();
    }
}
