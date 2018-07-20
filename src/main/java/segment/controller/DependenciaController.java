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
import javax.inject.Inject;
import segment.fachada.DependenciaFacade;
import segment.modelo.Dependencia;
import util.JSFutil;
import util.JSFutil.PersistAction;

/**
 *
 * @author jmferreira
 */
@Named(value = "DependenciaController")
@SessionScoped
public class DependenciaController implements Serializable {

    private static final Logger LOG = Logger.getLogger(DependenciaController.class.getName());
    ResourceBundle bundle = ResourceBundle.getBundle("propiedades.bundle", JSFutil.getmyLocale());

    @Inject
    DependenciaFacade dependenciaFacade;
    @Inject
    CommonController commonController;

    private Dependencia dependencia;
    private List<Dependencia> listaDependencia;
    private String criterio;

    public DependenciaController() {
    }

    public String getCriterio() {
        return criterio;
    }

    public void setCriterio(String criterio) {
        this.criterio = criterio;
    }

    public Dependencia getDependencia() {
        return dependencia;
    }

    public void setDependencia(Dependencia dependencia) {
        this.dependencia = dependencia;
    }

    public List<Dependencia> getListaDependencia() {
        return listaDependencia;
    }

    public void setListaDependencia(List<Dependencia> listaDependencia) {
        this.listaDependencia = listaDependencia;
    }

    public String doListarForm() {
        if (this.listaDependencia == null) {
            this.listaDependencia = new ArrayList<>();
        }
        return "/pages/ListarDependencia";
    }

    public String doCrearForm() {
        this.dependencia = new Dependencia();
        return "/pages/CrearDependencia";
    }

    public String doEditarForm(Integer id) {
        this.dependencia = dependenciaFacade.find(id);
        return "/pages/CrearDependencia";
    }

    public String doBorrar(Integer id) {
        this.dependencia = dependenciaFacade.find(id);
        persist(PersistAction.DELETE);
        return doListarForm();
    }

    public String doRefrescar() {
        this.listaDependencia = dependenciaFacade.findDependencia("");
        if (this.listaDependencia.isEmpty()) {
            JSFutil.addMessage("No hay resultados...", JSFutil.StatusMessage.WARNING);
        } else {
            JSFutil.addMessage(this.listaDependencia.size() + " registros recuperados", JSFutil.StatusMessage.INFORMATION);
        }
        return "";
    }

    public String doBuscar() {
        if (this.criterio.isEmpty()) {
            JSFutil.addMessage("No hay criterios para buscar...", JSFutil.StatusMessage.WARNING);
            return "";
        }
        this.listaDependencia = dependenciaFacade.findDependencia(this.criterio);
        if (this.listaDependencia.isEmpty()) {
            JSFutil.addMessage("No hay resultados...", JSFutil.StatusMessage.WARNING);
        } else {
            JSFutil.addMessage(this.listaDependencia.size() + " registros recuperados", JSFutil.StatusMessage.INFORMATION);
        }
        return "";
    }

    public String doGuardar() {
        if (this.dependencia.getIdDependencia() != null) {
            persist(PersistAction.UPDATE);
        } else {
            persist(PersistAction.CREATE);
        }
        this.listaDependencia = dependenciaFacade.findDependencia(dependencia.getDescripcionDependencia());
        return doListarForm();
    }

    private void persist(PersistAction persistAction) {
        try {
            if (persistAction.compareTo(PersistAction.CREATE) == 0) {
                dependenciaFacade.create(dependencia);
            } else if (persistAction.compareTo(PersistAction.UPDATE) == 0) {
                dependenciaFacade.edit(dependencia);
            } else if (persistAction.compareTo(PersistAction.DELETE) == 0) {
                dependenciaFacade.remove(dependencia);
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
}
