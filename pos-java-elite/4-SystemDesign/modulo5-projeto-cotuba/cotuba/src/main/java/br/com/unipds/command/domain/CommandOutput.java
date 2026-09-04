package br.com.unipds.command.domain;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

import br.com.unipds.command.options.AvailableOption;
import org.apache.commons.lang3.StringUtils;

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

    public boolean hasLongNameValue() {
        return option != null && StringUtils.isNotBlank(output);
    }

    public Path getOutputAsPath() {
        return Paths.get(output);
    }
}
