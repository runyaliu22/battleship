package edu.duke.ece651.rl235;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.StringReader;

import org.junit.jupiter.api.Test;

public class TextPlayerTest {

  private TextPlayer createTextPlayer1(int w, int h, String inputData, ByteArrayOutputStream bytes) {
    BufferedReader input = new BufferedReader(new StringReader(inputData));
    PrintStream output = new PrintStream(bytes, true);
    Board<Character> board = new BattleShipBoard<Character>(w, h);
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

        Placement p = player1.readPlacement(prompt);

        assertEquals(p, expected[i]);

        assertEquals(prompt + "\n", bytes.toString());

        bytes.reset();
        
      }
      
    }



  @Test
  void test_do_one_placement() throws IOException{

    //StringReader sr1 = new StringReader("A0v\nA1v\nD1h\n");

    StringReader sr1 = new StringReader("A0v\nD0h\n");

    //StringReader sr1 = new StringReader("A0v\nA1v\n");

     //StringReader sr3 = new StringReader("D1h\n");

     BufferedReader br1 = new BufferedReader(sr1);

     //BufferedReader br2 = new BufferedReader(sr3);

     
        
      ByteArrayOutputStream bytes = new ByteArrayOutputStream();

      PrintStream ps = new PrintStream(bytes, true);//write printstream into bytes

      Board<Character> b1 = new BattleShipBoard<Character>(3, 4);

      Board<Character> b2 = new BattleShipBoard<Character>(3, 4);
      
      TextPlayer player1 = new TextPlayer("A", b1, br1, ps, new V1ShipFactory());

      TextPlayer player2 = new TextPlayer("B", b2, br1, ps, new V1ShipFactory());

      //TextPlayer player1 = createTextPlayer1(3, 3, "A0v\n", bytes);
    
      //TextPlayer player2 = createTextPlayer1(3, 3, "A0v\n", bytes);

       App app1 = new App(player1, player2);

       //String prompt = "Where would you like to put your ship?";//what is it for!

       app1.doPlacementPhase();

       String prompt = "Player  where do you want to place a Destroyer?";//what does it do?

       //app1.doPlacementPhase();

       //assertEquals(prompt + "\n", bytes.toString());//how does it work?

       //assertEquals(prompt, bytes.toString());//how does it work?
      

       //App app1 = new App(b1, sr1, ps);//read from stringreader and writes to printstream

       //String prompt = "Where would you like to put your ship?";//what is it for!

      // app1.doOnePlacement();
      

      assertEquals('d', b1.whatIsAt(new Coordinate("A0")));

      assertEquals(null, b1.whatIsAt(new Coordinate("A1")));

      //assertEquals('d', b1.whatIsAt(new Coordinate("A1")));

      assertEquals(null, b1.whatIsAt(new Coordinate("A2")));

       assertEquals('d', b1.whatIsAt(new Coordinate("B0")));

       assertEquals('d', b1.whatIsAt(new Coordinate("C0")));

       assertEquals('d', b2.whatIsAt(new Coordinate("D1")));

       
       
      bytes.reset();



      //!different oceans, different boards!

      StringReader sr2 = new StringReader("A0v\nA1v\n");

       BufferedReader br3 = new BufferedReader(sr2);
      
      Board<Character> b3 = new BattleShipBoard<Character>(3, 2);

      Board<Character> b4 = new BattleShipBoard<Character>(3, 2);

      TextPlayer player3 = new TextPlayer("A", b3, br3, ps, new V1ShipFactory());

      TextPlayer player4 = new TextPlayer("B", b4, br3, ps, new V1ShipFactory());

      //App app2 = new App(b2, sr2, ps);

      App app2 = new App(player3, player4);

      //String prompt = "Where would you like to put your ship?";//what is it for!

      app2.doPlacementPhase();
      
      assertEquals(null, b3.whatIsAt(new Coordinate("A0")));

      assertEquals(null, b3.whatIsAt(new Coordinate("A1")));

      assertEquals(null, b3.whatIsAt(new Coordinate("A2")));
      

      assertEquals(null, b4.whatIsAt(new Coordinate("B0")));  


      
  }

}
