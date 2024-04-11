package br.edu.utfpr.conta_corrente.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.edu.utfpr.conta_corrente.domain.Cliente;
import br.edu.utfpr.conta_corrente.services.ClienteService;

import java.net.URI;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteResource {
    @Autowired
    private ClienteService service;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Cliente> listarClientePorId(@PathVariable Integer id) {
        Cliente obj = service.buscarPorId(id);

        obj.add(linkTo(methodOn(ClienteResource.class).listarClientePorId(obj.getId())).withSelfRel());
        obj.add(linkTo(methodOn(ClienteResource.class).buscarTodos()).withRel(IanaLinkRelations.COLLECTION));
        System.out.println("Conta cadastrada com sucesso, atrelado a/ao: " + obj.getCpf());
        return ResponseEntity.ok().body(obj);
    }

    @RequestMapping(method = RequestMethod.GET)
    public CollectionModel<Cliente> buscarTodos() {
        List<Cliente> clientes = service.buscarTodos();

        for (Cliente cliente : clientes) {
            cliente.add(linkTo(methodOn(ClienteResource.class).listarClientePorId(cliente.getId())).withSelfRel());
        }
        return CollectionModel.of(service.buscarTodos()).add(
                WebMvcLinkBuilder
                        .linkTo(WebMvcLinkBuilder
                                .methodOn(ClienteResource.class)
                                .buscarTodos()).withSelfRel());
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Cliente> inserirCliente(@RequestBody Cliente cliente) {
        cliente = service.inserirCliente(cliente);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(cliente.getId()).toUri();
        System.out.println("Cliente cadastrado com sucesso");
        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<Cliente> atualizarCliente(@RequestBody Cliente cliente) {
        Cliente clienteObj = service.atualizarCliente(cliente);
        System.out.println("Cliente atualizado com sucesso");
        return ResponseEntity.status(204).body(clienteObj);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deletaCliente(@PathVariable Integer id) {
        service.deletaCliente(id);
        return ResponseEntity.noContent().build();
    }
}
