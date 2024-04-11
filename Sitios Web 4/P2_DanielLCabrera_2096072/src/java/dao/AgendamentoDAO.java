package dao;

import java.util.LinkedList;
import java.util.List;
import model.Equipe;
import model.Agendamento;
/**
 *
 * @author dudbub
 */
public class AgendamentoDAO extends GenericDAO<Agendamento> {
    public LinkedList<Agendamento> filtrarPorEquipe(Equipe equipe) {
        LinkedList<Agendamento> resp = new LinkedList<>();
        for(Agendamento agenda : lista) {
            if (agenda.getEquipe().equals(resp)){
                resp.add(agenda);
            }
        }
        return resp;
    }
    
    public List<Agendamento> filtrar(Equipe equip){
        List<Agendamento> resp;
        if (equip != null) {
            resp = filtrarPorEquipe(equip);
        } else {
            resp = listar();
        }
        List<Agendamento> aRemover = new LinkedList<>();
        for (Agendamento agenda : resp) {
            aRemover.add(agenda);
        }
        resp.removeAll(aRemover);
        return resp;
    }
}
