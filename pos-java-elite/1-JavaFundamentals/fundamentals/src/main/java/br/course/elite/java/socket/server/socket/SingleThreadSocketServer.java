package br.course.elite.java.socket.server.socket;

import br.course.elite.java.socket.server.domain.model.HandlerHttpResponse;
import br.course.elite.java.socket.server.domain.userCase.ResolverHttpPath;
import br.course.elite.java.socket.server.domain.userCase.handler.SocketHttpHandler;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.function.Consumer;

@Slf4j
public class SingleThreadSocketServer {

    public static final int TOTAL_ITERATION_SOCKET = 3;
    public static final int SOCKET_PORT = 8000;

    public SingleThreadSocketServer() {
    }

    public void execute() {
        try (ServerSocket serverSocket = new ServerSocket(SOCKET_PORT)) {
            log.info("Server started at " + SOCKET_PORT);

            repeat(TOTAL_ITERATION_SOCKET, serverSocket, executeSocket());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Consumer<ServerSocket> executeSocket() {
        return (value) -> {
            ResolverHttpPath resolver = new ResolverHttpPath();

            try (Socket socket = value.accept()) {
                SocketReaderService reader = new SocketReaderService(socket);
//                String body = read(socket);
                String body = reader.read();

                SocketHttpHandler handler = resolver.resolve(body);
                HandlerHttpResponse response = handler.execute();

                SocketWriterService writer = new SocketWriterService(socket);
//                write(socket, response);
                writer.write(response);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        };
    }

    private void repeat(int amountTimes, ServerSocket socket, Consumer<ServerSocket> consumer) {
        int count = amountTimes;

        while (count > 0) {
            log.info("-".repeat(50));
            log.info("Call number: " + amountTimes);

            consumer.accept(socket);
            count--;
        }
    }

//    private String read(Socket socket) throws IOException {
//        log.info("Reading...");
//
//        InputStream inputStream = socket.getInputStream();
//        StringBuilder sb = new StringBuilder();
//
//        int data;
//        do {
//            data = inputStream.read();
//            sb.append((char) data);
//        } while (inputStream.available() > 0);
//
//        return sb.toString();
//    }
//
//    private static void write(Socket socket, HandlerHttpResponse response) throws IOException {
//        log.info("Writing...");
//
//        PrintStream printStream = new PrintStream(socket.getOutputStream());
//
//        printStream.printf("HTTP/1.1 %d %s\n", response.status(), response.info());
//        printStream.println("Content-type: application/text; charset=UTF-8");
//        printStream.println();
//        printStream.println(response.body());
//
//        printStream.close();
//    }
}

@Slf4j
class SocketReaderService {

    private Socket socket;

    public SocketReaderService(Socket socket) {
        this.socket = socket;
    }

    public String read() throws IOException {
        log.info("Reading...");

        InputStream inputStream = socket.getInputStream();
        StringBuilder sb = new StringBuilder();

        int data;
        do {
            data = inputStream.read();
            sb.append((char) data);
        } while (inputStream.available() > 0);

        return sb.toString();
    }
}

@Slf4j
class SocketWriterService {

    private Socket socket;

    public SocketWriterService(Socket socket) {
        this.socket = socket;
    }

    public void write(HandlerHttpResponse response) throws IOException {
        log.info("Writing...");

        PrintStream printStream = new PrintStream(socket.getOutputStream());

        printStream.printf("HTTP/1.1 %d %s\n", response.status(), response.info());
        printStream.println("Content-type: application/text; charset=UTF-8");
        printStream.println();
        printStream.println(response.body());

        printStream.close();
    }
}