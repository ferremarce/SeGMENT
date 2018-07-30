/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package segment.controller;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJBException;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import segment.fachada.RolFacade;
import segment.modelo.Rol;
import util.JSFutil;
import util.JSFutil.PersistAction;

/**
 *
 * @author jmferreira
 */
@Named(value = "RolController")
@SessionScoped
public class RolController implements Serializable {

    private static final Logger LOG = Logger.getLogger(RolController.class.getName());
    ResourceBundle bundle = ResourceBundle.getBundle("propiedades.bundle", JSFutil.getmyLocale());

    @Inject
    RolFacade rolFacade;
    @Inject
    CommonController commonController;

    private Rol rol;
    private List<Rol> listaRol;
    private String criterio;

    public RolController() {
    }

    public RolFacade getRolFacade() {
        return rolFacade;
    }

    public void setRolFacade(RolFacade rolFacade) {
        this.rolFacade = rolFacade;
    }

    public String getCriterio() {
        return criterio;
    }

    public void setCriterio(String criterio) {
        this.criterio = criterio;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public List<Rol> getListaRol() {
        return listaRol;
    }

    public void setListaRol(List<Rol> listaRol) {
        this.listaRol = listaRol;
    }

    public String doListarForm() {
        if (this.listaRol == null) {
            this.listaRol = new ArrayList<>();
        }
        return "/pages/ListarRol";
    }

    public String doCrearForm() {
        this.rol = new Rol();
        return "/pages/CrearRol";
    }

    public String doEditarForm(Integer id) {
        this.rol = rolFacade.find(id);
        return "/pages/CrearRol";
    }

    public String doBorrar(Integer id) {
        this.rol = rolFacade.find(id);
        persist(PersistAction.DELETE);
        return doListarForm();
    }

    public String doRefrescar() {
        this.listaRol = rolFacade.findRol("");
        if (this.listaRol.isEmpty()) {
            JSFutil.addMessage("No hay resultados...", JSFutil.StatusMessage.WARNING);
        } else {
            JSFutil.addMessage(this.listaRol.size() + " registros recuperados", JSFutil.StatusMessage.INFORMATION);
        }
        return "";
    }

    public String doBuscar() {
        if (this.criterio.isEmpty()) {
            JSFutil.addMessage("No hay criterios para buscar...", JSFutil.StatusMessage.WARNING);
            return "";
        }
        this.listaRol = rolFacade.findRol(this.criterio);
        if (this.listaRol.isEmpty()) {
            JSFutil.addMessage("No hay resultados...", JSFutil.StatusMessage.WARNING);
        } else {
            JSFutil.addMessage(this.listaRol.size() + " registros recuperados", JSFutil.StatusMessage.INFORMATION);
        }
        return "";
    }

    public String doGuardar() {
        if (this.rol.getIdRol() != null) {
            persist(PersistAction.UPDATE);
        } else {
            persist(PersistAction.CREATE);
        }
        this.listaRol = rolFacade.findRol(rol.getDescripcionRol());
        return doListarForm();
    }

    private void persist(PersistAction persistAction) {
        try {
            if (persistAction.compareTo(PersistAction.CREATE) == 0) {
                rolFacade.create(rol);
            } else if (persistAction.compareTo(PersistAction.UPDATE) == 0) {
                rolFacade.edit(rol);
            } else if (persistAction.compareTo(PersistAction.DELETE) == 0) {
                rolFacade.remove(rol);
            }
            JSFutil.addMessage(this.bundle.getString("UpdateSuccess"), JSFutil.StatusMessage.INFORMATION);
        } catch (EJBException ex) {
            String msg = "";
            Throwable cause = ex.getCause();
            if (cause != null) {
                msg = cause.getLocalizedMessage();
            }
            if (msg.length() > 0) {
                JSFutil.addMessage(msg, JSFutil.StatusMessage.ERROR);
            } else {
                JSFutil.addMessage(this.bundle.getString("UpdateError"), JSFutil.StatusMessage.ERROR);
            }
            LOG.log(Level.SEVERE, null, ex);
        }
    }

    public SelectItem[] getRolSet() {
        return JSFutil.getSelectItems(rolFacade.findAll(), Boolean.TRUE);
    }

}
