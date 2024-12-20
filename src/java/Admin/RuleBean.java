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
    private ReglasFacade rf;
    private MovimientoFacade mf;
    private List<Integer> reglas;
    
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
    reglas = new ArrayList<>();
    listReglas();
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
    
    
    public void createRule() {
        int a = mf.findMovimiento(tempNameG);
        int b = mf.findMovimiento(tempNameP);
        if(a == -1 || b == -1){
        System.out.println("Error");
        } else {
        int c = rf.getMaxId();
        rf.createRegla(c, a, b, resultado, descripcion);
        }
    }
    
    public void deleteRule() {
        int a = rf.getId(id);
        if(a == id){
        rf.removeRegla(id);
        numRules--;
        } else {
        System.out.println("Error");
        }
        }
    
        public void deleteRule(int id) {
        int a = rf.getId(id);
        if(a == id){
        rf.removeRegla(id);
        numRules--;
        init();
        } else {
        System.out.println("Error");
        }
        }
    
    public void listReglas(){
        List<Integer> list = rf.listReglas();
        for (Integer integer : list) {
            reglas.add(integer);
            numRules++;
        }
    }
    
    public List<Integer> getReglas(){
    return reglas;
    }
    }