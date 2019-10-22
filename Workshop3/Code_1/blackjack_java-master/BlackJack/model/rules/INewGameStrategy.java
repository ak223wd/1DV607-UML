package BlackJack.model.rules;

import BlackJack.model.Card;
import BlackJack.model.Deck;
import BlackJack.model.Dealer;
import BlackJack.model.Player;

public abstract class INewGameStrategy {
    public abstract boolean NewGame(Deck a_deck, Dealer a_dealer, Player a_player);

    Card giveCard(Deck a_deck, Boolean b) {
        Card c = a_deck.GetCard();
        c.Show(b);
        return c;
    }

}