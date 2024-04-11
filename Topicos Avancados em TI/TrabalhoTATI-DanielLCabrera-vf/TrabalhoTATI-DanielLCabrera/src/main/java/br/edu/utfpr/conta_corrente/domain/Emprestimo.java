package br.edu.utfpr.conta_corrente.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Emprestimo extends RepresentationModel<Emprestimo> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private double valor;
    private Date vencimento;

    @ManyToOne
    @JoinColumn(name="conta_id")
    private Conta conta;

    public Emprestimo(Integer id, double valor, Date vencimento, Conta conta) {
        this.id = id;
        this.valor = valor;
        this.vencimento = vencimento;
        this.conta = conta;
    }
}