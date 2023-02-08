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
      "A  | |  A\n"+
      "B  | |  B\n"+
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
       "A  | |  A\n"+
       "B  | |  B\n"+
       "C  | |  C\n" +
       "D  | |  D\n" +
       "E  | |  E\n" +
      
      expectedHeader;

    
     assertEquals(expected, view.displayMyOwnBoard());
   }

  
  
  //123
  //456

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
       "B  | |s B\n" +
       expectedHeader;

     assertEquals(expected, view.displayMyOwnBoard());

    
    
    
   }

   @Test
   public void test_enemy_board(){

     Board<Character> b = new BattleShipBoard<Character>(4, 3,  'X');

     BoardTextView v = new BoardTextView(b);

     AbstractShipFactory<Character> f = new V1ShipFactory();

     Ship<Character> s1 = f.makeSubmarine(new Placement("A0v"));

     Ship<Character> s2 = f.makeDestroyer(new Placement("A3v"));
      
     b.tryAddShip(s1);

     b.tryAddShip(s2);

     b.fireAt(new Coordinate("A0"));

     b.fireAt(new Coordinate("B1"));

     b.fireAt(new Coordinate("A3"));

     b.fireAt(new Coordinate("B3"));

     b.fireAt(new Coordinate("C3"));

     String expected =
       "  0|1|2|3\n" +
       "A s| | |d A\n" +
       "B  |X| |d B\n" +
       "C  | | |d C\n" +
       "  0|1|2|3\n";

     assertEquals(expected, v.displayEnemyBoard());
    
     //System.out.println(v.displayEnemyBoard());

     /*
    String myView =
      "  0|1|2|3\n" +
      "A  | | |d A\n" +
      "B s|s| |d B\n" +
      "C  | | |d C\n" +
      "  0|1|2|3\n";
  
    */
   }

  @Test
  public void display_both(){

    Board<Character> my = new BattleShipBoard<Character>(4, 3,  'X');
    
    //BoardTextView myview = new BoardTextView(my);

    Board<Character> en = new BattleShipBoard<Character>(4, 3,  'X');

    //BoardTextView enview = new BoardTextView(en);

    AbstractShipFactory<Character> f = new V1ShipFactory();

    Ship<Character> s1 = f.makeSubmarine(new Placement("A0v"));

    Ship<Character> s2 = f.makeDestroyer(new Placement("A3v"));

    my.tryAddShip(s1);
    
    en.tryAddShip(s2);

    en.fireAt(new Coordinate("A0"));

    en.fireAt(new Coordinate("B3"));
    

    BoardTextView myview = new BoardTextView(my);

    BoardTextView enview = new BoardTextView(en);

    String actual = myview.displayMyBoardWithEnemyNextToIt(enview, "Your Ocean", "Player B's Ocean");
    
    String expected =
      "     Your Ocean               Player B's Ocean\n" +//5+10+15=30=2*4+22
      "  0|1|2|3                  0|1|2|3\n" +//2+16
      "A s| | |  A                A X| | |  A\n" +//16
      "B s| | |  B                B  | | |d B\n" +//16
      "C  | | |  C                C  | | |  C\n" +//16
      "  0|1|2|3                  0|1|2|3\n";//2+16
    

    
    
    assertEquals(expected, actual);
    
  }

}
