package br.com.unipds.generator.service.epub;

import br.com.unipds.command.domain.CommandInputValues;
import br.com.unipds.generator.domain.FileType;
import br.com.unipds.generator.exceptions.GeneratorException;
import br.com.unipds.generator.service.GeneratorBookFile;
import nl.siegmann.epublib.domain.Author;
import nl.siegmann.epublib.domain.Book;
import nl.siegmann.epublib.domain.GuideReference;
import nl.siegmann.epublib.domain.Resource;
import nl.siegmann.epublib.epub.EpubWriter;
import nl.siegmann.epublib.service.MediatypeService;
import org.commonmark.node.AbstractVisitor;
import org.commonmark.node.Heading;
import org.commonmark.node.Node;
import org.commonmark.node.Text;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.util.List;
import java.util.stream.Stream;

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

        String formato = commandInputValues.fileFormat();
        Path diretorioDosMD = commandInputValues.source();
        Path arquivoDeSaida = commandInputValues.fileName();

        if ("epub".equals(formato)) {

            try {
                var epub = new Book();

                //TODO: definir título e autor para o livro
                epub.getMetadata().addTitle("Livro");
                epub.getMetadata().addAuthor(new Author("Autor"));

                boolean[] ehPrimeiroCapitulo = {true};

                PathMatcher matcher = FileSystems.getDefault().getPathMatcher("glob:**/*.md");
                try (Stream<Path> streamMDs = Files.list(diretorioDosMD)) {
                    List<Path> arquivosMD = streamMDs
                            .filter(matcher::matches)
                            .sorted()
                            .toList();

                    if (arquivosMD.isEmpty()) {
                        throw new IllegalStateException("Não foram encontrados capítulos (arquivos .md) no diretório: " + diretorioDosMD.toAbsolutePath());
                    }

                    arquivosMD.forEach(arquivoMD -> {
                        Parser parser = Parser.builder().build();
                        Node document = null;
                        try {
                            document = parser.parseReader(Files.newBufferedReader(arquivoMD));
                            document.accept(new AbstractVisitor() {
                                @Override
                                public void visit(Heading heading) {
                                    if (heading.getLevel() == 1) {
                                        // capítulo
                                        String tituloDoCapitulo = ((Text) heading.getFirstChild()).getLiteral();
                                        // TODO: usar título do capítulo
                                    } else if (heading.getLevel() == 2) {
                                        // seção
                                    } else if (heading.getLevel() == 3) {
                                        // título
                                    }
                                }

                            });
                        } catch (Exception ex) {
                            throw new IllegalStateException("Erro ao fazer parse do arquivo " + arquivoMD, ex);
                        }

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
                    });
                } catch (IOException ex) {
                    throw new IllegalStateException("Erro tentando encontrar arquivos .md em " + diretorioDosMD.toAbsolutePath(), ex);
                }

                var epubWriter = new EpubWriter();

                try {
                    epubWriter.write(epub, Files.newOutputStream(arquivoDeSaida));
                } catch (IOException ex) {
                    throw new IllegalStateException("Erro ao criar arquivo EPUB: " + arquivoDeSaida.toAbsolutePath(), ex);
                }

            } catch (Exception ex) {
                throw new IllegalStateException("Erro ao gerar EPUB: " + arquivoDeSaida.toAbsolutePath(), ex);
            }
        }
    }
}
