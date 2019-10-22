package BlackJack.model.rules;

public class RulesFactory {

  public IHitStrategy GetHitRule() {
      //return new BasicHitStrategy();
      return new SoftSeventeen();
  }
  public INewGameStrategy GetNewGameRule() {
      return new AmericanNewGameStrategy();
      //return new InternationalNewGameStrategy();
  }
  public IPlayerWinsOnEqualHand GetWinnerRule() {
      return new PlayerWinsOnEqualHand();
  }
}