package edu.duke.ece651.rl235;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class SimpleShipDisplayInfoTest {
  @Test
  public void test_constructor() {

    ShipDisplayInfo<Character> s1 = new SimpleShipDisplayInfo<Character>('a' ,'b' );
    Character r1 = s1.getInfo(new Coordinate(0, 0), false);

    //Character r1 = s1.getInfo(false);
    assertEquals(r1, 'a');

    Character r2 = s1.getInfo(new Coordinate(0, 0), true);
    //Character r2 = s1.getInfo(true);
    assertEquals(r2, 'b');
    
    

  }

}
