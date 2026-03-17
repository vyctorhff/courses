package br.course.elite.service;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class HelloService {
    
    public String getHello() {
        return "Hello in service";
    }
}
