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
import segment.fachada.UsuarioFacade;
import segment.modelo.Usuario;
import util.JSFutil;
import util.JSFutil.PersistAction;

/**
 *
 * @author jmferreira
 */
@Named(value = "UsuarioController")
@SessionScoped
public class UsuarioController implements Serializable {

    private static final Logger LOG = Logger.getLogger(UsuarioController.class.getName());
    ResourceBundle bundle = ResourceBundle.getBundle("propiedades.bundle", JSFutil.getmyLocale());

    @Inject
    UsuarioFacade usuarioFacade;
    @Inject
    CommonController commonController;

    private Usuario usuario;
    private List<Usuario> listaUsuario;
    private String criterio;

    public UsuarioController() {
    }

    public UsuarioFacade getUsuarioFacade() {
        return usuarioFacade;
    }

    public void setUsuarioFacade(UsuarioFacade usuarioFacade) {
        this.usuarioFacade = usuarioFacade;
    }

    public String getCriterio() {
        return criterio;
    }

    public void setCriterio(String criterio) {
        this.criterio = criterio;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Usuario> getListaUsuario() {
        return listaUsuario;
    }

    public void setListaUsuario(List<Usuario> listaUsuario) {
        this.listaUsuario = listaUsuario;
    }

    public String doListarForm() {
        if (this.listaUsuario == null) {
            this.listaUsuario = new ArrayList<>();
        }
        return "/usuario/ListarUsuario";
    }

    public String doCrearForm() {
        this.usuario = new Usuario();
        return "/usuario/CrearUsuario";
    }

    public String doEditarForm(Integer id) {
        this.usuario = usuarioFacade.find(id);
        return "/usuario/CrearUsuario";
    }

    public String doBorrar(Integer id) {
        this.usuario = usuarioFacade.find(id);
        persist(PersistAction.DELETE);
        return doListarForm();
    }

    public String doRefrescar() {
        this.listaUsuario = usuarioFacade.findUsuario("");
        if (this.listaUsuario.isEmpty()) {
            JSFutil.addMessage("No hay resultados...", JSFutil.StatusMessage.WARNING);
        } else {
            JSFutil.addMessage(this.listaUsuario.size() + " registros recuperados", JSFutil.StatusMessage.INFORMATION);
        }
        return "";
    }

    public String doBuscar() {
        if (this.criterio.isEmpty()) {
            JSFutil.addMessage("No hay criterios para buscar...", JSFutil.StatusMessage.WARNING);
            return "";
        }
        this.listaUsuario = usuarioFacade.findUsuario(this.criterio);
        if (this.listaUsuario.isEmpty()) {
            JSFutil.addMessage("No hay resultados...", JSFutil.StatusMessage.WARNING);
        } else {
            JSFutil.addMessage(this.listaUsuario.size() + " registros recuperados", JSFutil.StatusMessage.INFORMATION);
        }
        return "";
    }

    public String doGuardar() {
        if (this.usuario.getIdUsuario() != null) {
            persist(PersistAction.UPDATE);
        } else {
            persist(PersistAction.CREATE);
        }
        this.listaUsuario = usuarioFacade.findUsuario(usuario.getCuenta());
        return doListarForm();
    }

    private void persist(PersistAction persistAction) {
        try {
            if (persistAction.compareTo(PersistAction.CREATE) == 0) {
                usuarioFacade.create(usuario);
            } else if (persistAction.compareTo(PersistAction.UPDATE) == 0) {
                usuarioFacade.edit(usuario);
            } else if (persistAction.compareTo(PersistAction.DELETE) == 0) {
                usuarioFacade.remove(usuario);
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
