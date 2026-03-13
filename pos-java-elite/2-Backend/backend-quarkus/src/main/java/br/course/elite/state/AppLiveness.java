package br.course.elite.state;

import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Liveness;

/**
 * Informa se a aplicação está 'viva'
 */

@Liveness
public class AppLiveness implements HealthCheck {

    @Override
    public HealthCheckResponse call() {
        return HealthCheckResponse.up("Estou vivo");
    }
    
}
