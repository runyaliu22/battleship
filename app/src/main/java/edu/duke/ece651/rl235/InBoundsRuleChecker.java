package edu.duke.ece651.rl235;

public class InBoundsRuleChecker<T> extends PlacementRuleChecker<T>{

  public InBoundsRuleChecker(PlacementRuleChecker<T> next) {
    super(next);
  }


  @Override
  protected boolean checkMyRule(Ship<T> theShip, Board<T> theBoard) {


    
    Iterable<Coordinate> ic = theShip.getCoordinates();
    
    for (Coordinate c : ic){

      //if (c.getRow() <0 || c.getRow() >= theBoard.getHeight() ){
      if (c.getRow() >= theBoard.getHeight() ){
        return false;
      }

      //if ( c.getColumn() <0 || c.getColumn() >= theBoard.getWidth()){
      if ( c.getColumn() >= theBoard.getWidth()){
        return false;
      }
      
    }

    return true;
    
  }

  

  

}
