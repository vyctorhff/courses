package br.course.elite.java.server.domain.userCase.handler;

import br.course.elite.java.server.domain.model.HandlerHttpResponse;

public class NotFountHandler implements SocketHttpHandler {
    @Override
    public HandlerHttpResponse execute() {
        return new HandlerHttpResponse(400, "ERROR", "Path not mapped");
    }
}
