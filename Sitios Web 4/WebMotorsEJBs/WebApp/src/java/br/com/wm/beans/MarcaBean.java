package br.com.wm.beans;

import br.com.wm.modelo.Marca;
import br.com.wm.servico.ServicoMarca;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.model.SelectItem;


@Named(value = "marcaBean")
@SessionScoped
public class MarcaBean implements Serializable {

    @EJB(lookup="java:global/WebMotorsEJB/MarcaDAO!br.com.wm.servico.ServicoMarca")
    ServicoMarca servicoMarca;

    private Marca marca;
    
    public MarcaBean() {
        marca = new Marca();
    }

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }
    
    public String confirmar() {
        servicoMarca.inserir(marca);
        marca = new Marca();
        return null;
    }
    
    public List<Marca> getMarcas() {
        return servicoMarca.listar();
    }
    
    public List<SelectItem> getMarcasAsItems() {
        LinkedList<SelectItem> items = new LinkedList<>();
        for (Marca m : getMarcas()) {
            items.add(new SelectItem(m, m.getDescricao()));
        }
        return items;
    }
}
