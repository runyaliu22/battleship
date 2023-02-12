package edu.duke.ece651.rl235;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.PrintStream;
import java.io.StringReader;

import org.junit.jupiter.api.Test;

public class TextPlayerTest {

  private TextPlayer createTextPlayer1(int w, int h, String inputData, ByteArrayOutputStream bytes) {
    BufferedReader input = new BufferedReader(new StringReader(inputData));
    PrintStream output = new PrintStream(bytes, true);
    Board<Character> board = new BattleShipBoard<Character>(w, h, 'X');
    V1ShipFactory shipFactory = new V1ShipFactory();
    return new TextPlayer("A", board, input, output, shipFactory);
  }

  /*
  private TextPlayer createTextPlayer2(int w, int h, String inputData, ByteArrayOutputStream bytes) {
    BufferedReader input = new BufferedReader(new StringReader(inputData));
    PrintStream output = new PrintStream(bytes, true);
    Board<Character> board = new BattleShipBoard<Character>(w, h);
    V1ShipFactory shipFactory = new V1ShipFactory();
    return new TextPlayer("B", board, input, output, shipFactory);
  }
  
  */


  @Test
    void test_read_placement() throws IOException{

    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    
    TextPlayer player1 = createTextPlayer1(10, 20, "B2V\nC8H\na4v\n", bytes);//same board or different?
    
    //TextPlayer player2 = createTextPlayer2(10, 20, "B2V\nC8H\na4v\n", bytes);

    //App app = new App(player1, player2);

    
      
    //StringReader sr = new StringReader("B2V\nC8H\na4v\n");
      
    //ByteArrayOutputStream bytes = new ByteArrayOutputStream();

    //PrintStream ps = new PrintStream(bytes, true);//write printstream into bytes

      

    //Board<Character> b = new BattleShipBoard<Character>(10, 20);

    //App app = new App(b, sr, ps);//read from stringreader and writes to printstream

      String prompt = "Please enter a location for a ship:";

      Placement[] expected = new Placement[3];

      expected[0] = new Placement(new Coordinate(1, 2), 'V');

      expected[1] = new Placement(new Coordinate(2, 8), 'H');

      expected[2] = new Placement(new Coordinate(0, 4), 'V');

      for (int i = 0; i < expected.length; i++){

        Placement p = player1.readPlacement(prompt, "Submarine");

        assertEquals(p, expected[i]);

        assertEquals(prompt + "\n", bytes.toString());//why newline?

        bytes.reset();
        
      }
      
    }

  @Test
  //void test_eof() throws EOFException{

  //void test_eof() throws EOFException{
 void test_eof() {

    StringReader sr = new StringReader("");

    BufferedReader br = new BufferedReader(sr);

    ByteArrayOutputStream bytes = new ByteArrayOutputStream();

    PrintStream ps = new PrintStream(bytes, true);

    Board<Character> b1 = new BattleShipBoard<Character>(10, 20, 'X');

    // Board<Character> b2 = new BattleShipBoard<Character>(10, 20);
      
     TextPlayer player1 = new TextPlayer("A", b1, br, ps, new V1ShipFactory());

     //TextPlayer player2 = new TextPlayer("B", b2, br, ps, new V1ShipFactory());

     //App app = new App(player1, player2);

     //assertThrows(EOFException.class, ()->player1.doPlacementPhase());

     assertThrows(EOFException.class, ()->player1.readPlacement("", "Submarine"));
     
     
    
  }
  

