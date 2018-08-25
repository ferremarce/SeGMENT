/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package segment.controller;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.inject.Inject;
import segment.fachada.ExpedienteFacade;
import segment.modelo.Expediente;
import segment.reportes.FuenteReporte;
import segment.reportes.JasperManager;
import util.JSFutil;

/**
 *
 * @author jmferreira
 */
@Named(value = "ReporteController")
@SessionScoped
public class ReporteController implements Serializable {

    @Inject
    ExpedienteFacade expedienteFacade;
    private Date fechaDesde;
    private Date fechaHasta;
    private String tipoReporte = "PDF";
    private String idFuenteReporte = "1";

    /**
     * Creates a new instance of ReporteController
     */
    public ReporteController() {
    }

    public Date getFechaDesde() {
        return fechaDesde;
    }

    public void setFechaDesde(Date fechaDesde) {
        this.fechaDesde = fechaDesde;
    }

    public Date getFechaHasta() {
        return fechaHasta;
    }

    public void setFechaHasta(Date fechaHasta) {
        this.fechaHasta = fechaHasta;
    }

    public void generarReporte(List<?> dataList) {
        JasperManager jm = new JasperManager();
        FuenteReporte fr = new FuenteReporte(Integer.valueOf(idFuenteReporte));
        String reportSource = jm.getPathweb() + "reportes/template/" + fr.getNombreReporte();
        if (dataList.size() > 0) {
            jm.generarReporte(reportSource, tipoReporte, dataList);
        } else {
            JSFutil.addMessage("No hay datos para generar el reporte", JSFutil.StatusMessage.WARNING);
        }

    }

    public String doExpedienteReportForm() {
        return "/reportes/rptExpediente";
    }

    public void doExpedienteReport() {
        this.tipoReporte = "PDF";
        this.idFuenteReporte = "1";
        List<Expediente> lista = expedienteFacade.findAll();
        this.generarReporte(lista);
    }
    }
