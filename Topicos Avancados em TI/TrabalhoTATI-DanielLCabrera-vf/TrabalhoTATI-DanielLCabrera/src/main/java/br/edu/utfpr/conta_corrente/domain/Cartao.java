package br.edu.utfpr.conta_corrente.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.*;
import java.util.Date;

@Entity @Getter
@Setter
@NoArgsConstructor
public class Cartao extends RepresentationModel<Cartao> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private double limite;
    private double valor;
    private Date vencimento;

    @OneToOne
    private Conta conta;

    public Cartao(Integer id, double limite, double valor, Date vencimento, Conta conta) {
        this.id = id;
        this.limite = limite;
        this.valor = valor;
        this.vencimento = vencimento;
        this.conta = conta;
    }

}