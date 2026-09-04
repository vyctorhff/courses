package br.com.unipds;

import br.com.unipds.command.domain.CommandInputValues;
import br.com.unipds.command.exceptions.CommandExeception;
import br.com.unipds.command.options.OptionsFactory;
import br.com.unipds.command.service.CommandExecutorService;
import br.com.unipds.command.service.CreateInputCommandService;
import br.com.unipds.generator.domain.FileType;
import br.com.unipds.generator.service.GeneratorBookFile;
import br.com.unipds.generator.service.epub.EpubGeneratorService;
import br.com.unipds.generator.service.pdf.PdfGeneratorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class Main {

    public static final int EXIT_CODE_ERROR = 1;
    public static final int EXIT_CODE_SUCCESS = 0;

    private final Logger logger = LoggerFactory.getLogger(Main.class);

    void main(String[] args) {
        int exitCode = execute(args);
        if (exitCode != EXIT_CODE_SUCCESS) {
            System.exit(exitCode);
        }
    }

    int execute(String[] args) {
        boolean verbose = false;
        try {
            var commandInputValues = executeCommand(args);
            verbose = commandInputValues.verboseMode();

            var fileType = FileType.create(commandInputValues.fileFormat());
            getGeneratorBookFiles().stream()
                    .filter(generator -> generator.canProcess(fileType))
                    .forEach(generator -> generator.process(commandInputValues));

            logger.info("Arquivo gerado com sucesso: {}", commandInputValues.outputDir());
            return EXIT_CODE_SUCCESS;

        } catch (Exception ex) {
            System.err.println(ex.getMessage());

            if (verbose) {
                logger.error(ex.getMessage(), ex);
            }
            return EXIT_CODE_ERROR;
        }
    }

    private static CommandInputValues executeCommand(String[] args) throws CommandExeception {
        var commandExecutor = new CommandExecutorService(new OptionsFactory());
        var commandOutputList = commandExecutor.execute(args);

        return new CreateInputCommandService()
                .create(commandOutputList);
    }

    private static List<GeneratorBookFile> getGeneratorBookFiles() {
        return List.of(
                new PdfGeneratorService(),
                new EpubGeneratorService()
        );
    }
}