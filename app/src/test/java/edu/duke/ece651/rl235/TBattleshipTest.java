package edu.duke.ece651.rl235;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class TBattleshipTest {
  

  @Test
  public void test_constructor(){

    TBattleship<Character> s1 = new TBattleship<Character>(new Coordinate("A2"), 'b', '*');
    Character c1 = s1.getDisplayInfoAt(new Coordinate("A3"), true);//false hit
    assertEquals(c1, 'b');

    assertEquals(s1.getName(), "testTBattleship");

    
    TBattleship<Character> s2 = new TBattleship<Character>("TBattleship", new Coordinate("A2"), 'D', 'b', '*');
     Character c2 = s2.getDisplayInfoAt(new Coordinate("A2"), true);//false hit
    assertEquals(c2, 'b');

    TBattleship<Character> s3 = new TBattleship<Character>("TBattleship", new Coordinate("A2"), 'R', 'b', '*');
     Character c3 = s3.getDisplayInfoAt(new Coordinate("A2"), true);//false hit
    assertEquals(c3, 'b');

    TBattleship<Character> s4 = new TBattleship<Character>("TBattleship", new Coordinate("A2"), 'L', 'b', '*');
     Character c4 = s4.getDisplayInfoAt(new Coordinate("B2"), true);//false hit
    assertEquals(c4, 'b');
    
  }
}
