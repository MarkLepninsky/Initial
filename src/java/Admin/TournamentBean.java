
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

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    public ArrayList<Player> playTournament() {
        ArrayList<Player> playersTemp = new ArrayList<>();

        while (players.size() > 1) {
            if (players.size() % 2 != 0) {
                playersTemp.add(players.remove(0)); // Move odd player to next round
            }
            int size = players.size() / 2;
            for (int i = 0; i < size; i++) {
                Player player1 = players.get(2 * i);
                Player player2 = players.get(2 * i + 1);

                // Simulate move selection for each player
                MoveBean.Move move1 = decideMove(player1);
                MoveBean.Move move2 = decideMove(player2);

                // Create Game object and determine winner using playGame
                GameBean.Game juego = new Game (player1,move1);

                if (winner != null) {
                    playersTemp.add(winner); // Add winner to next round
                }
            }

            // Prepare for the next round
            players.clear();
            players.addAll(playersTemp);
            playersTemp.clear();
        }

        return players; // Return final winner
    }

    private MoveBean.Move decideMove(Player player) {
        ArrayList<MoveBean.Move> moves = moveBean.getMoves();
        if (!moves.isEmpty()) {
            int randomIndex = (int) (Math.random() * moves.size());
            return moves.get(randomIndex); // Randomly choose a move
        } else {
            return new MoveBean.Move("Default", "Default Move"); // Fallback move
        }
    }
}
