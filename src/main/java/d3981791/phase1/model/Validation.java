/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package d3981791.phase1.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

/**
 * Validation class that contains methods for validating user input.
 */

public class Validation implements Serializable {

    /**
     * Validates user input to ensure it is an integer.
     *
     * @param prompt The prompt to be displayed to the user.
     * @param min    The minimum value of the integer.
     * @param max    The maximum value of the integer.
     * @return The integer input.
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
     * Validates user input to ensure it is a string.
     *
     * @param prompt The prompt to be displayed to the user.
     * @return The double input.
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
     * Validates user input to ensure it is a date.
     *
     * @param prompt The prompt to be displayed to the user.
     * @return The date input.
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

    public static LocalTime getTimeInput(String prompt) {
        Scanner scanner = new Scanner(System.in);
        LocalTime inputTime = null;
        boolean isValid = false;
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

        do {
            System.out.print(prompt);
            String userInput = scanner.nextLine().trim();

            try {
                inputTime = LocalTime.parse(userInput, timeFormatter);
                isValid = true;
            } catch (DateTimeParseException e) {
                System.err.println("Invalid input. Please enter a time in the format HH:MM.");
            }
        } while (!isValid);

        return inputTime;
    }

}