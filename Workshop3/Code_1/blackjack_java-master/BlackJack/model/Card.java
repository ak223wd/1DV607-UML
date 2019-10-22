package BlackJack.model;

public class Card {

  public enum Color {
    Hearts,
    Spades,
    Diamonds,
    Clubs,
    Count,
    Hidden
  }

  public enum Value {
    Two,
    Three,
    Four,
    Five,
    Six,
    Seven,
    Eight,
    Nine,
    Ten,
    Knight,
    Queen,
    King,
    Ace,
    Count,
    Hidden
  }
  
  private Color m_color;
  private Value m_value;
  private boolean m_isHidden;
  
  public Card(Color a_color, Value a_value) {
    m_value = a_value;
    m_color = a_color;
    m_isHidden = true;
  }
  
  public Color GetColor() { return m_isHidden? Color.Hidden : m_color; }
  public Value GetValue() { return m_isHidden?  Value.Hidden: m_value; }
  public void Show(boolean a_show) {
      m_isHidden = !a_show;
  }
}