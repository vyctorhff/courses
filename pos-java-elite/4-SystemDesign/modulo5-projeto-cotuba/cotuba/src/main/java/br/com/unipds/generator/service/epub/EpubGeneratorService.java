package br.com.unipds.generator.service.epub;

import br.com.unipds.command.domain.CommandInputValues;
import br.com.unipds.generator.domain.FileType;
import br.com.unipds.generator.exceptions.GeneratorException;
import br.com.unipds.generator.service.GeneratorBookFile;
import br.com.unipds.generator.service.HeadingVisitor;
import br.com.unipds.generator.service.ParseInputListService;
import nl.siegmann.epublib.domain.Author;
import nl.siegmann.epublib.domain.Book;
import nl.siegmann.epublib.domain.GuideReference;
import nl.siegmann.epublib.domain.Resource;
import nl.siegmann.epublib.epub.EpubWriter;
import nl.siegmann.epublib.service.MediatypeService;
import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class EpubGeneratorService implements GeneratorBookFile {

    private final Logger logger = LoggerFactory.getLogger(EpubGeneratorService.class);

    private FileType type = FileType.EPUB;

    @Override
    public boolean canProcess(FileType fileType) {
        return type.equals(fileType);
    }

    @Override
    public void process(CommandInputValues commandInputValues) throws GeneratorException {
        logger.info("Processing {}", type);

        Path sourceDir = commandInputValues.sourceDir();
        Path outputDir = commandInputValues.outputDir();

        try {
            var epub = new Book();
            boolean[] ehPrimeiroCapitulo = {true};

            setAuthorAndTitle(epub);

            var parseInputList = new ParseInputListService();
            parseInputList.process(sourceDir, arquivoMD -> {
                Node document = createDocument(arquivoMD);
                renderingHtml(arquivoMD, document, epub, ehPrimeiroCapitulo);
            });

            try {
                var epubWriter = new EpubWriter();
                epubWriter.write(epub, Files.newOutputStream(outputDir));
            } catch (IOException ex) {
                throw new IllegalStateException("Erro ao criar arquivo EPUB: " + outputDir.toAbsolutePath(), ex);
            }

        } catch (Exception ex) {
            throw new IllegalStateException("Erro ao gerar EPUB: " + outputDir.toAbsolutePath(), ex);
        }
    }

    private static void renderingHtml(Path arquivoMD, Node document, Book epub, boolean[] ehPrimeiroCapitulo) {
        try {
            HtmlRenderer renderer = HtmlRenderer.builder().build();
            String html = renderer.render(document);

            // TODO: usar título do capítulo
            String epubHtml = """
                          <html xmlns="http://www.w3.org/1999/xhtml">
                            <head>
                              <title>Capítulo</title>
                            </head>
                            <body>
                              %s
                            </body>
                          </html>
                        """.formatted(html);
            var chapter = new Resource(epubHtml.getBytes(), MediatypeService.XHTML);
            epub.addSection("Capítulo", chapter);

            if (ehPrimeiroCapitulo[0]) {
                epub.getGuide().addReference(new GuideReference(chapter, "text", "Start Reading"));
                ehPrimeiroCapitulo[0] = false;
            }

        } catch (Exception ex) {
            throw new IllegalStateException("Erro ao renderizar para HTML o arquivo " + arquivoMD, ex);
        }
    }

    private static Node createDocument(Path arquivoMD) {
        Parser parser = Parser.builder().build();
        Node document = null;
        try {
            document = parser.parseReader(Files.newBufferedReader(arquivoMD));
            document.accept(new HeadingVisitor());
        } catch (Exception ex) {
            throw new IllegalStateException("Erro ao fazer parse do arquivo " + arquivoMD, ex);
        }
        return document;
    }

    private static void setAuthorAndTitle(Book epub) {
        //TODO: definir título e autor para o livro
        epub.getMetadata().addTitle("Livro");
        epub.getMetadata().addAuthor(new Author("Autor"));
    }
}
