/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package segment.controller;

import java.io.IOException;
import java.io.InputStream;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.Date;
import java.util.TimeZone;
import java.util.logging.Logger;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;
import util.JSFutil;

/**
 *
 * @author root
 */
@Named(value = "CommonController")
@SessionScoped
public class CommonController implements Serializable {

    private static final Logger LOG = Logger.getLogger(CommonController.class.getName());

    private Boolean mobileMode = true;
    String nuevaURL;

    /**
     * Creates a new instance of MainController
     */
    public CommonController() {
    }

    public String getNuevaURL() {
        return nuevaURL;
    }

    public void setNuevaURL(String nuevaURL) {
        this.nuevaURL = nuevaURL;
    }

    public Boolean getMobileMode() {
        return mobileMode;
    }

    public void setMobileMode(Boolean mobileMode) {
        this.mobileMode = mobileMode;
    }

    public Date getHoraActualDate() {
        return JSFutil.getFechaHoraActual();
    }

    public String acotarCadena(String cadena, Integer longitud) {
        //Integer longitud = 20;
        if (cadena.length() > longitud) {
            return cadena.substring(0, longitud) + "...";
        } else {
            return cadena;
        }
    }

    public TimeZone getMyTimeZone() {
        return JSFutil.getMyTimeZone();
    }

    public String getServerURL() {
        //System.out.print("Web: "+JSFutil.getAbsoluteApplicationUrl());
        return JSFutil.getAbsoluteApplicationUrl();
    }

    public String getServerURLDownload() {
        return JSFutil.getAbsoluteApplicationUrl() + "/descarga";
    }

    public String getCurrentView() {
        return JSFutil.getAbsoluteApplicationUrl() + JSFutil.getCurrentPrettyFaceView();
    }

    public String cleanHtmlText(String texto) {
        return JSFutil.html2text(texto);
    }

    public void cambiarModo() {
        this.mobileMode = !this.mobileMode;
    }

    public StreamedContent downloadAdjuntoTMP(UploadedFile adjunto) throws IOException {
        //System.out.println("Invocado...");
        if (adjunto != null) {
            InputStream stream = adjunto.getInputstream();
            StreamedContent file = new DefaultStreamedContent(stream, adjunto.getContentType(), adjunto.getFileName());
            return file;
        } else {
            return null;
        }
    }

}
