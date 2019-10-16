package BlackJack.view;

import BlackJack.controller.CheckCommand;
import BlackJack.controller.PlayGame;

import java.util.ArrayList;
import java.util.List;

public class SimpleView implements IView 
{
    private List<CheckCommand> cKC;

    public SimpleView (){
        cKC = new ArrayList<CheckCommand>();
    }

  public void DisplayWelcomeMessage() {
          for(int i = 0; i < 50; i++) {System.out.print("\n");}
          System.out.println("Hello Black Jack World");

          GetInput();
        }

        public int GetInput()
        {
            System.out.println("Type 'p' to Play, 'h' to Hit, 's' to Stand or 'q' to Quit\n");
          try {
            int c = System.in.read();
            while (c == '\r' || c =='\n') {
              c = System.in.read();
            }
              if (c == 'p')
              {
                  for(CheckCommand csk:cKC ){
                      csk.newGameCommand();
                  }
              }
              else if (c == 'h')
              {
                  for(CheckCommand csk:cKC ){
                      csk.hitCommand();
                  }
              }
              else if (c == 's')
              {
                  for(CheckCommand csk:cKC ){
                      csk.standCommand();
                  }
              } else if(c=='q'){
                  for(CheckCommand csk:cKC ){
                      csk.quitCommand();
                  }
              }
              GetInput();

          } catch (java.io.IOException e) {
            System.out.println("" + e);

          }
            return 0;
        }



        public void DisplayCard(BlackJack.model.Card a_card)
        {
            System.out.println("" + a_card.GetValue() + " of " + a_card.GetColor());
        }

        public void DisplayPlayerHand(Iterable<BlackJack.model.Card> a_hand, int a_score)
        {
            DisplayHand("Player", a_hand, a_score);
        }

        public void DisplayDealerHand(Iterable<BlackJack.model.Card> a_hand, int a_score)
        {
            DisplayHand("Dealer", a_hand, a_score);
        }

        private void DisplayHand(String a_name, Iterable<BlackJack.model.Card> a_hand, int a_score)
        {
            System.out.println(a_name + " Has: ");
            for(BlackJack.model.Card c : a_hand)
            {
                DisplayCard(c);
            }
            System.out.println("Score: " + a_score);
            System.out.println("");
        }

        public void DisplayGameOver(boolean a_dealerIsWinner)
        {
            System.out.println("GameOver: ");
            if (a_dealerIsWinner)
            {
                System.out.println("Dealer Won!");
            }
            else
            {
                System.out.println("You Won!");
            }
            
        }

    @Override
    public void DisplayDealerStatus() {
        for ( int i = 0 ; i<1 ; i++ ){
            System.out.print("\n") ;
        }
        System.out.println("getting a card...\n");
    }

    @Override
    public void addCommand(CheckCommand cKC) {
        this.cKC.add(cKC);
    }
}
