/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
@Table(name = "reglas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Reglas.findAll", query = "SELECT r FROM Reglas r")
    , @NamedQuery(name = "Reglas.findByIdReglas", query = "SELECT r FROM Reglas r WHERE r.idReglas = :idReglas")
    , @NamedQuery(name = "Reglas.findByResultado", query = "SELECT r FROM Reglas r WHERE r.resultado = :resultado")
    , @NamedQuery(name = "Reglas.findByDescripcion", query = "SELECT r FROM Reglas r WHERE r.descripcion = :descripcion")})
public class Reglas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    ////
    @Column(name = "idReglas")
    private Integer idReglas;
    @Basic(optional = false)
    ////
    ////@Size(max = 45)
    @Column(name = "Resultado")
    private String resultado;
    ////@Size(max = 45)
    @Column(name = "Descripcion")
    private String descripcion;
    @JoinColumn(name = "Movimiento_idMovimiento", referencedColumnName = "idMovimiento")
    @OneToOne(optional = false)
    private Movimiento movimientoidMovimiento;
    @JoinColumn(name = "Movimiento_idMovimiento1", referencedColumnName = "idMovimiento")
    @OneToOne(optional = false)
    private Movimiento movimientoidMovimiento1;

    public Reglas() {
    }

    public Reglas(Integer idReglas) {
        this.idReglas = idReglas;
    }
    
    public Integer getIdReglas() {
        return idReglas;
    }

    public void setIdReglas(Integer idReglas) {
        this.idReglas = idReglas;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Movimiento getMovimientoidMovimiento() {
        return movimientoidMovimiento;
    }

    public void setMovimientoidMovimiento(Movimiento movimientoidMovimiento) {
        this.movimientoidMovimiento = movimientoidMovimiento;
    }

    public Movimiento getMovimientoidMovimiento1() {
        return movimientoidMovimiento1;
    }

    public void setMovimientoidMovimiento1(Movimiento movimientoidMovimiento1) {
        this.movimientoidMovimiento1 = movimientoidMovimiento1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idReglas != null ? idReglas.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Reglas)) {
            return false;
        }
        Reglas other = (Reglas) object;
        if ((this.idReglas == null && other.idReglas != null) || (this.idReglas != null && !this.idReglas.equals(other.idReglas))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jpa.entities.Reglas[ idReglas=" + idReglas + "Resultado" + resultado +"Descripción"+ descripcion +"Primer Movimiento" + movimientoidMovimiento + "Segundo Movimiento" + movimientoidMovimiento1 + "]";
    }
    
}
