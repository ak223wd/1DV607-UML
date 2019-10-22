package BlackJack;

import BlackJack.model.Game;
import BlackJack.view.*;
import BlackJack.controller.*;

public class Program {
  public static void main(String[] a_args) throws InterruptedException {
    Game g = new Game();
    IView v = new SimpleView();
    PlayGame myGame = new PlayGame(g, v);
    myGame.Play();
  }
}