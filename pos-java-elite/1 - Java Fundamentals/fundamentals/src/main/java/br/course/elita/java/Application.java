package br.course.elita.java;

import br.course.elita.java.server.socket.SingleThreadSocketServer;

public class Application {

    static void main() {
        new SingleThreadSocketServer().execute();
    }
}
