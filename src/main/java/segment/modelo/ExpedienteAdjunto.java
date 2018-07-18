/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package segment.modelo;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

/**
 *
 * @author jmferreira
 */
@Entity
@Table(name = "expediente_adjunto")
@NamedQueries({
    @NamedQuery(name = "ExpedienteAdjunto.findAll", query = "SELECT e FROM ExpedienteAdjunto e")})
public class ExpedienteAdjunto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_expediente_adjunto")
    private Integer idExpedienteAdjunto;
    @Lob
    @Column(name = "ARCHIVO")
    private byte[] archivo;
    @Column(name = "FECHA_REGISTRO")
    @Temporal(TemporalType.DATE)
    private Date fechaRegistro;
    @Size(max = 255)
    @Column(name = "NOMBRE_ARCHIVO")
    private String nombreArchivo;
    @Column(name = "TAMANHO_ARCHIVO")
    private BigInteger tamanhoArchivo;
    @Size(max = 255)
    @Column(name = "TIPO_ADJUNTO")
    private String tipoAdjunto;
    @Size(max = 255)
    @Column(name = "TIPO_ARCHIVO_MIME")
    private String tipoArchivoMime;
    @JoinColumn(name = "id_expediente", referencedColumnName = "id_expediente")
    @ManyToOne
    private Expediente idExpediente;

    public ExpedienteAdjunto() {
    }

    public ExpedienteAdjunto(Integer idExpedienteAdjunto) {
        this.idExpedienteAdjunto = idExpedienteAdjunto;
    }

    public Integer getIdExpedienteAdjunto() {
        return idExpedienteAdjunto;
    }

    public void setIdExpedienteAdjunto(Integer idExpedienteAdjunto) {
        this.idExpedienteAdjunto = idExpedienteAdjunto;
    }

    public byte[] getArchivo() {
        return archivo;
    }

    public void setArchivo(byte[] archivo) {
        this.archivo = archivo;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    public BigInteger getTamanhoArchivo() {
        return tamanhoArchivo;
    }

    public void setTamanhoArchivo(BigInteger tamanhoArchivo) {
        this.tamanhoArchivo = tamanhoArchivo;
    }

    public String getTipoAdjunto() {
        return tipoAdjunto;
    }

    public void setTipoAdjunto(String tipoAdjunto) {
        this.tipoAdjunto = tipoAdjunto;
    }

    public String getTipoArchivoMime() {
        return tipoArchivoMime;
    }

    public void setTipoArchivoMime(String tipoArchivoMime) {
        this.tipoArchivoMime = tipoArchivoMime;
    }

    public Expediente getIdExpediente() {
        return idExpediente;
    }

    public void setIdExpediente(Expediente idExpediente) {
        this.idExpediente = idExpediente;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idExpedienteAdjunto != null ? idExpedienteAdjunto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ExpedienteAdjunto)) {
            return false;
        }
        ExpedienteAdjunto other = (ExpedienteAdjunto) object;
        if ((this.idExpedienteAdjunto == null && other.idExpedienteAdjunto != null) || (this.idExpedienteAdjunto != null && !this.idExpedienteAdjunto.equals(other.idExpedienteAdjunto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "segment.modelo.ExpedienteAdjunto[ idExpedienteAdjunto=" + idExpedienteAdjunto + " ]";
    }
    
}
