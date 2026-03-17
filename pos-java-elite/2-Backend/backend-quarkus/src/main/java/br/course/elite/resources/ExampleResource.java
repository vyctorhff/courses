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
import jakarta.ws.rs.core.Response;

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

    @GET
    @Path("/{number}")
    public Response get3(Integer number) {
        return Response.ok(count).build();
    }

    @POST
    public void post() {
        count++;
    }

    @PUT
    public Response put() {
        count++;
        return Response.accepted().build();
    }

    @DELETE
    public Response delete() {
        count--;
        return Response
            .status(Response.Status.ACCEPTED)
            .build();
    }
}
