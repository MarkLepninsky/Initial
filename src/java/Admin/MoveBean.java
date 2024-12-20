package Admin;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import jpa.session.*;
import javax.enterprise.context.SessionScoped;

/**
 *
 * @author MSENIUK
 */
@Named(value = "move")
@SessionScoped
public class MoveBean implements Serializable{

    
    
   

    /**
     * Creates a new instance of MoveBean
     */
    public MoveBean() {
        
        /**
     * hay que cargar moves de base de dato
     */
    }
    
    
    private String tempName;
    private String tempDescription;
    private int numMoves;
    private List<String> moves;
    private MovimientoFacade mf;
    public class Move {
        
        public String name;
        public String description;
       
        
        public Move (String name, String description){
            this.name=name;
            this.description=description;
           
        }
        public String getName() {
            return name;
        }

        public String getDescription() {
            return description;
        }
    }
    
     public String getTempName () 
    {
        return tempName;
    }
    
    public void setTempName (String tempName) 
    {
        this.tempName= tempName;
    }
    
    public String getTempDescription () 
    {
        return tempDescription;
    }
    
    public void setTempDescription (String tempDescription) 
    {
        this.tempDescription= tempDescription;
    }
    @PostConstruct
    public void init(){
    moves = new ArrayList<>();
    listaMoves();
    }
    
     public void deleteSelectedMove() {
     int a = mf.findMovimiento(tempName);
     if(a == -1){
     System.out.println("Error");
     } else {
     mf.removeMovimiento(a);
     numMoves--;
     }
    }
     
    public void deleteSelectedMove(String nombre) {
     int a = mf.findMovimiento(nombre);
     if(a == -1){
     System.out.println("Error");
     } else {
     mf.removeMovimiento(a);
     numMoves--;
     init();
     }
    }
     
     public void createMove() {
         int a = mf.getMaxId();
         mf.createMovimiento(a, tempName, tempDescription);
    }
    
     public int getNumMoves() {
        return numMoves;
    }
     
    public void listaMoves(){
    List<String> list = mf.listMovimiento();
    for(String string : list){
    moves.add(string);
    numMoves++;
    }
    }
    
     
    public List<String> getMoves() {
        return moves;
    }
}
