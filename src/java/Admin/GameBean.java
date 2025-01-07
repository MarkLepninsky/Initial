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
import java.util.List;
import jpa.session.*;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import jpa.entities.*;
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
    private UsuarioFacade u;
    private RuleBean ruleBean;
    
    private String tempName;
    private String tempMov;
    private int numPartidas;
    private List<Partida> listGames;
    private ArrayList<Game> games = new ArrayList<>();
    private Movimiento selectedMovimiento;
    private Partida selectedPartida;
    
   
    
    public class Game{

        private ArrayList<RuleBean.Rule> rules; 
        private PlayerBean.Player player1,player2;
        private MoveBean.Move mp1,mp2;

        
        public PlayerBean.Player getPlayer1(){
            return player1;
        }
        
        public PlayerBean.Player getPlayer2(){
        return player2;
        }
        
        public void setPlayer1(PlayerBean.Player player1){
        this.player1 = player1;
        }
        
        public void setPlayer2(PlayerBean.Player player1){
        this.player1 = player1;
        }
        
        public MoveBean.Move getMove1(){
        return mp1;
        }
        
        public MoveBean.Move getMove2(){
        return mp2;
        }
        
        public void setMove1(MoveBean.Move mp1){
        this.mp1 = mp1;
        }
        
        public void setMove2(MoveBean.Move mp2){
        this.mp2 = mp2;
        }
        
        
        public ArrayList<Game> partidas(){
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
            listGames = new ArrayList<>();
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
   
   public List<Partida> getPartidasTerminadas() {
        List<Partida> list = p.listaPartidas(); 
        List<Partida> listTemp = new ArrayList<>();
        for (Partida partida : list){
            if (partida.getIdMovimiento2() != -1) {
                listTemp.add(partida);
            }
        }
        return listTemp;
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
        int b = u.getId(tempName);
        p.crearPartida(a+1, b, movId);
        addPartida(a+1);
        init();
    }
  
  public void addPartida(int id) {
        p.addPartida(id);
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
}