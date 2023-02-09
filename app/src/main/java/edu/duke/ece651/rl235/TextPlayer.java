package edu.duke.ece651.rl235;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.function.Function;

public class TextPlayer {

  private final AbstractShipFactory<Character> shipFactory;

  private final Board<Character> theBoard;

  private final BoardTextView view;

  private final BufferedReader inputReader;

  private final PrintStream out;

  private final String name;

  private final ArrayList<String> shipsToPlace = new ArrayList<>();

  private final HashMap<String, Function<Placement, Ship<Character>>> shipCreationFns = new HashMap<>();

  // how to initialize these two fields in the constructor???

  public TextPlayer(String name, Board<Character> theBoard, BufferedReader inputSource, PrintStream out,
      AbstractShipFactory<Character> factory) {

    // BufferedReader

    // BattleShipBoard<Character>(10,20);
    // this.theBoard = new Board(10, 20);

    // this.view = new BoardTextView(theBoard);
    // this.inputReader = new BufferedReader(new InputStreamReader(System.in));
    // this.out = System.out;

    // this.shipFactory = new V1ShipFactory();//need this constructor to implement

    this.shipFactory = factory;

    this.theBoard = theBoard;

    this.view = new BoardTextView(theBoard);

    this.inputReader = inputSource;

    this.out = out;// !interesting!!

    this.name = name;

  }

  //public boolean check_lost(){

    //for (Ship<Character> ship: theBoard.)

      //}
  public String getName(){
    return name;
  }

  public String playOneTurn(Board<Character> enBoard) throws IOException{

    out.println("Player " + name + "'s turn:\n");

    String s = inputReader.readLine();

    Coordinate c = new Coordinate(s);//need to take care of error handling!

    enBoard.fireAt(c);

    if (enBoard.whatIsAtForEnemy(c) == 's'){
      
      return "You hit a submarine!\n";
      
    }

   if (enBoard.whatIsAtForEnemy(c) == 'd'){
     
      return "You hit a destroyer!\n";
    }

   if (enBoard.whatIsAtForEnemy(c) == 'b'){//tested in main later!
     
      return "You hit a battleship!\n";
    }

   if (enBoard.whatIsAtForEnemy(c) == 'c'){
     
      return "You hit a carrier!\n";
    }
   
    return "You missed!\n";
    
  }

    

  // hashmap!
  protected void setupShipCreationMap() {

    shipCreationFns.put("Submarine", pp -> shipFactory.makeSubmarine(pp));

    // shipCreationFns.put("Submarine", p->shipFactory.makeSubmarine(p));

    shipCreationFns.put("Destroyer", pp -> shipFactory.makeDestroyer(pp));

    // shipCreationFns.put("Destroyer", p->shipFactory.makeDestroyer(p));

    // shipCreationFns.put("Destroyer", p->shipFactory.makeDestroyer(p));

    shipCreationFns.put("Battleship", pp -> shipFactory.makeBattleship(pp));

    shipCreationFns.put("Carrier", p -> shipFactory.makeCarrier(p));

  }

  protected void setupShipCreationList() {//need to be changed for more testing!

    shipsToPlace.addAll(Collections.nCopies(2, "Submarine"));

    shipsToPlace.addAll(Collections.nCopies(3, "Destroyer"));

  }

  public void doPlacementPhase() throws IOException {

    out.println(view.displayMyOwnBoard());

    out.println("Play !!!  instructions!");

    // this.doOnePlacement();//with or without this is correct!

    setupShipCreationMap();
    setupShipCreationList();

    for (String s : shipsToPlace) {

      // here's the loop
      doOnePlacement(s, shipCreationFns.get(s));

    }

  }

  /*
   * public Placement readPlacement(String prompt) throws IOException {
   * 
   * out.println(prompt);
   * 
   * String s = inputReader.readLine();
   * 
   * // System.out.println(s);
   * 
   * return new Placement(s);//will throw if invalid orientation!
   * 
   * }
   */

  public Placement readPlacement(String prompt) throws IOException {

     out.println(prompt);

    // String s = inputReader.readLine();

    //// System.out.println(s);

    // return new Placement(s);// will throw if invalid orientation!

    Placement p = null;

    boolean state = false;

    //String s = inputReader.readLine();

    while (state == false){
      
      String s = inputReader.readLine();
      //System.out.println(s);

      try{

        if ( s == "" || s == null){
          throw new EOFException();
        }

        p = new Placement(s);
        state = true;
        
      }

      catch (IllegalArgumentException e){
        
        out.println(e.getMessage());

      }      
     
    }

    return p;
 
  }

  

  public void doOnePlacement(String shipName, Function<Placement, Ship<Character>> createFn) throws IOException {

    String prompt = "Player " + name + "  where do you want to place a Destroyer?";

    Placement p = readPlacement(prompt);

    // Coordinate c = p.getCoordinate();

    // Ship s = new BasicShip<Character>(c);

    // Ship<Character> s = new RectangleShip<Character>(c, 's', '*');

    // Ship<Character> s = shipFactory.makeDestroyer(p);

    Ship<Character> s = createFn.apply(p);

    //theBoard.tryAddShip(s);
    
    // s.recordHitAt(new Coordinate("Q4"));

    String m = theBoard.tryAddShip(s);

    
    while (m != null){

      out.println(m);
      Placement p1 = readPlacement(prompt);
      Ship<Character> s1 = createFn.apply(p1);//interesting
      m = theBoard.tryAddShip(s1);//add the ship to the board
      
      
    }
    

    out.println(view.displayMyOwnBoard()); // meaning?print write to out
  }

}
