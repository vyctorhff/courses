package br.course.elite.resources;

import jakarta.decorator.Delegate;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/examples")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ExampleResource {

    private int count = 0;

    @GET
    public int get() {
        return 0;
    }

    @GET
    @Path("/get2")
    public int get2() {
        return count;
    }

    @POST
    public void post() {
        count++;
    }

    @PUT
    public void put() {
        count++;
    }

    @DELETE
    public void delete() {
        count--;
    }
}
