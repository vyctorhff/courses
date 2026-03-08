package br.course.elite.java.server.domain.model;

public record HandlerHttpResponse(
        int status,
        String info,
        String body
) {
    public static HandlerHttpResponse success(String content) {
        return new HandlerHttpResponse(200, "OK", content);
    }
}
