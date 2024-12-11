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
    private String tempEmail;
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

    public String getTempEmail() {
        return tempEmail;
    }

    public void setTempEmail(String tempEmail) {
        this.tempEmail = tempEmail;
    }

    public int getNumPlayers() {
        return numPlayers;
    }
    public void deleteSelectedPlayer() {
        Player playerToRemove = null;
        for (Player player : players) {
            if (player.getName().equals(tempName)) {
                playerToRemove = player;
                break;
            }
        }

        if (playerToRemove != null) {
            players.remove(playerToRemove);
            numPlayers = players.size();
            System.out.println("Removed Player: " + playerToRemove.getName());
        }
    }
    public void modifySelectedPlayer() {
        Player playerToRemove = null;
        for (Player player : players) {
            if (player.getName().equals(tempName)) {
                playerToRemove = player;
                break;
            }
        }

        if (playerToRemove != null) {
            players.remove(playerToRemove);
            System.out.println("Removed Player: " + playerToRemove.getName());
            Player newp = new Player (mname, tempEmail, tempPass);
            players.add(newp);
            numPlayers = players.size();
        }
    }
    
    public void createPlayer() {
        int a = u.getMaxId();
        u.createAccount(tempName,tempPass,a+1);
    }

}
 
    
