package beans;

import dao.AgendamentoDAO;
import dao.EquipeDAO;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.validation.constraints.Future;
import model.Agendamento;
import model.Equipe;
import model.Servico;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author dudbub
 */
@Named(value = "buscaBean")
@SessionScoped
public class BuscaBean implements Serializable { //Bean de sessão para atualizar as informações apenas para a atendente que estiver usando-a

    private Servico servicoSelecionado;
    private Equipe equipeSelecionada;
    private Agendamento atual;
    private LocalDate date;

    @Future //Anotação descoberta com o primefaces(v12.0.0)
    private List<Date> invalidDates;
    private List<Integer> invalidDays;
    private LocalDate minDate;
    private LocalDate maxDate;

    private char periodo;
    private String contratante, telefone, email, endereco;
    private String formataPeriodo = "";
    private final LocalDate hoje = LocalDate.now();
    private double valorTotal;

    @Inject //Injeção de dependência
    EquipeDAO equipeDAO;

    @Inject //Injeção de dependência
    AgendamentoDAO agendamentoDAO;

    List<Agendamento> agenda = new LinkedList<>();

    private LinkedList<SelectItem> itensEquipe;

    public BuscaBean() {
    }

    @PostConstruct //anotação passada em aula para a o método ser executado quando a sessão é iniciada
    public void iniciar() {
        invalidDates = new ArrayList<>();
        invalidDays = new ArrayList<>();
        invalidDays.add(0); //não atende no domingo
        minDate = LocalDate.now().minusYears(0);//não permite agendamento em data passada
        maxDate = LocalDate.now().plusYears(1); //só permite agendamento de até 1 ano
        atual = new Agendamento();
        itensEquipe = new LinkedList<SelectItem>();
        itensEquipe.add(new SelectItem(null, "Selecione um tipo de Serviço primeiro"));
    }

    //getters e setters
    public Servico getServicoSelecionado() {
        return servicoSelecionado;
    }

    public void setServicoSelecionado(Servico servicoSelecionado) {
        this.servicoSelecionado = servicoSelecionado;
        itensEquipe.clear();
        if (servicoSelecionado == null) {
            itensEquipe.add(new SelectItem(null, "Selecione um tipo de serviço primeiro"));
        } else {
            itensEquipe.add(new SelectItem(null, "Equipes..."));
            for (Equipe equip : equipeDAO.filtrarPorServico(servicoSelecionado)) {
                itensEquipe.add(new SelectItem(equip, equip.getDesc()));
            }
        }
    }

    public EquipeDAO getEquipeDAO() {
        return equipeDAO;
    }

    public void setEquipeDAO(EquipeDAO equipeDAO) {
        this.equipeDAO = equipeDAO;
    }

    public AgendamentoDAO getAgendamentoDAO() {
        return agendamentoDAO;
    }

    public void setAgendamentoDAO(AgendamentoDAO agendamentoDAO) {
        this.agendamentoDAO = agendamentoDAO;
    }
    
    

    public LinkedList<SelectItem> getItensEquipe() {
        return itensEquipe;
    }

    public Equipe getEquipeSelecionada() {
        return equipeSelecionada;
    }

    public void setEquipeSelecionada(Equipe equipeSelecionada) {
        this.equipeSelecionada = equipeSelecionada;
    }

    public Agendamento getAtual() {
        return atual;
    }

    public void setAtual(Agendamento atual) {
        this.atual = atual;
    }

    public char getPeriodo() {
        return periodo;
    }

    public void setPeriodo(char periodo) {
        this.periodo = periodo;
    }

    public List<Agendamento> getAgenda() {
        return agenda;
    }

