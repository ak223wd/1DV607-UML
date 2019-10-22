package BlackJack.view;

import BlackJack.controller.CommandObserver;

import java.util.ArrayList;
import java.util.List;

public class SimpleView implements IView {

    private List<CommandObserver> subscribers;

    public SimpleView(){
        subscribers = new ArrayList<CommandObserver>();
    }

    public void DisplayWelcomeMessage() {
      for(int i = 0; i < 1; i++){System.out.print("\n");}
      System.out.println(" _____________________________________________");
      System.out.println("|                                             |");
      System.out.println("| ҉҉҉҉҉҉     Welcome to BLACKJACK      ҉҉҉҉҉҉ |") ;
      System.out.println("|_____________________________________________|\n");
      GetInput();
  }

    private void GetInput(){
        System.out.println("Type: 'p' to Play, 'h' to Hit, 's' to Stand or 'q' to Quit\n");
        try {
            int c = System.in.read();
            while (c == '\r' || c =='\n') { c = System.in.read(); }
            if ( c=='p')
                for (CommandObserver subscriber : subscribers)
                    subscriber.playPressed();
            else if (c=='h')
                for (CommandObserver subscriber : subscribers)
                    subscriber.hitPressed();
            else if (c=='s')
                for (CommandObserver subscriber : subscribers)
                    subscriber.standPressed();
            else if (c=='q')
                for (CommandObserver subscriber : subscribers)
                    subscriber.quitPressed();
            GetInput();
        } catch (java.io.IOException e) { System.out.println("" + e); }
    }

    @Override
    public void DisplayDealerStatus() {
        for ( int i = 0 ; i<1 ; i++ )
            System.out.print("\n") ;
        System.out.println("getting a card...\n");
    }

    @Override
    public void addSubscriber(CommandObserver subscriber) {
        subscribers.add(subscriber);
    }

    public void DisplayCard(BlackJack.model.Card a_card) {
      System.out.println("" + a_card.GetValue() + " of " + a_card.GetColor());
    }

    public void DisplayPlayerHand(Iterable<BlackJack.model.Card> hand, int score) {
         DisplayHand("Player", hand, score);
    }

    public void DisplayDealerHand(Iterable<BlackJack.model.Card> hand, int score) {
        DisplayHand("Dealer", hand, score);
    }

    private void DisplayHand(String name, Iterable<BlackJack.model.Card> hand, int score) {
        System.out.println(name + " Has: ");
        for(BlackJack.model.Card c : hand) { DisplayCard(c); }
        System.out.println("Score: " + score);
        System.out.println("");
    }

    public void DisplayGameOver(boolean a_dealerIsWinner) {
        System.out.println("GameOver: ");
        if (a_dealerIsWinner)  System.out.println("Dealer Won!");
        else  System.out.println("You Won!");
    }

    public void DisplayClearConsole(){ System.out.flush(); }

}