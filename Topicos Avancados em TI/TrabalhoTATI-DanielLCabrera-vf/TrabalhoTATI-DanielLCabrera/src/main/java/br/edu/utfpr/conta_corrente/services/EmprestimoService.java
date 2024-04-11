package br.edu.utfpr.conta_corrente.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.edu.utfpr.conta_corrente.domain.Conta;
import br.edu.utfpr.conta_corrente.domain.Emprestimo;
import br.edu.utfpr.conta_corrente.repositories.EmprestimoRepository;
import br.edu.utfpr.conta_corrente.resources.exceptions.DataConstraintException;

import java.util.List;
import java.util.Optional;

@Service
public class EmprestimoService {
    @Autowired
    private EmprestimoRepository repo;

    @Autowired
    private ContaService contaService;

    public Emprestimo buscarPorId(Integer id) {
        Optional<Emprestimo> obj = repo.findById(id);
        return obj.orElse(null);
    }

    public List<Emprestimo> buscarTodos() {
        return repo.findAll();
    }

    public Emprestimo inserirEmprestimo(Emprestimo emprestimo) {
        emprestimo.setId(null);
        Emprestimo emprestimoSalvo = repo.save(emprestimo);
        Conta conta = emprestimoSalvo.getConta();
        conta.setRenda(conta.getRenda()+emprestimoSalvo.getValor());
        contaService.atualizarConta(conta);

        return emprestimoSalvo;
    }

    public void deletaEmprestimo(Integer id) {
        buscarPorId(id);

        try{
            repo.deleteById(id);
        }
        catch (DataIntegrityViolationException exception) {
            throw new DataConstraintException("Este objeto está linkado com outro, não pode ser deletado");
        }
    }

    public Boolean pagamentoEmprestimo(Emprestimo emprestimo, double valorPagamento){
        Conta conta = emprestimo.getConta();
        double rendaConta = conta.getRenda();
        if(rendaConta >= valorPagamento){
            emprestimo.setValor(emprestimo.getValor()-valorPagamento);
            conta.setRenda(rendaConta-valorPagamento);
            contaService.atualizarConta(conta);
            repo.save(emprestimo);
            return true;
        } 
        return false;
    }

}
