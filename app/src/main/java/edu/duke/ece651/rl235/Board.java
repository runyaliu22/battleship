package edu.duke.ece651.rl235;

public interface Board<T> {
  
  public int getWidth();

  public int getHeight();

  public String  tryAddShip(Ship<T> toAdd);

  // public boolean  tryAddShip(Ship<T> toAdd);

  public T whatIsAtForSelf(Coordinate where);

  public T whatIsAtForEnemy(Coordinate where);

  public Ship<T> fireAt(Coordinate c);

  public boolean check_lost();

  public Ship<T> whatShipIsAt(Coordinate where);

  public String tryShip(Ship<T> toAdd);
  

}
