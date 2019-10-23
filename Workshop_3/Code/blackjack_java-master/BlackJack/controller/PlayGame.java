package BlackJack.controller;

import BlackJack.model.rules.IObserverGame;
import BlackJack.view.IView;
import BlackJack.model.Game;

public class PlayGame implements IObserverGame {

  private Game o_game;
  private IView o_view;
  public boolean Play(Game a_game, IView a_view) {
    o_game = a_game;
    o_view = a_view;

    o_game.addGameObserver(this); //Adding Playgame (who implements IobserverGame) to the GameObservable.
    a_view.DisplayWelcomeMessage();

    a_view.DisplayDealerHand(a_game.GetDealerHand(), a_game.GetDealerScore());
    a_view.DisplayPlayerHand(a_game.GetPlayerHand(), a_game.GetPlayerScore());

    if (a_game.IsGameOver()) {
      a_view.DisplayGameOver(a_game.IsDealerWinner());
    }

    int input = a_view.GetInput();

    switch(input){
      case 1:
        a_game.NewGame();
        break;
      case 2:
        a_game.Hit();
        break;
      case 3:
        a_game.Stand();
        break;
      case 4:
        return false;
    }

    return true;
  }

  @Override
  public void updateObservers() {
    try{
      Thread.sleep(3000);
    }catch(InterruptedException e){
      e.printStackTrace();
    }
    o_view.DisplayDealerHand(o_game.GetDealerHand(), o_game.GetDealerScore());
    o_view.DisplayPlayerHand(o_game.GetPlayerHand(), o_game.GetPlayerScore());



  }
}