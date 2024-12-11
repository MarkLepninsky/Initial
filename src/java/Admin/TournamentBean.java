package Admin;

import Admin.PlayerBean.Player;
import Admin.RuleBean.Rule;
import Admin.MoveBean.Move;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named(value = "tournament")
@SessionScoped
public class TournamentBean implements Serializable {

    private ArrayList<Player> players = new ArrayList<>();
    private Map<Player, Move> playerMoves = new HashMap<>();
    private GameBean gameBean = new GameBean(); 
    private RuleBean ruleBean = new RuleBean(); 
    private MoveBean moveBean = new MoveBean();

    public TournamentBean() {}

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public Map<Player, Move> getPlayerMoves() {
        return playerMoves;
    }

    public void addPlayer(Player player, Move move) {
        if (!players.contains(player)) {
            players.add(player);
            playerMoves.put(player, move);
        }
    }

    public void updatePlayerMove(Player player, Move move) {
        if (players.contains(player)) {
            playerMoves.put(player, move);
        }
    }
 
    // Start the tournament
     public Player startTournament(int userId) {
        if (players.size() < 2) {
            return null; 
        }
        // if (userId != admin), no start tournament

        List<Player> currentRoundPlayers = new ArrayList<>(players);
        

        while (currentRoundPlayers.size() > 1) {
            List<Player> nextRoundPlayers = new ArrayList<>();

            for (int i = 0; i < currentRoundPlayers.size(); i += 2) {
                if (i + 1 < currentRoundPlayers.size()) { 
                    
                    // get the player and moves and play
                    Player player1 = currentRoundPlayers.get(i);
                    Player player2 = currentRoundPlayers.get(i + 1);
                    
                    Move move1 = playerMoves.get(player1);
                    Move move2 = playerMoves.get(player2);
                    
                    // play the game
                    GameBean.Game game = new GameBean.Game(player1, player2, move1, move2);
                    Player winner = playGame(game);
                    
                    // the winner to the next round
                    if (winner != null) {
                        nextRoundPlayers.add(winner);
                    }
                }
            }
            currentRoundPlayers = nextRoundPlayers;
        }

        Player tournamentWinner = currentRoundPlayers.get(0);
        
        return tournamentWinner;
    }

    public Player playGame(GameBean.Game game) {

        for (int i = 0; i < game.rules.size(); i++) {
            if (game.rules.get(i).nameG.equals(game.mp1.name) && game.rules.get(i).nameP.equals(game.mp2.name)) {
                return game.player1;
            } else if (game.rules.get(i).nameG.equals(game.mp2.name) && game.rules.get(i).nameP.equals(game.mp1.name)) {
                return game.player2;
            }
        }
        return null; //
    }
}