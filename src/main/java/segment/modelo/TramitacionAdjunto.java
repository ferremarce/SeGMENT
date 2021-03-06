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
@Table(name = "tramitacion_adjunto")
@NamedQueries({
    @NamedQuery(name = "TramitacionAdjunto.findAll", query = "SELECT t FROM TramitacionAdjunto t")})
public class TramitacionAdjunto implements Serializable {

    @Lob
    @Column(name = "archivo")
    private byte[] archivo;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tramitacion_adjunto")
    private Integer idTramitacionAdjunto;
    @Column(name = "fecha_registro")
    @Temporal(TemporalType.DATE)
    private Date fechaRegistro;
    @Size(max = 255)
    @Column(name = "nombre_archivo")
    private String nombreArchivo;
    @Column(name = "tamanho_archivo")
    private BigInteger tamanhoArchivo;
    @Size(max = 255)
    @Column(name = "tipo_adjunto")
    private String tipoAdjunto;
    @Size(max = 255)
    @Column(name = "tipo_archivo_mime")
    private String tipoArchivoMime;
    @JoinColumn(name = "id_tramitacion", referencedColumnName = "id_tramitacion")
    @ManyToOne
    private Tramitacion idTramitacion;

    public TramitacionAdjunto() {
    }

    public TramitacionAdjunto(Integer idTramitacionAdjunto) {
        this.idTramitacionAdjunto = idTramitacionAdjunto;
    }

    public Integer getIdTramitacionAdjunto() {
        return idTramitacionAdjunto;
    }

    public void setIdTramitacionAdjunto(Integer idTramitacionAdjunto) {
        this.idTramitacionAdjunto = idTramitacionAdjunto;
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

    public Tramitacion getIdTramitacion() {
        return idTramitacion;
    }

    public void setIdTramitacion(Tramitacion idTramitacion) {
        this.idTramitacion = idTramitacion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTramitacionAdjunto != null ? idTramitacionAdjunto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TramitacionAdjunto)) {
            return false;
        }
        TramitacionAdjunto other = (TramitacionAdjunto) object;
        if ((this.idTramitacionAdjunto == null && other.idTramitacionAdjunto != null) || (this.idTramitacionAdjunto != null && !this.idTramitacionAdjunto.equals(other.idTramitacionAdjunto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "segment.modelo.TramitacionAdjunto[ idTramitacionAdjunto=" + idTramitacionAdjunto + " ]";
    }

    public String toNameDownload() {
        String extension = "";
        int i = this.getNombreArchivo().lastIndexOf('.');
        if (i > 0) {
            extension = this.getNombreArchivo().substring(i + 1);
        }
        if (extension.length() <= 0) {
            extension = "doc";
        }
        //return this.getTipoAdjunto() + "-" + this.idProyecto.getExpedienteCamara() + "." + extension;
        return this.getNombreArchivo();
    }

    public String toURLDownload() {
        return "tramitacion-" + this.idTramitacionAdjunto;
    }


    public String toTamanho() {
        if (this.tamanhoArchivo.intValue() < 1024) {
            return tamanhoArchivo + "B";
        } else if (this.tamanhoArchivo.intValue() < 1048576) {
            return (tamanhoArchivo.intValue() / 1024) + "KB";
        } else {
            return (tamanhoArchivo.intValue() / 1048576) + "MB";
        }
    }

    public byte[] getArchivo() {
        return archivo;
    }

    public void setArchivo(byte[] archivo) {
        this.archivo = archivo;
    }

}
