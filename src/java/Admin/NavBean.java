/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Admin;

import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author IVSTOYKO
 */
@Named(value = "NavBean")
@RequestScoped
public class NavBean {

    /**
     * Creates a new instance of NavBean
     */
  public NavBean()
    {
    }
  //------------------------Administrador-----------------------//
    

    public String goToAddPlayer()
    {
        return "/faces/administration/player/addPlayer.xhtml?faces-redirect=true";
    }
    
    public String goToAdmin()
    {
        return "/faces/administration/admin.xhtml?faces-redirect=true";
    }
    
    public String goToUpdatePlayer()
    {
        return "/faces/administration/player/updatePlayer.xhtml?faces-redirect=true";
    }
    
    public String goToRemovePlayer()
    {
        return "/faces/administration/player/removePlayer.xhtml?faces-redirect=true";
    }
    
    public String goToListPlayers()
    {
        return "/faces/administration/player/listPlayers.xhtml?faces-redirect=true";
    }
    
    public String goToAddMove()
    {
        return "/faces/administration/move/addMove.xhtml?faces-redirect=true";
    }
    
    public String goToRemoveMove()
    {
        return "/faces/administration/move/removeMove.xhtml?faces-redirect=true";
    }
    
    public String goToAddRule()
    {
        return "/faces/administration/rule/addRule.xhtml?faces-redirect=true";
    }
    
    public String goToRemoveRule()
    {  
        return "/faces/administration/rule/removeRule.xhtml?faces-redirect=true";
    }
    
    public String goToListGameMoves()
    {
        return "/faces/administration/move/listGameMoves.xhtml?faces-redirect=true";
    }
    
    //-----------Cliente-------------//
    
    public String goToLogout()
    {
        return "/faces/cliente/login.xhtml?faces-redirect=true";
    }
    public String gotoCliente() {
        return "/faces/cliente/cliente.xhtml?faces-redirect=true";
    }

    public String gotoCrear_Cliente() {
        return "/faces/cliente/crear_usuario.xhtml?faces-redirect=true";
    }

    public String gotoCrear_Juego() {
        return "/faces/cliente/crear_juego.xhtml?faces-redirect=true";
    }

    public String gotoList_Juego() {
        return "/faces/cliente/list_juego.xhtml?faces-redirect=true";
    }
    

    public String gotoLogin() {
        return "/faces/cliente/login.xhtml?faces-redirect=true";
    }

    public String gotoJugar_Partida() {
        return "/faces/cliente/jugar_partida.xhtml?faces-redirect=true";
    }
    
     public String gotoJugar_Torneo() {
        return "/faces/cliente/jugar_torneo.xhtml?faces-redirect=true";
    }
    public String goToAdminLog() {
        return "/faces/administration/adminLogIn.xhtml?faces-redirect=true";
    }
    public String goToClienteLog() {
        return "/faces/cliente/clienteLogIn.xhtml?faces-redirect=true";
    }
}

