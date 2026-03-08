package br.course.elite.java.socket.server.domain.userCase.handler;

public enum PathHandlerEnum {
    LIST("GET", "/list", new ListHandler()),
    CREATE("POST", "/create", new CreateHandler()),
    UPDATE("PUT", "/update", new CreateHandler());

    private final String httpVerbo;
    private final String path;
    private final SocketHttpHandler handler;

    PathHandlerEnum(String httpVerbo, String path, SocketHttpHandler handler) {
        this.httpVerbo = httpVerbo;
        this.path = path;
        this.handler = handler;
    }

    public String getPath() {
        return path;
    }

    public SocketHttpHandler getHandler() {
        return handler;
    }

    public boolean isRequestValid(String content) {
        return content.contains("%s %s ".formatted(httpVerbo, path));
    }
}
