package edu.duke.ece651.rl235;

import java.util.function.Function;

/**
 * This class handles textual display of
 * a Board (i.e., converting it to a string to show
 * to the user).
 * It supports two ways to display the Board:
 * one for the player's own board, and one for the
 * enemy's board.
 */

public class BoardTextView {

  /**
   * The Board to display
   */
  private final Board<Character> toDisplay;

  /**
   * Constructs a BoardView, given the board it will display.
   * 
   * @param toDisplay is the Board to display
   * @throws IllegalArgumentException if
   */

  public BoardTextView(Board<Character> toDisplay) {
    this.toDisplay = toDisplay;
    if (toDisplay.getWidth() > 10 || toDisplay.getHeight() > 26) {
      throw new IllegalArgumentException(
          "Board must be no larger than 10x26, but is " + toDisplay.getWidth() + "x" + toDisplay.getHeight());
    }

  }

  /**
   * This makes the header line, e.g. 0|1|2|3|4\n
   * 
   * @return the String that is the header line for the given board
   */
  String makeHeader() {
    StringBuilder ans = new StringBuilder("  "); // README shows two spaces at
    String sep = ""; // start with nothing to separate, then switch to | to separate
    for (int i = 0; i < toDisplay.getWidth(); i++) {
      ans.append(sep);
      ans.append(i);
      sep = "|";
    }
    ans.append("\n");
    return ans.toString();
  }
  // getSquareFn.apply

  public String displayMyOwnBoard() {
    return displayAnyBoard((c) -> toDisplay.whatIsAtForSelf(c));
  }

  public String displayEnemyBoard() {
    return displayAnyBoard((c) -> toDisplay.whatIsAtForEnemy(c));
  }

  protected String displayAnyBoard(Function<Coordinate, Character> getSquareFn) {
    // public String displayMyOwnBoard() {

    StringBuilder ans = new StringBuilder("");
    String sep = "  |";

    ans.append(makeHeader());

    for (int row = 0; row < toDisplay.getHeight(); row++) {
      char letter = (char) (row + 65);
      ans.append(letter);

      for (int column = 0; column < toDisplay.getWidth() - 1; column++) {

        // if (toDisplay.whatIsAtForSelf(new Coordinate(row, column)) == null) {

        if (getSquareFn.apply(new Coordinate(row, column)) == null) {

          ans.append(sep);

        }

        else {

          // ans.append(toDisplay.whatIsAtForSelf(new Coordinate(row, column)));

          ans.append(getSquareFn.apply(new Coordinate(row, column)));//need to add a space to be consistent with the end!

          ans.append("|");

        }
      }

      // Character temp = toDisplay.whatIsAtForSelf(new Coordinate(row,
      // toDisplay.getWidth()-1));

      Character temp = getSquareFn.apply(new Coordinate(row, toDisplay.getWidth() - 1));

      if (temp == null) {

        ans.append("  " + letter + '\n');

      } else {

        ans.append("" + temp + " " + letter + '\n');

      }
    }

    ans.append(makeHeader());

    return ans.toString(); // this is a placeholder for the moment
  }

}
