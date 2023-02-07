package edu.duke.ece651.rl235;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class NoCollisionRuleCheckerTest {
  @Test
  public void test_NoCollision() {

    //next rule is null
    PlacementRuleChecker<Character> p1 = new NoCollisionRuleChecker<>(null);
    
    Board<Character> b1 = new BattleShipBoard<>(4, 3, p1, 'X');//it seems p doesn't get used in battlefile program

    AbstractShipFactory<Character> f =  new V1ShipFactory();//ASF doens't have constructor because it's only implemented

    //check ok to add s1 to an empty board
    Ship<Character> s1 = f.makeSubmarine(new Placement("A0v"));

    //assertEquals(p1.checkMyRule(s1, b1), true);

    assertEquals(p1.checkMyRule(s1, b1), null);
    
    b1.tryAddShip(s1);

    //check not ok to add s2
    Ship<Character> s2 = f.makeBattleship(new Placement("B0h"));

    // assertEquals(p1.checkMyRule(s2, b1), false);

    assertEquals(p1.checkMyRule(s2, b1), "That placement is invalid: the ship overlaps another ship.");

    //combine two rules

    PlacementRuleChecker<Character> p2 = new InBoundsRuleChecker<>(p1);
    Board<Character> b2 = new BattleShipBoard<>(4, 3, p2, 'X');



    //assertEquals(true, p2.checkPlacement(s2, b2));

    assertEquals(null, p2.checkPlacement(s2, b2));

    b2.tryAddShip(s2);

    //assertEquals(false, p2.checkPlacement(s1, b2));//need to check the ship satisfies the rules before added to the board!

     assertEquals("That placement is invalid: the ship overlaps another ship.", p2.checkPlacement(s1, b2));

    
    
    

    

  }

}
