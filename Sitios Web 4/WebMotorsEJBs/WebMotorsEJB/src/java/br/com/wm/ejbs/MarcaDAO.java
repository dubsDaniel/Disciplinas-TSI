package br.com.wm.ejbs;

import br.com.wm.modelo.Marca;
import br.com.wm.servico.ServicoMarca;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class MarcaDAO implements ServicoMarca {
    
    @PersistenceContext
    EntityManager em; 
    
    @Override
    public void inserir(Marca m) {
        em.persist(m);
    }

    @Override
    public void alterar(Marca m) {
        em.merge(m);
    }

    @Override
    public void remover(Marca m) {
        em.remove(m);
    }

    @Override
    public List<Marca> listar() {
        return em.createQuery("select m from Marca m order by m.descricao")
                .getResultList();
    }
    
    @Override
    public Marca buscar(int id) {
        return em.find(Marca.class, id);
    }
    
}
