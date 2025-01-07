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
import javax.ejb.EJB;
import javax.inject.Named;
import jpa.session.*;
import javax.enterprise.context.SessionScoped;
import jpa.entities.*;

/**
 *
 * @author MSENIUK
 */
@Named(value = "rule")
@SessionScoped
public class RuleBean implements Serializable{

    /**
     * Creates a new instance of RuleBean
     */
    public RuleBean() {
    }
    
    
    
    private String tempNameG;
    private String tempNameP;
    private int id;
    private int numRules;
    private String resultado;
    private String descripcion;
    @EJB
    private ReglasFacade rf;
    @EJB
    private MovimientoFacade mf;
    private List<Reglas> reglas;
    
    public class Rule {
        
        public String nameG;
        public String nameP;
       
        
        public Rule (String nameG, String nameP){
            this.nameG=nameG;
            this.nameP=nameP;
           
        }
        public String getNameG() {
            return nameG;
        }
        public String getNameP() {
            return nameP;
        }
    }
    
    @PostConstruct
    public void init(){
        if(rf == null || mf == null){
        throw new IllegalStateException("Error de inyección");
        }
    reglas = new ArrayList<>();
    listRegla();
    }
    
      public String getTempNameG() {
        return tempNameG;
    }

    public void setTempNameG(String tempNameG) {
        this.tempNameG = tempNameG;
    }
    
      public String getTempNameP() {
        return tempNameP;
    }

    public void setTempNameP(String tempNameP) {
        this.tempNameP = tempNameP;
    }
    
    public int getNumRules() {
        return numRules;
    }
    
    public int getId(){
    return id;
    }
    
    public void setId(int id){
    this.id = id;
    }
    
    public void createRule() {
        int a = mf.findMovimiento(tempNameG);
        int b = mf.findMovimiento(tempNameP);
        String r = "Gana " + tempNameG;
        String d = tempNameG +" gana "+tempNameP;
        if(a == -1 || b == -1){
        System.out.println("Error");
        } else {
        int c = rf.getMaxId();
        rf.createRegla(c+1, a, b, r, d);
        init();
        }
    }
    
    public void deleteRule() {
        int a = mf.findMovimiento(tempNameG);
        int b = mf.findMovimiento(tempNameP);
        int c = rf.getId(a,b);
        if(a == -1){
        System.out.println("Error");
        } else {
        rf.removeRegla(c);
        init();
        }
        }
    
    public void deleteRule(String tempNameG, String tempNameP) {
        int a = mf.findMovimiento(tempNameG);
        int b = mf.findMovimiento(tempNameP);
        int c = rf.getId(a,b);
        if(a == -1){
        System.out.println("Error");
        } else {
        rf.removeRegla(c);
        init();
        }
        }
    
    public void listRegla(){
        List<Reglas> list = rf.listaReglas();
        numRules = 0;
        for (Reglas integer : list) {
            reglas.add(integer);
            numRules++;
        }
    }
    
    public List<Reglas> getReglas(){
    return reglas;
    }
    }