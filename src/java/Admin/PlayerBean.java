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
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import jpa.entities.*;
@Named(value = "player")
@SessionScoped
public class PlayerBean implements Serializable {

    @EJB
    private UsuarioFacade u;

    public PlayerBean() {

    }

    private String tempName;
    private String mname;
    private String tempPass;
    private int numPlayers;
    private List<Usuario> listUsuarios;
    private ArrayList<Player> players = new ArrayList<>();
    private Usuario m;
    private Usuario selectedPlayer;

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

        public String getEmail() {
            return email;
        }

        public String getPass() {
            return pass;
        }
    }
    
        @PostConstruct
        public void init() {
            listUsuarios = new ArrayList<>();
            addListUsers();
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
        init();
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
    
        public void addAdmin(String nombre) {
        int a;
        a = u.getId(nombre);
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

    public void removeUser(String nombre) {
        removeClient(nombre);
        removeAdmin(nombre);
        int a;
        a = u.getId(nombre);
        if (a == -1) {
            System.out.println("Error");
        } else {
            u.removeAccount(a);
            init();
        }
    }
    
    public void removeUser() {
        removeClient(tempName);
        removeAdmin(tempName);
        int a;
        a = u.getId(tempName);
        if (a == -1) {
            System.out.println("Error");
        } else {
            u.removeAccount(a);
            init();
        }
    }

    public void removeClient() {
        int a;
        a = u.getId(tempName);
        if (a == -1) {
            System.out.println("Error");
        } else {
            u.removeClient(a);
        }
    }

    public void removeClient(String nombre) {
        int a;
        a = u.getId(nombre);
        if (a == -1) {
            System.out.println("Error");
        } else {
            u.removeClient(a);
        }
    }

    public boolean checkPassword(String password) {
        String truePassword = u.findPassword(password);
        if (password.equals(truePassword)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean checkUsername(String Nombre) {
        String trueUsername = u.findName(Nombre);
        if (trueUsername.equals(Nombre)) {
            return true;
        } else {
            return false;
        }
    }

    public List<Usuario> getUsers() {
        List<Usuario> list = u.listaUsuarios(); 
        if (list.isEmpty()) {
            return null;
        } else {
            return list;
        }
    }

    public void addListUsers() {
        List<Usuario> list = getUsers();
        numPlayers = 0;
        for (Usuario string : list) {
            listUsuarios.add(string);
            numPlayers++;
        }
    }

    public int getNumUsers() {
        return numPlayers;
    }

    public List<Usuario> getListUsuarios() {
        return listUsuarios;
    }
    
    public void clearName(){
    tempName = null;
    }
    
public void onPlayerChange() {
    for (Usuario u : listUsuarios) {
        if (u.getNombre().equals(tempName)) {
            selectedPlayer = u;
            break;
        }
    }
}

public Usuario getSelectedPlayer() {
    return selectedPlayer;
}

public void setSelectedPlayer(Usuario selectedPlayer) {
    this.selectedPlayer = selectedPlayer;
}

public void updatePlayer(String nombre, String password){
selectedPlayer.setNombre(nombre);
selectedPlayer.setPassword(password);
}

}
