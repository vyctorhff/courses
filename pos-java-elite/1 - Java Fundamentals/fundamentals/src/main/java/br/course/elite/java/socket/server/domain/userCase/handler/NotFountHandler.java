package br.course.elite.java.socket.server.domain.userCase.handler;

import br.course.elite.java.socket.server.domain.model.HandlerHttpResponse;

public class NotFountHandler implements SocketHttpHandler {
    @Override
    public HandlerHttpResponse execute() {
        return new HandlerHttpResponse(400, "ERROR", "Path not mapped");
    }
}
