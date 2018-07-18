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
@Table(name = "tipo_tree")
@NamedQueries({
    @NamedQuery(name = "TipoTree.findAll", query = "SELECT t FROM TipoTree t")})
public class TipoTree implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 255)
    @Column(name = "descripcion")
    private String descripcion;
    @Size(max = 255)
    @Column(name = "mapeo_numerico")
    private String mapeoNumerico;
    @OneToMany(mappedBy = "idPadre")
    private List<TipoTree> tipoTreeList;
    @JoinColumn(name = "id_padre", referencedColumnName = "id")
    @ManyToOne
    private TipoTree idPadre;

    public TipoTree() {
    }

    public TipoTree(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getMapeoNumerico() {
        return mapeoNumerico;
    }

    public void setMapeoNumerico(String mapeoNumerico) {
        this.mapeoNumerico = mapeoNumerico;
    }

    public List<TipoTree> getTipoTreeList() {
        return tipoTreeList;
    }

    public void setTipoTreeList(List<TipoTree> tipoTreeList) {
        this.tipoTreeList = tipoTreeList;
    }

    public TipoTree getIdPadre() {
        return idPadre;
    }

    public void setIdPadre(TipoTree idPadre) {
        this.idPadre = idPadre;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoTree)) {
            return false;
        }
        TipoTree other = (TipoTree) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "segment.modelo.TipoTree[ id=" + id + " ]";
    }
    
}
