package br.com.unipds.command.exceptions;

import br.com.unipds.shared.exception.CotubaExeception;

public class CommandExeception extends CotubaExeception {

    public CommandExeception(String message) {
        super(message);
    }

    public CommandExeception(String message, Throwable cause) {
        super(message, cause);
    }
}
