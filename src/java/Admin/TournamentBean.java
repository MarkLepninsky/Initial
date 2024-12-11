
package Admin;

import Admin.PlayerBean.Player;
import Admin.RuleBean.Rule;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
public class TournamentBean implements Serializable {

    private ArrayList<Player> players = new ArrayList<>();
    private GameBean gameBean = new GameBean(); // Assume properly injected or instantiated
    private RuleBean ruleBean = new RuleBean(); // Assume properly injected or instantiated
    private MoveBean moveBean = new MoveBean(); // Assume properly injected or instantiated

    public TournamentBean() {}

    public ArrayList<Player> getPlayers() {
        return players;
    }
    
    public void addPlayer(Player player /*, get user move*/ ) {
        players.add(player);
        // CREAR UN DICCIONARIO CON UN PLAYER Y SU MOVIENTO
    }
    /*
    public void removePlayer(Player player) {
        for(int i = 0; i < players.size()-1 ; i ++){
            if(players.get(i).getId().equals(player.getId()))
            players.remove(i);
        }
    } 
    public void startTournament( if userid is admin){
         // Suponiendo que tengo el diccionario
        for(int i = 0; i < players.size()-3 ; i = i + 2){ // This loop goes two by two, it will create a game for every player in the tournament
            gameBean.CrearPartida(players.get(i), moveOfPlayer1); 
            gameBean.agregarMovimientoP2(games.get(0), players.get(i+1), moveOfPlayer2);
        }
        
    }*/
}
