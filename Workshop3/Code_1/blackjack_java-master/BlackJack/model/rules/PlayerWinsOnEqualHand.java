package BlackJack.model.rules;

import BlackJack.model.Dealer;
import BlackJack.model.Player;


public class PlayerWinsOnEqualHand implements IPlayerWinsOnEqualHand{
    public boolean isDealerWinner(Player a_player, Dealer a_dealer) {
        int max_score = 21;
        if (a_player.CalcScore() > max_score) return true;
        else if (a_dealer.CalcScore() > max_score) return false;
        return a_dealer.CalcScore() > a_player.CalcScore();
    }
}
