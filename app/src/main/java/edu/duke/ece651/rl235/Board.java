package edu.duke.ece651.rl235;

public interface Board<T> {
  
  public int getWidth();

  public int getHeight();

  public boolean tryAddShip(Ship<T> toAdd);

  public T whatIsAt(Coordinate where);

  

}
