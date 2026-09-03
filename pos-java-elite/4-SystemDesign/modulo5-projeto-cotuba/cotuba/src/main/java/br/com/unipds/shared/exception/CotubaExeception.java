package br.com.unipds.shared.exception;

public class CotubaExeception extends RuntimeException {

    private int code;

    public CotubaExeception(String message) {
        super(message);
    }

    public CotubaExeception(String message, Integer code) {
        super(message);
        this.code = code;
    }

    public CotubaExeception(String message, Throwable cause) {
        super(message, cause);
    }

    public int getCode() {
        return code;
    }
}
