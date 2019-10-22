package BlackJack.model.rules;

import BlackJack.model.Card;
import BlackJack.model.Player;

public class Soft17 implements IHitStrategy {
    private final int g_hitLimit = 17;

    @Override
    public boolean DoHit(Player a_dealer) {
        if (a_dealer.CalcScore() < g_hitLimit) {
            return true;
        } else if (a_dealer.CalcScore() == g_hitLimit) {
            boolean hasAce = false;

            for (Card c : a_dealer.GetHand()) {
                if (c.GetValue() == Card.Value.Ace) {
                    hasAce = true;
                }
            }

            if (hasAce) {
                return true;
            }
        }

        return false;
    }
}
