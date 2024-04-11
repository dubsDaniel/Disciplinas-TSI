package br.edu.utfpr.conta_corrente.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.edu.utfpr.conta_corrente.domain.Conta;
import br.edu.utfpr.conta_corrente.repositories.ContaRepository;
import br.edu.utfpr.conta_corrente.resources.exceptions.DataConstraintException;

import java.util.List;
import java.util.Optional;

@Service
public class ContaService {
    @Autowired
    private ContaRepository repo;

    public Conta buscarPorId(Integer id) {
        Optional<Conta> obj = repo.findById(id);
        return obj.orElse(null);
    }

    public List<Conta> buscarTodos() {
        return repo.findAll();
    }

    public Conta inserirConta(Conta conta) {
        conta.setId(null);
        return repo.save(conta);
    }

    public Conta atualizarConta(Conta conta) {
        Conta novaConta = buscarPorId(conta.getId());
        atualizarContaObj(novaConta, conta);

        return repo.save(novaConta);
    }

    public void atualizarContaObj(Conta novaConta, Conta conta) {
        novaConta.setRenda(conta.getRenda());
    }

    public void deletaConta(Integer id) {
        buscarPorId(id);

        try{
            repo.deleteById(id);
        }
        catch (DataIntegrityViolationException exception) {
            throw new DataConstraintException("Este objeto está linkado com outro, não pode ser deletado");
        }
    }
}
