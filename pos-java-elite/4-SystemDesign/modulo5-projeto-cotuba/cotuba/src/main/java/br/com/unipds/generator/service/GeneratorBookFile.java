package br.com.unipds.generator.service;

import br.com.unipds.command.domain.CommandInputValues;
import br.com.unipds.generator.domain.FileType;
import br.com.unipds.generator.exceptions.GeneratorException;

public interface GeneratorBookFile {

    boolean canProcess(FileType fileType);

    void process(CommandInputValues commandInputValues) throws GeneratorException;
}
