package br.com.unipds.generator.service;

import br.com.unipds.shared.exception.CotubaExeception;
import org.commonmark.node.Node;
import org.commonmark.parser.Parser;

import java.nio.file.Files;
import java.nio.file.Path;

public class CreateDocumentService {

    public static Node create(Path arquivoMD) {
        Parser parser = Parser.builder().build();
        try {
            Node document = parser.parseReader(Files.newBufferedReader(arquivoMD));
            document.accept(new HeadingVisitor());

            return document;
        } catch (Exception ex) {
            throw new CotubaExeception("Erro ao fazer parse do arquivo " + arquivoMD, ex);
        }
    }
}
