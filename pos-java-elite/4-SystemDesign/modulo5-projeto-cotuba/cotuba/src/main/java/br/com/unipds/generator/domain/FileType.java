package br.com.unipds.generator.domain;

import java.util.Optional;
import java.util.stream.Stream;

public enum FileType {
    PDF, EPUB;

    public static FileType create(String type) {
        Optional<FileType> fileType1 = Stream.of(FileType.values())
                .filter(fileType -> fileType.name().equals(type.toUpperCase()))
                .findFirst();
        return fileType1
                .orElseThrow(() -> new IllegalArgumentException("Formato do ebook inválido: " + type));
    }
}