  @Test
  void test_shiptobeMoved_shiptobeAdded()throws IOException{
    
    StringReader sr = new StringReader("a1\na0\na1\nb3r");

    BufferedReader br = new BufferedReader(sr);
    
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();

    PrintStream ps = new PrintStream(bytes, true);//write printstream into bytes

    Board<Character> b1 = new BattleShipBoard<Character>(10, 20, 'X');

    AbstractShipFactory<Character> factory = new V2ShipFactory();

    TextPlayer player1 = new TextPlayer("A", b1, br, ps, factory);

    Ship<Character> s1 = factory.makeBattleship(new Placement(new Coordinate("a0"), 'u'));

    b1.tryAddShip(s1);

    //check shiptobemoved!
    assertEquals(s1, player1.shiptobeMoved());

    assertEquals(null, player1.shiptobeMoved());

    b1.tryAddShip(s1);

    b1.fireAt(new Coordinate("b0"));



    player1.moveShip();

    assertEquals(null, b1.whatIsAtForSelf(new Coordinate("b0")));
    assertEquals('*', b1.whatIsAtForSelf(new Coordinate("b3")));

    

    
     

    
    
  }
  

  
  /*
  @Test
  void test_do_one_placement() throws IOException{

    //StringReader sr1 = new StringReader("A0v\nA1v\nD1h\n");

    //StringReader sr1 = new StringReader("A0v\nC1h\nG7h\nh8v\np1h\nA8h\nR1v\nI8v\nH5h\nO9v\n");

    StringReader sr1 = new StringReader("A0v\nC1h\nG7h\nh8v\np1h\nA8h\nR1v\nI8v\nH5h\nO9v\nA8\nH5\nA0");

    //StringReader sr1 = new StringReader("A0v\nA1v\n");

     //StringReader sr3 = new StringReader("D1h\n");

     BufferedReader br1 = new BufferedReader(sr1);

     //BufferedReader br2 = new BufferedReader(sr3);

     
        
      ByteArrayOutputStream bytes = new ByteArrayOutputStream();

      PrintStream ps = new PrintStream(bytes, true);//write printstream into bytes

      Board<Character> b1 = new BattleShipBoard<Character>(10, 20, 'X');

      Board<Character> b2 = new BattleShipBoard<Character>(10, 20, 'X');
      
      TextPlayer player1 = new TextPlayer("A", b1, br1, ps, new V1ShipFactory());

      TextPlayer player2 = new TextPlayer("B", b2, br1, ps, new V1ShipFactory());

      //TextPlayer player1 = createTextPlayer1(3, 3, "A0v\n", bytes);
    
      //TextPlayer player2 = createTextPlayer1(3, 3, "A0v\n", bytes);

       App app1 = new App(player1, player2);

       //String prompt = "Where would you like to put your ship?";//what is it for!

       app1.doPlacementPhase();

       BoardTextView enView = new BoardTextView(b2);
      

       assertEquals("You hit a submarine!\n", player1.playOneTurn(b2, enView, "Your Ocean", "Player B's Ocean"));

       assertEquals("You hit a destroyer!\n", player1.playOneTurn(b2, enView, "Your Ocean", "Player B's Ocean"));

       assertEquals("You missed!\n", player1.playOneTurn(b2, enView, "Your Ocean", "Player B's Ocean"));
         
       
       String prompt = "Player  where do you want to place a Destroyer?";//what does it do?

       //app1.doPlacementPhase();

       //assertEquals(prompt + "\n", bytes.toString());//how does it work?

       //assertEquals(prompt, bytes.toString());//how does it work?
      

       //App app1 = new App(b1, sr1, ps);//read from stringreader and writes to printstream

       //String prompt = "Where would you like to put your ship?";//what is it for!

      // app1.doOnePlacement();
      

      assertEquals('s', b1.whatIsAtForSelf(new Coordinate("A0")));

      assertEquals(null, b1.whatIsAtForSelf(new Coordinate("A1")));

      //assertEquals('d', b1.whatIsAt(new Coordinate("A1")));

      assertEquals(null, b1.whatIsAtForSelf(new Coordinate("A2")));

       assertEquals('s', b1.whatIsAtForSelf(new Coordinate("B0")));

       assertEquals(null, b1.whatIsAtForSelf(new Coordinate("C0")));
       

       assertEquals(null, b2.whatIsAtForSelf(new Coordinate("D1")));

       
       
      bytes.reset();


*/

      
      /*

      //!different oceans, different boards!

      //StringReader sr2 = new StringReader("A0v\nA1v\nB2V\nC8H\na4v\nC7v\nF6h\nT1h\nR4h\nD9v\n");

      StringReader sr2 = new StringReader("A0v\nC1h\nG7h\nh8v\np1h\nA8h\nR1v\nI8v\nH5h\nO9v\n");



       BufferedReader br3 = new BufferedReader(sr2);
      
      Board<Character> b3 = new BattleShipBoard<Character>(10, 20, 'X');

      Board<Character> b4 = new BattleShipBoard<Character>(10, 20, 'X');

      TextPlayer player3 = new TextPlayer("A", b3, br3, ps, new V1ShipFactory());

      TextPlayer player4 = new TextPlayer("B", b4, br3, ps, new V1ShipFactory());

      //App app2 = new App(b2, sr2, ps);

      App app2 = new App(player3, player4);

      //String prompt = "Where would you like to put your ship?";//what is it for!

      app2.doPlacementPhase();
      
      
      assertEquals('s', b3.whatIsAtForSelf(new Coordinate("A0")));

      assertEquals(null, b3.whatIsAtForSelf(new Coordinate("A1")));

      assertEquals(null, b3.whatIsAtForSelf(new Coordinate("A2")));
      

      assertEquals(null, b4.whatIsAtForSelf(new Coordinate("B0")));

      //Ship<Character> s = b3.fireAt(new Coordinate("B0"));

      //assertSame(s, ));

      */

      
  /*    
  }
*/
  
}
