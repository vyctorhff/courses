package br.course.elite.java.socket.server.domain.userCase.handler;

import br.course.elite.java.socket.server.domain.model.HandlerHttpResponse;

public class CreateHandler implements SocketHttpHandler {
    @Override
    public HandlerHttpResponse execute() {
        return new HandlerHttpResponse(201, "OK", "record created");
    }
}
