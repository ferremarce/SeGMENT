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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author jmferreira
 */
@Entity
@Table(name = "clasificador")
public class Clasificador implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_clasificador")
    private Integer idClasificador;
    @Size(max = 255)
    @Column(name = "descripcion")
    private String descripcion;
    @Size(max = 255)
    @Column(name = "mapeo_numerico")
    private String mapeoNumerico;
    @OneToMany(mappedBy = "idPadre")
    private List<Clasificador> ClasificadorList;
    @JoinColumn(name = "id_padre", referencedColumnName = "id_Clasificador")
    @ManyToOne
    private Clasificador idPadre;

    public Clasificador() {
    }

    public Clasificador(Integer idClasificador) {
        this.idClasificador = idClasificador;
    }

    public Integer getIdClasificador() {
        return idClasificador;
    }

    public void setIdClasificador(Integer idClasificador) {
        this.idClasificador = idClasificador;
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

    public List<Clasificador> getClasificadorList() {
        return ClasificadorList;
    }

    public void setClasificadorList(List<Clasificador> ClasificadorList) {
        this.ClasificadorList = ClasificadorList;
    }

   
    public Clasificador getIdPadre() {
        return idPadre;
    }

    public void setIdPadre(Clasificador idPadre) {
        this.idPadre = idPadre;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idClasificador != null ? idClasificador.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Clasificador)) {
            return false;
        }
        Clasificador other = (Clasificador) object;
        if ((this.idClasificador == null && other.idClasificador != null) || (this.idClasificador != null && !this.idClasificador.equals(other.idClasificador))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "segment.modelo.Clasificador[ Clasificador=" + idClasificador + " ]";
    }

}
