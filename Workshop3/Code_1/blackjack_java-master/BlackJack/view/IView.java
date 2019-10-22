package BlackJack.view;

import BlackJack.controller.CommandObserver;

public interface IView {
  void DisplayWelcomeMessage();
  //int GetInput();
  void DisplayCard(BlackJack.model.Card a_card);
  void DisplayPlayerHand(Iterable<BlackJack.model.Card> hand, int score);
  void DisplayDealerHand(Iterable<BlackJack.model.Card> hand, int score);
  void DisplayGameOver(boolean a_dealerIsWinner);
  void DisplayClearConsole();
  void DisplayDealerStatus () ; // Observer
  void addSubscriber(CommandObserver subscriber);
}