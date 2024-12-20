/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author IVSTOYKO
 */
@Entity
@Table(name = "usuario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u")
    , @NamedQuery(name = "Usuario.findByIdUsuario", query = "SELECT u FROM Usuario u WHERE u.idUsuario = :idUsuario")
    , @NamedQuery(name = "Usuario.findByNombre", query = "SELECT u FROM Usuario u WHERE u.nombre = :nombre")
    , @NamedQuery(name = "Usuario.findByPassword", query = "SELECT u FROM Usuario u WHERE u.password = :password")
    , @NamedQuery(name = "Usuario.findByNVictorias", query = "SELECT u FROM Usuario u WHERE u.nVictorias = :nVictorias")
    , @NamedQuery(name = "Usuario.findByNDerrotas", query = "SELECT u FROM Usuario u WHERE u.nDerrotas = :nDerrotas")
    , @NamedQuery(name = "Usuario.findByNEmpates", query = "SELECT u FROM Usuario u WHERE u.nEmpates = :nEmpates")
    , @NamedQuery(name = "Usuario.findByEmail", query = "SELECT u FROM Usuario u WHERE u.email = :email")})
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    ////
    @Column(name = "idUsuario")
    private Integer idUsuario;
    @Basic(optional = false)
    ////
    ////@Size(min = 1, max = 45)
    @Column(name = "Nombre")
    private String nombre;
    @Basic(optional = false)
    ////
    ////@Size(min = 1, max = 45)
    @Column(name = "Password")
    private String password;
    @Column(name = "N_Victorias")
    private Integer nVictorias;
    @Column(name = "N_Derrotas")
    private Integer nDerrotas;
    @Column(name = "N_Empates")
    private Integer nEmpates;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    ////
    ////@Size(min = 1, max = 45)
    @Column(name = "email")
    private String email;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "usuario")
    private Admin admin;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "usuarioidUsuario1")
    private Partida partida;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "usuarioidUsuario2")
    private Partida partida1;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "usuario")
    private Player player;

    public Usuario() {
    }

    public Usuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Usuario(Integer idUsuario, String nombre, String password, String email) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.password = password;
        this.email = email;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getNVictorias() {
        return nVictorias;
    }

    public void setNVictorias(Integer nVictorias) {
        this.nVictorias = nVictorias;
    }

    public Integer getNDerrotas() {
        return nDerrotas;
    }

    public void setNDerrotas(Integer nDerrotas) {
        this.nDerrotas = nDerrotas;
    }

    public Integer getNEmpates() {
        return nEmpates;
    }

    public void setNEmpates(Integer nEmpates) {
        this.nEmpates = nEmpates;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public Partida getPartida() {
        return partida;
    }

    public void setPartida(Partida partida) {
        this.partida = partida;
    }

    public Partida getPartida1() {
        return partida1;
    }

    public void setPartida1(Partida partida1) {
        this.partida1 = partida1;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
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
        return "jpa.entities.Usuario[ idUsuario=" + idUsuario + " ]";
    }
    
}
