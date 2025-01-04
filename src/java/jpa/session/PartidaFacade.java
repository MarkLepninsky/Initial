/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa.session;

import javax.ejb.Stateless;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import jpa.entities.Partida;

/**
 *
 * @author IVSTOYKO
 */
@Stateless
public class PartidaFacade extends AbstractFacade<Partida> {

    @PersistenceContext(unitName = "PapelPiedraTijerasPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PartidaFacade() {
        super(Partida.class);
    }
    
    public void crearPartida(int id, int rondas, int u1, int u2){
    em.createNativeQuery("INSERT INTO partida values(?,0,0,?,0,0,?,?)").setParameter(1,id).setParameter(2,rondas).setParameter(3,u1).setParameter(4,u2).executeUpdate();
    }
    
    public int getMaxid(){
    List<Integer> list = em.createNativeQuery("SELECT max(idPartida) FROM partida").getResultList();
    if(list.isEmpty()){
    return 0;
    } else {
    return list.get(0);
    }
    }
    
    public List<Partida> listaPartidas(){
    return em.createNamedQuery("Partida.findAll",Partida.class).getResultList();
    }
    
    public List<Partida> getPartida(int a){
    return em.createNativeQuery("SELECT * FROM partida WHERE idPartida = ?").setParameter(1, a).getResultList();
    }
}
