package edu.duke.ece651.rl235;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;

public class ComputerPlayer extends TextPlayer {

  public ComputerPlayer(Integer moveChances, Integer sonarChances,String name, Board<Character> theBoard, BufferedReader inputSource, PrintStream out,
                    AbstractShipFactory<Character> factory) {

    super(moveChances, sonarChances, name, theBoard, inputSource,  out,
          factory);
    
  }


  @Override

  public String playOneTurn(Board<Character> enBoard, BoardTextView enView, String myHeader, String enHeader) throws IOException{
    
    out.println("Player " + name + "'s turn:\n");

    String s = inputReader.readLine();

    Coordinate c = new Coordinate(s);// need to take care of error handling!

    enBoard.fireAt(c);

    if (enBoard.whatIsAtForEnemy(c) == 's') {

      return "You hit a submarine at " + s + "!\n";

    }

    if (enBoard.whatIsAtForEnemy(c) == 'd') {

      return "You hit a destroyer at " + s + "!\n";
    }

    if (enBoard.whatIsAtForEnemy(c) == 'b') {// tested in main later!

      return "You hit a battleship at " + s + "!\n";
    }

    if (enBoard.whatIsAtForEnemy(c) == 'c') {

      return "You hit a carrier at " + s + "!\n";
    }

    return "You missed!\n";
  }

}
