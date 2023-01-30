package edu.duke.ece651.rl235;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
    Character c = s.getDisplayInfoAt(new Coordinate("A2"));//false hit
    assertEquals(c, 's');

    assertEquals(s.getName(), "testship");
  }
  

  @Test
  public void test_throw(){

    RectangleShip<Character> s = new RectangleShip<Character>(new Coordinate("A2"), 's', '*');
     assertThrows(IllegalArgumentException.class, () -> s.checkCoordinateInThisShip(new Coordinate("A3")));
    
    
    
  }

  @Test
  public void test_recordHitAt(){

    RectangleShip<Character> s1 = new RectangleShip<Character>("submarine", new Coordinate("A2"),1,3, 's', '*');
   s1.recordHitAt(new Coordinate("B2"));

   assertEquals(true, s1.wasHitAt(new Coordinate("B2")));

   assertEquals(false, s1.wasHitAt(new Coordinate("A2")));

   assertEquals(false, s1.wasHitAt(new Coordinate("C2")));
    
    
  }

  @Test
  public void test_isSunk(){

    RectangleShip<Character> s1 = new RectangleShip<Character>("submarine", new Coordinate("A2"),1,3, 's', '*');

    assertEquals("submarine", s1.getName());

    s1.recordHitAt(new Coordinate("B2"));

    assertEquals(false, s1.isSunk());

     s1.recordHitAt(new Coordinate("A2"));

     assertEquals(false, s1.isSunk());

     s1.recordHitAt(new Coordinate("C2"));

     assertEquals(true, s1.isSunk());

     

   
  }


  @Test
  public void test_getDisplayInfoAt(){

    RectangleShip<Character> s1 = new RectangleShip<Character>("submarine", new Coordinate("A2"),1,3, 's', '*');

    Coordinate c1 = new Coordinate("B2");

    Coordinate c2  = new Coordinate("A2");

    Coordinate c3  = new Coordinate("C2");

    

    s1.recordHitAt(c1);

    assertEquals('*', s1.getDisplayInfoAt(c1));

    assertEquals('s', s1.getDisplayInfoAt(c2));

    s1.recordHitAt(c2);

     assertEquals('*', s1.getDisplayInfoAt(c2));

      assertEquals('s', s1.getDisplayInfoAt(c3));


     

     

    

    


    
    

    
    
  }

}
