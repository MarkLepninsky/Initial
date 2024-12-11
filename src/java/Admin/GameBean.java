/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Admin;
import java.util.ArrayList;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author IVSTOYKO
 */
@Named(value = "game")
@SessionScoped
public class GameBean implements Serializable {
    private ArrayList<Game> games = new ArrayList<>();
    private RuleBean ruleBean;

    public class Game{

        ArrayList<RuleBean.Rule> rules; 
        PlayerBean.Player player1;
        PlayerBean.Player player2;
        MoveBean.Move mp1;
        MoveBean.Move mp2;

        public Game (){
            rules =  ruleBean.getRules();
        }
         public Game (PlayerBean.Player player1, MoveBean.Move mp1){
            this.player1 = player1;
            this.mp1 = mp1;
            rules =  ruleBean.getRules();
        }
        
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
        
        public String CrearPartida(PlayerBean.Player jugador, MoveBean.Move mp1){
            this.player1 = jugador;
            this.mp1 = mp1;
            Game game = new Game(jugador,mp1);
            games.add(game);
            return "Esperando jugador";
        }
        
        public String agregarMovimientP2 (Game game, PlayerBean.Player player2, MoveBean.Move mp2){
        game.player2 = player2;
        game.mp2 = mp2;
        PlayerBean.Player ganador = playGame(game);
        String msj = "Ha ganado: ";
        msj += ganador.getName();
        games.remove(game);
        return msj;
        }
        
        public ArrayList<Game> partidas(){
            return games;
        }
    }
    
  public GameBean() {
  }




  public PlayerBean.Player playGame(Game game){
      for(int i=0; i<game.rules.size();i++){
          if(game.rules.get(i).nameG.equals(game.mp1.name)&&game.rules.get(i).nameP.equals(game.mp2.name)){
              return game.player1;}
          else if(game.rules.get(i).nameG.equals(game.mp2.name)&&game.rules.get(i).nameP.equals(game.mp1.name)){
              return game.player2;} 
      }
      return null;
  }
}