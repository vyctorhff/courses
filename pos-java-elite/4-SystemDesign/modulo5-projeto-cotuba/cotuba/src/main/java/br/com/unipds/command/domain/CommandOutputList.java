package br.com.unipds.command.domain;

import java.util.ArrayList;
import java.util.List;

import br.com.unipds.command.options.AvailableOption;

public record CommandOutputList(
    List<CommandOutput> outputs
) {

    public static CommandOutputList create() {
        return new CommandOutputList(new ArrayList<>());
    }

    public void add(CommandOutput commandOutput) {
        this.outputs.add(commandOutput);
    }

    public String getOptionValueByLongName(AvailableOption option) {
        return outputs.stream()
            .filter(op -> op.option().getLongOption().equals(option.getLongOption()))
            .findFirst()
            .orElse(new CommandOutput(null, null))
            .output();
    }

    public boolean hasOption(AvailableOption option) {
        return outputs.stream()
            .filter(op -> op.option().getLongOption().equals(option.getLongOption()))
            .findAny()
            .map(op -> op.option().isHasArg())
            .orElse(Boolean.FALSE);
    }
}
