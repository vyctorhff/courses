package br.course.elite.java.server.domain.userCase;

import br.course.elite.java.server.domain.userCase.handler.NotFountHandler;
import br.course.elite.java.server.domain.userCase.handler.PathHandlerEnum;
import br.course.elite.java.server.domain.userCase.handler.SocketHttpHandler;

public class ResolverHttpPath {

    public SocketHttpHandler resolve(String content) {
        for (PathHandlerEnum pathHandlerEnum : PathHandlerEnum.values()) {

            if (pathHandlerEnum.isRequestValid(content)) {
                return pathHandlerEnum.getHandler();
            }
        }

        return new NotFountHandler();
    }
}