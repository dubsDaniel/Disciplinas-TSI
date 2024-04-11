package br.com.wm.webservices;

import br.com.wm.modelo.Marca;
import br.com.wm.servico.ServicoMarca;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/*
    É necessário definir como um EJB para suportar a injeção de outro EJB. 
*/
@Stateless
@Path("/marca")
public class MarcasResource {

    @Context
    private UriInfo context;

    @EJB(lookup="java:global/WebMotorsEJB/MarcaDAO!br.com.wm.servico.ServicoMarca")
    ServicoMarca servico;

    public MarcasResource() {
    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Marca> getJson() {
       return servico.listar();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response postJson(Marca marca) {
        servico.inserir(marca);
        return Response.created(context.getAbsolutePath()).build();
    }


    @Path("{id}")
    public MarcaResource getMarcaResource(@PathParam("id") String id) {
        // Como o recurso para acessar um objeto Marca específico é criado por esta classe,
        // a injeção de EJB não funcionaria. Tem que passar o servico como parâmetro.
        return MarcaResource.getInstance(id, servico);
    }
}
