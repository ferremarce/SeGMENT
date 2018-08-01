/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package segment.modelo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

/**
 *
 * @author jmferreira
 */
@Entity
@Table(name = "tramitacion")
@NamedQueries({
    @NamedQuery(name = "Tramitacion.findAll", query = "SELECT t FROM Tramitacion t")})
public class Tramitacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tramitacion")
    private Integer idTramitacion;
    @Size(max = 255)
    @Column(name = "descripcion_tramite")
    private String descripcionTramite;
    @Column(name = "fecha_registro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;
    @Column(name = "fecha_tramite")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaTramite;
    @Size(max = 255)
    @Column(name = "observacion")
    private String observacion;
    @Size(max = 255)
    @Column(name = "remitido_a")
    private String remitidoA;
    @Size(max = 45)
    @Column(name = "fecha_confirmado")
    private String fechaConfirmado;
    @OneToMany(mappedBy = "idTramitacion")
    private List<TramitacionAdjunto> tramitacionAdjuntoList;
    @JoinColumn(name = "id_estado_tramite", referencedColumnName = "id_sub_tipo")
    @ManyToOne
    private SubTipo idEstadoTramite;
    @JoinColumn(name = "id_expediente", referencedColumnName = "id_expediente")
    @ManyToOne
    private Expediente idExpediente;
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    @ManyToOne
    private Usuario idUsuario;
    @JoinColumn(name = "id_dependencia", referencedColumnName = "id_dependencia")
    @ManyToOne
    private Dependencia idDependencia;
    @OneToMany(mappedBy = "idTramitacionAnterior")
    private List<Tramitacion> tramitacionList;
    @JoinColumn(name = "id_tramitacion_anterior", referencedColumnName = "id_tramitacion")
    @ManyToOne
    private Tramitacion idTramitacionAnterior;

    public Tramitacion() {
    }

    public Tramitacion(Integer idTramitacion) {
        this.idTramitacion = idTramitacion;
    }

    public Integer getIdTramitacion() {
        return idTramitacion;
    }

    public void setIdTramitacion(Integer idTramitacion) {
        this.idTramitacion = idTramitacion;
    }

    public String getDescripcionTramite() {
        return descripcionTramite;
    }

    public void setDescripcionTramite(String descripcionTramite) {
        this.descripcionTramite = descripcionTramite;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Date getFechaTramite() {
        return fechaTramite;
    }

    public void setFechaTramite(Date fechaTramite) {
        this.fechaTramite = fechaTramite;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public String getRemitidoA() {
        return remitidoA;
    }

    public void setRemitidoA(String remitidoA) {
        this.remitidoA = remitidoA;
    }

    public String getFechaConfirmado() {
        return fechaConfirmado;
    }

    public void setFechaConfirmado(String fechaConfirmado) {
        this.fechaConfirmado = fechaConfirmado;
    }

    public List<TramitacionAdjunto> getTramitacionAdjuntoList() {
        return tramitacionAdjuntoList;
    }

    public void setTramitacionAdjuntoList(List<TramitacionAdjunto> tramitacionAdjuntoList) {
        this.tramitacionAdjuntoList = tramitacionAdjuntoList;
    }

    public SubTipo getIdEstadoTramite() {
        return idEstadoTramite;
    }

    public void setIdEstadoTramite(SubTipo idEstadoTramite) {
        this.idEstadoTramite = idEstadoTramite;
    }

    public Expediente getIdExpediente() {
        return idExpediente;
    }

    public void setIdExpediente(Expediente idExpediente) {
        this.idExpediente = idExpediente;
    }

    public Usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Dependencia getIdDependencia() {
        return idDependencia;
    }

    public void setIdDependencia(Dependencia idDependencia) {
        this.idDependencia = idDependencia;
    }

    public List<Tramitacion> getTramitacionList() {
        return tramitacionList;
    }

    public void setTramitacionList(List<Tramitacion> tramitacionList) {
        this.tramitacionList = tramitacionList;
    }

    public Tramitacion getIdTramitacionAnterior() {
        return idTramitacionAnterior;
    }

    public void setIdTramitacionAnterior(Tramitacion idTramitacionAnterior) {
        this.idTramitacionAnterior = idTramitacionAnterior;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTramitacion != null ? idTramitacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tramitacion)) {
            return false;
        }
        Tramitacion other = (Tramitacion) object;
        if ((this.idTramitacion == null && other.idTramitacion != null) || (this.idTramitacion != null && !this.idTramitacion.equals(other.idTramitacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "segment.modelo.Tramitacion[ idTramitacion=" + idTramitacion + " ]";
    }
    
}
