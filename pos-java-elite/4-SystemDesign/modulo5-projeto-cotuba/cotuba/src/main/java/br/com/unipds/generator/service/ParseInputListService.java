package br.com.unipds.generator.service;

import br.com.unipds.shared.exception.CotubaExeception;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Stream;

public class ParseInputListService {

    public static final String FILE_NOT_FOUND = "Não foram encontrados capítulos (arquivos .md) no diretório: ";
    public static final String ERROR_FINDING_FILES = "Erro tentando encontrar arquivos .md em ";

    public void process(Path pathSource, Consumer<Path> pathConsumer) {
        PathMatcher matcher = FileSystems.getDefault().getPathMatcher("glob:**/*.md");

        try (Stream<Path> streamMDs = Files.list(pathSource)) {
            List<Path> arquivosMD = streamMDs
                    .filter(matcher::matches)
                    .sorted()
                    .toList();

            if (arquivosMD.isEmpty()) {
                throw new CotubaExeception(FILE_NOT_FOUND + pathSource.toAbsolutePath());
            }

            arquivosMD.forEach(pathConsumer);
        } catch (IOException ex) {
            throw new CotubaExeception(ERROR_FINDING_FILES + pathSource.toAbsolutePath(), ex);
        }
    }
}
