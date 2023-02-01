package edu.duke.ece651.rl235;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class InBoundsRuleCheckerTest {
  @Test
  public void test_inBounds() {
    
    //use the same board 
    Board<Character> b = new BattleShipBoard<Character>(3, 2, null);

    AbstractShipFactory<Character> f = new V1ShipFactory();
    
    //ship 1
    Ship<Character> s1 = f.makeDestroyer(new Placement("A0v"));
    
    PlacementRuleChecker<Character> p = new InBoundsRuleChecker<Character>(null);

    assertEquals(p.checkMyRule(s1, b), false);

    assertEquals(p.checkPlacement(s1, b), false);

    //ship 2
    Ship<Character> s2 = f.makeBattleship(new Placement("A0h"));

    assertEquals(p.checkMyRule(s2, b), false);


    //ship3
    Ship<Character> s3 = f.makeSubmarine(new Placement("A0h"));

    assertEquals(p.checkMyRule(s3, b), true);

    assertEquals(p.checkPlacement(s3, b), true);

    

    
    
    

  }

}
