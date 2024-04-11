package br.edu.utfpr.conta_corrente.resources;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.edu.utfpr.conta_corrente.domain.Conta;
import br.edu.utfpr.conta_corrente.services.ContaService;

import java.net.URI;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping(value = "/contas")
public class ContaResource {
    @Autowired
    private ContaService service;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Conta> listarContaPorId(@PathVariable Integer id) {
        Conta obj = service.buscarPorId(id);
        obj.add(linkTo(methodOn(ContaResource.class).listarContaPorId(obj.getId())).withSelfRel());
        obj.add(linkTo(methodOn(ContaResource.class).buscarTodos()).withRel(IanaLinkRelations.COLLECTION));
        obj.getCliente().add(linkTo(methodOn(ClienteResource.class).listarClientePorId(obj.getCliente().getId())).withSelfRel());
        return ResponseEntity.ok().body(obj);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Conta>> buscarTodos() {
        List<Conta> contas = service.buscarTodos();
        for(Conta conta: contas){
            conta.add((linkTo(methodOn(ContaResource.class).buscarTodos()).withSelfRel()));
            conta.getCliente().add(
                    linkTo(methodOn(ClienteResource.class).listarClientePorId(conta.getCliente().getId())).withSelfRel()
            );
            conta.add(linkTo(methodOn(ContaResource.class).listarContaPorId(conta.getId())).withRel("conta"));

        }
        return ResponseEntity.ok().body(contas);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> inserirConta(@RequestBody Conta conta) {
        conta = service.inserirConta(conta);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(conta.getId()).toUri();
        System.out.println("Conta cadastrada com sucesso");
        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<Void> atualizarConta(@RequestBody Conta conta) {
        service.atualizarConta(conta);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deletaConta(@PathVariable Integer id) {
        service.deletaConta(id);
        return ResponseEntity.noContent().build();
    }
}
