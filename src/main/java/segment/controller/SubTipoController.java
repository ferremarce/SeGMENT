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
import segment.fachada.SubTipoFacade;
import segment.modelo.SubTipo;
import util.JSFutil;
import util.JSFutil.PersistAction;

/**
 *
 * @author jmferreira
 */
@Named(value = "SubTipoController")
@SessionScoped
public class SubTipoController implements Serializable {

    private static final Logger LOG = Logger.getLogger(SubTipoController.class.getName());
    ResourceBundle bundle = ResourceBundle.getBundle("propiedades.bundle", JSFutil.getmyLocale());

    @Inject
    SubTipoFacade subTipoFacade;

    private SubTipo subTipo;
    private List<SubTipo> listaSubTipo;

    /**
     * Creates a new instance of SubTipoController
     */
    public SubTipoController() {
    }

    public SubTipoFacade getSubTipoFacade() {
        return subTipoFacade;
    }

    public void setSubTipoFacade(SubTipoFacade subTipoFacade) {
        this.subTipoFacade = subTipoFacade;
    }

    public SubTipo getSubTipo() {
        return subTipo;
    }

    public void setSubTipo(SubTipo subTipo) {
        this.subTipo = subTipo;
    }

    public List<SubTipo> getListaSubTipo() {
        return listaSubTipo;
    }

    public void setListaSubTipo(List<SubTipo> listaSubTipo) {
        this.listaSubTipo = listaSubTipo;
    }

    public SelectItem[] getSubTipoExpedienteSet() {
        return JSFutil.getSelectItems(subTipoFacade.findAllbyTipo(1, Boolean.TRUE), Boolean.TRUE);
    }


    public String doListarForm() {
        if (this.listaSubTipo == null) {
            this.listaSubTipo = new ArrayList<>();
        }

        return "/pages/ListarSubTipo";
    }

    public String doCrearForm() {
        this.subTipo = new SubTipo();
        return "/pages/CrearSubTipo";
    }

    public String doEditarForm(Integer id) {
        this.subTipo = subTipoFacade.find(id);
        return "/pages/CrearSubTipo";
    }

    public String doRefrescar() {
        this.listaSubTipo = subTipoFacade.findAll();
        if (this.listaSubTipo.isEmpty()) {
            JSFutil.addMessage("No hay resultados...", JSFutil.StatusMessage.WARNING);
        } else {
            JSFutil.addMessage(this.listaSubTipo.size() + " registros recuperados", JSFutil.StatusMessage.INFORMATION);
        }
        return "";
    }

    public String doGuardar() {
        if (this.subTipo.getIdSubTipo() != null) {
            persist(PersistAction.UPDATE);
        } else {
            persist(PersistAction.CREATE);
        }
        this.doRefrescar();
        return doListarForm();
    }

    public String doBorrar(Integer id) {
        this.subTipo = subTipoFacade.find(id);
        persist(PersistAction.DELETE);
        return doListarForm();
    }

    private void persist(PersistAction persistAction) {
        try {
            if (persistAction.compareTo(PersistAction.CREATE) == 0) {
                subTipoFacade.create(subTipo);
            } else if (persistAction.compareTo(PersistAction.UPDATE) == 0) {
                subTipoFacade.edit(subTipo);
            } else if (persistAction.compareTo(PersistAction.DELETE) == 0) {
                subTipoFacade.remove(subTipo);
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
