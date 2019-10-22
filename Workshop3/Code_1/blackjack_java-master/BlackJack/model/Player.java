package BlackJack.model;

import java.util.List;
import java.util.LinkedList;

public class Player  {

    private List<Card> m_hand;
    final int g_maxScore = 21;

    public Player() {
        m_hand = new LinkedList<Card>();
    }

    public void DealCard(Card a_addToHand) {
        m_hand.add(a_addToHand);
    }

    public Iterable<Card> GetHand() {
        return m_hand;
    }

    void ClearHand() {
        m_hand.clear();
    }

    void ShowHand() {
        for(Card c : m_hand)
            c.Show(true);
    }

    public int CalcScore() {
        // the number of scores is dependant on the number of values
        // cardScores[13] = {...};
        int cardScores[] = {
                2, 3, 4, 5, 6, 7, 8, 9, 10, 10 ,10 ,10, 11
        };
        if ((cardScores.length != Card.Value.Count.ordinal()))
            throw new AssertionError("Card Scores array size does not match number of card values");
        int score = 0;
        for(Card c : GetHand())
            if (c.GetValue() != Card.Value.Hidden)
                score += cardScores[c.GetValue().ordinal()];


        if (score > g_maxScore)
            for(Card c : GetHand())
                if (c.GetValue() == Card.Value.Ace && score > g_maxScore)
                    score -= 10;

        return score;
    }
}