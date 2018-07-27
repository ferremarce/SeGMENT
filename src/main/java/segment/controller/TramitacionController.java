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
import org.eclipse.persistence.exceptions.DatabaseException;
import segment.fachada.TramitacionFacade;
import segment.modelo.Dependencia;
import segment.modelo.Expediente;
import segment.modelo.SubTipo;
import segment.modelo.Tramitacion;
import util.JSFutil;

/**
 *
 * @author jmferreira
 */
@Named(value = "TramitacionController")
@SessionScoped
public class TramitacionController implements Serializable {

    private static final Logger LOG = Logger.getLogger(TramitacionController.class.getName());
    ResourceBundle bundle = ResourceBundle.getBundle("propiedades.bundle", JSFutil.getmyLocale());

    @Inject
    TramitacionFacade tramitacionFacade;

    private Tramitacion tramitacion;
    private List<Tramitacion> listaTramitacion;
    private Dependencia[] arrayDependencias;

    /**
     * Creates a new instance of TramitacionFacade
     */
    public TramitacionController() {
    }

    public Dependencia[] getArrayDependencias() {
        return arrayDependencias;
    }

    public void setArrayDependencias(Dependencia[] arrayDependencias) {
        this.arrayDependencias = arrayDependencias;
    }

    public Tramitacion getTramitacion() {
        return tramitacion;
    }

    public void setTramitacion(Tramitacion tramitacion) {
        this.tramitacion = tramitacion;
    }

    public List<Tramitacion> getListaTramitacion() {
        return listaTramitacion;
    }

    public void setListaTramitacion(List<Tramitacion> listaTramitacion) {
        this.listaTramitacion = listaTramitacion;
    }

    public void doListarTramitacionEntrada() {
        //Pendiente
        this.listaTramitacion = tramitacionFacade.findAllTramitacion(6);
    }

    public void doListarTramitacionSalida() {
        //Confirmado
        this.listaTramitacion = tramitacionFacade.findAllTramitacion(7);
    }

    public void doListarTramitacionProcesado() {
        //Derivado
        this.listaTramitacion = tramitacionFacade.findAllTramitacion(9);
    }

    public Integer doCantidadEntrada() {
        return tramitacionFacade.findAllTramitacion(6).size();
    }

    public Integer doCantidadSalida() {
        return tramitacionFacade.findAllTramitacion(7).size();
    }

    public Integer doCantidadProcesado() {
        return tramitacionFacade.findAllTramitacion(9).size();
    }

    public String doMisTareasForm() {
        this.listaTramitacion = new ArrayList<>();
        return "/pages/MisTareas";
    }

    public void doAceptarTramite(Integer idTramitacion) {
        try {
            Tramitacion tram = tramitacionFacade.find(idTramitacion);
            //Confirmado
            tram.setIdEstadoTramite(new SubTipo(7));

            tramitacionFacade.edit(tram);
            this.doListarTramitacionEntrada();
        } catch (EJBException ex) {
            String msg = "";
            Throwable t = ex.getCause();
            while ((t != null) && !(t instanceof DatabaseException)) {
                t = t.getCause();
            }
            if (t != null) {
                msg = t.getLocalizedMessage();
            }
            if (t instanceof DatabaseException) {
                JSFutil.addMessage(msg, JSFutil.StatusMessage.ERROR);
            } else {
                JSFutil.addMessage(this.bundle.getString("UpdateError") + " " + msg, JSFutil.StatusMessage.ERROR);
            }
            LOG.log(Level.SEVERE, null, ex);
        }
    }
        public void doArchivarTramite(Integer idTramitacion) {
        try {
            Tramitacion tram = tramitacionFacade.find(idTramitacion);
            //Archivado
            tram.setIdEstadoTramite(new SubTipo(10));
            tramitacionFacade.edit(tram);
            this.doListarTramitacionEntrada();
        } catch (EJBException ex) {
            String msg = "";
            Throwable t = ex.getCause();
            while ((t != null) && !(t instanceof DatabaseException)) {
                t = t.getCause();
            }
            if (t != null) {
                msg = t.getLocalizedMessage();
            }
            if (t instanceof DatabaseException) {
                JSFutil.addMessage(msg, JSFutil.StatusMessage.ERROR);
            } else {
                JSFutil.addMessage(this.bundle.getString("UpdateError") + " " + msg, JSFutil.StatusMessage.ERROR);
            }
            LOG.log(Level.SEVERE, null, ex);
        }
    }

    public String doTramitarForm(Integer idTramitacion) {
        Tramitacion tramActual = tramitacionFacade.find(idTramitacion);
        this.tramitacion = new Tramitacion();
        this.tramitacion.setIdTramitacionAnterior(tramActual);
        this.tramitacion.setIdOrigen(JSFutil.getUsuarioConectado().getIdDependencia());
        this.tramitacion.setIdExpediente(tramActual.getIdExpediente());
        return "/pages/TramitarExpediente";
    }

    public String doTramitar() {
        try {
            Tramitacion tramAnterior = tramitacion.getIdTramitacionAnterior();
            for (Dependencia dep : this.arrayDependencias) {
                this.tramitacion.setIdTramitacion(null);
                this.tramitacion.setFechaRegistro(JSFutil.getFechaHoraActual());
                this.tramitacion.setIdEstadoTramite(new SubTipo(6));
                this.tramitacion.setIdDestino(dep);
                this.tramitacion.setIdUsuarioOrigen(JSFutil.getUsuarioConectado());
                this.tramitacionFacade.create(tramitacion);
            }
            tramAnterior.setIdEstadoTramite(new SubTipo(9));
            this.tramitacionFacade.edit(tramAnterior);
            this.doListarTramitacionSalida();
            JSFutil.addMessage(this.bundle.getString("UpdateSuccess"), JSFutil.StatusMessage.INFORMATION);
        } catch (EJBException ex) {
            String msg = "";
            Throwable t = ex.getCause();
            while ((t != null) && !(t instanceof DatabaseException)) {
                t = t.getCause();
            }
            if (t != null) {
                msg = t.getLocalizedMessage();
            }
            if (t instanceof DatabaseException) {
                JSFutil.addMessage(msg, JSFutil.StatusMessage.ERROR);
            } else {
                JSFutil.addMessage(this.bundle.getString("UpdateError") + " " + msg, JSFutil.StatusMessage.ERROR);
            }
            LOG.log(Level.SEVERE, null, ex);
        }
        return "/pages/MisTareas";
    }
}
