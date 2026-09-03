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
import br.com.unipds.generator.domain.FileType;
import br.com.unipds.generator.service.GeneratorBookFile;
import br.com.unipds.generator.service.PdfGeneratorService;
import br.com.unipds.generator.service.epub.EpubGeneratorService;
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

        GeneratorBookFile pdfGenerator = new PdfGeneratorService();
        GeneratorBookFile epubGenerator = new EpubGeneratorService();
        List<GeneratorBookFile> generators = List.of(
                new PdfGeneratorService(),
                new EpubGeneratorService()
        ); // TODO: go on !!!
        try {
            var fileType = FileType.create(commandInputValues.fileFormat());

            if (pdfGenerator.canProcess(fileType)) {
                pdfGenerator.process(commandInputValues);
            } else if (epubGenerator.canProcess(fileType)) {
                epubGenerator.process(commandInputValues);
            }

            logger.info("Arquivo gerado com sucesso: {}", commandInputValues.fileName());
            return EXIT_CODE_SUCCESS;

        } catch (Exception ex) {
            System.err.println(ex.getMessage());

            if (commandInputValues.verboseMode()) {
                logger.error(ex.getMessage(), ex);
            }
            return EXIT_CODE_ERROR;
        }
    }
}