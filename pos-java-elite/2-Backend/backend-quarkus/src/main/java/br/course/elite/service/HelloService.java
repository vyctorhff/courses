package br.course.elite.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class HelloService {
    
    @Inject
    private OutroService outroService;

    public String getHello() {
        return "Hello in service";
    }

    public void outro() {
        outroService.outro();
    }
}
