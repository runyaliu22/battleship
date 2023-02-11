package edu.duke.ece651.rl235;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class ZCarrierTest {

  @Test
  public void test_constructor(){

    ZCarrier<Character> s1 = new ZCarrier<Character>(new Coordinate("A2"), 'c', '*');
    Character c1 = s1.getDisplayInfoAt(new Coordinate("A2"), true);//false hit
    assertEquals(c1, 'c');

    assertEquals(s1.getName(), "testZCarrier");

    
    ZCarrier<Character> s2 = new ZCarrier<Character>("TBattleship", new Coordinate("A2"), 'D', 'c', '*');
     Character c2 = s2.getDisplayInfoAt(new Coordinate("A2"), true);//false hit
    assertEquals(c2, 'c');

    ZCarrier<Character> s3 = new ZCarrier<Character>("TBattleship", new Coordinate("A2"), 'R', 'c', '*');
     Character c3 = s3.getDisplayInfoAt(new Coordinate("A3"), true);//false hit
    assertEquals(c3, 'c');

    Character c4 = s3.getDisplayInfoAt(new Coordinate("B3"), true);//false hit
    assertEquals(c4, 'c');

    ZCarrier<Character> s4 = new ZCarrier<Character>("TBattleship", new Coordinate("A2"), 'L', 'c', '*');
     Character c5 = s4.getDisplayInfoAt(new Coordinate("B2"), true);//false hit
    assertEquals(c5, 'c');

    Character c6 = s4.getDisplayInfoAt(new Coordinate("A4"), true);//false hit
    assertEquals(c6, 'c');

    //Character c7 = s4.getDisplayInfoAt(new Coordinate("A3"), true);//false hit
    //assertEquals(c7, null);
    
  }

}
