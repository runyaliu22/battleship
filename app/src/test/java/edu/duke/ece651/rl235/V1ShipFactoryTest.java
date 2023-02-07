package edu.duke.ece651.rl235;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class V1ShipFactoryTest {

  private void checkShip(Ship<Character> testShip, String expectedName, char expectedLetter, Coordinate... expectedLocs){

    for (int i = 0; i < expectedLocs.length; i++ ){

      assertEquals(expectedLetter, testShip.getDisplayInfoAt(expectedLocs[i], true));
      
    }

  }
  @Test
  public void test_createShip() {

    AbstractShipFactory<Character> haha = new V1ShipFactory();

    Placement p1 = new Placement(new Coordinate(1, 2), 'V');
    Ship<Character> s1 = haha.makeSubmarine(p1);
    checkShip(s1, "Submarine", 's', new Coordinate(1, 2), new Coordinate(2, 2));

     Placement p2 = new Placement(new Coordinate(1, 2), 'H');
    Ship<Character> s2 = haha.makeCarrier(p2);
    
    checkShip(s2, "Carrier", 'c',  new Coordinate(1, 2), new Coordinate(1, 3), new Coordinate(1, 4), new Coordinate(1, 5), new Coordinate(1, 6), new Coordinate(1, 7));

    Placement p3 = new Placement(new Coordinate(4, 3), 'V');
    Ship<Character> s3 = haha.makeBattleship(p3);
    checkShip(s3, "Battleship", 'b', new Coordinate(4, 3), new Coordinate(5, 3), new Coordinate(6, 3), new Coordinate(7, 3) );
    

  }

}
