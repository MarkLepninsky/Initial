package Admin;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import jpa.session.*;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import javax.ejb.EJB;

@Named(value = "player")
@SessionScoped
public class PlayerBean implements Serializable {

    @EJB
    private UsuarioFacade u;

    public PlayerBean() {
        players.add(new Player("Alice", "alice@example.com", "password123"));
        players.add(new Player("Bob", "bob@example.com", "securepass456"));
        players.add(new Player("Charlie", "charlie@example.com", "mypassword789"));
        numPlayers = players.size();
    }

    private String tempName;
    private String mname;
    private String tempPass;
    private int numPlayers = 0;

    private ArrayList<Player> players = new ArrayList<>();

    public class Player {

        private String name;
        private String email;
        private String pass;
        private int score;

        public Player(String name, String email, String pass) {
            this.name = name;
            this.email = email;
            this.pass = pass;
        }

        public String getName() {
            return name;
        }

        public String getEmail() {
            return email;
        }

        public String getPass() {
            return pass;
        }
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public String getTempName() {
        return tempName;
    }

    public void setTempName(String tempName) {
        this.tempName = tempName;
    }

    public String getMname() {
        return mname;
    }

    public void setMname(String mname) {
        this.mname = mname;
    }

    public String getTempPass() {
        return tempPass;
    }

    public void setTempPass(String tempPass) {
        this.tempPass = tempPass;
    }

    public int getNumPlayers() {
        return numPlayers;
    }

    public void createPlayer() {
        int a = u.getMaxId();
        u.createAccount(tempName, tempPass, a + 1);
        addCliente(tempName);
    }

    public void addCliente() {
        int a;
        a = u.getId(tempName);
        if (a == -1) {
            System.out.println("Error");
        } else {
            u.addClient(a);
        }
    }

    public void addCliente(String nombre) {
        int a;
        a = u.getId(nombre);
        if (a == -1) {
            System.out.println("Error");
        } else {
            u.addClient(a);
        }
    }

    public void addAdmin() {
        int a;
        a = u.getId(tempName);
        if (a == -1) {
            System.out.println("Error");
        } else {
            u.addAdmin(a);
        }
    }

    public void removeAdmin() {
        int a;
        a = u.getId(tempName);
        if (a == -1) {
            System.out.println("Error");
        } else {
            u.removeAdmin(a);
        }
    }
    
    public void removeAdmin(String nombre) {
        int a;
        a = u.getId(nombre);
        if (a == -1) {
            System.out.println("Error");
        } else {
            u.removeAdmin(a);
        }
    }
    
    public void removeUser(){
        removeClient(tempName);
        removeAdmin(tempName);
        int a;
        a = u.getId(tempName);
        if(a == -1){
            System.out.println("Error");
        } else {
            u.removeAccount(a);
        }
    }
    
    public void removeClient(){
        int a;
        a = u.getId(tempName);
        if (a == -1) {
            System.out.println("Error");
        } else {
            u.removeClient(a);
        }
    }
    
    public void removeClient(String nombre){
        int a;
        a = u.getId(nombre);
        if (a == -1) {
            System.out.println("Error");
        } else {
            u.removeClient(a);
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
        if(trueUsername == null){
        return true;
        } else {
        return false;
        }
    }
}
