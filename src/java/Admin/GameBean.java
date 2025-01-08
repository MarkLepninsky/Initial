/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Admin;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import jpa.session.*;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import jpa.entities.*;
import java.util.logging.Logger;

/**
 *
 * @author IVSTOYKO
 */
@Named(value = "game")
@SessionScoped
public class GameBean implements Serializable {

    @EJB
    private PartidaFacade p;
    @EJB
    private MovimientoFacade m;
    @EJB
    private UsuarioFacade uf;
    @EJB
    private ReglasFacade rf;

    private String tempName;
    private String tempMov;
    private int numPartidas;
    private List<Partida> listGames;
    private List<Partida> listFinishedGames;
    private ArrayList<Game> games = new ArrayList<>();
    private Movimiento selectedMovimiento;
    private Partida selectedPartida;
    private int idPartida;
    private List<Usuario> listUsuarios;
    private Usuario selectedPlayer;
    private static final Logger logger = Logger.getLogger(GameBean.class.getName());

    public class Game {

        private ArrayList<RuleBean.Rule> rules;
        private PlayerBean.Player player1, player2;
        private MoveBean.Move mp1, mp2;

        public PlayerBean.Player getPlayer1() {
            return player1;
        }

        public PlayerBean.Player getPlayer2() {
            return player2;
        }

        public void setPlayer1(PlayerBean.Player player1) {
            this.player1 = player1;
        }

        public void setPlayer2(PlayerBean.Player player1) {
            this.player1 = player1;
        }

        public MoveBean.Move getMove1() {
            return mp1;
        }

        public MoveBean.Move getMove2() {
            return mp2;
        }

        public void setMove1(MoveBean.Move mp1) {
            this.mp1 = mp1;
        }

        public void setMove2(MoveBean.Move mp2) {
            this.mp2 = mp2;
        }

        public ArrayList<Game> partidas() {
            return games;
        }
    }

    public Movimiento getSelectedMovimiento() {
        return selectedMovimiento;
    }

    public void setSelectedMovimiento(Movimiento mov) {
        this.selectedMovimiento = mov;
    }

    public String getTempName() {
        return tempName;
    }

    public void setTempName(String tempName) {
        this.tempName = tempName;
    }

    public List<Partida> getListGames() {
        return listGames;
    }

    public void setListGames(List<Partida> listGames) {
        this.listGames = listGames;
    }

    public String getTempMov() {
        return tempMov;
    }

    public void setTempMov(String tempMov) {
        this.tempMov = tempMov;
    }

    public int getNumPartidas() {
        return numPartidas;
    }

    public void setNumPartidas(int numPartidas) {
        this.numPartidas = numPartidas;
    }

    public int getIdPartida() {
        return idPartida;
    }

    public void setIdPartida(int idPartida) {
        this.idPartida = idPartida;
    }

    public Partida getSelectedPartida() {
        return selectedPartida;
    }

    public void setSelectedPartida(Partida selectedPartida) {
        this.selectedPartida = selectedPartida;
    }

    public GameBean() {
    }

    @PostConstruct
    public void init() {
        listUsuarios = new ArrayList<>();
        listGames = new ArrayList<>();
        addListUsers();
        addListGames();
    }

    public List<Partida> getPartidas() {
        List<Partida> list = p.listaPartidas();
        if (list.isEmpty()) {
            return null;
        } else {
            return list;
        }
    }

    public void getPartidasTerminadas() {
        List<Partida> list = p.listaPartidas();
        for (Partida partida : list) {
            if (partida.getIdMovimiento2() != -1) {
                listFinishedGames.add(partida);
            }
        }
    }

    public void addListGames() {
        List<Partida> list = getPartidas();
        numPartidas = 0;
        for (Partida string : list) {
            listGames.add(string);
            numPartidas++;
        }
    }

    public void createGame() {
        int a = p.getMaxid();
        int movId = m.findMovimiento(tempMov);
        int b = uf.getId(tempName);
        p.crearPartida(a + 1, b, movId);
        init();
    }

    public void createGame(String nombre) {
        int a = p.getMaxid();
        int movId = m.findMovimiento(nombre);
        int b = uf.getId(tempName);
        p.crearPartida(a + 1, b, movId);
        init();
    }

