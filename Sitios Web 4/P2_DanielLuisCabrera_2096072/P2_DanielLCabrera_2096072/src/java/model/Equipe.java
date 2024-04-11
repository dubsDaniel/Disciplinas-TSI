package model;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * @author dudbub Classe para listas as equipes de cada tipo de serviço
 */
public class Equipe implements Serializable { //Classe para gerar a lista de equipes, controlar pelo ID e atrelamento ao serviço

    @Id //anotação Id passado nas últimas aulas
    private int id;
    @ManyToOne //anotação passada em aula para mapear um relacionamento de Muitos-Pra-Um, nesse caso, muitas equipes para um Serviço
    //Váriaveis para atualizar dados no BuscaBean
    private Servico tipoServico;
    private String desc, responsavel;
    private double valorPeriodo;
    private LocalDate filtrarData;

    public Equipe() {
    } //Construtor vazio

    public Equipe(int id, String desc, Servico tipoServico, String responsavel, double valorPeriodo) { //Construtor com os atributos
        this.id = id;
        this.desc = desc;
        this.responsavel = responsavel;
        this.tipoServico = tipoServico;
        this.valorPeriodo = valorPeriodo;
    }

    //getters e setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Servico getTipoServico() {
        return tipoServico;
    }

    public void setTipoServico(Servico tipoServico) {
        this.tipoServico = tipoServico;
    }

    public String getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(String responsavel) {
        this.responsavel = responsavel;
    }

    public double getValorPeriodo() {
        return valorPeriodo;
    }

    public void setValorPeriodo(double valorPeriodo) {
        this.valorPeriodo = valorPeriodo;
    }

    public LocalDate getFiltrarData() {
        return filtrarData;
    }

    public void setFiltrarData(LocalDate filtrarData) {
        this.filtrarData = filtrarData;
    }

    //hash code e equals conforme passado em aula
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 83 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Equipe other = (Equipe) obj;
        return this.id == other.id;
    }

}
