package edu.duke.ece651.rl235;

import java.util.HashSet;

public class ZCarrier<T> extends BasicShip<T> {

  private final String name;
  private final Character Orientation;
  

  public ZCarrier(String name, Iterable<Coordinate> where, Character orientation, ShipDisplayInfo<T> myinfo, ShipDisplayInfo<T> eninfo){

    //super(TBattleship.makeCoords(upperLeft, orientation), myinfo, eninfo);//Is it right?

    super(where, myinfo, eninfo);
    this.name = name;

    this.Orientation = orientation;
    
    //call the constructor of the parent class
    
  }
  

  public ZCarrier(String name, Coordinate upperLeft, Character orientation, T data, T onHit) {
    this(name, ZCarrier.makeCoords(upperLeft, orientation), orientation, new SimpleShipDisplayInfo<T>(data, onHit), new SimpleShipDisplayInfo<>(null, data));//calling the constructor right above
    
  }
  
  
  public ZCarrier(Coordinate upperLeft, T data, T onHit) {//convinience constructor
    this("testZCarrier", upperLeft, 'U', data, onHit);
  }//call the constructor right above
  
  
  

  static HashSet<Coordinate> makeCoords(Coordinate upperLeft, Character orientation) {
    int r = upperLeft.getRow();
    int c = upperLeft.getColumn();
    HashSet<Coordinate> res = new HashSet<>();
    if (orientation == 'U') {
      res.add(new Coordinate(r, c));
      res.add(new Coordinate(r + 1, c));
      res.add(new Coordinate(r + 2, c));
      res.add(new Coordinate(r + 3, c));
      res.add(new Coordinate(r + 2, c + 1));
      res.add(new Coordinate(r + 3, c + 1));
      res.add(new Coordinate(r + 4, c + 1));
    }
    else if (orientation == 'R') {
      res.add(new Coordinate(r, c + 1));
      res.add(new Coordinate(r, c + 2));
      res.add(new Coordinate(r, c + 3));
      res.add(new Coordinate(r, c + 4));
      res.add(new Coordinate(r + 1, c));
      res.add(new Coordinate(r + 1, c + 1));
      res.add(new Coordinate(r + 1, c + 2));
    }
    else if (orientation == 'D') {
      res.add(new Coordinate(r, c));
      res.add(new Coordinate(r + 1, c));
      res.add(new Coordinate(r + 2, c));
      res.add(new Coordinate(r + 1, c + 1));
      res.add(new Coordinate(r + 2, c + 1));
      res.add(new Coordinate(r + 3, c + 1));
      res.add(new Coordinate(r + 4, c + 1));
    }
    else {
      res.add(new Coordinate(r, c + 2));
      res.add(new Coordinate(r, c + 3));
      res.add(new Coordinate(r, c + 4));
      res.add(new Coordinate(r + 1, c));
      res.add(new Coordinate(r + 1, c + 1));
      res.add(new Coordinate(r + 1, c + 2));
      res.add(new Coordinate(r + 1, c + 3));
    }
    return res;
  }
  


  
  @Override
  public String getName() {
    return name;
  }


  @Override
  public Character getOrientation() {

    return Orientation;
    
  }

  

}
