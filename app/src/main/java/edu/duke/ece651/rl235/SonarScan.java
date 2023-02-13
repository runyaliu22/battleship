package edu.duke.ece651.rl235;

import java.util.HashMap;

public class SonarScan<T> {

  private final Board<T> board;

  public SonarScan(Board<T> board) {

    this.board = board;
  }

  public HashMap<String, Integer> getScanResult(Coordinate center)
  {

    int column = center.getColumn();
    int row = center.getRow();

    int k = 0;

    HashMap<String, Integer> hashMap = new HashMap<>();

    for (int j = column - 3; j <= column + 3; j++) {
      for (int i = row - k; i <= row + k; i++) {
        if (0 <= i && i < board.getHeight() && 0 <= j && j < board.getWidth()
            && board.whatShipIsAt(new Coordinate(i, j)) != null) {
          if (board.whatShipIsAt(new Coordinate(i, j)).getName() == "Submarine") {
            hashMap.put("Submarine", hashMap.getOrDefault("Submarine", 0) + 1);
          } else if (board.whatShipIsAt(new Coordinate(i, j)).getName() == "Battleship") {
            hashMap.put("Battleship", hashMap.getOrDefault("Battleship", 0) + 1);
          } else if (board.whatShipIsAt(new Coordinate(i, j)).getName() == "Destroyer") {
            hashMap.put("Destroyer", hashMap.getOrDefault("Destroyer", 0) + 1);
          } else {
            hashMap.put("Carrier", hashMap.getOrDefault("Carrier", 0) + 1);
          }
        }
      }
      if (j >= column) {
        k -= 1;
      } else {
        k += 1;
      }
    }

    return hashMap;

  }

  public String displayResult(HashMap<String, Integer> myMap) {

    String result = "Submarines occupy " + myMap.getOrDefault("Submarine", 0) + " squares\n" +
      "Destroyers occupy " + myMap.getOrDefault("Destroyer", 0) + " squares\n" +
      "Battleships occupy " + myMap.getOrDefault("Battleship", 0) + " squares\n" +
      "Carriers occupy " + myMap.getOrDefault("Carrier", 0) + " squares\n";
    
    return result;

  }

}
