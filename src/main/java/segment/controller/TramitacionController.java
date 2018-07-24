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
import java.util.logging.Logger;
import javax.inject.Inject;
import segment.fachada.TramitacionFacade;
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

    /**
     * Creates a new instance of TramitacionFacade
     */
    public TramitacionController() {
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

    public Integer doCantidadEntrada() {
        return tramitacionFacade.findAllTramitacion(6).size();
    }

    public Integer doCantidadSalida() {
        return tramitacionFacade.findAllTramitacion(7).size();
    }

    public String doMisTareasForm() {
        this.listaTramitacion = new ArrayList<>();
        return "/pages/MisTareas";
    }
}
