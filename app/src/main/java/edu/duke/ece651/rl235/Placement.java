package edu.duke.ece651.rl235;

public class Placement {

  private final Coordinate where;
  private final char orientation;

  public Placement(Coordinate w, char o) {//constructed through string and coordinate
    this.where = w;

    char ori = Character.toUpperCase(o);

    if (ori != 'H' && ori != 'V' && ori != 'U' && ori != 'D' && ori != 'R' && ori != 'L' ) {
      throw new IllegalArgumentException("Invalid orientation!");
    }

    this.orientation = ori;
  }

  public Placement(String descr) {
    if (descr.length() != 3) {
      throw new IllegalArgumentException("The length of descr should be 3!");
    }

    this.where = new Coordinate(descr.substring(0, 2));

    char ori = Character.toUpperCase(descr.charAt(2));

    if (ori != 'H' && ori != 'V' && ori != 'U' && ori != 'D' && ori != 'R' && ori != 'L' ) {
      throw new IllegalArgumentException("Invalid orientation!");
    }

    this.orientation = ori;

  }

  public Coordinate getCoordinate() {
    return where;
  }

  public char getOrientation() {
    return orientation;
  }

  @Override
  public boolean equals(Object o) {
    if (o.getClass().equals(getClass())) {
      Placement c = (Placement) o;
      return where.equals(c.where) && orientation == c.orientation;
    }
    return false;
  }

  @Override
  public int hashCode() {
    return toString().hashCode();
  }

  @Override
  public String toString() {
    return where.toString() + " " + orientation;
  }

}
