package edu.duke.ece651.rl235;

import java.util.ArrayList;
import java.util.HashMap;

//public class BasicShip implements Ship<Character> {

public abstract class BasicShip<T> implements Ship<T> {

  protected ShipDisplayInfo<T> myDisplayInfo;

  protected ShipDisplayInfo<T> enemyDisplayInfo;


  // private final Coordinate myLocation;

  protected HashMap<Coordinate, Boolean> myPieces = new HashMap<>();

  // public BasicShip(Coordinate c){

  // this.myLocation = c;
  

  // myPieces = new HashMap<Coordinate, Boolean>();
  // myPieces.put(c, false);

  // }

  

  public BasicShip(Iterable<Coordinate> where, ShipDisplayInfo<T> myDisplayInfo, ShipDisplayInfo<T> enemyDisplayInfo) {

    this.enemyDisplayInfo = enemyDisplayInfo;
    
    this.myDisplayInfo = myDisplayInfo;

    for (Coordinate c : where) {
      //this.myPieces.put(c, false);
      myPieces.put(c, false);
    }

  }

  public void update(HashMap<Coordinate, Boolean> myPieces_new){

    myPieces.clear();

    myPieces.putAll(myPieces_new);
    
  }

  
  //why no override?
  public Iterable<Coordinate> getCoordinates(){

    return myPieces.keySet();

  }

  //coordinate row and column less than 0
  public HashMap<Coordinate, Boolean> rotateMyPieces(ArrayList<Integer> rotationMatrix){

    HashMap<Coordinate, Boolean> a = new HashMap<>();

    for (Coordinate c : myPieces.keySet()){

      a.put(new Coordinate(c.getRow() * rotationMatrix.get(0) + c.getColumn() * rotationMatrix.get(1), c.getRow() * rotationMatrix.get(2) + c.getColumn() * rotationMatrix.get(3)), myPieces.get(c));
      
      
    }

    /*
    for (Coordinate b: a.keySet()){
      System.out.println(b);
      
    }
    */
    return a;

  }

  
  //can be moved to textplayer
  //need to be changed, min row, min column
  public Coordinate getUpperLeft(){

    Coordinate myCoordinate = null;

     for (Coordinate c : myPieces.keySet()){

       //System.out.println(c);

       if (myCoordinate == null){
         myCoordinate = c;
       }

       else{

         if (c.getRow() < myCoordinate.getRow()){
           myCoordinate = c;
         }

         if (c.getRow() == myCoordinate.getRow() && c.getColumn() < myCoordinate.getColumn()){
           myCoordinate = c;
         }
         
       }
      
    }

     return myCoordinate;

    
    
    
  }

  

  protected void checkCoordinateInThisShip(Coordinate c){
    if (myPieces.get(c) == null){
      throw new IllegalArgumentException("the coordinate c does not belong to this ship");
    }
  }//added
  

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

    for (Coordinate c : myPieces.keySet()){
      if (myPieces.get(c) == false){
        return false;
      }
    }
    
    return true;
  }

  @Override
  public void  recordHitAt(Coordinate where) {
    
    checkCoordinateInThisShip(where);
    
    myPieces.put(where, true);

  }

  @Override
  public boolean wasHitAt(Coordinate where) {

    checkCoordinateInThisShip(where);
    
    return myPieces.get(where);
  }

  @Override
  public T getDisplayInfoAt(Coordinate where, boolean myShip) {//decide whether myDisplayInfo or enemy~

    //return 's';
    
    //return myDisplayInfo.getInfo(wasHitAt(where));//added

    //need to use myShip to determine what to display
    if (myShip){
      
      return myDisplayInfo.getInfo(where, wasHitAt(where));//said for future convenience
      
    }
    else{
      
      return enemyDisplayInfo.getInfo(where,wasHitAt(where));
      
    }
  }

  

}
