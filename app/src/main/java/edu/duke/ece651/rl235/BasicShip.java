package edu.duke.ece651.rl235;

public class BasicShip implements Ship<Character> {

  private final Coordinate myLocation;

  public BasicShip(Coordinate location){
    
    this.myLocation = location;
  }

  @Override
  public boolean occupiesCoordinates(Coordinate where) {
    
    return where.equals(myLocation);
  }

  @Override
  public boolean isSunk() {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public void recordHitAt(Coordinate where) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public boolean wasHitAt(Coordinate where) {
   
    return false;
  }

  @Override
  public Character getDisplayInfoAt(Coordinate where) {
   
    return 's';
    
  }

  
  

}
