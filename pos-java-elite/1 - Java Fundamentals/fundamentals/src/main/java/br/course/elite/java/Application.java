package br.course.elite.java;

import br.course.elite.java.i18n.Internacionalization;
import br.course.elite.java.socket.server.socket.SingleThreadSocketServer;

public class Application {

    static void main() {
//        new SingleThreadSocketServer().execute();
        new Internacionalization().execute();
    }
}
