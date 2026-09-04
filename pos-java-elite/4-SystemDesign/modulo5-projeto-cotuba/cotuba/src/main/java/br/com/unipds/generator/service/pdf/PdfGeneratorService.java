package br.com.unipds.generator.service.pdf;

import br.com.unipds.command.domain.CommandInputValues;
import br.com.unipds.generator.domain.FileType;
import br.com.unipds.generator.exceptions.GeneratorException;
import br.com.unipds.generator.service.GeneratorBookFile;
import br.com.unipds.generator.service.HeadingVisitor;
import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfOutline;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.navigation.PdfExplicitDestination;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.AreaBreak;
import com.itextpdf.layout.element.IBlockElement;
import com.itextpdf.layout.element.IElement;
import com.itextpdf.layout.properties.AreaBreakType;
import org.commonmark.node.Node;
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

public class PdfGeneratorService implements GeneratorBookFile {

    private final Logger logger = LoggerFactory.getLogger(PdfGeneratorService.class);

    private FileType type = FileType.PDF;

    @Override
    public boolean canProcess(FileType fileType) {
        return type.equals(fileType);
    }

    @Override
    public void process(CommandInputValues commandInputValues) throws GeneratorException {
        logger.info("Processing {}", type);

        Path diretorioDosMD = commandInputValues.source();
        Path arquivoDeSaida = commandInputValues.fileName();

        try (var writer = new PdfWriter(Files.newOutputStream(arquivoDeSaida));
             var pdf = new PdfDocument(writer);
             var pdfDocument = new Document(pdf)) {

            setAuthorAndTitle(pdf);

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
                    Node document = createDocument(arquivoMD);
                    addContentToPdf(arquivoMD, pdf, document, pdfDocument);
                });
            } catch (IOException ex) {
                throw new IllegalStateException("Erro tentando encontrar arquivos .md em " + diretorioDosMD.toAbsolutePath(), ex);
            }

        } catch (Exception ex) {
            System.err.println(ex.getMessage());
            throw new IllegalStateException("Erro ao gerar PDF: " + arquivoDeSaida.toAbsolutePath(), ex);
        }
    }

    private static void addContentToPdf(Path arquivoMD, PdfDocument pdf, Node document, Document pdfDocument) {
        try {
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

            List<IElement> convertToElements = getIElementsFromHtmlConverter(document);
            for (IElement element : convertToElements) {
                pdfDocument.add((IBlockElement) element);
            }
            // TODO: não adicionar página depois do último capítulo
            pdfDocument.add(new AreaBreak(AreaBreakType.NEXT_PAGE));

        } catch (Exception ex) {
            throw new IllegalStateException("Erro ao renderizar para HTML o arquivo " + arquivoMD, ex);
        }
    }

    private static List<IElement> getIElementsFromHtmlConverter(Node document) {
        HtmlRenderer renderer = HtmlRenderer.builder().build();
        String html = renderer.render(document);

        return HtmlConverter.convertToElements(html);
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

    private static void setAuthorAndTitle(PdfDocument pdf) {
        // TODO: definir título e autor para o livro
        pdf.getDocumentInfo().setTitle("Livro");
        pdf.getDocumentInfo().setAuthor("Autor");
    }
}
