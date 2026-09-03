package br.com.unipds.command.service;

import br.com.unipds.command.domain.CommandInputValues;
import br.com.unipds.command.domain.CommandOutputList;
import br.com.unipds.command.options.AvailableOption;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CreateInputCommandService {

    private final Logger logger = LoggerFactory.getLogger(CreateInputCommandService.class);

    public CommandInputValues create(CommandOutputList list) {
        Path source = createSourceDirectory(list);
        String fileFormat = createFormart(list);

        Path fileName = createFileOutputName(list, fileFormat);
        boolean verboseMode = list.hasOption(AvailableOption.VERBOSE);

        return new CommandInputValues(source, fileFormat, fileName, verboseMode);
    }

    private static Path createFileOutputName(CommandOutputList list, String formato) {
        String nomeDoArquivoDeSaidaDoEbook = list.getOptionValueByLongName(AvailableOption.OUTPUT);
        if (nomeDoArquivoDeSaidaDoEbook != null) {
            return Paths.get(nomeDoArquivoDeSaidaDoEbook);
        } else {
            return Paths.get("book." + formato.toLowerCase());
        }
    }

    private static String createFormart(CommandOutputList list) {
        String nomeDoFormatoDoEbook = list.getOptionValueByLongName(AvailableOption.FORMAT);
        String formato;
        if (nomeDoFormatoDoEbook != null) {
            formato = nomeDoFormatoDoEbook.toLowerCase();
        } else {
            formato = "pdf";
        }
        return formato;
    }

    private static Path createSourceDirectory(CommandOutputList list) {
        String nomeDoDiretorioDosMD = list.getOptionValueByLongName(AvailableOption.DIR);
        Path diretorioDosMD;
        if (nomeDoDiretorioDosMD != null) {
            diretorioDosMD = Paths.get(nomeDoDiretorioDosMD);
            if (!Files.isDirectory(diretorioDosMD)) {
                throw new IllegalArgumentException(nomeDoDiretorioDosMD + " não é um diretório.");
            }
            return diretorioDosMD;
        } else {
            return Paths.get("");
        }
    }
}
