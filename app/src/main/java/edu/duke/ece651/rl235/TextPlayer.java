package edu.duke.ece651.rl235;

import java.io.BufferedReader;
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
  
  //how to initialize these two fields in the constructor???

  public TextPlayer(String name, Board<Character> theBoard, BufferedReader inputSource, PrintStream out, AbstractShipFactory<Character> factory) {

    // BufferedReader

    //BattleShipBoard<Character>(10,20);
    // this.theBoard = new Board(10, 20);

    // this.view = new BoardTextView(theBoard);
    // this.inputReader = new BufferedReader(new InputStreamReader(System.in));
    // this.out = System.out;

    //this.shipFactory = new V1ShipFactory();//need this constructor to implement
    
    this.shipFactory = factory;
    
    this.theBoard = theBoard;

    this.view = new BoardTextView(theBoard);

    this.inputReader = inputSource;

    this.out = out;//!interesting!!

    this.name = name;

   
  }

  //hashmap!
  protected void setupShipCreationMap(){

    shipCreationFns.put("Submarine", p->shipFactory.makeSubmarine(p));

    //shipCreationFns.put("Submarine", p->shipFactory.makeSubmarine(p));

    shipCreationFns.put("Destroyer", p->shipFactory.makeDestroyer(p));

    //shipCreationFns.put("Destroyer", p->shipFactory.makeDestroyer(p));

    //shipCreationFns.put("Destroyer", p->shipFactory.makeDestroyer(p));

    shipCreationFns.put("Battleship", p->shipFactory.makeBattleship(p));

    shipCreationFns.put("Carrier", p->shipFactory.makeCarrier(p));

    
    

  }

  protected void setupShipCreationList(){

    shipsToPlace.addAll(Collections.nCopies(2, "Submarine"));

    shipsToPlace.addAll(Collections.nCopies(3, "Destroyer"));
    
  }

  

  public void doPlacementPhase() throws IOException{

    out.println(view.displayMyOwnBoard());

    out.println("Play !!!  instructions!");

    //this.doOnePlacement();//with or without this is correct!

    
    setupShipCreationMap();
    setupShipCreationList();

    for (String s: shipsToPlace){

      
      doOnePlacement(s, shipCreationFns.get(s));
      
      
    }
  
}  



  public Placement readPlacement(String prompt) throws IOException {

    out.println(prompt);

    String s = inputReader.readLine();

    System.out.println(s);

    return new Placement(s);//will throw if invalid orientation!

  }
  

  public void doOnePlacement(String shipName, Function<Placement, Ship<Character>> createFn) throws IOException {

    String prompt = "Player " + name + "  where do you want to place a Destroyer?";

    Placement p = readPlacement(prompt);

    //Coordinate c = p.getCoordinate();

    //Ship  s = new BasicShip<Character>(c);
    
    //Ship<Character> s = new RectangleShip<Character>(c, 's', '*');

    //Ship<Character> s  = shipFactory.makeDestroyer(p);

    Ship<Character> s = createFn.apply(p);

    theBoard.tryAddShip(s);
    //s.recordHitAt(new Coordinate("Q4"));


    out.println(view.displayMyOwnBoard()); // meaning?print write to out
  }




  



}