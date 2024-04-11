package beans;

import dao.AgendamentoDAO;
import dao.EquipeDAO;
import dao.ServicoDAO;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.faces.model.SelectItem;
import model.Equipe;
import model.Servico;

/**
 *
 * @author dudbub
 */
@Named(value = "aplicacao")
@ApplicationScoped
public class AplicacaoBean {

    private ServicoDAO servicoDAO;
    private EquipeDAO equipeDAO;
    private AgendamentoDAO agendamentoDAO;
    
    private List<SelectItem> itensServico;
    
    private String arquivo = "C:\tmp/dados_webmotors.dat";
    
    public AplicacaoBean() {}
    
    @PostConstruct
    public void iniciar() {
        try {
            FileInputStream fis = new FileInputStream(arquivo);
            ObjectInputStream ois = new ObjectInputStream(fis);
            servicoDAO = (ServicoDAO) ois.readObject();
            equipeDAO = (EquipeDAO) ois.readObject();
            agendamentoDAO = (AgendamentoDAO) ois.readObject();
            ois.close();
            fis.close();
        } catch (Exception ex) { //não foi possível ler os dados, criar com os valores padrão 
            System.out.println("Erro lendo arquivos, reiniciando dados...");
            servicoDAO = new ServicoDAO();
            equipeDAO = new EquipeDAO();
            
            Servico s = new Servico(1, "Limpeza de terrenos urbanos");
            servicoDAO.inserir(s);
            equipeDAO.inserir(new Equipe(1, "Equipe A1", s, "Jão", 300.00));
            equipeDAO.inserir(new Equipe(2, "Equipe A2", s, "Maria", 330.00));

            s = new Servico(2, "Remoção de entulhos de obras");
            servicoDAO.inserir(s);
            equipeDAO.inserir(new Equipe(3, "Equipe B1", s, "Maria", 400.00));
            equipeDAO.inserir(new Equipe(4, "Equipe B2", s, "Jão", 460.00));

            s = new Servico(3, "Faxina geral (empresas ou residências)");
            servicoDAO.inserir(s);
            equipeDAO.inserir(new Equipe(5, "Equipe C1", s, "Jão", 500.00));
            equipeDAO.inserir(new Equipe(6, "Equipe C2", s, "Maria", 480.00));

            s = new Servico(4, "Jardinagem");
            servicoDAO.inserir(s);
            equipeDAO.inserir(new Equipe(7, "Equipe D1", s, "Maria", 280.00));
            equipeDAO.inserir(new Equipe(8, "Equipe D2", s, "Jão", 340.00));

            s = new Servico(5, "Limpeza de fachadas de vidro");
            servicoDAO.inserir(s);
            equipeDAO.inserir(new Equipe(9, "Equipe E1", s, "Maria", 280.00));
            equipeDAO.inserir(new Equipe(10, "Equipe E2", s, "Maria", 300.00));
            
            agendamentoDAO = new AgendamentoDAO();
        }
    }
    
    public List<SelectItem> getItensServicos() {
        if (itensServico == null) {
            itensServico = new LinkedList<>();
            itensServico.add(new SelectItem(null, "Selecione um tipo de Serviço"));
            for (Servico s : servicoDAO.listar()) {
                itensServico.add(new SelectItem(s, s.getDesc()));
            }
        }
        return itensServico;
    }
    
    @Produces
    public ServicoDAO getServicoDAO() {
        return servicoDAO;
    }
    
    @Produces
    public EquipeDAO getEquipeDAO() {
        return equipeDAO;
    }
    
    @Produces
    public AgendamentoDAO getAgendamentoDAO() {
        return agendamentoDAO;
    }
    
    @PreDestroy
    public void gravarArquivo(){
        try {
            FileOutputStream fos = new FileOutputStream(arquivo);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(servicoDAO);
            oos.writeObject(equipeDAO);
            oos.writeObject(agendamentoDAO);
            oos.flush();
            oos.close();
            fos.close();
        } catch(Throwable t) {
            t.printStackTrace();
        }
    }
}
