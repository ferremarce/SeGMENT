/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package segment.modelo;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author jmferreira
 */
@Entity
@Table(name = "dependencia")
@NamedQueries({
    @NamedQuery(name = "Dependencia.findAll", query = "SELECT d FROM Dependencia d")})
public class Dependencia implements Serializable {

    @OneToMany(mappedBy = "idDependencia")
    private List<Tramitacion> tramitacionList;

    @OneToMany(mappedBy = "idDependencia")
    private List<Usuario> usuarioList;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_dependencia")
    private Integer idDependencia;
    @Column(name = "activo")
    private Boolean activo;
    @Size(max = 255)
    @Column(name = "descripcion_dependencia")
    private String descripcionDependencia;
    @Column(name = "orden")
    private Integer orden;

    public Dependencia() {
    }

    public Dependencia(Integer idDependencia) {
        this.idDependencia = idDependencia;
    }

    public Integer getIdDependencia() {
        return idDependencia;
    }

    public void setIdDependencia(Integer idDependencia) {
        this.idDependencia = idDependencia;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public String getDescripcionDependencia() {
        return descripcionDependencia;
    }

    public void setDescripcionDependencia(String descripcionDependencia) {
        this.descripcionDependencia = descripcionDependencia;
    }

    public Integer getOrden() {
        return orden;
    }

    public void setOrden(Integer orden) {
        this.orden = orden;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDependencia != null ? idDependencia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Dependencia)) {
            return false;
        }
        Dependencia other = (Dependencia) object;
        if ((this.idDependencia == null && other.idDependencia != null) || (this.idDependencia != null && !this.idDependencia.equals(other.idDependencia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.getDescripcionDependencia();
    }

    public List<Usuario> getUsuarioList() {
        return usuarioList;
    }

    public void setUsuarioList(List<Usuario> usuarioList) {
        this.usuarioList = usuarioList;
    }

    public List<Tramitacion> getTramitacionList() {
        return tramitacionList;
    }

    public void setTramitacionList(List<Tramitacion> tramitacionList) {
        this.tramitacionList = tramitacionList;
    }

}
