package br.edu.utfpr.conta_corrente.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import java.util.Set;

import javax.persistence.*;

@Entity @Getter
@Setter
@NoArgsConstructor
public class Conta extends RepresentationModel<Conta> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private double renda;

    @OneToOne
    private Cliente cliente;

    @JsonIgnore
    @OneToMany(mappedBy="conta")
    private Set<Cartao> cartao;

    @JsonIgnore
    @OneToMany(mappedBy="conta")
    private Set<Emprestimo> emprestimo;

    public Conta(Integer id, double renda, Cliente cliente) {
        this.id = id;
        this.renda = renda;
        this.cliente = cliente;
    }
}
