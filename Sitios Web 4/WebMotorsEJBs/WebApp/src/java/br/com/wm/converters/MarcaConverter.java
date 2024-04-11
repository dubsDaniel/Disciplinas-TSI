package br.com.wm.converters;

import br.com.wm.modelo.Marca;
import br.com.wm.servico.ServicoMarca;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Named;

@Named(value = "marcaConverter")
@Stateless
public class MarcaConverter implements Converter<Marca> {

    @EJB(lookup="java:global/WebMotorsEJB/MarcaDAO!br.com.wm.servico.ServicoMarca")
    ServicoMarca servicoMarca;
    
    @Override
    public Marca getAsObject(FacesContext context, UIComponent component, String value) {
        try {
            int id = Integer.parseInt(value);
            return servicoMarca.buscar(id);
        } catch(Throwable t) {
            t.printStackTrace();
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Marca value) {
        if (value != null) {
            return String.valueOf( value.getId() );
        }
        return null;
    }
    
}
