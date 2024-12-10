
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

}
