/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package segment.controller;

import java.io.ByteArrayInputStream;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJBException;
import javax.inject.Inject;
import org.eclipse.persistence.exceptions.DatabaseException;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import segment.fachada.TramitacionAdjuntoFacade;
import segment.fachada.TramitacionFacade;
import segment.modelo.Dependencia;
import segment.modelo.SubTipo;
import segment.modelo.Tramitacion;
import segment.modelo.TramitacionAdjunto;
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
    @Inject
    TramitacionAdjuntoFacade tramitacionAdjuntoFacade;

    private Tramitacion tramitacion;
    private List<Tramitacion> listaTramitacion;
    private Dependencia[] arrayDependencias;
    private List<UploadedFile> adjuntoTramitacion;
    private Integer estadoTramite;

    /**
     * Creates a new instance of TramitacionFacade
     */
    public TramitacionController() {
    }

    public Integer getEstadoTramite() {
        return estadoTramite;
    }

    public void setEstadoTramite(Integer estadoTramite) {
        this.estadoTramite = estadoTramite;
    }

    public Dependencia[] getArrayDependencias() {
        return arrayDependencias;
    }

    public void setArrayDependencias(Dependencia[] arrayDependencias) {
        this.arrayDependencias = arrayDependencias;
    }

    public List<UploadedFile> getAdjuntoTramitacion() {
        return adjuntoTramitacion;
    }

    public void setAdjuntoTramitacion(List<UploadedFile> adjuntoTramitacion) {
        this.adjuntoTramitacion = adjuntoTramitacion;
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
        this.listaTramitacion = tramitacionFacade.findAllTramitacion(6, JSFutil.getUsuarioConectado().getIdDependencia().getIdDependencia());
        if (this.listaTramitacion.isEmpty()) {
            JSFutil.addMessage("No hay expedientes en la bandeja de entrada", JSFutil.StatusMessage.WARNING);
        }
    }

    public void doListarTramitacionSalida() {
        //Confirmado
        this.listaTramitacion = tramitacionFacade.findAllTramitacion(7, JSFutil.getUsuarioConectado().getIdDependencia().getIdDependencia());
        if (this.listaTramitacion.isEmpty()) {
            JSFutil.addMessage("No hay expedientes en la bandeja de salida", JSFutil.StatusMessage.WARNING);
        }

    }

    public void doListarTramitacionProcesado() {
        //Derivado
        this.listaTramitacion = tramitacionFacade.findAllTramitacionProcesados(JSFutil.getUsuarioConectado().getIdDependencia().getIdDependencia());
        if (this.listaTramitacion.isEmpty()) {
            JSFutil.addMessage("No hay expedientes en la bandeja de procesados", JSFutil.StatusMessage.WARNING);
        }

    }

    public Integer doCantidadEntrada() {
        return tramitacionFacade.findAllTramitacion(6, JSFutil.getUsuarioConectado().getIdDependencia().getIdDependencia()).size();
    }

    public Integer doCantidadSalida() {
        return tramitacionFacade.findAllTramitacion(7, JSFutil.getUsuarioConectado().getIdDependencia().getIdDependencia()).size();
    }

    public Integer doCantidadProcesado() {
        return tramitacionFacade.findAllTramitacionProcesados(JSFutil.getUsuarioConectado().getIdDependencia().getIdDependencia()).size();
    }

    public String doMisTareasForm() {
        this.listaTramitacion = new ArrayList<>();
        return "/pages/MisTareas";
    }

    public void handleFileUpload(FileUploadEvent event) {
        //LOG.log(Level.INFO, "Agregado el archivo {0}", event.getFile().getFileName());
        this.adjuntoTramitacion.add(event.getFile());
    }

    public void doAceptarTramite(Integer idTramitacion) {
        try {
            Tramitacion tram = tramitacionFacade.find(idTramitacion);
            //Confirmado
            tram.setIdEstadoTramite(new SubTipo(JSFutil.estadoTramite.RECIBIDO));
            tram.setIdUsuarioRecibido(JSFutil.getUsuarioConectado());
            tram.setFechaRecibido(JSFutil.getFechaHoraActual());
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

    public void doSacarAdjunto(int indice) {
        this.adjuntoTramitacion.remove(indice);
    }

    public void doArchivarTramite(Integer idTramitacion) {
        try {
            Tramitacion tram = tramitacionFacade.find(idTramitacion);
            //Archivado
            tram.setIdEstadoTramite(new SubTipo(JSFutil.estadoTramite.ARCHIVADO));
            tram.setFechaArchivado(JSFutil.getFechaHoraActual());
            tram.setIdUsuarioArchivado(JSFutil.getUsuarioConectado());
            tramitacionFacade.edit(tram);
            this.doListarTramitacionSalida();
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
        this.estadoTramite = JSFutil.estadoTramite.DERIVADO;
        Tramitacion tramActual = tramitacionFacade.find(idTramitacion);
        this.tramitacion = new Tramitacion();
        this.tramitacion.setIdTramitacionAnterior(tramActual);
        this.tramitacion.setIdDependencia(JSFutil.getUsuarioConectado().getIdDependencia());
        this.tramitacion.setIdExpediente(tramActual.getIdExpediente());
        this.tramitacion.setFechaTramite(JSFutil.getFechaHoraActual());
        this.tramitacion.setDescripcionTramite("A consideración");
        this.adjuntoTramitacion = new ArrayList<>();
        return "/pages/TramitarExpediente";
    }

    public String doRechazarForm(Integer idTramitacion) {
        this.estadoTramite = JSFutil.estadoTramite.DEVUELTO;
        Tramitacion tramActual = tramitacionFacade.find(idTramitacion);
        this.tramitacion = new Tramitacion();
        this.tramitacion.setIdTramitacionAnterior(tramActual);
        this.tramitacion.setIdDependencia(JSFutil.getUsuarioConectado().getIdDependencia());
        this.tramitacion.setIdExpediente(tramActual.getIdExpediente());
        this.tramitacion.setFechaTramite(JSFutil.getFechaHoraActual());
        this.tramitacion.setDescripcionTramite("Devolución de Expediente");
        arrayDependencias = new Dependencia[1];
        arrayDependencias[0] = tramActual.getIdTramitacionAnterior().getIdDependencia();
        this.adjuntoTramitacion = new ArrayList<>();
        return "/pages/TramitarExpediente";
    }

    public String doDerivar() {
        return "/pages/MisTareas";
    }

    public String doRechazar() {
        return "/pages/MisTareas";
    }

    public String doTramitar() {
        try {
            Tramitacion tramAnterior = tramitacion.getIdTramitacionAnterior();
            tramAnterior.setIdEstadoTramite(new SubTipo(this.estadoTramite));
            this.tramitacionFacade.edit(tramAnterior);
            TramitacionAdjunto ap;
            for (UploadedFile uf : this.adjuntoTramitacion) {
                ap = new TramitacionAdjunto();
                //ap.setArchivo(uf.getContents());
                ap.setTipoArchivoMime(uf.getContentType());
                ap.setTamanhoArchivo(BigInteger.valueOf(uf.getSize()));
                ap.setNombreArchivo(JSFutil.sanitizeFilename(uf.getFileName()));
                ap.setIdTramitacion(tramAnterior);
                ap.setFechaRegistro(JSFutil.getFechaHoraActual());
                tramitacionAdjuntoFacade.create(ap);
                int resultado = JSFutil.fileToDisk(new ByteArrayInputStream(uf.getContents()), JSFutil.folderTramitacion + ap.getIdTramitacionAdjunto() + "-" + JSFutil.sanitizeFilename(uf.getFileName()));
                if (resultado != 0) {
                    JSFutil.addMessage("No se ha podido guardar el adjunto debido a un error interno en el procesamiento del archivo. Se deshace el guardado del archivo.", JSFutil.StatusMessage.ERROR);
                    tramitacionAdjuntoFacade.remove(ap);
                }
            }
            //Derivacion
            for (Dependencia dep : this.arrayDependencias) {
                this.tramitacion.setIdTramitacion(null);
                this.tramitacion.setFechaTramite(JSFutil.getFechaHoraActual());
                this.tramitacion.setIdEstadoTramite(new SubTipo(JSFutil.estadoTramite.PENDIENTE));
                this.tramitacion.setIdDependencia(dep);
                this.tramitacionFacade.create(tramitacion);

            }
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
