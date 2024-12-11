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
import jpa.entities.Usuario;

/**
 *
 * @author IVSTOYKO
 */
@Stateless
public class UsuarioFacade extends AbstractFacade<Usuario> {

    @PersistenceContext(unitName = "PapelPiedraTijerasPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioFacade() {
        super(Usuario.class);
    }
    
    
    public void createAccount(String nombre, String password, int id){
        em.createNativeQuery("INSERT INTO usuario (idUsuario, Nombre, Password,N_Victorias , N_Derrotas, N_Empates, Ranking) VALUES (?, ?, ?, 0, 0, 0, 0)").setParameter(1, id).setParameter(2, nombre).setParameter(3, password).executeUpdate();
    }
    
    public String findName(String nombre){
    List<String> list = em.createNativeQuery("SELECT Nombre FROM usuario WHERE Nombre = ?").setParameter(1,nombre).getResultList();
    if(list.get(0) == null){
    return null;
    } else{
    return list.get(0);
    }
    }
    
    public String findPassword(String contra){
    List<String> list = em.createNativeQuery("SELECT Password FROM usuario WHERE Password = :contra").setParameter("contra",contra).getResultList();
    if(list.get(0) == null){
    return null;
    } else{
    return list.get(0);
    }
    }
    
    public int getMaxId(){
    List<Integer> list = em.createNativeQuery("SELECT max(idUsuario) FROM usuario").getResultList();
    return list.get(0);
    }
    
    public int getId(String nombre){
    List<Integer> list = em.createNativeQuery("SELECT idUsuario FROM usuario WHERE Nombre = ?").setParameter(1, nombre).getResultList();
    if(list.get(0) == null || list.isEmpty()){
    return -1;
    } else {
    return list.get(0);
    }
    }
    
    public void addAdmin(int id){
    em.createNativeQuery("INSERT INTO admin (Usuario_idUsuario) VALUES (?)").setParameter(1,id).executeUpdate();
    }
    
    public void addCliente(int id){
    em.createNativeQuery("INSERT INTO player (Usuario_idUsuario) VALUES (?)").setParameter(1,id).executeUpdate();
    }
    
    public void removeCliente(int id){
    em.createNativeQuery("DELETE FROM player where Usuario_idUsuario = ?").setParameter(1,id).executeUpdate();
    }
    
    public void removeAdmin(int id){
    em.createNativeQuery("DELETE FROM admin where Usuario_idUsuario = ?").setParameter(1,id).executeUpdate();
    }
}
