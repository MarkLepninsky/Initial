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
        em.createQuery("INSERT INTO mydb.usuario values(id,nombre,password,0,0,0)").setParameter("id", id).setParameter("nombre", nombre).setParameter("password", password);
    }
    
    public String findName(String nombre){
    String usuario;
    List<String> list = em.createQuery("SELECT u.Nombre FROM mydb.usuario u WHERE u.Nombre = usuario").setParameter("usuario",nombre).getResultList();
    usuario = list.toString();
    if(usuario.equals(null)){
    return null;
    } else{
    return usuario;
    }
    }
    
    public String findPassword(String contra){
    String password;
    List<String> list = em.createQuery("SELECT u.password FROM mydb.usuario u WHERE u.password = contra").setParameter("contra",contra).getResultList();
    password = list.toString();
    if(password.equals(null)){
    return null;
    } else{
    return password;
    }
    }
    
    public int getMaxId(){
    String conv;
    List<Integer> list = em.createQuery("SELECT max(u.id) FROM mydb.usuario u").getResultList();
    conv = list.toString();
    int a = Integer.valueOf(conv);
    return a;
    }
    
    public int getId(String nombre){
    String conv;
    List<Integer> list = em.createQuery("SELECT u.id FROM mydb.usuario u WHERE u.nombre = usuario").setParameter("usuario", nombre).getResultList();
    conv = list.toString();
    int a = Integer.valueOf(conv);
    return a;
    }
}
