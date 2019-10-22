package BlackJack.controller;

import BlackJack.model.DealtObserver;
import BlackJack.view.IView;
import BlackJack.model.Game;

public class PlayGame implements CommandObserver, DealtObserver {

    private Game game;
    private IView view;

    public PlayGame(Game a_game, IView a_view){
        view = a_view;
        a_view.addSubscriber(this);
        game = a_game;
        a_game.addSubscriber(this);
    }

    public void Play() {
        view.DisplayClearConsole();
        view.DisplayWelcomeMessage();
        showPlayerHands();
    }

    private void showPlayerHands(){
        view.DisplayDealerHand(game.GetDealerHand(), game.GetDealerScore());
        view.DisplayPlayerHand(game.GetPlayerHand(), game.GetPlayerScore());
    }

    @Override
    public void NotifyNewCardDealt() {
        try {
            view.DisplayDealerStatus();    // Display Dealer status
            Thread.sleep(2000);     //Delay
            view.DisplayDealerHand(game.GetDealerHand(), game.GetDealerScore());
            view.DisplayPlayerHand(game.GetPlayerHand(), game.GetPlayerScore());
        }
        catch ( InterruptedException e ) {
            e.printStackTrace();
        }
    }

    private void checkGameOver(){
        if (game.IsGameOver()) { view.DisplayGameOver(game.IsDealerWinner());}
    }

    @Override
    public void playPressed() {
        game.NewGame();
        showPlayerHands();
        checkGameOver();
    }

    @Override
    public void hitPressed() {
        game.Hit();
       // showPlayerHands();
        checkGameOver();
    }

    public void standPressed(){
        game.Stand();
        showPlayerHands();
        checkGameOver();
    }

    @Override
    public void quitPressed() {
        System.exit(0);
    }
}