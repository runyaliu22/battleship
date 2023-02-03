package edu.duke.ece651.rl235;

import java.util.ArrayList;

public class BattleShipBoard<T> implements Board<T> {

  private final int width;

  private final int height;

  private final ArrayList<Ship<T>> myShips;

  private final PlacementRuleChecker<T> placementChecker;

  public int getWidth() {
    return width;

  }

  public int getHeight() {
    return height;
  }

  /**
   * Constructs a BattleShipBoard with the specified width
   * and height
   * 
   * @param w is the width of the newly constructed board.
   * @param h is the height of the newly constructed board.
   * @throws IllegalArgumentException if the width or height are less than or
   *                                  equal to zero.
   */

  public BattleShipBoard(int w, int h) {

    this(w, h, new InBoundsRuleChecker<T>(new NoCollisionRuleChecker<T>(null)));// what does null do?
    // if (w <= 0){
    // throw new IllegalArgumentException("BattleShipBoard's width must be positive
    // but is " + w);
    // }
    // if (h <= 0){
    // throw new IllegalArgumentException("BattleShipBoard's height must be positive
    // but is " + h);
    // }
    // this.width = w;
    // this.myShips = new ArrayList<Ship<T>>();
    // this.height = h;

  }

  // public BattleShipBoard(int w, int h, InBoundsRuleChecker<T> ibrk){
  public BattleShipBoard(int w, int h, PlacementRuleChecker<T> prc) {

    if (w <= 0) {
      throw new IllegalArgumentException("BattleShipBoard's width must be positive but is " + w);
    }
    if (h <= 0) {
      throw new IllegalArgumentException("BattleShipBoard's height must be positive but is " + h);
    }
    this.width = w;
    this.myShips = new ArrayList<Ship<T>>();
    this.height = h;

    this.placementChecker = prc;

  }
  /*
  public boolean tryAddShip(Ship<T> toAdd) {

    if (placementChecker.checkPlacement(toAdd, this)) {
      myShips.add(toAdd);
      return true;
    }

    return false;

    // myShips.add(toAdd);

    //return true;

  }
  */

  public String tryAddShip(Ship<T> toAdd) {

    String s = placementChecker.checkPlacement(toAdd, this);

    if (s == null){
      myShips.add(toAdd);
      return null;
    }
    
    return s;

    
  }

  

  public T whatIsAt(Coordinate where) {
    for (Ship<T> s : myShips) {
      if (s.occupiesCoordinates(where)) {// see if my coordinate where is actually a ship!
        return s.getDisplayInfoAt(where);// !interesting!
      }
    }
    return null;
  }

}
