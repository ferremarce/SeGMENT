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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "sub_tipo")
@NamedQueries({
    @NamedQuery(name = "SubTipo.findAll", query = "SELECT s FROM SubTipo s")})
public class SubTipo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_sub_tipo")
    private Integer idSubTipo;
    @Size(max = 255)
    @Column(name = "descripcion_sub_tipo")
    private String descripcionSubTipo;
    @Column(name = "orden")
    private Integer orden;
    @JoinColumn(name = "id_tipo", referencedColumnName = "id_tipo")
    @ManyToOne
    private Tipo idTipo;
    @OneToMany(mappedBy = "idTipoExpediente")
    private List<Expediente> expedienteList;
    @OneToMany(mappedBy = "idPadre")
    private List<SubTipo> subTipoList;
    @JoinColumn(name = "id_padre", referencedColumnName = "id_sub_tipo")
    @ManyToOne
    private SubTipo idPadre;
    @OneToMany(mappedBy = "idEstadoTramite")
    private List<Tramitacion> tramitacionList;
    @Size(max = 50)
    @Column(name = "acronimo")
    private String acronimo;

    public SubTipo() {
    }

    public SubTipo(Integer idSubTipo) {
        this.idSubTipo = idSubTipo;
    }

    public Integer getIdSubTipo() {
        return idSubTipo;
    }

    public void setIdSubTipo(Integer idSubTipo) {
        this.idSubTipo = idSubTipo;
    }

    public String getDescripcionSubTipo() {
        return descripcionSubTipo;
    }

    public void setDescripcionSubTipo(String descripcionSubTipo) {
        this.descripcionSubTipo = descripcionSubTipo;
    }

    public Integer getOrden() {
        return orden;
    }

    public void setOrden(Integer orden) {
        this.orden = orden;
    }

    public Tipo getIdTipo() {
        return idTipo;
    }

    public void setIdTipo(Tipo idTipo) {
        this.idTipo = idTipo;
    }

    public String getAcronimo() {
        return acronimo;
    }

    public void setAcronimo(String acronimo) {
        this.acronimo = acronimo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idSubTipo != null ? idSubTipo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SubTipo)) {
            return false;
        }
        SubTipo other = (SubTipo) object;
        if ((this.idSubTipo == null && other.idSubTipo != null) || (this.idSubTipo != null && !this.idSubTipo.equals(other.idSubTipo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.getDescripcionSubTipo();
    }

    public List<Expediente> getExpedienteList() {
        return expedienteList;
    }

    public void setExpedienteList(List<Expediente> expedienteList) {
        this.expedienteList = expedienteList;
    }

    public List<SubTipo> getSubTipoList() {
        return subTipoList;
    }

    public void setSubTipoList(List<SubTipo> subTipoList) {
        this.subTipoList = subTipoList;
    }

    public SubTipo getIdPadre() {
        return idPadre;
    }

    public void setIdPadre(SubTipo idPadre) {
        this.idPadre = idPadre;
    }

    public List<Tramitacion> getTramitacionList() {
        return tramitacionList;
    }

    public void setTramitacionList(List<Tramitacion> tramitacionList) {
        this.tramitacionList = tramitacionList;
    }

}
