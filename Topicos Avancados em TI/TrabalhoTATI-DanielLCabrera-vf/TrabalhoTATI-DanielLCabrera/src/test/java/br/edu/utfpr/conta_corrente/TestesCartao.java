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
import br.edu.utfpr.conta_corrente.domain.Cartao;
import br.edu.utfpr.conta_corrente.services.CartaoService;

@SpringBootTest

@Service
class TestesCartao {
    @Autowired
    CartaoService cartaoService;    

    @Test
    void testeComprarLimiteCredito() {
        Calendar c = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        c.add(Calendar.MONTH, 1);
        long oneMonthInFuture = c.getTimeInMillis();
        Cliente cliente = new Cliente(null, "Testador", "9999999999");
        Conta conta = new Conta(null, 1200.00, cliente);
        Cartao cartao = new Cartao(1, 1000.00, 0.00, new Date(oneMonthInFuture), conta);
        assertTrue(cartaoService.compraCredito(cartao, 1000) == true);
    }

    @Test
    void testePagarFatura() {
        Calendar c = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        c.add(Calendar.MONTH, 1);
        long oneMonthInFuture = c.getTimeInMillis();
        Cliente cliente = new Cliente(null, "Testador", "9999999999");
        Conta conta = new Conta(null, 1200.00, cliente);
        Cartao cartao = new Cartao(1, 1000.00, 0.00, new Date(oneMonthInFuture), conta);

        assertTrue(cartaoService.pagamentoFatura(cartao, 1000) == true);
    }
}
