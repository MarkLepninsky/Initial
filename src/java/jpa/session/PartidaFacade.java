/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa.session;

import Admin.GameBean;
import javax.ejb.Stateless;
import java.util.List;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import jpa.entities.Partida;
import jpa.entities.Usuario;

/**
 *
 * @author IVSTOYKO
 */
@Stateless
public class PartidaFacade extends AbstractFacade<Partida> {

    @PersistenceContext(unitName = "PapelPiedraTijerasPU")
    private EntityManager em;
    private static final Logger logger = Logger.getLogger(PartidaFacade.class.getName());
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PartidaFacade() {
        super(Partida.class);
    }
    
    public void crearPartida(int id, int u1, int mov1){
    em.createNativeQuery("INSERT INTO partida values(?,0,0,1,0,0,0,?,-1,?,-1)").setParameter(1,id).setParameter(2,u1).setParameter(3,mov1).executeUpdate();
    }
    
    public void crearPartidaTorneo(int id, int u1, int u2){
    em.createNativeQuery("INSERT INTO partida values(?,0,0,1,0,0,0,?, ?,-1,-1)").setParameter(1,id).setParameter(2,u1).setParameter(3,u2).executeUpdate();
    } 
    
    public void eliminarPartida(int id){
    em.createNativeQuery("DELETE FROM Partida WHERE idPartida = ?").setParameter(1,id).executeUpdate();
    }
    
    public void unirPartida(int id, int u1, int mov1, int u2, int mov2, int g1, int g2){
    em.createNativeQuery("INSERT INTO partida values(?,?,?,1,0,0,0,?,?,?,?)").setParameter(1,id).setParameter(2,g1).setParameter(3,g2).setParameter(4,u1).setParameter(5,u2).setParameter(6,mov1).setParameter(7,mov2).executeUpdate();
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

}
