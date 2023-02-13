package edu.duke.ece651.rl235;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;

public class ModeChoose {

  final BufferedReader inputReader;

  final PrintStream out;

  public ModeChoose(BufferedReader inputReader, PrintStream out) {
    this.inputReader = inputReader;
    this.out = out;
  }

  public String chooseMode() throws IOException {
    String prompt = "There are four modes of this game, please enter the corresponding number to play with:\n" +
    "1. Human vs Human\n" + 
    "2. Human vs Computer\n" + 
    "3. Computer vs Human\n" + 
    "4. Computer vs Computer\n";
    out.println(prompt);

    String s = inputReader.readLine();
    return s;

  }

}
