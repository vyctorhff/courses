package br.com.unipds.command.domain;

import java.nio.file.Path;

public record CommandInputValues (
        Path sourceDir,
        String fileFormat,
        Path outputDir,
        boolean verboseMode
) {
}
