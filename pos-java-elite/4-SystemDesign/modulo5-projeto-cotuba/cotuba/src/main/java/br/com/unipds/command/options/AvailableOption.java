package br.com.unipds.command.options;

public enum AvailableOption {

    DIR("d", "dir", true, "Diretório que contém os arquivos md. Default: diretório atual."),
    FORMAT("f", "format", true, "Formato de saída do ebook. Pode ser: pdf ou epub. Default: pdf"),
    OUTPUT("o", "output", true, "Arquivo de saída do ebook. Default: book.{formato}."),
    VERBOSE("v", "verbose", false, "Habilita modo verboso.");

    private boolean hasArg;
    private String option;
    private String longOption;
    private String description;

    AvailableOption(String option, String longOption, boolean hasArg, String description) {
        this.hasArg = hasArg;
        this.option = option;
        this.longOption = longOption;
        this.description = description;
    }

    public boolean isHasArg() {
        return hasArg;
    }

    public String getOption() {
        return option;
    }

    public String getLongOption() {
        return longOption;
    }

    public String getDescription() {
        return description;
    }
}
