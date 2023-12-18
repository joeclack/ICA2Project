/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package D3981791.phase_1.Model;

import java.io.Serializable;
import java.util.Scanner;

public class Validation implements Serializable {

  public static int intOnly(String prompt) {
    Scanner scanner = new Scanner(System.in);
    int input = 0;
    boolean isValid = false;

    do {
      System.out.print(prompt);
      String userInput = scanner.nextLine().trim();

      try {
        input = Integer.parseInt(userInput);
        isValid = true;
      } catch (NumberFormatException e) {
        System.err.println("Invalid input. Please enter an integer.");
      }
    } while (!isValid);

    return input;
  }


}