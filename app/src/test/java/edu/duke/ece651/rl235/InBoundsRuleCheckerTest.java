package edu.duke.ece651.rl235;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

//import sun.net.www.content.text.plain;

public class InBoundsRuleCheckerTest {
  @Test
  public void test_inBounds() {
    
    //use the same board 
    //Board<Character> b = new BattleShipBoard<Character>(3, 2, p);

    AbstractShipFactory<Character> f = new V1ShipFactory();
    
    //ship 1
    //Ship<Character> s1 = f.makeDestroyer(new Placement("A0v\n"));

    Ship<Character> s1 = f.makeDestroyer(new Placement("A0v"));
    
    //InBoundsRuleChecker<Character> p = new InBoundsRuleChecker<Character>(null);

    PlacementRuleChecker<Character> p = new InBoundsRuleChecker<Character>(null);

    Board<Character> b1 = new BattleShipBoard<Character>(3, 2, p, 'X');

    assertEquals(p.checkMyRule(s1, b1), "That placement is invalid: the ship goes off the bottom of the board.");

    assertEquals(p.checkPlacement(s1, b1), "That placement is invalid: the ship goes off the bottom of the board.");

    //ship 2
    Ship<Character> s2 = f.makeBattleship(new Placement("A0h"));

    assertEquals(p.checkMyRule(s2, b1), "That placement is invalid: the ship goes off the right of the board.");


    //ship3
    Ship<Character> s3 = f.makeSubmarine(new Placement("A0h"));

    assertEquals(p.checkMyRule(s3, b1), null);

    assertEquals(p.checkPlacement(s3, b1), null);

    

    Board<Character> b2 = new BattleShipBoard<Character>(3, 4, p, 'X');

    Ship<Character> s4 = f.makeBattleship(new Placement(new Coordinate(-1, 0), 'v'));
    
    assertEquals(p.checkMyRule(s4, b2), "That placement is invalid: the ship goes off the top of the board.");

     Ship<Character> s5 = f.makeBattleship(new Placement(new Coordinate(2, -2), 'v'));

    assertEquals(p.checkMyRule(s5, b2), "That placement is invalid: the ship goes off the left of the board.");
    

    
    
    

  }

}
