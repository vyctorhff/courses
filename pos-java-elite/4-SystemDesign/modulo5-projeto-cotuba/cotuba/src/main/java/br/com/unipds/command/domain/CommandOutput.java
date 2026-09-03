package br.com.unipds.command.domain;

import java.util.Objects;

import br.com.unipds.command.options.AvailableOption;

public record CommandOutput(
    AvailableOption option,
    String output
) {
    public boolean hasValue() {
        // TODO: add lib apache commons
        return Objects.nonNull(output)
            && !output.isBlank()
            && !output.isEmpty();
    }
}
