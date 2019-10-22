package BlackJack.model.rules;

import BlackJack.model.Card;
import BlackJack.model.Player;

public class SoftSeventeen implements IHitStrategy {

    public boolean DoHit(Player a_dealer) {
        boolean isAce = false;
        for (Card c : a_dealer.GetHand())
            if (c.GetValue() == Card.Value.Ace)
                isAce = true;
        int g_hitLimit = 17;
        return a_dealer.CalcScore() == 17 && isAce || a_dealer.CalcScore() <= g_hitLimit;
    }
}
