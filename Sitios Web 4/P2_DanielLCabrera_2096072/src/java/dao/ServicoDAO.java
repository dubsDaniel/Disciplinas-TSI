package dao;

import java.io.Serializable;
import model.Servico;

/**
  *
  * @author dudbub
  */
public class ServicoDAO extends GenericDAO<Servico> implements Serializable{
    
    public Servico findById(int id) {
        return findByExample(new Servico(id, null));
    }
}
