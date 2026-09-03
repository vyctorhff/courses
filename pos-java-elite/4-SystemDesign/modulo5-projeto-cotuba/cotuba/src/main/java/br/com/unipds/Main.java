package br.com.unipds;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

import br.com.unipds.command.domain.CommandInputValues;
import br.com.unipds.command.service.CreateInputCommandService;
import com.itextpdf.kernel.pdf.PdfOutline;
import com.itextpdf.kernel.pdf.navigation.PdfExplicitDestination;
import nl.siegmann.epublib.domain.*;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.ParseException;
import org.commonmark.node.AbstractVisitor;
import org.commonmark.node.Heading;
import org.commonmark.node.Node;
import org.commonmark.node.Text;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;

import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.AreaBreak;
import com.itextpdf.layout.element.IBlockElement;
import com.itextpdf.layout.element.IElement;
import com.itextpdf.layout.properties.AreaBreakType;

import br.com.unipds.command.domain.CommandOutputList;
import br.com.unipds.command.exceptions.CommandExeception;
import br.com.unipds.command.options.AvailableOption;
import br.com.unipds.command.options.OptionsFactory;
import br.com.unipds.command.service.CommandExecutorService;
import nl.siegmann.epublib.epub.EpubWriter;
import nl.siegmann.epublib.service.MediatypeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {

    public static final int EXIT_CODE_SUCCESS = 0;
    public static final int EXIT_CODE_ERROR = 1;

    private final Logger logger = LoggerFactory.getLogger(Main.class);

    void main(String[] args) {
        int exitCode = executar(args);
        if (exitCode != EXIT_CODE_SUCCESS) {
            System.exit(exitCode);
        }
    }

    int executar(String[] args) {
        CommandOutputList commandOutputList;
        try {
            var commandExecutor = new CommandExecutorService(new OptionsFactory());
            commandOutputList = commandExecutor.execute(args);
        } catch (CommandExeception e) {
            System.err.println(e.getMessage());
            return EXIT_CODE_ERROR;
        }

        var createInputs = new CreateInputCommandService();
        var commandInputValues = createInputs.create(commandOutputList);

        Path diretorioDosMD = commandInputValues.source();
        String formato = commandInputValues.fileFormat();
        Path arquivoDeSaida = commandInputValues.fileName();
        boolean modoVerboso = commandInputValues.verboseMode();

        try {
            if ("pdf".equals(formato)) {
                try (var writer = new PdfWriter(Files.newOutputStream(arquivoDeSaida));
                     var pdf = new PdfDocument(writer);
                     var pdfDocument = new Document(pdf)) {

                    // TODO: definir título e autor para o livro
                    pdf.getDocumentInfo().setTitle("Livro");
                    pdf.getDocumentInfo().setAuthor("Autor");

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

                                List<IElement> convertToElements = HtmlConverter.convertToElements(html);

                                if (pdf.getNumberOfPages() == 0) {
                                    pdf.addNewPage();
                                }
                                PdfOutline rootOutline = pdf.getOutlines(false);
                                if (rootOutline == null) {
                                    pdf.initializeOutlines();
                                    rootOutline = pdf.getOutlines(false);
                                }

                                // TODO: usar título do capítulo
                                PdfOutline chapterOutline = rootOutline.addOutline("Capítulo");
                                chapterOutline.addDestination(PdfExplicitDestination.createFit(pdf.getLastPage()));

                                for (IElement element : convertToElements) {
                                    pdfDocument.add((IBlockElement) element);
                                }
                                // TODO: não adicionar página depois do último capítulo
                                pdfDocument.add(new AreaBreak(AreaBreakType.NEXT_PAGE));

                            } catch (Exception ex) {
                                throw new IllegalStateException("Erro ao renderizar para HTML o arquivo " + arquivoMD, ex);
                            }

                        });
                    } catch (IOException ex) {
                        throw new IllegalStateException("Erro tentando encontrar arquivos .md em " + diretorioDosMD.toAbsolutePath(), ex);
                    }

                } catch (Exception ex) {
                    System.err.println(ex.getMessage());
                    throw new IllegalStateException("Erro ao gerar PDF: " + arquivoDeSaida.toAbsolutePath(), ex);
                }

            } else if ("epub".equals(formato)) {

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
            } else {
                throw new IllegalArgumentException("Formato do ebook inválido: " + formato);
            }

            logger.info("Arquivo gerado com sucesso: {}", arquivoDeSaida);
            return EXIT_CODE_SUCCESS;

        } catch (Exception ex) {
            System.err.println(ex.getMessage());
            if (modoVerboso) {
                logger.error(ex.getMessage(), ex);
            }
            return EXIT_CODE_ERROR;
        }
    }
}