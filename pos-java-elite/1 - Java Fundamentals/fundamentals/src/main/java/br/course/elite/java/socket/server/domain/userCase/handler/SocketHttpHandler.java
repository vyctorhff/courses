package br.course.elite.java.socket.server.domain.userCase.handler;

import br.course.elite.java.socket.server.domain.model.HandlerHttpResponse;

public interface SocketHttpHandler {
    HandlerHttpResponse execute();
}
