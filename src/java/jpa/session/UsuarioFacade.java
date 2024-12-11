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
    String usuario;
    List<String> list = em.createQuery("SELECT Nombre FROM usuario WHERE Nombre = usuario").setParameter("usuario",nombre).getResultList();
    usuario = list.toString();
    if(usuario.equals(null)){
    return null;
    } else{
    return usuario;
    }
    }
    
    public String findPassword(String contra){
    String password;
    List<String> list = em.createQuery("SELECT Password FROM usuario WHERE Password = contra").setParameter("contra",contra).getResultList();
    password = list.toString();
    if(password.equals(null)){
    return null;
    } else{
    return password;
    }
    }
    
    public int getMaxId(){
    List<Integer> list = em.createNativeQuery("SELECT max(idUsuario) FROM usuario").getResultList();
    return list.get(0);
    }
    
    public int getId(String nombre){
    String conv;
    List<Integer> list = em.createQuery("SELECT idUsuario FROM usuario WHERE Nombre = usuario").setParameter("usuario", nombre).getResultList();
    conv = list.toString();
    int a = Integer.valueOf(conv);
    return a;
    }
}
