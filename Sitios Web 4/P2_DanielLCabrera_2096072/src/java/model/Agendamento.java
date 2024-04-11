package model;

import java.util.Date;
import java.util.Objects;

/**
  * @author dudbub
  * Classe para listar todos os agendamentos
  */
public class Agendamento {
    private Equipe equipe;
    private Servico servico;
    private double valorTotal;
    private String responsavel, contratante, telefone, email, endereco, agendado;
    private Date data;
    private char periodo;

    public Agendamento() {
    }

    public Agendamento(Equipe equipe, Servico servico, double valorTotal, String responsavel, String contratante, String telefone, String email, String endereco, char periodo) {
        String idAgenda = responsavel + contratante + data + periodo;
        
        this.equipe = equipe;
        this.servico = servico;
        this.responsavel = responsavel;
        this.contratante = contratante;
        this.telefone = telefone;
        this.email = email;
        this.endereco = endereco;
        this.agendado = idAgenda;
        //this.data = data;
        this.periodo = periodo;
        if (periodo == 'I'){
            this.valorTotal = valorTotal*2;
        } else {
            this.valorTotal = valorTotal;
        }
    }

    public Equipe getEquipe() {
        return equipe;
    }

    public void setEquipe(Equipe equipe) {
        this.equipe = equipe;
    }

    public Servico getServico() {
        return servico;
    }

    public void setServico(Servico servico) {
        this.servico = servico;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public String getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(String responsavel) {
        this.responsavel = responsavel;
    }

    public String getContratante() {
        return contratante;
    }

    public void setContratante(String contratante) {
        this.contratante = contratante;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
    
    public char getPeriodo() {
        return periodo;
    }

    public void setPeriodo(char periodo) {
        if (periodo == 'I') {
            setValorTotal(valorTotal*2);
        }
        this.periodo = periodo;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }
    
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + Objects.hashCode(this.agendado);
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
        final Agendamento other = (Agendamento) obj;
        return Objects.equals(this.agendado, other.agendado);
    }    
}
