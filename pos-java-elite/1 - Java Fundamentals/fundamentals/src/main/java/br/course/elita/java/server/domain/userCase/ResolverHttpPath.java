package br.course.elita.java.server.domain.userCase;

import br.course.elita.java.server.domain.userCase.handler.NotFountHandler;
import br.course.elita.java.server.domain.userCase.handler.PathHandlerEnum;
import br.course.elita.java.server.domain.userCase.handler.SocketHttpHandler;

public class ResolverHttpPath {

    public SocketHttpHandler resolve(String content) {
        for (PathHandlerEnum pathHandlerEnum : PathHandlerEnum.values()) {

            if (content.contains(pathHandlerEnum.getPath())) {
                return pathHandlerEnum.getHandler();
            }
        }

        return new NotFountHandler();
    }
}