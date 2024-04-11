package converters;

import dao.ServicoDAO;
import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import model.Servico;

/**
 *
 * @author dudbub
 */
@Named(value = "servicoConverter")
@ApplicationScoped
public class ServicoConverter  implements Converter<Servico>{

    @Inject
    ServicoDAO dao;

    @Override
    public Servico getAsObject(FacesContext context, UIComponent component, String value) {
        try {
            int id = Integer.parseInt(value);
            return dao.findById(id);
        } catch(Exception ex) {
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Servico value) {
        if (value == null){
            return null;
        }
        return String.valueOf(value.getId());
    }
    
}
