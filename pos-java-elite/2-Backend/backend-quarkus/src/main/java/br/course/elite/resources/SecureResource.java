package br.course.elite.resources;

import org.eclipse.microprofile.jwt.Claim;
import org.eclipse.microprofile.jwt.Claims;

import jakarta.annotation.security.RolesAllowed;
import jakarta.enterprise.context.RequestScoped;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

@Path("/seguranca")
@RequestScoped // ou @ApplicationScope necessário para usar o @Claim
public class SecureResource {
    
    @Claim(standard = Claims.preferred_username)
    private String username;

    @GET
    public String getUsername() {
        return "O nome do usuário é: " + username;
    }

    @GET
    @Path("/with-role")
    @RolesAllowed(value = {"Subscriber"}) // permite apenas se o usuário estiver neste role
    public String getUsernameInRole() {
        return "O nome do usuário está na role: " + username;
    }
}
