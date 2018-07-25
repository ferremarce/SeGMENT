/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package segment.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import segment.controller.DependenciaController;
import segment.modelo.Dependencia;

/**
 * Converter del Dependencia.class
 * @author jmferreira
 */
@FacesConverter(forClass = Dependencia.class)
public class DependenciaConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
        //Debe comparar con ---opciones--- que se carga en el ListItem de JSFutil
        if (value == null || value.length() == 0 || value.compareTo("------ Opciones ------") == 0) {
            return null;
        }
        DependenciaController controller = (DependenciaController) facesContext.getApplication().getELResolver().
                getValue(facesContext.getELContext(), null, "DependenciaController");
        return controller.getDependenciaFacade().find(getKey(value));
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
        if (object instanceof Dependencia) {
            Dependencia o = (Dependencia) object;
            return getStringKey(o.getIdDependencia());
        } else {
            throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + DependenciaController.class.getName());
        }
    }
}
