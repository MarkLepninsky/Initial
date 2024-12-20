/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Admin;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.ejb.EJB;
import jpa.session.UsuarioFacade;

/**
 *
 * @author IVSTOYKO
 */
@Named(value = "loginBeanAdmin")
@SessionScoped
public class LoginBeanAdmin implements Serializable {
    @EJB
    private UsuarioFacade u;
    private String username;
    private String password;

    /**
     * Creates a new instance of LoginBeanAdmin
     */
    public LoginBeanAdmin() {
        username = "admin";
        password = "admin";
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
    if(checkPassword(password) && checkUsername(username) && password != null && username != null){
    int a = u.getId(username);
    if(u.AdminId(a)){
    return "/faces/administration/admin.xhtml?faces-redirect=true";
    } else {
    return "/faces/administration/adminLogIn.xhtml?faces-redirect=true";
    }
    } else {
    return "/faces/administration/adminLogIn.xhtml?faces-redirect=true";
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