    public void onGameChange() {

        int a = uf.getId(tempName);
        int b = m.findMovimiento(tempMov);
        for (Partida p : listGames) {
            if (p.getIdPartida().equals(idPartida) || p.getIdPartida() == idPartida) {
                p.setIdU2(a);
                p.setIdMovimiento2(b);
            }
        }
    }

    public void onPartidaChange() {
        for (Partida p : listGames) {
            if (p.getIdPartida().equals(idPartida)) {
                selectedPartida = p;
                break;
            }
        }
    }

    public void updatePartida(int u2, int mov2) {
        int u1 = selectedPartida.getIdU1();
        int mov1 = selectedPartida.getIdMovimiento1();
        int id = selectedPartida.getIdPartida();
        p.eliminarPartida(id);
        List<Reglas> reglas = rf.listaReglas();
        for (Reglas regla : reglas) {
            int a = regla.getMovimientoidMovimiento().getIdMovimiento();
            int b = regla.getMovimientoidMovimiento1().getIdMovimiento();
            if (a == mov1 && b == mov2) {
                onPlayerChange(u1);
                updatePlayerG();
                onPlayerChange(u2);
                updatePlayerP();
                p.unirPartida(id, u1, mov1, u2, mov2, 1, 0);
            } else if (a == mov2 && b == mov1) {
                onPlayerChange(u2);
                updatePlayerG();
                onPlayerChange(u1);
                updatePlayerP();
                p.unirPartida(id, u1, mov1, u2, mov2, 0, 1);
            } else if (mov1 == mov2){
                onPlayerChange(u1);
                updatePlayerE();
                onPlayerChange(u2);
                updatePlayerE();
                p.unirPartida(id, u1, mov1, u2, mov2, 0, 0);
            }
        }
    }

    public void onPlayerChange(int id) {
        for (Usuario u : listUsuarios) {
            if (u.getIdUsuario() == id) {
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

    public void updatePlayerG() {
        int a = selectedPlayer.getNVictorias();
        int b = selectedPlayer.getNDerrotas();
        int c = selectedPlayer.getNEmpates();
        int d = selectedPlayer.getRanking();
        String pass = selectedPlayer.getPassword();
        String name = selectedPlayer.getNombre();
        int e = selectedPlayer.getIdUsuario();
        a++;
        uf.removeAccount(e);
        uf.createAccount2(name,pass,e,a,b,c,d);
    }
    public void updatePlayerP() {
        int a = selectedPlayer.getNVictorias();
        int b = selectedPlayer.getNDerrotas();
        int c = selectedPlayer.getNEmpates();
        int d = selectedPlayer.getRanking();
        String pass = selectedPlayer.getPassword();
        String name = selectedPlayer.getNombre();
        int e = selectedPlayer.getIdUsuario();
        b++;
        uf.removeAccount(e);
        uf.createAccount2(name,pass,e,a,b,c,d);
    }
    
    public void updatePlayerE() {
        int a = selectedPlayer.getNVictorias();
        int b = selectedPlayer.getNDerrotas();
        int c = selectedPlayer.getNEmpates();
        int d = selectedPlayer.getRanking();
        String pass = selectedPlayer.getPassword();
        String name = selectedPlayer.getNombre();
        int e = selectedPlayer.getIdUsuario();
        c++;
        uf.removeAccount(e);
        uf.createAccount2(name,pass,e,a,b,c,d);
    }
    
    public List<Usuario> getListUsuarios() {
        return listUsuarios;
    }

    public List<Usuario> getUsers() {
        List<Usuario> list = uf.listaUsuarios();
        if (list.isEmpty()) {
            return null;
        } else {
            return list;
        }
    }

    public void addListUsers() {
        List<Usuario> list = getUsers();
        for (Usuario string : list) {
            listUsuarios.add(string);
        }
    }

}


/*
  public PlayerBean.Player playGame(Game game){
      for(int i=0; i<game.rules.size();i++){
          if(game.rules.get(i).nameG.equals(game.mp1.name)&&game.rules.get(i).nameP.equals(game.mp2.name)){
              return game.player1;}
          else if(game.rules.get(i).nameG.equals(game.mp2.name)&&game.rules.get(i).nameP.equals(game.mp1.name)){
              return game.player2;} 
      }
      return null;
  }*/
