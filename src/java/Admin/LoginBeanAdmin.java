/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Admin;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;

/**
 *
 * @author IVSTOYKO
 */
@Named(value = "loginBeanAdmin")
@SessionScoped
public class LoginBeanAdmin implements Serializable {
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
    if(this.username.equals(username) && this.password.equals(password) && password != null && username != null){
    return "/faces/administration/admin.xhtml?faces-redirect=true";
    } else {
    return "/faces/administration/adminLogIn.xhtml?faces-redirect=true";
    }
    }
    
}
