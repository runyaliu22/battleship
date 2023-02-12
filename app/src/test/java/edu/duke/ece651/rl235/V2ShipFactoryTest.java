package edu.duke.ece651.rl235;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class V2ShipFactoryTest {

  // Board<Character> b = new BattleShipBoard<Character>(, 0, null, null)

  private void checkShip(Ship<Character> testShip, String expectedName, char expectedLetter,
      Coordinate... expectedLocs) {

    for (int i = 0; i < expectedLocs.length; i++) {

      assertEquals(expectedLetter, testShip.getDisplayInfoAt(expectedLocs[i], true));

    }

  }

  @Test
  public void test_createShip() {

    AbstractShipFactory<Character> haha = new V2ShipFactory();

    Placement p1 = new Placement(new Coordinate(1, 2), 'D');
    Ship<Character> s1 = haha.makeBattleship(p1);
    checkShip(s1, "Battleship", 'b', new Coordinate(1, 2), new Coordinate(1, 3), new Coordinate(1,4), new Coordinate(2, 3));

    Placement p2 = new Placement(new Coordinate(1, 2), 'R');
    Ship<Character> s2 = haha.makeCarrier(p2);

    checkShip(s2, "Carrier", 'c', new Coordinate(1, 3), new Coordinate(1, 4), new Coordinate(1, 5),
              new Coordinate(1, 6), new Coordinate(2, 2), new Coordinate(2, 3), new Coordinate(2, 4));



  }

}
