package BlackJack.model.rules;

import BlackJack.model.Deck;
import BlackJack.model.Dealer;
import BlackJack.model.Player;
import BlackJack.model.Card;  

class InternationalNewGameStrategy extends INewGameStrategy {

  public boolean NewGame(Deck a_deck, Dealer a_dealer, Player a_player) {
    a_player.DealCard(giveCard(a_deck,true));
    a_dealer.DealCard(giveCard(a_deck,true));
    a_player.DealCard(giveCard(a_deck,true));
    return true;
  }

}