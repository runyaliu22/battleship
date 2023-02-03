package edu.duke.ece651.rl235;

public abstract class PlacementRuleChecker<T> {

  private final PlacementRuleChecker<T> next;
     //more stuff


  public PlacementRuleChecker(PlacementRuleChecker<T> next) {
    this.next = next;
 }


  //protected abstract boolean checkMyRule(Ship<T> theShip, Board<T> theBoard);

  protected abstract String checkMyRule(Ship<T> theShip, Board<T> theBoard);
  
  /*
  public boolean checkPlacement (Ship<T> theShip, Board<T> theBoard) {
    //if we fail our own rule: stop the placement is not legal
    if (!checkMyRule(theShip, theBoard)) {
      return false;
    }
    //other wise, ask the rest of the chain.
    if (next != null) {
      return next.checkPlacement(theShip, theBoard); 
    }
    //if there are no more rules, then the placement is legal
    return true;
  }
  */

  public String checkPlacement (Ship<T> theShip, Board<T> theBoard) {
    //if we fail our own rule: stop the placement is not legal

    String s = checkMyRule(theShip, theBoard);
    if (s != null) {
      return s;
    }
    //other wise, ask the rest of the chain.
    if (next != null) {
      return next.checkPlacement(theShip, theBoard); 
    }
    //if there are no more rules, then the placement is legal
    return null;
  }



  

}
