
package Admin;

import Admin.PlayerBean.Player;
import Admin.RuleBean.Rule;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TournamentBean implements Serializable {

    private ArrayList<Player> players = new ArrayList<>();
    private GameBean gameBean = new GameBean(); 
    private RuleBean ruleBean = new RuleBean();
    private MoveBean moveBean = new MoveBean(); 
    private Map<Player, String> playerMoves = new HashMap<>();
    
    
    public TournamentBean() {}
    
    public void setGameBean(GameBean gameBean) {
        this.gameBean = gameBean;
    }

    public void setRuleBean(RuleBean ruleBean) {
        this.ruleBean = ruleBean;
    }

    public void setMoveBean(MoveBean moveBean) {
        this.moveBean = moveBean;
    }

    
    public void addPlayer(Player player, String move) {
        players.add(player);
        playerMoves.put(player, move);
    }
    
    public void removePlayer(Player player) {
        players.remove(player);
        playerMoves.remove(player);
    }
    
    public void startTournament(int userId) throws Exception {
        if (!isAdmin(userId)) {
            throw new Exception("El usuario no tiene permisos para iniciar el torneo.");
        }
        if (players.size() < 2) {
            throw new Exception("No hay suficientes jugadores para iniciar el torneo.");
        }

        for (int i = 0; i < players.size() - 1; i += 2) {
            Player player1 = players.get(i);
            Player player2 = players.get(i + 1);

            Move movePlayer1 = playerMoves.get(player1);
            Move movePlayer2 = playerMoves.get(player2);


            GameBean.Game game = gameBean.new Game(player1, movePlayer1);
            gameBean.partidas().add(game);
            gameBean.agregarMovimientP2(game, player2, movePlayer2);
        }
    }
    /*
    private boolean isAdmin(int userId) {

        AdminFacade adminFacade = new AdminFacade();
        return adminFacade.isAdmin(userId);
    }
    */
}
