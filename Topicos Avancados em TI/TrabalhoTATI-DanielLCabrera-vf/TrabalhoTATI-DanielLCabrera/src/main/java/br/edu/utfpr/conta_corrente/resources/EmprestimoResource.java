package br.edu.utfpr.conta_corrente.resources;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import br.edu.utfpr.conta_corrente.domain.Emprestimo;
import br.edu.utfpr.conta_corrente.services.EmprestimoService;

import java.net.URI;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping(value = "/emprestimo")
public class EmprestimoResource {
    @Autowired
    private EmprestimoService service;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Emprestimo> listarEmprestimoPorId(@PathVariable Integer id) {
        Emprestimo obj = service.buscarPorId(id);
        obj.add(linkTo(methodOn(EmprestimoResource.class).listarEmprestimoPorId(obj.getId())).withSelfRel());
        obj.add(linkTo(methodOn(EmprestimoResource.class).buscarTodos()).withRel(IanaLinkRelations.COLLECTION));
        obj.getConta().add(linkTo(methodOn(ClienteResource.class).listarClientePorId(obj.getConta().getId())).withSelfRel());
        return ResponseEntity.ok().body(obj);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Emprestimo>> buscarTodos() {
        List<Emprestimo> emprestimos = service.buscarTodos();
        for(Emprestimo emprestimo: emprestimos){
            emprestimo.add((linkTo(methodOn(EmprestimoResource.class).buscarTodos()).withSelfRel()));
            emprestimo.getConta().add(
                    linkTo(methodOn(ClienteResource.class).listarClientePorId(emprestimo.getConta().getId())).withSelfRel()
            );
            emprestimo.add(linkTo(methodOn(EmprestimoResource.class).listarEmprestimoPorId(emprestimo.getId())).withRel("emprestimo"));

        }
        return ResponseEntity.ok().body(emprestimos);
    }

    @RequestMapping(value = "/pegarEmprestimo", method = RequestMethod.POST)
    public ResponseEntity<Void> inserirEmprestimo(@RequestBody Emprestimo emprestimo) {
        emprestimo = service.inserirEmprestimo(emprestimo);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(emprestimo.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deletaEmprestimo(@PathVariable Integer id) {
        service.deletaEmprestimo(id);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "pagamentoEmprestimo/{id}", method = RequestMethod.POST)
    public ResponseEntity<String> pagamentoEmprestimo(@PathVariable Integer id, @RequestBody double valorPagamento) {
        Emprestimo obj = service.buscarPorId(id);
        if (service.pagamentoEmprestimo(obj, valorPagamento)){
            return ResponseEntity.ok().body("Pagamento do emprestimo realizado com sucesso");
        }
        return ResponseEntity.ok().body("Não foi possivel realizar o pagamento - Saldo invalido");
    }
}
