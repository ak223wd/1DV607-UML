package BlackJack.model.rules;

import BlackJack.model.Player;

class BasicHitStrategy implements IHitStrategy {

    public boolean DoHit(Player a_dealer) {
        int g_hitLimit = 17;
        return a_dealer.CalcScore() < g_hitLimit;
    }
}