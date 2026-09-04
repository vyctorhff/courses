package br.com.unipds;

import java.util.List;

import br.com.unipds.command.service.CreateInputCommandService;
import br.com.unipds.generator.domain.FileType;
import br.com.unipds.generator.service.GeneratorBookFile;
import br.com.unipds.generator.service.pdf.PdfGeneratorService;
import br.com.unipds.generator.service.epub.EpubGeneratorService;

import br.com.unipds.command.domain.CommandOutputList;
import br.com.unipds.command.exceptions.CommandExeception;
import br.com.unipds.command.options.OptionsFactory;
import br.com.unipds.command.service.CommandExecutorService;
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

        var commandInputValues = new CreateInputCommandService()
                .create(commandOutputList);

        List<GeneratorBookFile> generators = List.of(
                new PdfGeneratorService(),
                new EpubGeneratorService()
        );
        try {
            var fileType = FileType.create(commandInputValues.fileFormat());
            generators.stream()
                    .filter(generator -> generator.canProcess(fileType))
                    .forEach(generator -> generator.process(commandInputValues));

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