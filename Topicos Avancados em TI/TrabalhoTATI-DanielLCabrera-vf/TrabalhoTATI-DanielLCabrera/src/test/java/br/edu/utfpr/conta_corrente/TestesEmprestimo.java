package br.edu.utfpr.conta_corrente;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;
import br.edu.utfpr.conta_corrente.domain.Cliente;
import br.edu.utfpr.conta_corrente.domain.Conta;
import br.edu.utfpr.conta_corrente.domain.Emprestimo;
import br.edu.utfpr.conta_corrente.services.EmprestimoService;

@SpringBootTest

@Service
class TestesEmprestimo {
    @Autowired
    EmprestimoService emprestimoService;    

    @Test
    void testePegarEmprestimo() {
        Calendar c = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        c.add(Calendar.MONTH, 1);
        long oneMonthInFuture = c.getTimeInMillis();
        Cliente cliente = new Cliente(null, "Testador", "9999999999");
        Conta conta = new Conta(null, 1200.00, cliente);
        emprestimoService.inserirEmprestimo(new Emprestimo(1, 1000.00, new Date(oneMonthInFuture), conta));

        assertTrue(emprestimoService.buscarPorId(1).getConta().getRenda() == 2200.00);
    }

    @Test
    void testePagarEmprestimo() {
        Calendar c = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        c.add(Calendar.MONTH, 1);
        long oneMonthInFuture = c.getTimeInMillis();
        Cliente cliente = new Cliente(null, "Testador", "9999999999");
        Conta conta = new Conta(null, 1200.10, cliente);
        Emprestimo pegouEmprestimo = emprestimoService.inserirEmprestimo(new Emprestimo(1, 1000.00, new Date(oneMonthInFuture), conta));
        emprestimoService.pagamentoEmprestimo(pegouEmprestimo, 1000.00);
        assertTrue(emprestimoService.buscarPorId(1).getConta().getRenda() == 2200.10);
    }
}
