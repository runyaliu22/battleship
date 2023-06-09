package edu.duke.ece651.rl235;

import java.util.HashSet;

public class RectangleShip<T> extends BasicShip<T> {

  private final String name;
  private final Character Orientation;
  

  static HashSet<Coordinate> makeCoords(Coordinate upperLeft, int width, int height){//public?

    HashSet<Coordinate> mySet = new HashSet<Coordinate>();

    for (int i = upperLeft.getColumn(); i <= upperLeft.getColumn() + width - 1; i++){

      for (int j = upperLeft.getRow(); j <= upperLeft.getRow() + height - 1; j++){

        mySet.add(new Coordinate(j, i));
        
      }
      
    }

    return mySet;

    
  }
  
  /*
  public RectangleShip(String name,Coordinate upperLeft, int width, int height, ShipDisplayInfo<T> myinfo, ShipDisplayInfo<T> eninfo){

    super(RectangleShip.makeCoords(upperLeft, width, height), myinfo, eninfo);//Is it right?
    this.name = name;
    //call the constructor of the parent class
    
  }
  */

  public RectangleShip(String name, Iterable<Coordinate> where, int width, int height, ShipDisplayInfo<T> myinfo, ShipDisplayInfo<T> eninfo){

    super(where, myinfo, eninfo);//Is it right?
    
    this.name = name;

    if (width > height){
      this.Orientation = 'H';
    }
    else{
      this.Orientation = 'V';
    }
    
    //call the constructor of the parent class
    
  }

  public RectangleShip(String name, Coordinate upperLeft, int width, int height, T data, T onHit) {
    this(name, RectangleShip.makeCoords(upperLeft, width, height), width,  height, new SimpleShipDisplayInfo<T>(data, onHit), new SimpleShipDisplayInfo<>(null, data));//calling the constructor right above
  }
  
  public RectangleShip(Coordinate upperLeft, T data, T onHit) {//convinience constructor
    this("testship", upperLeft, 1, 1, data, onHit);
  }//call the constructor right above

  

  @Override
  public String getName() {

    return name;
  }

  @Override
  public Character getOrientation() {
    
    return Orientation;
  }


    
  

  
  
 
}
