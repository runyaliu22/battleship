package edu.duke.ece651.rl235;

import java.util.HashSet;

public class TBattleship<T> extends BasicShip<T> {

  private final String name;
  /*
  static HashSet<Coordinate> makeCoords(Coordinate upperLeft, Character orientation) {
    int r = upperLeft.getRow();
    int c = upperLeft.getColumn();
    HashSet<Coordinate> res = new HashSet<>();
    if (orientation == 'U') {
      res.add(new Coordinate(r, c + 1));
      res.add(new Coordinate(r + 1, c));
      res.add(new Coordinate(r + 1, c + 1));
      res.add(new Coordinate(r + 1, c + 2));
    } else if (orientation == 'R') {
      res.add(new Coordinate(r, c));
      res.add(new Coordinate(r + 1, c));
      res.add(new Coordinate(r + 1, c + 1));
      res.add(new Coordinate(r + 2, c));
    } else if (orientation == 'D') {
      res.add(new Coordinate(r, c));
      res.add(new Coordinate(r, c + 1));
      res.add(new Coordinate(r, c + 2));
      res.add(new Coordinate(r + 1, c + 1));
    } else {
      res.add(new Coordinate(r, c + 1));
      res.add(new Coordinate(r + 1, c));
      res.add(new Coordinate(r + 1, c + 1));
      res.add(new Coordinate(r + 2, c + 1));
    }
    return res;
  }
  */

  
  public TBattleship(String name, Iterable<Coordinate> where, ShipDisplayInfo<T> myinfo, ShipDisplayInfo<T> eninfo){

    //super(TBattleship.makeCoords(upperLeft, orientation), myinfo, eninfo);//Is it right?

    super(where, myinfo, eninfo);
    this.name = name;
    
    //call the constructor of the parent class
    
  }
  

  public TBattleship(String name, Coordinate upperLeft, Character orientation, T data, T onHit) {
    this(name, TBattleship.makeCoords(upperLeft, orientation), new SimpleShipDisplayInfo<T>(data, onHit), new SimpleShipDisplayInfo<>(null, data));//calling the constructor right above
  }
  
  
  public TBattleship(Coordinate upperLeft, T data, T onHit) {//convinience constructor
    this("testTBattleship", upperLeft, 'U', data, onHit);
  }//call the constructor right above
  
  

  

  static HashSet<Coordinate> makeCoords(Coordinate upperLeft, Character orientation) {
    int r = upperLeft.getRow();
    int c = upperLeft.getColumn();
    HashSet<Coordinate> res = new HashSet<>();
    
    if (orientation == 'U') {
      res.add(new Coordinate(r, c + 1));
      res.add(new Coordinate(r + 1, c));
      res.add(new Coordinate(r + 1, c + 1));
      res.add(new Coordinate(r + 1, c + 2));
    }
    else if (orientation == 'R') {
      res.add(new Coordinate(r, c));
      res.add(new Coordinate(r + 1, c));
      res.add(new Coordinate(r + 1, c + 1));
      res.add(new Coordinate(r + 2, c));
    }
    else if (orientation == 'D') {
      res.add(new Coordinate(r, c));
      res.add(new Coordinate(r, c + 1));
      res.add(new Coordinate(r, c + 2));
      res.add(new Coordinate(r + 1, c + 1));
    }
    else {
      res.add(new Coordinate(r, c + 1));
      res.add(new Coordinate(r + 1, c));
      res.add(new Coordinate(r + 1, c + 1));
      res.add(new Coordinate(r + 2, c + 1));
    }
    return res;
  }
  
  

  
  @Override
  public String getName() {
    return name;
  }

  

}
