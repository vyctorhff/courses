package br.course.elite.java.serialization;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.*;

public class Serialization {

    private static final String pathFile = "src/main/resources/test.ser";

    public void execute() {
        write();
        read();
    }

    private void read() {
        try (var fio = new FileInputStream(pathFile);
             var ois = new ObjectInputStream(fio)
        ) {
//            var pix = (PixRecord) ois.readObject();
            var pix = (PixClass) ois.readObject();
            IO.println(pix);
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void write() {
//        var pix = new PixRecord("aaa", 200.0);
        var pix = new PixClass("aaa", 200.0);

        try (var foo = new FileOutputStream(pathFile);
             var oos = new ObjectOutputStream(foo)
        ) {
            oos.writeObject(pix);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

/**
 * Record vs Class in serialization
 *
 * Class gera um número de serialização. Caso a classe seja alterada após
 * a serialização, o java dará um erro de versionamento errado.
 *
 * Para resolver isso, geralmente é criado um número de serial que o programador pode controlar.
 * Esta estratégia só é válida caso adicione métodos, mas não para adição de atributos
 *
 * Record não precisa de serialVersion
 */

record PixRecord (String key, Double amount) implements Serializable {}

@Data
@AllArgsConstructor
class PixClass implements Serializable {

//    @Serial // adicionado no java 14 para definir um nome qualquer para a versão
    private static final long serialVersionUID = 1L;

    private String key;
    private Double amount;

    public boolean temp() {
        return false;
    }

    public boolean temp2() {
        return true;
    }
}