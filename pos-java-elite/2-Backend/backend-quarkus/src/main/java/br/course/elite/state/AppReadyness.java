package br.course.elite.state;

import java.util.List;

import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Readiness;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import br.course.elite.domain.StarWarFilm;
import br.course.elite.rest.FaultToleranceService;

/**
 * Informa se a aplicação está pronta para receber requisições
 * 
 * Isso é importante porque pode acontecer de algumas dependências(banco, serviços, outra app)
 * estarem com problema
 */

@Readiness
public class AppReadyness implements HealthCheck {

    @RestClient
    private FaultToleranceService service;

    @Override
    public HealthCheckResponse call() {
        List<StarWarFilm> films = service.getFilms();

        if (films.isEmpty()) {
            return HealthCheckResponse.down("APIs dependentes não estão retornando");
        }

        return HealthCheckResponse.up("Estou pronto para receber requisicoes");
    }
}
