package edu.duke.ece651.rl235;

public class SimpleShipDisplayInfo<T> implements ShipDisplayInfo<T> {

  private final T myData;
  private final T onHit;

  public SimpleShipDisplayInfo(T myData, T onHit){
    
    this.myData = myData;
    this.onHit = onHit;
    
  }

  @Override
  public T getInfo(Coordinate where, boolean hit) {//what's coordinate for?
    if (hit == true){
      return onHit;
    }
    else{
      return myData;
    }
    
  }
  

}
