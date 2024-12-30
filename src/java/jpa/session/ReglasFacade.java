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
import jpa.entities.Reglas;

/**
 *
 * @author IVSTOYKO
 */
@Stateless
public class ReglasFacade extends AbstractFacade<Reglas> {

    @PersistenceContext(unitName = "PapelPiedraTijerasPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ReglasFacade() {
        super(Reglas.class);
    }
     public void createRegla( int id, int idMov1, int idMov2, String resultado, String descripcion){
        em.createNativeQuery("INSERT INTO reglas (idReglas, Movimiento_idMovimiento, Movimiento_idMovimiento1, Resultado , Descripcion) VALUES (?, ?, ?, ?, ?)").setParameter(1, id).setParameter(2, idMov1).setParameter(3, idMov2).setParameter(4,resultado).setParameter(5,descripcion).executeUpdate();
    }
    
    public int findMov1(int id){
    List<Integer> list = em.createNativeQuery("SELECT Movimiento_idMovimiento FROM reglas WHERE idReglas = ?").setParameter(1,id).getResultList();
    if(list.isEmpty()){
    return -1;
    } else{
    return list.get(0);
    }
    }
    
    public int findMov2(int id){
    List<Integer> list = em.createNativeQuery("SELECT Movimiento_idMovimiento1 FROM reglas WHERE idReglas = ?").setParameter(1,id).getResultList();
    if(list.isEmpty()){
    return -1;
    } else{
    return list.get(0);
    }
    }
    
    
    public int getMaxId(){
    List<Integer> list = em.createNativeQuery("SELECT max(idReglas) FROM reglas").getResultList();
    if(list.isEmpty()){
    return 0;
    } else {
    return list.get(0);
    }
    }
    
    public int getId(int id){
    List<Integer> list = em.createNativeQuery("SELECT idReglas FROM reglas WHERE idReglas = ?").setParameter(1, id).getResultList();
    if(list.isEmpty()){
    return -1;
    } else {
    return list.get(0);
    }
    }
    
    public void removeRegla(int id){
    em.createNativeQuery("DELETE FROM reglas WHERE idReglas = ?").setParameter(1,id).executeUpdate();
    }
    
    public List<Integer> listReglas(){
    List<Integer> list = em.createNativeQuery("SELECT idReglas FROM reglas").getResultList();
    if(list.isEmpty()){
    return null;
    } else {
    return list;
    }
    }
}
