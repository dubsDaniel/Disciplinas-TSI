package model;

import java.io.PrintStream;
import java.util.Date;

/**
  * @author dudbub
  * Classe para listas as equipes de cada tipo de serviço
  */
public class Equipe {
    private int id;
    private String desc, responsavel;
    private Servico tipoServico;
    private double valorPeriodo;

    public Equipe() {}

    public Equipe(int id, String desc, Servico tipoServico, String responsavel, double valorPeriodo) {
        this.id = id;
        this.desc = desc;
        this.responsavel = responsavel;
        this.tipoServico = tipoServico;
        this.valorPeriodo = valorPeriodo;
    }

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
