/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Admin;

import jpa.session.*;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.ejb.EJB;
import java.util.logging.Logger;

/**
 *
 * @author IVSTOYKO
 */
@Named(value = "loginBeanCliente")
@SessionScoped
public class LoginBeanCliente implements Serializable {
        @EJB
        private UsuarioFacade u;
        private static final Logger log = Logger.getLogger(LoginBeanCliente.class.getName());
        
        
        private String username;
        private String password;

    /**
     * Creates a new instance of LoginBeanCliente
     */
    public LoginBeanCliente(){
        username = "client";
        password = "client";
    }
    
    public void setUsername(String username){
    this.username = username;
    }
    public void setPassword(String password){
    this.password = password;
    }
    
    public String getUsername(){
    return username;
    }
    
    public String getPassword(){
    return password;
    }
    
    public String loginCheck(){
    if((checkPassword(password)) && (checkUsername(username)) && password != null && username != null){
        log.info("Usuario y Contra verificados!!!!");
        int a = u.getId(username);
        boolean comp = u.ClientId(a);
        if(comp == true){
            return "/faces/cliente/cliente.xhtml?faces-redirect=true";
        } else {
            return "/faces/cliente/clienteLogIn.xhtml?faces-redirect=true";
        }
        } else {
            return "/faces/cliente/clienteLogIn.xhtml?faces-redirect=true";
        }
    }
    
    public boolean checkPassword(String password){
        String truePassword = u.findPassword(password);
        if(password.equals(truePassword)){
        return true;
        } else {
        return false;
        }
    }
    
    public boolean checkUsername(String Nombre){
        String trueUsername = u.findName(Nombre);
        if(trueUsername.equals(Nombre)){
        return true;
        } else {
        return false;
        }
    }
}
