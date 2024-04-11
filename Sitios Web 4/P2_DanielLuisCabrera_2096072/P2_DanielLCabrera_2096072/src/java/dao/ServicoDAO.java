package dao;

import java.io.Serializable;
import model.Servico;

/**
 *
 * @author dudbub
 */
public class ServicoDAO extends GenericDAO<Servico> implements Serializable { //Classe DAO passada em aula, editada para ser compatível com a classe Serviços e reutilizada
//Classe que extende o GenericDAO para buscar um serviço pelo seu ID

    public Servico findById(int id) {
        return findByExample(new Servico(id, null));
    }
}
