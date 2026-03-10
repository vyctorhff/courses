package br.course.elite.java.socket.server.domain.userCase.handler;

import br.course.elite.java.socket.server.domain.model.HandlerHttpResponse;

public class ListHandler implements SocketHttpHandler {
    @Override
    public HandlerHttpResponse execute() {
        String content = """
                1;Fulano;
                2;Cicrano;
                3;Beltrano
                """;
        return HandlerHttpResponse.success(content);
    }
}
