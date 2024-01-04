/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package D3981791.phase_1.Model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

/**
 * Validation class that contains methods for validating user input.
 */

public class Validation implements Serializable{

  /**
   * Validates user input to ensure it is an integer.
   * @param prompt
   * @param min
   * @param max
   * @return
   */
  public static int intOnly(String prompt, int min, int max) {
    Scanner scanner = new Scanner(System.in);
    int input = 0;
    boolean isValid = false;

    do {
      System.out.print(prompt);
      String userInput = scanner.nextLine().trim();

      try {
        input = Integer.parseInt(userInput);

        if (input >= min && input <= max) {
          isValid = true;
        } else {
          System.err.println("Invalid input. Please enter an integer between " + min + " and " + max + ".");
        }
      } catch (NumberFormatException e) {
        System.err.println("Invalid input. Please enter an integer.");
      }
    } while (!isValid);

    return input;
  }

  /**
   * Validates user input to ensure it is a double.
   * @param prompt
   * @param min
   * @param max
   * @return
   */
  public static String stringOnly(String prompt) {
    Scanner scanner = new Scanner(System.in);
    String input;

    do {
      System.out.print(prompt);
      input = scanner.nextLine().trim();

      if (!input.matches("[a-zA-Z]+")) {
        System.err.println("Invalid input. Please enter a string containing only alphabetical characters.");
        input = "";
      }
    } while (input.isEmpty());

    return input;
  }

  /**
   * Validates user input to ensure it is a double.
   * @param prompt
   * @param min
   * @param max
   * @return
   */
  public static LocalDate getDateInput(String prompt) {
    Scanner scanner = new Scanner(System.in);
    LocalDate inputDate = null;
    boolean isValid = false;
    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yy");

    do {
      System.out.print(prompt);
      String userInput = scanner.nextLine().trim();

      try {
        inputDate = LocalDate.parse(userInput, dateFormatter);
        isValid = true;
      } catch (DateTimeParseException e) {
        System.err.println("Invalid input. Please enter a date in the format DD-MM-YY.");
      }
    } while (!isValid);

    return inputDate;
  }

}