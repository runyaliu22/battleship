package edu.duke.ece651.rl235;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class BattleShipBoardTest {
  @Test
  
  public void test_width_and_heightt() {

    Board<Character> b1 = new BattleShipBoard<Character> (10, 20, 'X');
    assertEquals(10, b1.getWidth());
    assertEquals(20, b1.getHeight());

  }

  @Test
   public void test_invalid_dimensions() {
    assertThrows(IllegalArgumentException.class, () -> new BattleShipBoard<Character> (10, 0, 'X'));
    assertThrows(IllegalArgumentException.class, () -> new BattleShipBoard<Character> (0, 20, 'X'));
    assertThrows(IllegalArgumentException.class, () -> new BattleShipBoard<Character> (10, -5, 'X'));
    assertThrows(IllegalArgumentException.class, () -> new BattleShipBoard<Character> (-8, 20, 'X'));
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

    Board<Character> b2 = new BattleShipBoard<Character> (2, 1, 'X');
    
    assertEquals(null, b2.tryAddShip(s1));

    assertEquals('s', b2.whatIsAtForSelf(new Coordinate("A0")));
    assertEquals(null, b2.whatIsAtForSelf(new Coordinate("A1")));
    
    
    
    
  }

  @Test

  public void test_initial_board(){
    
    Board<Character> b2 = new BattleShipBoard<Character> (2, 1, 'X');
    assertEquals(null, b2.whatIsAtForSelf(new Coordinate("A0")));
    assertEquals(null, b2.whatIsAtForSelf(new Coordinate("A1")));

    //Ship<Character> s1 = new BasicShip(new Coordinate("A0"));
   
    
  }

  //Character[][] c= new Character[1][2];
  //c[0][0] = 's';

  @Test

  public void test_fire(){

    Board<Character> b3 = new BattleShipBoard<>(4, 3, 'X');

    AbstractShipFactory<Character> f = new V1ShipFactory();

    Ship<Character> s1 = f.makeSubmarine(new Placement("A0v"));

    //Ship<Character> s2 = f.makeDestroyer(new Placement("B1h"));

    b3.tryAddShip(s1);

    //b3.tryAddShip(s2);

     Ship<Character> s_1 = b3.fireAt(new Coordinate("B0"));

     assertFalse(s_1.isSunk());

     assertSame(s_1, s1);

     b3.fireAt(new Coordinate("A0"));

     assertTrue(s_1.isSunk());

     assertEquals(null, b3.fireAt(new Coordinate("C3")));;
  }

  @Test

  public void what_is_for_enemy(){

    Board<Character> b4 = new BattleShipBoard<>(4, 3, 'X');

    AbstractShipFactory<Character> f = new V1ShipFactory();

    Ship<Character> s1 = f.makeSubmarine(new Placement("A0v"));

    b4.tryAddShip(s1);

    assertEquals(b4.whatIsAtForEnemy(new Coordinate("A0")), null);

    assertEquals(b4.whatIsAtForEnemy(new Coordinate("B0")), null);

    b4.fireAt(new Coordinate("B0"));

    assertEquals(b4.whatIsAtForEnemy(new Coordinate("B0")), 's');

    assertEquals(b4.whatIsAtForEnemy(new Coordinate("A0")), null);

    b4.fireAt(new Coordinate("C0"));

     assertEquals(b4.whatIsAtForEnemy(new Coordinate("C0")), 'X');

    
  }

  @Test

  public void test_check_lost(){

    Board<Character> b5 = new BattleShipBoard<Character>(4, 3,  'X');

    AbstractShipFactory<Character> f = new V1ShipFactory();

    Ship<Character> s1 = f.makeSubmarine(new Placement("A0v"));

    Ship<Character> s2 = f.makeDestroyer(new Placement("C1h"));

    b5.tryAddShip(s1);

    //b5.tryAddShip(s2);

    b5.fireAt(new Coordinate("A0"));

    b5.fireAt(new Coordinate("B0"));

    assertEquals(true, b5.check_lost());

    b5.tryAddShip(s2);

    assertEquals(false, b5.check_lost());

    b5.fireAt(new Coordinate("C1"));

    assertEquals(false, b5.check_lost());

    b5.fireAt(new Coordinate("C2"));

    assertEquals(false, b5.check_lost());

    b5.fireAt(new Coordinate("C3"));

    assertEquals(true, b5.check_lost());

    

    

    

    
    

    

    
    

  }
  
}



