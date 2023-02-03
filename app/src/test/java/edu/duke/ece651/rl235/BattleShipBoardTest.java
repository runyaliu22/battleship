package edu.duke.ece651.rl235;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class BattleShipBoardTest {
  @Test
  
  public void test_width_and_heightt() {

    Board<Character> b1 = new BattleShipBoard<Character> (10, 20);
    assertEquals(10, b1.getWidth());
    assertEquals(20, b1.getHeight());

  }

  @Test
   public void test_invalid_dimensions() {
    assertThrows(IllegalArgumentException.class, () -> new BattleShipBoard<Character> (10, 0));
    assertThrows(IllegalArgumentException.class, () -> new BattleShipBoard<Character> (0, 20));
    assertThrows(IllegalArgumentException.class, () -> new BattleShipBoard<Character> (10, -5));
    assertThrows(IllegalArgumentException.class, () -> new BattleShipBoard<Character> (-8, 20));
  }

  /*
  @Test

   private <T> void checkWhatIsAtBoard(BattleShipBoard<T> b, T[][] expected){
    
    
    
  }
  */

  @Test
  
  public void test_try_add(){

    //Ship<Character> s1 = new BasicShip(new Coordinate("A0"));

    Ship<Character> s1 = new RectangleShip<Character>(new Coordinate("A0"), 's', '*');

    Board<Character> b2 = new BattleShipBoard<Character> (2, 1);
    
    assertEquals(null, b2.tryAddShip(s1));

    assertEquals('s', b2.whatIsAt(new Coordinate("A0")));
    assertEquals(null, b2.whatIsAt(new Coordinate("A1")));
    
    
    
    
  }

  @Test

  public void test_initial_board(){
    
    Board<Character> b2 = new BattleShipBoard<Character> (2, 1);
    assertEquals(null, b2.whatIsAt(new Coordinate("A0")));
    assertEquals(null, b2.whatIsAt(new Coordinate("A1")));

    //Ship<Character> s1 = new BasicShip(new Coordinate("A0"));
    
    
    
   
    
    
    
  }

  //Character[][] c= new Character[1][2];
  //c[0][0] = 's';
  
  
  
}



