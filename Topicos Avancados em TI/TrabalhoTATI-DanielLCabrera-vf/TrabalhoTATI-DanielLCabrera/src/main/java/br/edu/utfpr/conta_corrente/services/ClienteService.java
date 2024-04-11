package br.edu.utfpr.conta_corrente.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.edu.utfpr.conta_corrente.domain.Cliente;
import br.edu.utfpr.conta_corrente.repositories.ClienteRepository;
import br.edu.utfpr.conta_corrente.resources.exceptions.DataConstraintException;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository repo;

    public Cliente buscarPorId(Integer id) {
        Optional<Cliente> obj = repo.findById(id);
        return obj.orElse(null);
    }

    public List<Cliente> buscarTodos() {
        return repo.findAll();
    }

    public Cliente inserirCliente(Cliente cliente) {
        cliente.setId(null);
        return repo.save(cliente);
    }

    public Cliente atualizarCliente(Cliente cliente) {
        Cliente novoCliente = buscarPorId(cliente.getId());
        atualizarContaObj(novoCliente, cliente);

        return repo.save(novoCliente);
    }

    public void atualizarContaObj(Cliente novoCliente, Cliente cliente) {
        novoCliente.setNome(cliente.getNome());
        novoCliente.setCpf(cliente.getCpf());
    }

    public void deletaCliente(Integer id) {
        buscarPorId(id);

        try{
            repo.deleteById(id);
        }
        catch (DataIntegrityViolationException exception) {
            throw new DataConstraintException("Este objeto está linkado com outro, não pode ser deletado");
        }
    }
}
