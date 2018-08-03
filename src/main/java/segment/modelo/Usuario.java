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
@Table(name = "usuario")
@NamedQueries({
    @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u")})
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_usuario")
    private Integer idUsuario;
    @Column(name = "activo")
    private Boolean activo;
    @Size(max = 255)
    @Column(name = "apellidos")
    private String apellidos;
    @Size(max = 255)
    @Column(name = "contrasenha")
    private String contrasenha;
    @Size(max = 255)
    @Column(name = "cuenta")
    private String cuenta;
    @Column(name = "id_set_preferences")
    private Integer idSetPreferences;
    @Column(name = "login_externo")
    private Boolean loginExterno;
    @Size(max = 255)
    @Column(name = "nombres")
    private String nombres;
    @OneToMany(mappedBy = "idUsuario")
    private List<Expediente> expedienteList;
    @OneToMany(mappedBy = "idUsuarioTramite")
    private List<Tramitacion> tramitacionList;
    @OneToMany(mappedBy = "idUsuarioRecibido")
    private List<Tramitacion> tramitacionList1;
    @OneToMany(mappedBy = "idUsuarioArchivado")
    private List<Tramitacion> tramitacionList2;
    @JoinColumn(name = "id_dependencia", referencedColumnName = "id_dependencia")
    @ManyToOne
    private Dependencia idDependencia;
    @JoinColumn(name = "id_rol", referencedColumnName = "id_rol")
    @ManyToOne
    private Rol idRol;

    public Usuario() {
    }

    public Usuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getContrasenha() {
        return contrasenha;
    }

    public void setContrasenha(String contrasenha) {
        this.contrasenha = contrasenha;
    }

    public String getCuenta() {
        return cuenta;
    }

    public void setCuenta(String cuenta) {
        this.cuenta = cuenta;
    }

    public Integer getIdSetPreferences() {
        return idSetPreferences;
    }

    public void setIdSetPreferences(Integer idSetPreferences) {
        this.idSetPreferences = idSetPreferences;
    }

    public Boolean getLoginExterno() {
        return loginExterno;
    }

    public void setLoginExterno(Boolean loginExterno) {
        this.loginExterno = loginExterno;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public List<Expediente> getExpedienteList() {
        return expedienteList;
    }

    public void setExpedienteList(List<Expediente> expedienteList) {
        this.expedienteList = expedienteList;
    }

    public List<Tramitacion> getTramitacionList() {
        return tramitacionList;
    }

    public void setTramitacionList(List<Tramitacion> tramitacionList) {
        this.tramitacionList = tramitacionList;
    }

    public List<Tramitacion> getTramitacionList1() {
        return tramitacionList1;
    }

    public void setTramitacionList1(List<Tramitacion> tramitacionList1) {
        this.tramitacionList1 = tramitacionList1;
    }

    public List<Tramitacion> getTramitacionList2() {
        return tramitacionList2;
    }

    public void setTramitacionList2(List<Tramitacion> tramitacionList2) {
        this.tramitacionList2 = tramitacionList2;
    }

    public Dependencia getIdDependencia() {
        return idDependencia;
    }

    public void setIdDependencia(Dependencia idDependencia) {
        this.idDependencia = idDependencia;
    }

    public Rol getIdRol() {
        return idRol;
    }

    public void setIdRol(Rol idRol) {
        this.idRol = idRol;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUsuario != null ? idUsuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.idUsuario == null && other.idUsuario != null) || (this.idUsuario != null && !this.idUsuario.equals(other.idUsuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.nombres + " " + this.apellidos;
    }

}
