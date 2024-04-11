package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * @author dudbub Classe para listar todos os agendamentos
 */
public class Agendamento implements Serializable { //Classe de agendamentos para adicionar novos agendamentos

    @Id //anotação Id passado nas últimas aulas
    private String agendado;
    @ManyToOne //anotação passada em aula para mapear um relacionamento de Muitos-Pra-Um, nesse caso, muitos Agendamentos para uma Equipe
    private Equipe equipe;
    @ManyToOne //anotação passada em aula para mapear um relacionamento de Muitos-Pra-Um, nesse caso, muitos agendamentos para um Serviço
    private Servico servico;

    private double valorTotal;
    private String responsavel, contratante, telefone, email, endereco, periodo;
    private LocalDate data;
    private String checaPeriodo = "Dia Inteiro";

    public Agendamento() { //Construtor vazio
    }

    public Agendamento(Equipe equipe, Servico servico, double valorTotal, String responsavel, String periodo,
            String contratante, String telefone, String email, String endereco, LocalDate data) { //Construtor com os atributos
        String idAgenda = responsavel + contratante + periodo;
        this.equipe = equipe;
        this.servico = servico;
        this.responsavel = responsavel;
        this.periodo = periodo;
        if (periodo.equals(checaPeriodo)) { //Checa se 
            this.valorTotal = valorTotal * 2;
        } else {
            this.valorTotal = valorTotal;
        }
        this.contratante = contratante;
        this.telefone = telefone;
        this.email = email;
        this.endereco = endereco;
        this.agendado = idAgenda;
        this.data = data;
    }

    //getters e setters
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

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    //hash code e equals conforme passado em aula
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
