package BlackJack.model.rules;

import BlackJack.model.Dealer;
import BlackJack.model.Player;

public interface IPlayerWinsOnEqualHand {
    boolean isDealerWinner(Player a_player, Dealer a_dealer);
}
