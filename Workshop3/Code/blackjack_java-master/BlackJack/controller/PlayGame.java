package BlackJack.controller;

import BlackJack.view.IView;
import BlackJack.model.Game;

public class PlayGame implements CheckCommand {

  private Game aGame;
  private IView iview;

  public PlayGame(Game a_game, IView a_view){
    iview = a_view;
    aGame = a_game;
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
}