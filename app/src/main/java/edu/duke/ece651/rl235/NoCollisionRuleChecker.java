package edu.duke.ece651.rl235;

public class NoCollisionRuleChecker<T> extends PlacementRuleChecker<T> {

  public NoCollisionRuleChecker(PlacementRuleChecker<T>next){
    super(next);
  }
  

  @Override
  protected boolean checkMyRule(Ship<T> theShip, Board<T> theBoard) {

    Iterable<Coordinate> ic = theShip.getCoordinates();

    for (Coordinate c: ic){

      if (theBoard.whatIsAt(c) != null){
        return false;
      }
      
    }

    return true;
    
  }

  

}
