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
@Table(name = "tree")
@NamedQueries({
    @NamedQuery(name = "Tree.findAll", query = "SELECT t FROM Tree t")})
public class Tree implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tree")
    private Integer idTree;
    @Size(max = 255)
    @Column(name = "descripcion")
    private String descripcion;
    @Size(max = 255)
    @Column(name = "mapeo_numerico")
    private String mapeoNumerico;
    @OneToMany(mappedBy = "idPadre")
    private List<Tree> treeList;
    @JoinColumn(name = "id_padre", referencedColumnName = "id_tree")
    @ManyToOne
    private Tree idPadre;

    public Tree() {
    }

    public Tree(Integer idTree) {
        this.idTree = idTree;
    }

    public Integer getIdTree() {
        return idTree;
    }

    public void setIdTree(Integer idTree) {
        this.idTree = idTree;
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

    public List<Tree> getTreeList() {
        return treeList;
    }

    public void setTreeList(List<Tree> treeList) {
        this.treeList = treeList;
    }

    public Tree getIdPadre() {
        return idPadre;
    }

    public void setIdPadre(Tree idPadre) {
        this.idPadre = idPadre;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTree != null ? idTree.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tree)) {
            return false;
        }
        Tree other = (Tree) object;
        if ((this.idTree == null && other.idTree != null) || (this.idTree != null && !this.idTree.equals(other.idTree))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "segment.modelo.Tree[ idTree=" + idTree + " ]";
    }
    
}
