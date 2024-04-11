package dao;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author dudbub
 */
public abstract class GenericDAO<T> implements Serializable { //Classe abstrata DAO passada em aula, editada para o tema da avaliação
//Classe abstrata para implementar as funcionalidades (inserir e filtrar ou listar)

    protected LinkedList<T> lista;

    public GenericDAO() {
        lista = new LinkedList<>();
    }

    public void inserir(T obj) {
        if (!lista.contains(obj)) {
            lista.add(obj);
        }
    }

    public List<T> listar() {
        LinkedList<T> resp = new LinkedList<>();
        resp.addAll(lista);
        return resp;
    }

    public T findByExample(T ex) {
        int pos = lista.indexOf(ex);
        if (pos >= 0) {
            return lista.get(pos);
        }
        return null;
    }

}
