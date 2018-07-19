/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package segment.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
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
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;
import segment.fachada.ExpedienteAdjuntoFacade;
import segment.fachada.ExpedienteFacade;
import segment.modelo.Expediente;
import segment.modelo.ExpedienteAdjunto;
import util.JSFutil;
import util.JSFutil.PersistAction;

/**
 *
 * @author jmferreira
 */
@Named(value = "ExpedienteController")
@SessionScoped
public class ExpedienteController implements Serializable {

    private static final Logger LOG = Logger.getLogger(ExpedienteController.class.getName());
    ResourceBundle bundle = ResourceBundle.getBundle("propiedades.bundle", JSFutil.getmyLocale());

    @Inject
    ExpedienteFacade expedienteFacade;
    @Inject
    CommonController commonController;
    @Inject
    ExpedienteAdjuntoFacade expedienteAdjuntoFacade;

    private Expediente expediente;
    private List<Expediente> listaExpediente;
    private String criterio;
    private List<UploadedFile> adjuntoExpediente;
    private Integer indexAdjunto;

    public ExpedienteController() {
    }

    public String getCriterio() {
        return criterio;
    }

    public void setCriterio(String criterio) {
        this.criterio = criterio;
    }

    public List<UploadedFile> getAdjuntoExpediente() {
        return adjuntoExpediente;
    }

    public void setAdjuntoExpediente(List<UploadedFile> adjuntoExpediente) {
        this.adjuntoExpediente = adjuntoExpediente;
    }

    public Expediente getExpediente() {
        return expediente;
    }

    public void setExpediente(Expediente expediente) {
        this.expediente = expediente;
    }

    public List<Expediente> getListaExpediente() {
        return listaExpediente;
    }

    public void setListaExpediente(List<Expediente> listaExpediente) {
        this.listaExpediente = listaExpediente;
    }

    public String doListarForm() {
        if (this.listaExpediente == null) {
            this.listaExpediente = new ArrayList<>();
        }
        return "/pages/ListarExpediente";
    }

    public String doCrearForm() {
        this.expediente = new Expediente();
        this.adjuntoExpediente = new ArrayList<>();
        this.indexAdjunto = 0;
        return "/pages/CrearExpediente";
    }

    public String doEditarForm(Integer id) {
        this.expediente = expedienteFacade.find(id);
        this.adjuntoExpediente = new ArrayList<>();
        this.indexAdjunto = 0;
        return "/pages/CrearExpediente";
    }

    public String doBorrar(Integer id) {
        this.expediente = expedienteFacade.find(id);
        persist(PersistAction.DELETE);
        return doListarForm();
    }

