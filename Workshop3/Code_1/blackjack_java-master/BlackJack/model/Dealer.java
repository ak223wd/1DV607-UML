package BlackJack.model;

import BlackJack.model.rules.*;

import java.util.ArrayList;
import java.util.List;

public class Dealer extends Player {

  private Deck m_deck;
  private INewGameStrategy m_newGameRule;
  private IHitStrategy m_hitRule;
  private IPlayerWinsOnEqualHand m_WinnerRule;
  private List<DealtObserver> subscribers ;

  Dealer(RulesFactory a_rulesFactory) {
    m_newGameRule = a_rulesFactory.GetNewGameRule();
    m_hitRule = a_rulesFactory.GetHitRule();
    m_WinnerRule = a_rulesFactory.GetWinnerRule();
    subscribers = new ArrayList<DealtObserver>();

  }

  boolean NewGame(Player a_player) {
    if (m_deck == null || IsGameOver()) {
      m_deck = new Deck();
      ClearHand();
      a_player.ClearHand();
      return m_newGameRule.NewGame(m_deck, this, a_player);
    }
    return false;
  }

  boolean Hit(Player a_player) {
    if (m_deck != null && a_player.CalcScore() < g_maxScore && !IsGameOver()) {
      Card c = DealAndShowCard();
      a_player.DealCard(c);
      for (DealtObserver subscriber : subscribers) {
          subscriber.NotifyNewCardDealt();            // calls the Notify () in playGame that a new card has been dealt
      }
      return true;
    }return false;
  }

  boolean Stand() {
    if (m_deck != null) {
      ShowHand();
      while (m_hitRule.DoHit(this)) {
        Card c = DealAndShowCard();
        DealCard(c);
        return true;
      }
    }
    return false;
  }

  boolean IsDealerWinner(Player a_player) {
    return m_WinnerRule.isDealerWinner(a_player, this);
  }

  boolean IsGameOver() { return m_deck != null && !m_hitRule.DoHit(this); }

  private Card DealAndShowCard() {
    Card c = m_deck.GetCard();
    c.Show(true);
    return c;
  }

  public void addSubscriber(DealtObserver subscriber){subscribers.add(subscriber); }

}