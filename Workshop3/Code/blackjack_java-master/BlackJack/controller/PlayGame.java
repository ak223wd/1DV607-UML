package BlackJack.controller;

import BlackJack.view.IView;
import BlackJack.model.Game;
import BlackJack.model.DealerObs;

public class PlayGame implements CheckCommand, DealerObs {

  private Game aGame;
  private IView iview;

  public PlayGame(Game a_game, IView a_view){
    iview = a_view;
    a_view.addCommand(this);
    aGame = a_game;
    a_game.addCommand(this);
  }


  public void Play() {
    iview.DisplayWelcomeMessage();
    showPlayerHand();


}


private void showPlayerHand(){
  iview.DisplayDealerHand(aGame.GetDealerHand(), aGame.GetDealerScore());
  iview.DisplayPlayerHand(aGame.GetPlayerHand(), aGame.GetPlayerScore());

}

private void isEndGame(){
  if (aGame.IsGameOver())
  {
    iview.DisplayGameOver(aGame.IsDealerWinner());
  }
}

  //WHEN EACH COMMAND IS PRESSED
  @Override
  public void newGameCommand() {
    aGame.NewGame();
    showPlayerHand();

  }

  @Override
  public void hitCommand() {
    aGame.Hit();
    isEndGame();

  }

  @Override
  public void standCommand() {
    aGame.Stand();
    showPlayerHand();
    isEndGame();

  }

  @Override
  public void quitCommand() {
    System.exit(0);

  }

  @Override
  public void NotifyNewCardDealt() {
    try {
      iview.DisplayDealerStatus();    // Display Dealer status
      Thread.sleep(2000);     //Delay
      iview.DisplayDealerHand(aGame.GetDealerHand(), aGame.GetDealerScore());
      iview.DisplayPlayerHand(aGame.GetPlayerHand(), aGame.GetPlayerScore());
    }
    catch ( InterruptedException e ) {
      e.printStackTrace();
    }

  }
}