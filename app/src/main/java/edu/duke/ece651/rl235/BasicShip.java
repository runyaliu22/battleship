package edu.duke.ece651.rl235;

import java.util.HashMap;

//public class BasicShip implements Ship<Character> {

public class BasicShip<T> implements Ship<T> {

  protected ShipDisplayInfo<T> myDisplayInfo;

  // private final Coordinate myLocation;

  protected HashMap<Coordinate, Boolean> myPieces = new HashMap<>();

  // public BasicShip(Coordinate c){

  // this.myLocation = c;

  // myPieces = new HashMap<Coordinate, Boolean>();
  // myPieces.put(c, false);

  // }

  public BasicShip(Iterable<Coordinate> where, ShipDisplayInfo<T> myDisplayInfo) {
    
    this.myDisplayInfo = myDisplayInfo;

    for (Coordinate c : where) {
      this.myPieces.put(c, false);
    }

    

  }

  @Override
  public boolean occupiesCoordinates(Coordinate where) {

    if (myPieces.get(where) == null) {
       return false;
    }
    else{
      return true;
    }
    //if (myPieces.containsKey(where)) {
    //return true;
    //} else {
    //return false;
    //}
    
    // return where.equals(myLocation);
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
  public T getDisplayInfoAt(Coordinate where) {

    //return 's';
    //TODO this is not right.  We need to
    //look up the hit status of this coordinate
    return myDisplayInfo.getInfo(where, false);

  }

}
