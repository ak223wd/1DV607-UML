package BlackJack.model.rules;

public class RulesFactory {

  public IHitStrategy GetHitRule() {
    return new BasicHitStrategy();
  }
  public IHitStrategy GetSoft17Rule() {
    return new Soft17();
  }

  public INewGameStrategy GetNewGameRule() {
    return new AmericanNewGameStrategy();
  }
}