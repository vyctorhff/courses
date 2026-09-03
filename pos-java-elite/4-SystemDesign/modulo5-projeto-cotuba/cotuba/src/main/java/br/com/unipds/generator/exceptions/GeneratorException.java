package br.com.unipds.generator.exceptions;

import br.com.unipds.shared.exception.CotubaExeception;

public class GeneratorException extends CotubaExeception {

    public GeneratorException(String message) {
        super(message);
    }

    public GeneratorException(String message, Integer code) {
        super(message, code);
    }

    public GeneratorException(String message, Throwable cause) {
        super(message, cause);
    }
}
