/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package D3981791.phase_4.Model;

import java.io.Serializable;
import java.util.Scanner;

public class Validation implements Serializable {

  public static int intOnly(String prompt) {
    return validateInput(prompt, "integer");
  }

  public static int stringOnly(String prompt) {
    return validateInput(prompt, "string");
  }

  public static double doubleOnly(String prompt) {
    return validateInput(prompt, "double");
  }

  public static boolean boolOnly(String prompt) {
    return validateInput(prompt, "boolean") == 1;
  }

  /**
   *
   * @param prompt
   * @param type
   * @return
   */
  private static int validateInput(String prompt, String type) {
    Scanner scanner = new Scanner(System.in);

    int input = 0;
    boolean validInput = false;
    do {
      try {
        System.out.print(prompt);
        String userInput = scanner.nextLine().trim();

        switch (type.toLowerCase()) {
          case "integer":
            input = Integer.parseInt(userInput);
            break;
          case "double":
            input = (int) Double.parseDouble(userInput);
            break;
          case "boolean":
            input = userInput.equalsIgnoreCase("true") || userInput.equals("1") ? 1 : 0;
            break;
          default:
            throw new IllegalArgumentException("Unsupported type: " + type);
        }

        validInput = true;
      } catch (NumberFormatException e) {
        System.err.println("Error: Invalid input. Please enter a valid " + type + ".");
      } catch (IllegalArgumentException e) {
        System.err.println(e.getMessage());
      }
    } while (!validInput);
    return input;
  }

}