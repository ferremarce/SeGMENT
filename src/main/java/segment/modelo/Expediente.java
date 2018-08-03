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
import javax.persistence.CascadeType;
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
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

/**
 *
 * @author jmferreira
 */
@Entity
@Table(name = "expediente")
@NamedQueries({
    @NamedQuery(name = "Expediente.findAll", query = "SELECT e FROM Expediente e")})
public class Expediente implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_expediente")
    private Integer idExpediente;
    @Size(max = 3000)
    @Column(name = "acapite")
    private String acapite;
    @Column(name = "cerrado")
    private Boolean cerrado;
    @Column(name = "fecha_entrada")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaEntrada;
    @Column(name = "fecha_expediente")
    @Temporal(TemporalType.DATE)
    private Date fechaExpediente;
    @Column(name = "fecha_registro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;
    @Size(max = 255)
    @Column(name = "nro_expediente")
    private String nroExpediente;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "observacion")
    private String observacion;
    @Size(max = 255)
    @Column(name = "remitente")
    private String remitente;
    @OneToMany(mappedBy = "idExpediente", cascade = CascadeType.REMOVE)
    private List<ExpedienteAdjunto> expedienteAdjuntoList;
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    @ManyToOne
    private Usuario idUsuario;
    @OneToMany(mappedBy = "idExpediente")
    @OrderBy("fechaRegistro ASC")
    private List<Tramitacion> tramitacionList;
    @JoinColumn(name = "id_tipo_expediente", referencedColumnName = "id_sub_tipo")
    @ManyToOne
    private SubTipo idTipoExpediente;
    @JoinColumn(name = "id_clasificador", referencedColumnName = "id_clasificador")
    @ManyToOne
    private Clasificador idClasificador;
    @JoinColumn(name = "id_estado_expediente", referencedColumnName = "id_sub_tipo")
    @ManyToOne
    private SubTipo idEstadoExpediente;


    public Expediente() {
    }

    public Expediente(Integer idExpediente) {
        this.idExpediente = idExpediente;
    }

    public Integer getIdExpediente() {
        return idExpediente;
    }

    public void setIdExpediente(Integer idExpediente) {
        this.idExpediente = idExpediente;
    }

    public String getAcapite() {
        return acapite;
    }

    public void setAcapite(String acapite) {
        this.acapite = acapite;
    }

    public Boolean getCerrado() {
        return cerrado;
    }

    public void setCerrado(Boolean cerrado) {
        this.cerrado = cerrado;
    }

    public Date getFechaEntrada() {
        return fechaEntrada;
    }

    public void setFechaEntrada(Date fechaEntrada) {
        this.fechaEntrada = fechaEntrada;
    }

    public Date getFechaExpediente() {
        return fechaExpediente;
    }

    public void setFechaExpediente(Date fechaExpediente) {
        this.fechaExpediente = fechaExpediente;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getNroExpediente() {
        return nroExpediente;
    }

    public void setNroExpediente(String nroExpediente) {
        this.nroExpediente = nroExpediente;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public String getRemitente() {
        return remitente;
    }

    public void setRemitente(String remitente) {
        this.remitente = remitente;
    }

    public List<ExpedienteAdjunto> getExpedienteAdjuntoList() {
        return expedienteAdjuntoList;
    }

    public void setExpedienteAdjuntoList(List<ExpedienteAdjunto> expedienteAdjuntoList) {
        this.expedienteAdjuntoList = expedienteAdjuntoList;
    }

    public Usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
    }

    public List<Tramitacion> getTramitacionList() {
        return tramitacionList;
    }

    public void setTramitacionList(List<Tramitacion> tramitacionList) {
        this.tramitacionList = tramitacionList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idExpediente != null ? idExpediente.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Expediente)) {
            return false;
        }
        Expediente other = (Expediente) object;
        if ((this.idExpediente == null && other.idExpediente != null) || (this.idExpediente != null && !this.idExpediente.equals(other.idExpediente))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "segment.modelo.Expediente[ idExpediente=" + idExpediente + " ]";
    }

    public SubTipo getIdTipoExpediente() {
        return idTipoExpediente;
    }

    public void setIdTipoExpediente(SubTipo idTipoExpediente) {
        this.idTipoExpediente = idTipoExpediente;
    }

    public Clasificador getIdClasificador() {
        return idClasificador;
    }

    public void setIdClasificador(Clasificador idClasificador) {
        this.idClasificador = idClasificador;
    }

    public SubTipo getIdEstadoExpediente() {
        return idEstadoExpediente;
    }

    public void setIdEstadoExpediente(SubTipo idEstadoExpediente) {
        this.idEstadoExpediente = idEstadoExpediente;
    }
    
}
