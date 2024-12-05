package Admin;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.Serializable;
import java.util.ArrayList;
import javax.inject.Named;

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
    
    
    private String tempName="";
    private String tempDescription="";
    private int numMoves = 0;

    private ArrayList<Move> moves = new ArrayList<>();

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
    
    public ArrayList<Move> getPlayers() {
        return moves;
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
    
     public void deleteSelectedMove() {
        Move moveToRemove = null;
        for (Move move : moves) {
            if (move.getName().equals(tempName)) {
                moveToRemove = move;
                break;
            }
        }

        if (moveToRemove != null) {
            moves.remove(moveToRemove);
            numMoves = moves.size();
            System.out.println("Removed Move: " + moveToRemove.getName());
        }
    }
     
     public void createMove() {
        Move newm = new Move (tempName, tempDescription);
        moves.add(newm);
        numMoves++;
    }
    
     public int getNumMoves() {
        return numMoves;
    }
     
    public ArrayList<Move> getMoves() {
        return moves;
    }
     
}