    public String doBorrarAdjunto(Integer id) {
        try {
            ExpedienteAdjunto ea = expedienteAdjuntoFacade.find(id);
            String name = ea.getNombreArchivo();
            Boolean resultado = JSFutil.deleteFileFromDisk(JSFutil.folderExpediente + ea.getIdExpedienteAdjunto() + "-" + ea.getNombreArchivo());
            if (!resultado) {
                JSFutil.addMessage("Pero no se ha podido procesar el adjunto debido a un error interno en el procesamiento", JSFutil.StatusMessage.WARNING);
            } else {
                //Solo se borra el registro si el archivo existe fisicamente en el servidor
                expedienteAdjuntoFacade.remove(ea);
                JSFutil.addMessage("El Adjunto #" + name + "# ha sido eliminado.", JSFutil.StatusMessage.INFORMATION);
            }
            this.expediente = expedienteFacade.find(this.expediente.getIdExpediente());
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
        return "";
    }

    public String doRefrescar() {
        this.listaExpediente = expedienteFacade.findExpediente("");
        if (this.listaExpediente.isEmpty()) {
            JSFutil.addMessage("No hay resultados...", JSFutil.StatusMessage.WARNING);
        } else {
            JSFutil.addMessage(this.listaExpediente.size() + " registros recuperados", JSFutil.StatusMessage.INFORMATION);
        }
        return "";
    }

    public String doBuscar() {
        if (this.criterio.isEmpty()) {
            JSFutil.addMessage("No hay criterios para buscar...", JSFutil.StatusMessage.WARNING);
            return "";
        }
        this.listaExpediente = expedienteFacade.findExpediente(this.criterio);
        if (this.listaExpediente.isEmpty()) {
            JSFutil.addMessage("No hay resultados...", JSFutil.StatusMessage.WARNING);
        } else {
            JSFutil.addMessage(this.listaExpediente.size() + " registros recuperados", JSFutil.StatusMessage.INFORMATION);
        }
        return "";
    }

    public String doGuardar() {
        if (this.expediente.getIdExpediente() != null) {
            persist(PersistAction.UPDATE);
        } else {
            persist(PersistAction.CREATE);
        }
        this.listaExpediente = expedienteFacade.findExpediente(expediente.getAcapite());
        return doListarForm();
    }

    private void persist(PersistAction persistAction) {
        try {
            if (persistAction.compareTo(PersistAction.CREATE) == 0) {
                expedienteFacade.create(expediente);
                if (this.adjuntoExpediente.size() > 0) {
                    ExpedienteAdjunto ap;
                    for (UploadedFile uf : this.adjuntoExpediente) {
                        ap = new ExpedienteAdjunto();
                        //ap.setArchivo(uf.getContents());
                        ap.setTipoArchivoMime(uf.getContentType());
                        ap.setTamanhoArchivo(BigInteger.valueOf(uf.getSize()));
                        ap.setNombreArchivo(JSFutil.sanitizeFilename(uf.getFileName()));
                        ap.setIdExpediente(expediente);
                        //ap.setTipoAdjunto("PROYECTO");
                        ap.setFechaRegistro(JSFutil.getFechaHoraActual());
                        expedienteAdjuntoFacade.create(ap);
                        int resultado = JSFutil.fileToDisk(new ByteArrayInputStream(uf.getContents()), JSFutil.folderExpediente + ap.getIdExpedienteAdjunto() + "-" + JSFutil.sanitizeFilename(uf.getFileName()));
                        if (resultado != 0) {
                            JSFutil.addMessage("No se ha podido guardar el adjunto debido a un error interno en el procesamiento del archivo. Se deshace el guardado del archivo.", JSFutil.StatusMessage.ERROR);
                            expedienteAdjuntoFacade.remove(ap);
                        }
                    }
                }
            } else if (persistAction.compareTo(PersistAction.UPDATE) == 0) {
                expedienteFacade.edit(expediente);
                if (this.adjuntoExpediente.size() > 0) {
                    ExpedienteAdjunto ap;
                    for (UploadedFile uf : this.adjuntoExpediente) {
                        ap = new ExpedienteAdjunto();
                        //ap.setArchivo(uf.getContents());
                        ap.setTipoArchivoMime(uf.getContentType());
                        ap.setTamanhoArchivo(BigInteger.valueOf(uf.getSize()));
                        ap.setNombreArchivo(JSFutil.sanitizeFilename(uf.getFileName()));
                        ap.setIdExpediente(expediente);
                        //ap.setTipoAdjunto("PROYECTO");
                        ap.setFechaRegistro(JSFutil.getFechaHoraActual());
                        expedienteAdjuntoFacade.create(ap);
                        int resultado = JSFutil.fileToDisk(new ByteArrayInputStream(uf.getContents()), JSFutil.folderExpediente + ap.getIdExpedienteAdjunto() + "-" + JSFutil.sanitizeFilename(uf.getFileName()));
                        if (resultado != 0) {
                            JSFutil.addMessage("No se ha podido guardar el adjunto debido a un error interno en el procesamiento del archivo. Se deshace el guardado del archivo.", JSFutil.StatusMessage.ERROR);
                            expedienteAdjuntoFacade.remove(ap);
                        }
                    }
                }
            } else if (persistAction.compareTo(PersistAction.DELETE) == 0) {
                expedienteFacade.remove(expediente);
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

    public void handleFileUpload(FileUploadEvent event) {
        //LOG.log(Level.INFO, "Agregado el archivo {0}", event.getFile().getFileName());
        this.adjuntoExpediente.add(event.getFile());
    }

    public void doPreparePreviewUpload(Integer ind) {
        this.indexAdjunto = ind;
    }

    public StreamedContent expedientePreview() {
        //System.out.println("Indice... "+indexAdjunto);
        try {
            if (this.getAdjuntoExpediente().size() > 0) {
                return commonController.downloadAdjuntoTMP(this.getAdjuntoExpediente().get(indexAdjunto));
            } else {
                return null;
            }
        } catch (IOException ex) {
            LOG.log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
