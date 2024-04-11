package br.edu.utfpr.conta_corrente.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.utfpr.conta_corrente.domain.Cliente;
import br.edu.utfpr.conta_corrente.domain.Conta;
import br.edu.utfpr.conta_corrente.domain.Emprestimo;
import br.edu.utfpr.conta_corrente.domain.Cartao;
import br.edu.utfpr.conta_corrente.repositories.ClienteRepository;
import br.edu.utfpr.conta_corrente.repositories.ContaRepository;
import br.edu.utfpr.conta_corrente.repositories.EmprestimoRepository;

import br.edu.utfpr.conta_corrente.repositories.CartaoRepository;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

@Service
public class DBService {
    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    ContaRepository contaRepository;

    @Autowired
    EmprestimoRepository emprestimoRepository;

    @Autowired
    CartaoRepository cartaoRepository;

    public void iniciarBancoDeDados() {
        Cliente cliente = new Cliente(null, "Michelinda", "9999999999");
        Cliente cliente2 = new Cliente(null, "Gabriela", "9999999999");
        Cliente cliente3 = new Cliente(null, "Vera", "9999999999");
        Cliente cliente4 = new Cliente(null, "Daniel", "9999999999");

        clienteRepository.saveAll(Arrays.asList(cliente, cliente2, cliente3, cliente4));

        Conta conta1 = new Conta(null, 10.00, cliente);
        Conta conta2 = new Conta(null, 16.78, cliente2);
        Conta conta3 = new Conta(null, 245.00, cliente3);
        Conta conta4 = new Conta(null, 1200.00, cliente4);

        contaRepository.saveAll(Arrays.asList(conta1, conta2, conta3, conta4));
        
        Calendar c = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        c.add(Calendar.MONTH, 1);
        long oneMonthInFuture = c.getTimeInMillis();
        Cartao c1 = new Cartao(null, 10.00, 10.00, new Date(oneMonthInFuture), conta1);
        Cartao c2 = new Cartao(null, 16.78, 0, new Date(oneMonthInFuture), conta2);
        Cartao c3 = new Cartao(null, 245.10, 100.00, new Date(oneMonthInFuture), conta3);

        cartaoRepository.saveAll(Arrays.asList(c1, c2, c3));

        Emprestimo e1 = new Emprestimo(null, 1000.00, new Date(oneMonthInFuture), conta4);
        Emprestimo e2 = new Emprestimo(null, 1500.00, new Date(oneMonthInFuture), conta3);

        emprestimoRepository.saveAll(Arrays.asList(e1, e2));
    }
}
