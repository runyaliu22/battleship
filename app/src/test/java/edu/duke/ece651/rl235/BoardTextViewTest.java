package edu.duke.ece651.rl235;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;




public class BoardTextViewTest {
  @Test
  public void test_display_empty_2by2() {
    Board<Character> b1 = new BattleShipBoard<Character>(2, 2, 'X');
    BoardTextView view = new BoardTextView(b1);


    String expectedHeader= "  0|1\n";
    assertEquals(expectedHeader, view.makeHeader());
    String expected =
      expectedHeader+
      "A  |  A\n"+
      "B  |  B\n"+
      expectedHeader;
    assertEquals(expected, view.displayMyOwnBoard());
    
  }

  @Test
  public void test_display_empty_3by2(){
     Board<Character> b2 = new BattleShipBoard<Character>(3, 2, 'X');
     BoardTextView view = new BoardTextView(b2);

    String expectedHeader= "  0|1|2\n";
    assertEquals(expectedHeader, view.makeHeader());
    String expected =
      expectedHeader+
      "A  |  |  A\n"+
      "B  |  |  B\n"+
      expectedHeader;
    assertEquals(expected, view.displayMyOwnBoard());
  }

  @Test
  public void test_display_empty_3by5(){
     Board<Character> b3 = new BattleShipBoard<Character>(3, 5, 'X');
     BoardTextView view = new BoardTextView(b3);

    String expectedHeader= "  0|1|2\n";
    assertEquals(expectedHeader, view.makeHeader());
    String expected =
      expectedHeader+
      "A  |  |  A\n"+
      "B  |  |  B\n"+
      "C  |  |  C\n" +
      "D  |  |  D\n" +
      "E  |  |  E\n" +
      
      expectedHeader;

    
    assertEquals(expected, view.displayMyOwnBoard());
  }

  
  
  

  @Test
  public void test_invalid_board_size() {
    Board<Character> wideBoard = new BattleShipBoard<Character>(11, 20, 'X');
    Board<Character> tallBoard = new BattleShipBoard<Character>(10, 27, 'X');
    //you should write two assertThrows here
    assertThrows(IllegalArgumentException.class, () -> new BoardTextView(wideBoard));
    assertThrows(IllegalArgumentException.class, () -> new BoardTextView(tallBoard));
    
    
  }

  @Test
  public void test_add_ship_to_board(){

    String expectedHeader= "  0|1|2\n";
    
    Board<Character> b1 = new BattleShipBoard<Character>(3, 2, 'X');
    
    //Ship<Character> s1 = new BasicShip(new Coordinate("A1"));

    Ship<Character> s1 = new RectangleShip<Character>(new Coordinate("A1"), 's', '*');

    
    //Ship<Character> s2 = new BasicShip(new Coordinate("B2"));

    Ship<Character> s2 = new RectangleShip<Character>(new Coordinate("B2"), 's', '*');

    
    
    
    b1.tryAddShip(s1);
    b1.tryAddShip(s2);
    
    
    BoardTextView view = new BoardTextView(b1);

    String expected =
      expectedHeader +
      "A  |s|  A\n" +
      "B  |  |s B\n" +
      expectedHeader;

    assertEquals(expected, view.displayMyOwnBoard());

    
    
    
    
    
  }


}
