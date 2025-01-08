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
    
   public void createAccount2(String nombre, String password, int id, int v, int d, int e, int r){
        em.createNativeQuery("INSERT INTO usuario (idUsuario, Nombre, Password,N_Victorias , N_Derrotas, N_Empates, Ranking) VALUES (?, ?, ?, ?, ?, ?, ?)").setParameter(1, id).setParameter(2, nombre).setParameter(3, password).setParameter(4,v).setParameter(5, d).setParameter(6,e).setParameter(7,r).executeUpdate();
    } 
    
    public String findName(String nombre){
    List<String> list = em.createNativeQuery("SELECT Nombre FROM usuario WHERE Nombre = ?").setParameter(1,nombre).getResultList();
    if(list.isEmpty()){
    return null;
    } else{
    return list.get(0);
    }
    }
    
    public String findPassword(String contra){
    List<String> list = em.createNativeQuery("SELECT Password FROM usuario WHERE Password = ?").setParameter(1,contra).getResultList();
    if(list.isEmpty()){
    return null;
    } else{
    return list.get(0);
    }
    }
    
    public int getMaxId(){
    List<Integer> list = em.createNativeQuery("SELECT max(idUsuario) FROM usuario").getResultList();
    if(list.isEmpty()){
    return 0;
    } else {
    return list.get(0);
    }
    }
    
    public int getId(String nombre){
    List<Integer> list = em.createNativeQuery("SELECT idUsuario FROM usuario WHERE Nombre = ?").setParameter(1, nombre).getResultList();
    if(list.isEmpty()){
    return -1;
    } else {
    return list.get(0);
    }
    }
    
    public void addAdmin(int id){
    em.createNativeQuery("INSERT INTO admin (idAdmin) VALUES (?)").setParameter(1,id).executeUpdate();
    }
    
    public void addClient(int id){
    em.createNativeQuery("INSERT INTO player (idPlayer) VALUES (?)").setParameter(1,id).executeUpdate();
    }
    
    public void removeClient(int id){
    em.createNativeQuery("DELETE FROM player WHERE idPlayer = ?").setParameter(1,id).executeUpdate();
    }
    
    public void removeAdmin(int id){
    em.createNativeQuery("DELETE FROM admin WHERE idAdmin = ?").setParameter(1,id).executeUpdate();
    }
    
    public void removeAccount(int id){
    em.createNativeQuery("DELETE FROM usuario WHERE idUsuario = ?").setParameter(1,id).executeUpdate();
    }
    
    public boolean ClientId(int id){
        List<Integer> list = em.createNativeQuery("SELECT idPlayer FROM player WHERE idPlayer = ?").setParameter(1,id).getResultList();
        if(list.isEmpty()){
        return false;
        } else {
        return true;
        }
    }
    
    public boolean AdminId(int id){
        List<Integer> list = em.createNativeQuery("SELECT idAdmin FROM admin WHERE idAdmin = ?").setParameter(1,id).getResultList();
        if(list.isEmpty()){
            return false;
        } else {
            return true;
        }
    }
    
    public List<String> listUsers(){
    List<String> list = em.createNativeQuery("SELECT Nombre FROM usuario").getResultList();
    if(list.isEmpty()){
    return null;
    } else {
    return list;
    }
    }
    
    public List<Usuario> listaUsuarios(){
    return em.createNamedQuery("Usuario.findAll",Usuario.class).getResultList();
    }
}
