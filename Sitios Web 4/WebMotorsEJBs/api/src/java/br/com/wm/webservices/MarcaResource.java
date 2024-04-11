package br.com.wm.webservices;

import br.com.wm.modelo.Marca;
import br.com.wm.servico.ServicoMarca;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.DELETE;
import javax.ws.rs.core.MediaType;

public class MarcaResource {
    
    ServicoMarca servico;
    private int id;
    
    public MarcaResource() { }
    
    private MarcaResource(int id, ServicoMarca servico) {
        this.id = id;
        this.servico = servico;
    }


    // A referência ao serviço vem como parâmetro, par ser usada 
    public static MarcaResource getInstance(String id, ServicoMarca servico) {
        try {
            int key = Integer.parseInt(id);
            return new MarcaResource(key, servico);
        } catch(Throwable t) {
            t.printStackTrace();
            return null;
        }
    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Marca getJson() {
        return servico.buscar( id );
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(Marca marca) {
        marca.setId( id ); // Não pode alterar o id, ajusta para aquele da URL.
        servico.alterar( marca );
    }

    @DELETE
    public void delete() {
        // Recupera a marca relativa ao id da URL
        Marca marca = servico.buscar(id);
        // Solicita ao módulo EJB para remover a marca..
        servico.remover(marca);
    }
}