    public void setAgenda(List<Agendamento> agenda) {
        this.agenda = agenda;
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

    public String getFormataPeriodo() {
        return formataPeriodo;
    }

    public void setFormataPeriodo(String formataPeriodo) {
        this.formataPeriodo = formataPeriodo;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public List<Agendamento> getAgendaFiltrada() {
        return agenda;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public List<Date> getInvalidDates() {
        return invalidDates;
    }

    public void setInvalidDates(List<Date> invalidDates) {
        this.invalidDates = invalidDates;
    }

    public List<Integer> getInvalidDays() {
        return invalidDays;
    }

    public void setInvalidDays(List<Integer> invalidDays) {
        this.invalidDays = invalidDays;
    }

    public LocalDate getMinDate() {
        return minDate;
    }

    public void setMinDate(LocalDate minDate) {
        this.minDate = minDate;
    }

    public LocalDate getMaxDate() {
        return maxDate;
    }

    public void setMaxDate(LocalDate maxDate) {
        this.maxDate = maxDate;
    }

    //Métodos do Bean
    public String filtrar() {//chama metodo filtrar por equipe
        agenda = agendamentoDAO.filtrar(equipeSelecionada);
        return null;
    }

    public String agenda() { //mostra todos os agendamentos e chama o limpa os campos
        agenda = agendamentoDAO.agenda();
        limpaPreenchidos();
        return null;
    }

    public double atualizaValorTotal() { //Método para atualizar o valor Total quando chamado
        valorTotal = equipeSelecionada.getValorPeriodo();
        if (periodo == 'I') {
            valorTotal = valorTotal * 2;
        }
        return valorTotal;
    }

    public void inserir() { //Método para realizar a inserção do agendamento na agenda
        boolean conflito = false; //variavel para indicar se há algum conflito
        FacesContext facesContext = FacesContext.getCurrentInstance(); //gera o facesContext para trazer mensagens de erro
        switch (periodo) { //Checagem se o periodo é para o dia todo, se for deve adicionar 2 agendamentos
            case 'I' -> { //troquei por case pq o java insistiu kkk
                if (date.equals(hoje)) { //não aceita agendamento do dia todo se está tentando hoje
                    facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Seu agendamento foi desconsiderado", "O agendamento deve ser feito com antecedência"));
                } else {
                    if (telefone.length() == 11 && telefone.matches("\\d+")) { //checa se telefone tem 11 dígitos numéricos
                        if (email.contains("@") && email.contains(".com") && email.length() >= 7) { //checa se o email é válido
                            for (Agendamento agendamentoExistente : agenda) { //percorre a lista de agendamentos e caso já exista algum agendamento existente seta a variável conflito como true
                                if (agendamentoExistente.getData().equals(date) && equipeSelecionada.getId() == agendamentoExistente.getEquipe().getId()) {
                                    conflito = true;
                                }
                            }
                            if (conflito) { //checa conflito
                                facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Conflito de datas e períodos", "Já existe um agendamento para a data e período selecionados."));
                            } else { //caso passe em todas as confirmações insere para o periodo da manhã e da tarde
                                formataPeriodo = "Manha";
                                atual = new Agendamento(equipeSelecionada, servicoSelecionado,
                                        equipeSelecionada.getValorPeriodo(), equipeSelecionada.getResponsavel(),
                                        formataPeriodo, contratante, telefone, email, endereco, date);
                                agenda.add(atual);
                                agendamentoDAO.inserir(atual);

                                formataPeriodo = "Tarde";
                                atual = new Agendamento(equipeSelecionada, servicoSelecionado,
                                        equipeSelecionada.getValorPeriodo(), equipeSelecionada.getResponsavel(),
                                        formataPeriodo, contratante, telefone, email, endereco, date);
                                agenda.add(atual);
                                agendamentoDAO.inserir(atual);
                            }
                        } else {
                            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Seu agendamento foi desconsiderado", "Email informado inválido"));
                        }
                    } else {
                        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Seu agendamento foi desconsiderado", "Telefone de contato inválido"));
                    }
                }
            }
            case 'D' -> {
                if (date.equals(hoje)) { //não aceita agendamento durante a manhã se está tentando hoje
                    facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Seu agendamento foi desconsiderado", "O agendamento deve ser feito com antecedência"));
                } else { //caso passe em todas as confirmações insere para o periodo da manhã
                    if (telefone.length() == 11 && telefone.matches("\\d+")) { //checa se telefone tem 11 dígitos numéricos
                        if (email.contains("@") && email.contains(".com") && email.length() >= 7) { //checa se o email é válido)
                            formataPeriodo = "Manhã";
                            for (Agendamento agendamentoExistente : agenda) { //percorre a lista de agendamentos e caso já exista algum agendamento existente seta a variável conflito como true
                                if (agendamentoExistente.getData().equals(date) && equipeSelecionada.getId() == agendamentoExistente.getEquipe().getId()) {
                                    if (agendamentoExistente.getPeriodo().equals(formataPeriodo) || agendamentoExistente.getPeriodo().equals("Dia Inteiro")) {
                                        conflito = true;
                                    }
                                }
                            }
                            if (conflito) { //checa se tem conflito de agendamentos
                                facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Conflito de datas e períodos", "Já existe um agendamento para a data e período selecionados."));
                            } else { //caso passe em todas as confirmações insere para o periodo da manhã
                                atual = new Agendamento(equipeSelecionada, servicoSelecionado,
                                        equipeSelecionada.getValorPeriodo(), equipeSelecionada.getResponsavel(),
                                        formataPeriodo, contratante, telefone, email, endereco, date);
                                agenda.add(atual);
                                agendamentoDAO.inserir(atual);
                            }
                        } else {
                            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Seu agendamento foi desconsiderado", "Email informado inválido"));
                        }
                    } else {
                        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Seu agendamento foi desconsiderado", "Telefone de contato inválido"));
                    }
                }
            }
            case 'T' -> {
                if (telefone.length() == 11 && telefone.matches("\\d+")) { //checa se telefone tem 11 dígitos numéricos
                    if (email.contains("@") && email.contains(".com") && email.length() >= 7) { //checa se o email é válido
                        for (Agendamento agendamentoExistente : agenda) { //percorre a lista de agendamentos e caso já exista algum agendamento existente seta a variável conflito como true
                            formataPeriodo = "Tarde";
                            if (agendamentoExistente.getData().equals(date) && equipeSelecionada.getId() == agendamentoExistente.getEquipe().getId()) {
                                if (agendamentoExistente.getPeriodo().equals(formataPeriodo) || agendamentoExistente.getPeriodo().equals("Dia Inteiro")) {
                                    conflito = true;
                                }
                            }
                        }

                        if (conflito) { //checa conflito
                            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Conflito de datas e períodos", "Já existe um agendamento para a data e período selecionados."));
                        } else { //caso passe em todas as confirmações insere para o periodo da tarde
                            atual = new Agendamento(equipeSelecionada, servicoSelecionado,
                                    equipeSelecionada.getValorPeriodo(), equipeSelecionada.getResponsavel(),
                                    formataPeriodo, contratante, telefone, email, endereco, date);
                            agenda.add(atual);
                            agendamentoDAO.inserir(atual);
                        }
                    } else {
                        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Seu agendamento foi desconsiderado", "Email informado inválido"));
                    }
                } else {
                    facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Seu agendamento foi desconsiderado", "Telefone de contato inválido"));
                }
            } //caso de algum erro
            default ->
                facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Não foi possível realizar o agendamento", "Por favor, tente novamente"));
        }
        atual = new Agendamento();
        equipeSelecionada = new Equipe();
        servicoSelecionado = new Servico();
        contratante = null;
        telefone = null;
        email = null;
        endereco = null;
        date = null;
        formataPeriodo = null;
        periodo = ' ';
        valorTotal = 0.0;
        itensEquipe.add(new SelectItem(null, "Selecione um tipo de serviço primeiro"));
        agenda(); //chama a agenda para atualizar a tabela e buscar todos os agendamentos
    }

    public void dataSelecionada(SelectEvent<Date> dataSelecionada) { //Método necessário para que o calendário atrele a data
        Date data = dataSelecionada.getObject();
        Instant agora = data.toInstant();
        LocalDate vDataLocal = agora.atZone(ZoneId.systemDefault()).toLocalDate();
        agenda = agendamentoDAO.filtrarPorData(equipeSelecionada, vDataLocal); //tentativa
        this.date = vDataLocal;
    }

    public String limpaPreenchidos() { //Método para limpar todos os campos preenchidos
        atual = new Agendamento();
        equipeSelecionada = new Equipe();
        servicoSelecionado = new Servico();
        contratante = null;
        telefone = null;
        email = null;
        endereco = null;
        date = null;
        formataPeriodo = null;
        periodo = ' ';
        valorTotal = 0.0;
        itensEquipe.add(new SelectItem(null, "Selecione um tipo de serviço primeiro"));
        return "index.xhtml";
    }
}
