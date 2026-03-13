package br.course.elite.resources;

import java.util.List;

import br.course.elite.persistence.Pessoa;
import io.micrometer.core.annotation.Counted;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

@Path("/metrics")
public class PessoaMetricsResource {
    
    @GET
    @Path("/pessoa")
    @Counted("counted.getPessoa")
    public List<Pessoa> getPessoas() {
        return Pessoa.listAll();
    }
}
