package br.com.unipds.command.domain;

import java.nio.file.Path;

public record CommandInputValues (
        Path source,
        String fileFormat,
        Path fileName,
        boolean verboseMode
) {
}
