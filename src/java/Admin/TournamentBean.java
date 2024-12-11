
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
    
    public void addPlayer(Player player) {
        players.add(player);
    }
    /*
    public void removePlayer(Player player) {
        for(int i = 0; i < players.size()-1 ; i ++){
            if(players.get(i).getId().equals(player.getId()))
            players.remove(i);
        }
    } 
    public void startTournament(){
        gameBean.CrearPartida(player, mp1);
    }*/
}
