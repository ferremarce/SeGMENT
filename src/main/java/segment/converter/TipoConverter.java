/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package segment.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import segment.controller.TipoController;
import segment.modelo.Tipo;

/**
 * Converter del Tipo.class
 * @author jmferreira
 */
@FacesConverter(forClass = Tipo.class)
public class TipoConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
        //Debe comparar con ---opciones--- que se carga en el ListItem de JSFutil
        if (value == null || value.length() == 0 || value.compareTo("------ Opciones ------") == 0) {
            return null;
        }
        TipoController controller = (TipoController) facesContext.getApplication().getELResolver().
                getValue(facesContext.getELContext(), null, "TipoController");
        return controller.getTipoFacade().find(getKey(value));
    }

    java.lang.Integer getKey(String value) {
        java.lang.Integer key;
        key = Integer.valueOf(value);
        return key;
    }

    String getStringKey(java.lang.Integer value) {
        StringBuilder sb = new StringBuilder();
        sb.append(value);
        return sb.toString();
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
        if (object == null) {
            return null;
        }
        if (object instanceof String && object.toString().compareTo("------ Opciones ------") == 0) {
            return null;
        }
        if (object instanceof Tipo) {
            Tipo o = (Tipo) object;
            return getStringKey(o.getIdTipo());
        } else {
            throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + TipoController.class.getName());
        }
    }
}
