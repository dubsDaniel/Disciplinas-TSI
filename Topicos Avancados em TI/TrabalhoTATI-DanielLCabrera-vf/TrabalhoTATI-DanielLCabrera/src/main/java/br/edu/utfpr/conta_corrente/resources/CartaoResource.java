package br.edu.utfpr.conta_corrente.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.edu.utfpr.conta_corrente.domain.Cartao;
import br.edu.utfpr.conta_corrente.services.CartaoService;

import java.net.URI;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping(value = "/cartao")
public class CartaoResource {
    @Autowired
    private CartaoService service;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Cartao> listarCartaoPorId(@PathVariable Integer id) {
        Cartao obj = service.buscarPorId(id);
        obj.add(linkTo(methodOn(ContaResource.class).listarContaPorId(obj.getId())).withSelfRel());
        obj.add(linkTo(methodOn(ContaResource.class).buscarTodos()).withRel(IanaLinkRelations.COLLECTION));
        obj.getConta().add(linkTo(methodOn(ClienteResource.class).listarClientePorId(obj.getConta().getId())).withSelfRel());
        return ResponseEntity.ok().body(obj);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Cartao>> buscarTodos() {
        List<Cartao> cartoes = service.buscarTodos();
        for(Cartao cartao: cartoes){
            cartao.add((linkTo(methodOn(ContaResource.class).buscarTodos()).withSelfRel()));
            cartao.getConta().add(
                    linkTo(methodOn(ClienteResource.class).listarClientePorId(cartao.getConta().getId())).withSelfRel()
            );
            cartao.add(linkTo(methodOn(ContaResource.class).listarContaPorId(cartao.getId())).withRel("conta"));

        }
        return ResponseEntity.ok().body(cartoes);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> inserirCartao(@RequestBody Cartao cartao) {
        cartao = service.inserirCartao(cartao);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(cartao.getId()).toUri();
        System.out.println("Cartao cadastrado com sucesso");
        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<Void> atualizarCartao(@RequestBody Cartao cartao) {
        service.atualizarCartao(cartao);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deletaCartao(@PathVariable Integer id) {
        service.deletaCartao(id);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "compra/{id}", method = RequestMethod.POST)
    public ResponseEntity<String> realizarCompra(@PathVariable Integer id, @RequestBody double valorCompra) {
        Cartao obj = service.buscarPorId(id);
        if (service.compraCredito(obj, valorCompra)){
            return ResponseEntity.ok().body("Compra realizada com sucesso");
        }
        return ResponseEntity.ok().body("Não foi possivel realizar a compra - nao ha limite disponivel");
    }

    @RequestMapping(value = "pagamentoFatura/{id}", method = RequestMethod.POST)
    public ResponseEntity<String> pagamentoFatura(@PathVariable Integer id, @RequestBody double valorPagamento) {
        Cartao obj = service.buscarPorId(id);
        if (service.pagamentoFatura(obj, valorPagamento)){
            return ResponseEntity.ok().body("Pagamento da fatura realizado com sucesso");
        }
        return ResponseEntity.ok().body("Não foi possivel realizar o pagamento - Saldo insuficiente");
    }

}
