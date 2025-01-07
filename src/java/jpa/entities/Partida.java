/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author IVSTOYKO
 */
@Entity
@Table(name = "partida")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Partida.findAll", query = "SELECT p FROM Partida p")
    , @NamedQuery(name = "Partida.findByIdPartida", query = "SELECT p FROM Partida p WHERE p.idPartida = :idPartida")
    , @NamedQuery(name = "Partida.findByIdU1", query = "SELECT p FROM Partida p WHERE p.idU1 = :idU1")
    , @NamedQuery(name = "Partida.findByIdU2", query = "SELECT p FROM Partida p WHERE p.idU2 = :idU2")
    , @NamedQuery(name = "Partida.findByGanadoU1", query = "SELECT p FROM Partida p WHERE p.ganadoU1 = :ganadoU1")
    , @NamedQuery(name = "Partida.findByGanadoU2", query = "SELECT p FROM Partida p WHERE p.ganadoU2 = :ganadoU2")
    , @NamedQuery(name = "Partida.findByNRondas", query = "SELECT p FROM Partida p WHERE p.nRondas = :nRondas")
    , @NamedQuery(name = "Partida.findByNRondasHechas", query = "SELECT p FROM Partida p WHERE p.nRondasHechas = :nRondasHechas")
    , @NamedQuery(name = "Partida.findByNRondasGU1", query = "SELECT p FROM Partida p WHERE p.nRondasGU1 = :nRondasGU1")
    , @NamedQuery(name = "Partida.findByNRondasGU2", query = "SELECT p FROM Partida p WHERE p.nRondasGU2 = :nRondasGU2")})
public class Partida implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    ////
    @Column(name = "idPartida")
    private Integer idPartida;
    @Basic(optional = false)
    ////
    @Column(name = "idU1")
    private int idU1;
    @Basic(optional = false)
    ////
    @Column(name = "idU2")
    private int idU2;
    @Column(name = "Ganado_U1")
    private Integer ganadoU1;
    @Column(name = "Ganado_U2")
    private Integer ganadoU2;
    @Basic(optional = false)
    ////
    @Column(name = "N_Rondas")
    private int nRondas;
    @Column(name = "N_Rondas_Hechas")
    private Integer nRondasHechas;
    @Column(name = "N_Rondas_G_U1")
    private Integer nRondasGU1;
    @Column(name = "N_Rondas_G_U2")
    private Integer nRondasGU2;
    @ManyToMany(mappedBy = "partidaCollection")
    private Collection<Movimiento> movimientoCollection;
    @Column(name = "idMovimiento1")
    private Integer idMovimiento1;
    @Column(name = "idMovimiento2")
    private Integer idMovimiento2;
    
    public Partida() {
    }

    public Partida(Integer idPartida) {
        this.idPartida = idPartida;
    }

    public Partida(Integer idPartida, int idU1, int idU2, int nRondas) {
        this.idPartida = idPartida;
        this.idU1 = idU1;
        this.idU2 = idU2;
        this.nRondas = nRondas;
    }

    public Integer getIdPartida() {
        return idPartida;
    }

    public void setIdPartida(Integer idPartida) {
        this.idPartida = idPartida;
    }

    public int getIdU1() {
        return idU1;
    }
    
    public int getIdMovimiento1(){
    return idMovimiento1;
    }
    
    public void setIdMovimiento1(int idMovimiento1){
    this.idMovimiento1 = idMovimiento1;
    }
    
    public int getIdMovimiento2(){
    return idMovimiento2;
    }
    
    public void setIdMovimiento2(int idMovimiento2){
    this.idMovimiento2 = idMovimiento2;
    }
    
    public void setIdU1(int idU1) {
        this.idU1 = idU1;
    }

    public int getIdU2() {
        return idU2;
    }

    public void setIdU2(int idU2) {
        this.idU2 = idU2;
    }

    public Integer getGanadoU1() {
        return ganadoU1;
    }

    public void setGanadoU1(Integer ganadoU1) {
        this.ganadoU1 = ganadoU1;
    }

    public Integer getGanadoU2() {
        return ganadoU2;
    }

    public void setGanadoU2(Integer ganadoU2) {
        this.ganadoU2 = ganadoU2;
    }

    public int getNRondas() {
        return nRondas;
    }

    public void setNRondas(int nRondas) {
        this.nRondas = nRondas;
    }

    public Integer getNRondasHechas() {
        return nRondasHechas;
    }

    public void setNRondasHechas(Integer nRondasHechas) {
        this.nRondasHechas = nRondasHechas;
    }

    public Integer getNRondasGU1() {
        return nRondasGU1;
    }

    public void setNRondasGU1(Integer nRondasGU1) {
        this.nRondasGU1 = nRondasGU1;
    }

    public Integer getNRondasGU2() {
        return nRondasGU2;
    }

    public void setNRondasGU2(Integer nRondasGU2) {
        this.nRondasGU2 = nRondasGU2;
    }

    @XmlTransient
    public Collection<Movimiento> getMovimientoCollection() {
        return movimientoCollection;
    }

    public void setMovimientoCollection(Collection<Movimiento> movimientoCollection) {
        this.movimientoCollection = movimientoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPartida != null ? idPartida.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Partida)) {
            return false;
        }
        Partida other = (Partida) object;
        if ((this.idPartida == null && other.idPartida != null) || (this.idPartida != null && !this.idPartida.equals(other.idPartida))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jpa.entities.Partida[ idPartida=" + idPartida + " ]";
    }
    
}
