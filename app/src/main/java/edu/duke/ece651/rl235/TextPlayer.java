package edu.duke.ece651.rl235;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
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

  // private final ArrayList<Integer> orientationMatrix = new ArrayList<>();

  private final HashMap<Character, Integer> orientation = new HashMap<>();

  private final HashMap<Integer, ArrayList<Integer>> orientationMatrix = new HashMap<>();

  // private final HashMap<>

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

    ArrayList<Integer> a = new ArrayList<>();
    a.addAll(Arrays.asList(1, 0, 0, 1));
    this.orientationMatrix.put(0, a);

    ArrayList<Integer> b = new ArrayList<>();
    b.addAll(Arrays.asList(0, 1, -1, 0));
    this.orientationMatrix.put(1, b);

    ArrayList<Integer> c = new ArrayList<>();
    c.addAll(Arrays.asList(-1, 0, 0, -1));
    this.orientationMatrix.put(2, c);

    ArrayList<Integer> d = new ArrayList<>();
    d.addAll(Arrays.asList(0, -1, 1, 0));

    this.orientation.put('U', 0);
    this.orientation.put('R', 1);
    this.orientation.put('D', 2);
    this.orientation.put('L', 3);

  }

  // public boolean check_lost(){

  // for (Ship<Character> ship: theBoard.)

  // }

  public Ship<Character> shiptobeMoved() throws IOException {

     out.println("Which ship do you want to move?\n");

     String s = inputReader.readLine();//error handling

     Coordinate c = new Coordinate(s);

     //Coordinate c = readCoordinate("Which ship do you want to move?\n");// error handling

    return theBoard.whatShipIsAt(c);

  }
  

  public Ship<Character> shiptobeAdded(String ShipName, Function<Placement, Ship<Character>> createFn) throws IOException {

    // need error handling
    //Ship<Character> ship_old = this.shiptobeMoved();// has getName method

    // out.println("Player: " + this.name +"Where do you want to move it to?
    // (Coordinate + Orientation)");

    String prompt = "Player: " + this.name + "Where do you want to move it to? (Coordinate + Orientation)";

    // String s = inputReader.readLine();//error handling

    Placement p = this.readPlacement(prompt, ShipName);

    Ship<Character> ship_new = createFn.apply(p);

    String m = theBoard.tryShip(ship_new);

    while (m != null) {

      out.println(m);
      p = readPlacement(prompt, ShipName);

      ship_new = createFn.apply(p);// interesting
      m = theBoard.tryShip(ship_new);// add the ship to the board//avoid collision or off the board

    }

    return ship_new;

  }

  public Coordinate getUpperLeft(Iterable<Coordinate> mySet) {

    Coordinate myCoordinate = null;

    for (Coordinate c : mySet) {

      // System.out.println(c);

      if (myCoordinate == null) {
        myCoordinate = c;
      }

      else {

        if (c.getRow() < myCoordinate.getRow()) {
          myCoordinate = c;
        }

        if (c.getRow() == myCoordinate.getRow() && c.getColumn() < myCoordinate.getColumn()) {
          myCoordinate = c;
        }

      }

    }

    return myCoordinate;
  }

  public void moveShip() throws IOException {

    setupShipCreationMap();
    setupShipCreationList();

    Ship<Character> ship_old = this.shiptobeMoved();// has getName method

    Ship<Character> ship_new = this.shiptobeAdded(ship_old.getName(), shipCreationFns.get(ship_old.getName()));

    HashMap<Coordinate, Boolean> ship_new_myPieces = new HashMap<>();

    if (ship_new.getName() == "Battleship" || ship_new.getName() == "Carrier") {

      Character new_orientation = ship_new.getOrientation();
      Character old_orientation = ship_old.getOrientation();

      int new_int = orientation.get(new_orientation);
      int old_int = orientation.get(old_orientation);

      Coordinate UL_new = ship_new.getUpperLeft();

      HashMap<Coordinate, Boolean> afterRotationMap = null;

       int difference = new_int - old_int;

       if (difference >= 0){
      
         afterRotationMap = ship_old.rotateMyPieces(orientationMatrix.get(difference));
         
       }
       else{

         afterRotationMap = ship_old.rotateMyPieces(orientationMatrix.get(4 + difference));
         
       }

        

        Iterable<Coordinate> afterRotationCoordinates = afterRotationMap.keySet();

        Coordinate UL_old_rotate = this.getUpperLeft(afterRotationCoordinates);

        int row_diff = UL_new.getRow() - UL_old_rotate.getRow();
        int column_diff = UL_new.getColumn() - UL_old_rotate.getColumn();

        for (Coordinate c : afterRotationCoordinates) {

          ship_new_myPieces.put(new Coordinate(c.getRow() + row_diff, c.getColumn() + column_diff),
              afterRotationMap.get(c));

        }

        

      }

    ship_old.update(ship_new_myPieces);

      

    

  }

  /*

  public Coordinate readCoordinate(String prompt) throws IOException {

    String s = null;
    boolean state = false;
    Coordinate p = null;

    while (!state) {
      out.println(prompt);
      s = inputReader.readLine();
      try {
        if (s == "" || s == null) {
          throw new EOFException();
        }

        p = new Coordinate(s);

        if (!(0 <= p.getRow() && p.getRow() < this.theBoard.getHeight() && 0 <= p.getColumn()
            && p.getColumn() < this.theBoard.getWidth())) {
          throw new IllegalArgumentException("That coordinate goes off the board, please enter again!\n");

        }
        state = true;
      }

      catch (IllegalArgumentException e) {
        out.println(e.getMessage());
      }
    }
    return p;
  }
  */

  public String getName() {
    return name;
  }

  // public String displayMyBoardWithEnemyNextToIt(BoardTextView enemyView, String
  // myHeader, String enemyHeader) {

  public String playOneTurn(Board<Character> enBoard, BoardTextView enView, String myHeader, String enHeader)
      throws IOException {

    out.println("Player " + name + "'s turn:\n");

    out.println(view.displayMyBoardWithEnemyNextToIt(enView, myHeader, enHeader));

    String s = inputReader.readLine();

    Coordinate c = new Coordinate(s);// need to take care of error handling!

    enBoard.fireAt(c);

    if (enBoard.whatIsAtForEnemy(c) == 's') {

      return "You hit a submarine!\n";

    }

    if (enBoard.whatIsAtForEnemy(c) == 'd') {

      return "You hit a destroyer!\n";
    }

    if (enBoard.whatIsAtForEnemy(c) == 'b') {// tested in main later!

      return "You hit a battleship!\n";
    }

    if (enBoard.whatIsAtForEnemy(c) == 'c') {

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

  protected void setupShipCreationList() {// need to be changed for more testing!

    // shipsToPlace.addAll(Collections.nCopies(2, "Submarine"));

    // shipsToPlace.addAll(Collections.nCopies(3, "Destroyer"));

    // shipsToPlace.addAll(Collections.nCopies(3, "Battleship"));

    // shipsToPlace.addAll(Collections.nCopies(2, "Carrier"));

    shipsToPlace.addAll(Collections.nCopies(1, "Submarine"));

    shipsToPlace.addAll(Collections.nCopies(1, "Destroyer"));

    shipsToPlace.addAll(Collections.nCopies(1, "Battleship"));

    shipsToPlace.addAll(Collections.nCopies(1, "Carrier"));

  }

  public void doPlacementPhase() throws IOException {

    out.println(view.displayMyOwnBoard());

    // out.println("Play !!! instructions!");
    String message = "Player " + name +

        ": you are going to place the following ships (which are all\n" +
        "rectangular). For each ship, type the coordinate of the upper left\n" +
        "side of the ship, followed by either H (for horizontal) or V (for\n" +
        "vertical).  For example M4H would place a ship horizontally starting\n" +
        "at M4 and going to the right.  You have\n\n" +
        "2 \"Submarines\" ships that are 1x2\n" +
        "3 \"Destroyers\" that are 1x3\n" +
        "3 \"Battleships\" that are TShaped\n" +
        "2 \"Carriers\" that are ZShaped\n";

    out.println(message);

    // this.doOnePlacement();//with or without this is correct!

    setupShipCreationMap();
    setupShipCreationList();

    for (String s : shipsToPlace) {

      // here's the loop
      doOnePlacement(s, shipCreationFns.get(s));

    }

    out.println(view.displayMyOwnBoard());

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

  public Placement readPlacement(String prompt, String shipName) throws IOException {

    out.println(prompt);

    // String s = inputReader.readLine();

    //// System.out.println(s);

    // return new Placement(s);// will throw if invalid orientation!

    Placement p = null;

    boolean state = false;

    // String s = inputReader.readLine();

    while (state == false) {

      String s = inputReader.readLine();
      // System.out.println(s);

      try {

        // throw new EOFException();
        if (s == "" || s == null) {
          throw new EOFException();
        }

        p = new Placement(s);// might throw!

        if ((p.getOrientation() == 'H' || p.getOrientation() == 'V')
            && (shipName != "Submarine" && shipName != "Destroyer")) {
          throw new IllegalArgumentException("Invalid orientation!");
        }

        if ((p.getOrientation() == 'U' || p.getOrientation() == 'D' || p.getOrientation() == 'L'
            || p.getOrientation() == 'R') && (shipName != "Carrier" && shipName != "Battleship")) {
          throw new IllegalArgumentException("Invalid orientation!");
        }

        state = true;

      }

      catch (IllegalArgumentException e) {

        out.println(e.getMessage());

      }

    }

    return p;

  }

  public void doOnePlacement(String shipName, Function<Placement, Ship<Character>> createFn) throws IOException {

    String prompt = "Player " + name + "  where do you want to place a " + shipName + "?";

    Placement p = readPlacement(prompt, shipName);

    // Coordinate c = p.getCoordinate();

    // Ship s = new BasicShip<Character>(c);

    // Ship<Character> s = new RectangleShip<Character>(c, 's', '*');

    // Ship<Character> s = shipFactory.makeDestroyer(p);

    Ship<Character> s = createFn.apply(p);

    // theBoard.tryAddShip(s);

    // s.recordHitAt(new Coordinate("Q4"));

    // String m = theBoard.tryAddShip(s);

    /*
     * 
     * boolean state = false;
     * 
     * while (!state){
     * 
     * Placement p = readPlacement(prompt);
     * Ship<Character> s = createFn.apply(p);
     * 
     * if ((p.getOrientation() == 'H' || p.getOrientation() == 'V') && (s.getName()
     * != "Submarine" && s.getName() != "Battleship" )){
     * 
     * }
     * 
     * 
     * }
     */

    String m = theBoard.tryAddShip(s);

    while (m != null) {

      out.println(m);

      // Placement p1 = readPlacement(prompt, shipName);

      p = readPlacement(prompt, shipName);

      // Ship<Character> s1 = createFn.apply(p1);//interesting

      s = createFn.apply(p);

      // m = theBoard.tryAddShip(s1);//add the ship to the board

      m = theBoard.tryAddShip(s);

    }

    out.println(view.displayMyOwnBoard()); // meaning?print write to out
  }

}
