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
@Named(value = "rule")
@SessionScoped
public class RuleBean implements Serializable{

    /**
     * Creates a new instance of RuleBean
     */
    public RuleBean() {
    }
    
    
    
    private String tempNameG="";
    private String tempNameP="";
    private int numRules = 0;

    private ArrayList<Rule> rules = new ArrayList<>();

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
    
    public ArrayList<Rule> getRules() {
        return rules;
    }
    
    public void createRule() {
        Rule newr = new Rule (tempNameG, tempNameP);
        rules.add(newr);
        numRules++;
    }
    
    public void deleteSelectedRule() {
        Rule ruleToRemove = null;
        for (Rule rule : rules) {
            if (rule.getNameG().equals(tempNameG)) {
                ruleToRemove = rule;
                break;
            }
        }

        if (ruleToRemove != null) {
            rules.remove(ruleToRemove);
            numRules = rules.size();
            System.out.println("Removed Move: " + ruleToRemove.getNameG());
        }
    }
    
}
