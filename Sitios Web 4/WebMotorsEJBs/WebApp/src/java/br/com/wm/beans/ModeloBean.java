package br.com.wm.beans;

import br.com.wm.modelo.Marca;
import br.com.wm.modelo.Modelo;
import br.com.wm.servico.ServicoModelo;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

@Named("modeloBean")
@SessionScoped
public class ModeloBean implements Serializable {

    @EJB(lookup = "java:global/WebMotorsEJB/ModeloDAO!br.com.wm.servico.ServicoModelo")
    ServicoModelo servico;

    Modelo modelo;

    Marca marcaSelecionada;

    public ModeloBean() {
        modelo = new Modelo();
    }

    public String confirmar() {
        if (marcaSelecionada != null) {
            modelo.setMarca(marcaSelecionada);
            servico.inserir(modelo);
            modelo = new Modelo();
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Selecione a marca"));
        }
        return null;
    }

    public Modelo getModelo() {
        return modelo;
    }

    public void setModelo(Modelo modelo) {
        this.modelo = modelo;
    }

    public Marca getMarcaSelecionada() {
        return marcaSelecionada;
    }

    public void setMarcaSelecionada(Marca marcaSelecionada) {
        this.marcaSelecionada = marcaSelecionada;
    }

    public List<Modelo> getModelos() {
        if (marcaSelecionada == null) {
            return new LinkedList<>();
        }
        return servico.filtrarPorMarca(marcaSelecionada);
    }
}
