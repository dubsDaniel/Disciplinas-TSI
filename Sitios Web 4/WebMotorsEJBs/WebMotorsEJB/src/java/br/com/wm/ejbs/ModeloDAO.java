package br.com.wm.ejbs;

import br.com.wm.modelo.Marca;
import br.com.wm.modelo.Modelo;
import br.com.wm.servico.ServicoModelo;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class ModeloDAO implements ServicoModelo {

    @PersistenceContext
    EntityManager em;

    @Override
    public void inserir(Modelo m) {
        em.persist(m);
    }

    @Override
    public void alterar(Modelo m) {
        em.merge(m);
    }

    @Override
    public void remover(Modelo m) {
        em.remove(m);
    }

    @Override
    public List<Modelo> listar() {
        return em.createQuery("select m from Modelo m order by m.nome")
                .getResultList();
    }

    @Override
    public Modelo buscar(int id) {
        return em.find(Modelo.class, id);
    }

    @Override
    public List<Modelo> filtrarPorMarca(Marca m) {
        Query q = em.createQuery("select m from Modelo m where m.marca = :marca order by m.nome", Modelo.class);
        q.setParameter("marca", m);
        return q.getResultList();   
    }

}
