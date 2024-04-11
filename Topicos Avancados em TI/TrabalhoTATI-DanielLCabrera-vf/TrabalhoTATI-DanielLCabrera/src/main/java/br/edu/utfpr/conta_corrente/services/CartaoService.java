package br.edu.utfpr.conta_corrente.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.edu.utfpr.conta_corrente.domain.Cartao;
import br.edu.utfpr.conta_corrente.domain.Conta;
import br.edu.utfpr.conta_corrente.repositories.CartaoRepository;
import br.edu.utfpr.conta_corrente.resources.exceptions.DataConstraintException;

import java.util.List;
import java.util.Optional;

@Service
public class CartaoService {
    @Autowired
    private CartaoRepository repo;

    @Autowired
    private ContaService contaService;

    public Cartao buscarPorId(Integer id) {
        Optional<Cartao> obj = repo.findById(id);
        return obj.orElse(null);
    }

    public List<Cartao> buscarTodos() {
        return repo.findAll();
    }

    public Cartao inserirCartao(Cartao cartao) {
        cartao.setId(null);
        return repo.save(cartao);
    }

    public Cartao atualizarCartao(Cartao cartao) {
        Cartao novoCartao = buscarPorId(cartao.getId());
        atualizarCartaoObj(novoCartao, cartao);

        return repo.save(novoCartao);
    }

    public void atualizarCartaoObj(Cartao novoCartao, Cartao cartao) {
        novoCartao.setLimite(cartao.getLimite());
        novoCartao.setValor(cartao.getValor());
        novoCartao.setVencimento(cartao.getVencimento());
    }

    public void deletaCartao(Integer id) {
        buscarPorId(id);

        try{
            repo.deleteById(id);
        }
        catch (DataIntegrityViolationException exception) {
            throw new DataConstraintException("Este objeto está linkado com outro, não pode ser deletado");
        }
    }

    
    public Boolean compraCredito(Cartao cartao, double valorCompra){
        double total = valorCompra+cartao.getValor();
        if(cartao.getLimite() > total){
            cartao.setValor(total);
            repo.save(cartao);
            return true;
        } 
        return false;
    }

    public Boolean pagamentoFatura(Cartao cartao, double valorPagamento){
        Conta conta = cartao.getConta();
        double rendaConta = conta.getRenda();
        if(rendaConta >= valorPagamento){
            cartao.setValor(cartao.getValor()-valorPagamento);
            conta.setRenda(rendaConta-valorPagamento);
            contaService.atualizarConta(conta);
            repo.save(cartao);
            return true;
        } 
        return false;
    }

}
