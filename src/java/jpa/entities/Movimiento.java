/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author IVSTOYKO
 */
@Entity
@Table(name = "movimiento")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Movimiento.findAll", query = "SELECT m FROM Movimiento m")
    , @NamedQuery(name = "Movimiento.findByIdMovimiento", query = "SELECT m FROM Movimiento m WHERE m.idMovimiento = :idMovimiento")
    , @NamedQuery(name = "Movimiento.findByNombreMovimiento", query = "SELECT m FROM Movimiento m WHERE m.nombreMovimiento = :nombreMovimiento")
    , @NamedQuery(name = "Movimiento.findByDescripcion", query = "SELECT m FROM Movimiento m WHERE m.descripcion = :descripcion")})
public class Movimiento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    ////@NotNull
    @Column(name = "idMovimiento")
    private Integer idMovimiento;
    @Basic(optional = false)
    ////@NotNull
    //@Size(min = 1, max = 45)
    @Column(name = "Nombre_Movimiento")
    private String nombreMovimiento;
    //@Size(max = 45)
    @Column(name = "Descripcion")
    private String descripcion;
    @JoinTable(name = "partida_has_movimiento", joinColumns = {
        @JoinColumn(name = "Movimiento_idMovimiento", referencedColumnName = "idMovimiento")}, inverseJoinColumns = {
        @JoinColumn(name = "Partida_idPartida", referencedColumnName = "idPartida")})
    @ManyToMany
    private Collection<Partida> partidaCollection;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "movimientoidMovimiento")
    private Reglas reglas;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "movimientoidMovimiento1")
    private Reglas reglas1;

    public Movimiento() {
    }

    public Movimiento(Integer idMovimiento) {
        this.idMovimiento = idMovimiento;
    }

    public Movimiento(Integer idMovimiento, String nombreMovimiento) {
        this.idMovimiento = idMovimiento;
        this.nombreMovimiento = nombreMovimiento;
    }

    public Integer getIdMovimiento() {
        return idMovimiento;
    }

    public void setIdMovimiento(Integer idMovimiento) {
        this.idMovimiento = idMovimiento;
    }

    public String getNombreMovimiento() {
        return nombreMovimiento;
    }

    public void setNombreMovimiento(String nombreMovimiento) {
        this.nombreMovimiento = nombreMovimiento;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @XmlTransient
    public Collection<Partida> getPartidaCollection() {
        return partidaCollection;
    }

    public void setPartidaCollection(Collection<Partida> partidaCollection) {
        this.partidaCollection = partidaCollection;
    }

    public Reglas getReglas() {
        return reglas;
    }

    public void setReglas(Reglas reglas) {
        this.reglas = reglas;
    }

    public Reglas getReglas1() {
        return reglas1;
    }

    public void setReglas1(Reglas reglas1) {
        this.reglas1 = reglas1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMovimiento != null ? idMovimiento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Movimiento)) {
            return false;
        }
        Movimiento other = (Movimiento) object;
        if ((this.idMovimiento == null && other.idMovimiento != null) || (this.idMovimiento != null && !this.idMovimiento.equals(other.idMovimiento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jpa.entities.Movimiento[ idMovimiento=" + idMovimiento + " ]";
    }
    
}
