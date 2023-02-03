package edu.duke.ece651.rl235;

public class Coordinate {

  private final int row;
  private final int column;

  public Coordinate(int r, int c) {

    // if (r < 0 || c < 0) {
    // throw new IllegalArgumentException("Invalid row number or column number");//
    // }

    // because it needs to be handled later in rule checker in bounds

    this.row = r;
    this.column = c;

  }

  public Coordinate(String descr) {

    if (descr.length() != 2) {
      throw new IllegalArgumentException("The length of descr is 2!");
    }

    String s = descr.toUpperCase();

    char rLetter = s.charAt(0);
    int cLetter = s.charAt(1) - '0';

    // needs ot be changed based on the board size?

    if (rLetter < 'A' || rLetter > 'Z') {
      throw new IllegalArgumentException("Invalid row number!");
    }

    if (cLetter < 0 || cLetter > 9) {
      throw new IllegalArgumentException("Invalid column number!");
    }

    this.row = rLetter - 'A';
    this.column = cLetter;

  }

  public int getRow() {
    return row;
  }

  public int getColumn() {
    return column;
  }

  @Override
  public boolean equals(Object o) {
    if (o.getClass().equals(getClass())) {
      Coordinate c = (Coordinate) o;
      return row == c.row && column == c.column;
    }
    return false;
  }

  @Override
  public int hashCode() {
    return toString().hashCode();
  }

  @Override
  public String toString() {
    return "(" + row + ", " + column + ")";
  }

}
