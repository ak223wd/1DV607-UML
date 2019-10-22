package BlackJack.controller;

import BlackJack.view.IView;
import BlackJack.model.Game;

public class PlayGame {


  public boolean Play(Game a_game, IView a_view) {
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
/*
  public void newGameCommand() {
    aGame.NewGame();
    showPlayerHand();

  }


  public void hitCommand() {
    aGame.Hit();
    isEndGame();

  }


  public void standCommand() {
    aGame.Stand();
    showPlayerHand();
    isEndGame();

  }


  public void quitCommand() {
    System.exit(0);

  }
  */
}