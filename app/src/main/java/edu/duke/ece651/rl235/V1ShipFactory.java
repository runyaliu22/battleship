
package edu.duke.ece651.rl235;

public class V1ShipFactory implements AbstractShipFactory<Character> {

  protected Ship<Character> createShip(Placement where, int w, int h, char letter, String name) {

    //how to check orientation and throw exception?

    if (where.getOrientation() == 'H'){
      int store = w;
      w = h;
      h = store;
    }

    //RectangleShip<Character> r = new RectangleShip<Character>(name, where.getCoordinate(), w, h, letter, '*');

    Ship<Character> r = new RectangleShip<Character>(name, where.getCoordinate(), w, h, letter, '*');
    
    return r;
    
  }

  @Override
  public Ship<Character> makeSubmarine(Placement where) {

    return createShip(where, 1, 2, 's', "Submarine");

  }

  @Override
  public Ship<Character> makeDestroyer(Placement where) {

    return createShip(where, 1, 3, 'd', "Destroyer");

  }

  @Override
  public Ship<Character> makeBattleship(Placement where) {

    return createShip(where, 1, 4, 'b', "Battleship");
    
  }

  @Override
  public Ship<Character> makeCarrier(Placement where) {


    return createShip(where, 1, 6, 'c', "Carrier");
    
  }

}

