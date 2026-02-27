package br.course.elita.java.server.domain.userCase.handler;

public enum PathHandlerEnum {
    LIST("GET /socket/list", new ListHandler()),
    CREATE("POST /socket/create", new CreateHandler());

    private final String path;
    private final SocketHttpHandler handler;

    PathHandlerEnum(String path, SocketHttpHandler handler) {
        this.path = path;
        this.handler = handler;
    }

    public String getPath() {
        return path;
    }

    public SocketHttpHandler getHandler() {
        return handler;
    }
}
