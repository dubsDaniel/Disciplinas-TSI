
package br.com.wm.servico;

import br.com.wm.modelo.Marca;
import br.com.wm.modelo.Modelo;
import java.util.List;
import javax.ejb.Remote;

@Remote
public interface ServicoModelo {
    public void inserir(Modelo m);
    public void alterar(Modelo m);
    public void remover(Modelo m);
    public Modelo buscar(int id);
    public List<Modelo> filtrarPorMarca(Marca m);
    public List<Modelo> listar();    
}
