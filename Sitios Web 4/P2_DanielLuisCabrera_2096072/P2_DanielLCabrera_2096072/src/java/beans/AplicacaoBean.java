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
public class AplicacaoBean { //Bean de inicialização da aplicação

    private ServicoDAO servicoDAO;
    private EquipeDAO equipeDAO;
    private AgendamentoDAO agendamentoDAO;

    private List<SelectItem> itensServico;

    private String arquivo = "C:/tmp/TROQUE_PELO_ARQUIVO.dat"; //Lembre-se de determinar o caminho corretamente

    public AplicacaoBean() {
    } //Construtor vazio

    @PostConstruct //anotação passada em aula para a o método ser executado quando a aplicação é iniciada
    public void iniciar() {
        try { //tentando ler o arquivo onde está salvo em arquivo os agendamentos já criados
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
            equipeDAO.inserir(new Equipe(1, "Equipe A1", s, "Simone", 330.00));
            equipeDAO.inserir(new Equipe(2, "Equipe A2", s, "Samuel", 300.00));
            equipeDAO.inserir(new Equipe(3, "Equipe A3", s, "Oseias", 250.00));
            equipeDAO.inserir(new Equipe(4, "Equipe A4", s, "Miguel", 260.00));

            s = new Servico(2, "Remoção de entulhos de obras");
            servicoDAO.inserir(s);
            equipeDAO.inserir(new Equipe(5, "Equipe B1", s, "Fernanda", 400.00));
            equipeDAO.inserir(new Equipe(6, "Equipe B2", s, "Lucas", 460.00));
            equipeDAO.inserir(new Equipe(7, "Equipe B3", s, "Felipe", 450.00));

            s = new Servico(3, "Faxina geral (empresas ou residências)");
            servicoDAO.inserir(s);
            equipeDAO.inserir(new Equipe(8, "Equipe C1", s, "Ricardo", 500.00));
            equipeDAO.inserir(new Equipe(9, "Equipe C2", s, "Lourencio", 480.00));
            equipeDAO.inserir(new Equipe(10, "Equipe C3", s, "Bernardo", 550.00));

            s = new Servico(4, "Jardinagem");
            servicoDAO.inserir(s);
            equipeDAO.inserir(new Equipe(11, "Equipe D1", s, "Francisco", 280.00));
            equipeDAO.inserir(new Equipe(12, "Equipe D2", s, "Rita", 340.00));
            equipeDAO.inserir(new Equipe(13, "Equipe D3", s, "Vanessa", 300.00));
            equipeDAO.inserir(new Equipe(14, "Equipe D4", s, "Marcos", 350.00));

            s = new Servico(5, "Limpeza de fachadas de vidro");
            servicoDAO.inserir(s);
            equipeDAO.inserir(new Equipe(15, "Equipe E1", s, "Marcelo", 280.00));
            equipeDAO.inserir(new Equipe(16, "Equipe E2", s, "Roberta", 300.00));

            s = new Servico(6, "Limpeza de Caixa de gordura");
            servicoDAO.inserir(s);
            equipeDAO.inserir(new Equipe(17, "Equipe F1", s, "Thiago", 150.00));
            equipeDAO.inserir(new Equipe(18, "Equipe F2", s, "Fernando", 175.00));

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

    @PreDestroy //Antes de fechar a aplicação, salve todos os agendamentos
    public void gravarArquivo() {
        try {
            FileOutputStream fos = new FileOutputStream(arquivo);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(servicoDAO);
            oos.writeObject(equipeDAO);
            oos.writeObject(agendamentoDAO);
            oos.flush();
            oos.close();
            fos.close();
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }
}
