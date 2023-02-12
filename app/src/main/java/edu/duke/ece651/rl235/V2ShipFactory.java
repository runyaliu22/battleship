package edu.duke.ece651.rl235;

public class V2ShipFactory extends V1ShipFactory {

  @Override
  public Ship<Character> makeBattleship(Placement where) {
    
    return new TBattleship<Character>("Battleship", where.getCoordinate(), where.getOrientation(), 'b', '*');
    
  }

  @Override
  public Ship<Character> makeCarrier(Placement where) {
   
    return new ZCarrier<Character>("Carrier", where.getCoordinate(), where.getOrientation(), 'c', '*');
    
  }
  
}
