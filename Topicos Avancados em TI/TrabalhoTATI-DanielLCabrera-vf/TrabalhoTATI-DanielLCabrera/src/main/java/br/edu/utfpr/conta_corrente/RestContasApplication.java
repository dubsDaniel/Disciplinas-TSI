package br.edu.utfpr.conta_corrente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.edu.utfpr.conta_corrente.resources.DBService;

@SpringBootApplication
public class RestContasApplication implements CommandLineRunner {
    @Autowired
    private DBService banco;

    public static void main(String[] args) {
        SpringApplication.run(RestContasApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        banco.iniciarBancoDeDados();
    }
}
