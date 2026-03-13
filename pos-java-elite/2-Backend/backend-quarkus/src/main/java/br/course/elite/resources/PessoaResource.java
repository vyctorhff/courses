package br.course.elite.resources;

import java.util.List;

import br.course.elite.persistence.Pessoa;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;

@Path("/pessoa")
@Produces(MediaType.APPLICATION_JSON)
public class PessoaResource {
    
    @GET
    public List<Pessoa> getPessoa() {
        return Pessoa.listAll();
    }

    @GET
    @Path("/idade")
    public List<Pessoa> findPessoasByIdade(@QueryParam("idade") Integer idade) {
        return Pessoa.findByAno(idade);
    }

    @POST
    @Transactional
    public void post(Pessoa pessoa) {
        pessoa.id = null;
        pessoa.persist();
    }

    @PUT
    @Transactional
    public Pessoa put(Pessoa pessoa) {
        Pessoa p = Pessoa.findById(pessoa.id);

        p.nome = pessoa.nome;
        p.idade = pessoa.idade;

        p.persist();
        return p;
    }

    @DELETE
    @Transactional
    public void delete(Integer id) {
        Pessoa.deleteById(id);
    }
}
