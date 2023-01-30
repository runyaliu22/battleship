package edu.duke.ece651.rl235;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashSet;

import org.junit.jupiter.api.Test;

public class RectangleShipTest {
  
  @Test
  public void test_makeCoords() {

    //RectangleShip r = new RectangleShip();
    HashSet<Coordinate> actual = RectangleShip.makeCoords(new Coordinate(1, 2), 1, 3);
    HashSet<Coordinate> expected = new HashSet<Coordinate>();
    
    expected.add(new Coordinate(1,2));
    expected.add(new Coordinate(2,2));
    expected.add(new Coordinate(3,2));
    
 
    assertEquals(actual, expected);
    
  }

  @Test
  public void test_constructor(){

    RectangleShip<Character> s = new RectangleShip<Character>(new Coordinate("A2"), 's', '*');
    Character c = s.getDisplayInfoAt(new Coordinate("A2"));
    assertEquals(c, 's');
    
  }

}
