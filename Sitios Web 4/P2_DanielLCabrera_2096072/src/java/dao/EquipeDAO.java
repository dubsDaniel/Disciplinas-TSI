package dao;


import java.io.Serializable;
import java.util.LinkedList;
import model.Equipe;
import model.Servico;
/**
 *
 * @author dudbub
 */
public class EquipeDAO extends GenericDAO<Equipe> implements Serializable {
    
    public LinkedList<Equipe> filtrarPorServico(Servico servico) {
        LinkedList<Equipe> equipes = new LinkedList<>();
        for (Equipe equip : lista ) {
            if (equip.getTipoServico().equals(servico)) {
                equipes.add(equip);
            }
        }
        return equipes;
    }
    
    public Equipe findById(int id) {
        for (Equipe equip : lista) {
            if (equip.getId() == id) {
                return equip;
            }
        }
        return null;
    }
}
