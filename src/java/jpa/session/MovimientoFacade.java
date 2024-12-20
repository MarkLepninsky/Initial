/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa.session;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import jpa.entities.Movimiento;

/**
 *
 * @author IVSTOYKO
 */
@Stateless
public class MovimientoFacade extends AbstractFacade<Movimiento> {

    @PersistenceContext(unitName = "PapelPiedraTijerasPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MovimientoFacade() {
        super(Movimiento.class);
    }
    
    public void createMovimiento( int id, String nombre, String descripcion){
    em.createNativeQuery("INSERT INTO movimiento (idMovimiento, Nombre_Movimiento, Descripcion) VALUES (?, ?, ?)").setParameter(1, id).setParameter(2, nombre).setParameter(3, descripcion).executeUpdate();
    }
    
    public int findMovimiento(String nombre){
    List<Integer> list = em.createNativeQuery("SELECT idMovimiento FROM movimiento WHERE Nombre_Movimiento = ?").setParameter(1,nombre).getResultList();
    if(list.isEmpty()){
    return -1;
    } else{
    return list.get(0);
    }
    }
    
    public int getMaxId(){
    List<Integer> list = em.createNativeQuery("SELECT max(idMovimiento) FROM movimiento").getResultList();
    return list.get(0);
    }
    
    
    public void removeMovimiento(int id){
    em.createNativeQuery("DELETE FROM movimiento WHERE idMovimiento = ?").setParameter(1,id).executeUpdate();
    }
    
    public List<String> listMovimiento(){
    List<String> list = em.createNativeQuery("SELECT Nombre_Movimiento FROM movimiento").getResultList();
    return list;
    }
    
}
