package beans;

import dao.AgendamentoDAO;
import dao.EquipeDAO;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import model.Agendamento;
import model.Equipe;
import model.Servico;

/**
 *
 * @author dudbub
 */
@Named(value = "buscaBean")
@SessionScoped
public class BuscaBean implements Serializable {

    private Servico servicoSelecionado;
    private Equipe equipeSelecionada;
    
    @Inject
    EquipeDAO equipeDAO;
    
    @Inject
    AgendamentoDAO agendamentoDAO;
    
    List<Agendamento> agenda = new LinkedList<>();
    
    private LinkedList<SelectItem> itensEquipe;
            
    public BuscaBean() {}
    
    @PostConstruct
    public void iniciar() {
        itensEquipe = new LinkedList<SelectItem>();
        itensEquipe.add(new SelectItem(null, "Selecione um tipo de Serviço primeiro"));
    }
    
    public Servico getServicoSelecionado() {
        return servicoSelecionado;
    }
    
    public void setServicoSelecionado(Servico servicoSelecionado) {
        this.servicoSelecionado = servicoSelecionado;
        itensEquipe.clear();
        if (servicoSelecionado == null) {
            itensEquipe.add(new SelectItem(null, "Selecione um tipo de serviço primeiro"));
        } else {
            itensEquipe.add(new SelectItem(null, "Modelo..."));
            for (Equipe equip : equipeDAO.filtrarPorServico(servicoSelecionado)){
                itensEquipe.add(new SelectItem(equip, equip.getDesc()));
            }
        }
    }

    public LinkedList<SelectItem> getItensEquipe() {
        return itensEquipe;
    }
    
    public Equipe getEquipeSelecionada(){
        return equipeSelecionada;
    }
    
    public void setEquipeSelecionada(Equipe equipeSelecionada){
        this.equipeSelecionada = equipeSelecionada;
    }
    
    public String filtrar() {
        agenda.add(new Agendamento(equipeSelecionada, servicoSelecionado, 800.0, "Jao", "Maria", "000", "teste", "teste", 'I'));
        equipeSelecionada = null;
        agenda = agendamentoDAO.filtrar(equipeSelecionada);
        return null;
    }
    
    public List<Agendamento> getAgendaFiltrada() {
        return agenda;
    }
    
    public void inserir() {
        filtrar();
    }
    
}
