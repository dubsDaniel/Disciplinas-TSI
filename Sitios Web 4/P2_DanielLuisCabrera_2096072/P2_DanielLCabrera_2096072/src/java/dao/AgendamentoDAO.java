package dao;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import model.Equipe;
import model.Agendamento;

/**
 *
 * @author dudbub
 */
public class AgendamentoDAO extends GenericDAO<Agendamento> implements Serializable { //Classe DAO passada em aula, editada para ser compatível com a classe Agendamento e reutilizada
//Classe que extende o GenericDAO para filtrar a agenda usando uma equipe ou buscar todos os serviços cadastrados

    public LinkedList<Agendamento> filtrarPorEquipe(Equipe equipe) {
        LinkedList<Agendamento> resp = new LinkedList<>();
        for (Agendamento agenda : lista) {
            if (agenda.getEquipe().equals(equipe)) {
                resp.add(agenda);
            }
        }
        return resp;
    }

    public List<Agendamento> filtrar(Equipe equip) { //Método "Ou filtra ou traz todas"
        List<Agendamento> resp;
        if (equip != null) {
            resp = filtrarPorEquipe(equip);
        } else {
            resp = listar();
        }
        return resp;
    }

    public List<Agendamento> agenda() { //Método para trazer todos os agendamentos independente de equipe
        List<Agendamento> resp;
        resp = listar();
        return resp;
    }

    public List<Agendamento> filtrarPorData(Equipe equip, LocalDate data) { //tentativa
        List<Agendamento> resp;
        resp = listar();
        List<Agendamento> aRemover = new LinkedList<>();
        for (Agendamento agenda : resp) {
            if ((data != null || data != agenda.getData())) {
                aRemover.add(agenda);
            }
        }
        resp.removeAll(aRemover);
        return resp;
    }
}
