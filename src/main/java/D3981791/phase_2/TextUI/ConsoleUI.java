/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package D3981791.phase_2.TextUI;

import D3981791.phase_1.Model.Activity;
import D3981791.phase_1.Model.ActivityAddOn;
import D3981791.phase_1.Model.Itinerary;
import D3981791.phase_1.Model.ItineraryAddOn;

import java.io.Serializable;

/**
 * ConsoleUI class that handles the console user interface.
 */
public class ConsoleUI implements Serializable {

  final int MAX_WIDTH = 55;
  final String LEFT_BORDER = "| ";
  final String RIGHT_BORDER = " |";
  public StringBuilder whiteSpace;

  public int lineLength(String line) {
    return line.length();
  }

  /**
   * Generates a top or bottom border.
   * @return The top or bottom border.
   */
  private String topBottomBorder() {
    return "+" + "-".repeat(Math.max(0, MAX_WIDTH - 2)) + "+";
  }

  /**
   * Generates a formatted multi line.
   * @param line1 The first line to format.
   * @param line2 The second line to format.
   * @return The formatted multi line.
   */
  private String generateFormattedMultiLine(String line1, String line2) {
    whiteSpace = new StringBuilder();

    int allCharLengthIncludingBorders = LEFT_BORDER.length() + lineLength(line1) + lineLength(line2) + RIGHT_BORDER.length();
    int whiteSpaceToFillLength = MAX_WIDTH - allCharLengthIncludingBorders;
    whiteSpace.append(" ".repeat(Math.max(0, whiteSpaceToFillLength)));

    return LEFT_BORDER + line1 + whiteSpace + line2 + RIGHT_BORDER;
  }

  /**
   * Generates a formatted single line.
   * @param line The line to format.
   * @return The formatted line.
   */
  private String generateFormattedSingleLine(String line) {
    whiteSpace = new StringBuilder();

    int allCharLengthIncludingBorders = LEFT_BORDER.length() + lineLength(line) + RIGHT_BORDER.length();
    int whiteSpaceToFillLength = MAX_WIDTH - allCharLengthIncludingBorders;
    whiteSpace.append(" ".repeat(Math.max(0, whiteSpaceToFillLength)));

    return LEFT_BORDER + line + whiteSpace + RIGHT_BORDER;
  }

  /**
   * Generates a formatted cost line.
   * @param line The line to format.
   * @return The formatted cost line.
   */
  private String generateFormattedCostLine(String line) {
    whiteSpace = new StringBuilder();

    int allCharLengthIncludingBorders = LEFT_BORDER.length() + lineLength(line) + RIGHT_BORDER.length();
    int whiteSpaceToFillLength = MAX_WIDTH - allCharLengthIncludingBorders;
    whiteSpace.append(" ".repeat(Math.max(0, whiteSpaceToFillLength)));

    return LEFT_BORDER + whiteSpace + line + RIGHT_BORDER;
  }

  /**
   * Converts a number to a word.
   * @param number The number to convert.
   * @return The word representation of the number.
   */
  private String wordOrNumeric(int number) {
    return (number <= 5) ? Itinerary.numberToWordConverter(number) : String.valueOf(number);
  }

  /**
   * Prints the client info section of the itinerary.
   * @param itinerary The itinerary to print.
   */
  private void clientInfoSection(Itinerary itinerary) {
    System.out.println(generateFormattedMultiLine(("Client name: " + itinerary.getLeadAttendeeFirstName().charAt(0) + " " + itinerary.getLeadAttendeeLastName()), ("Ref: " + itinerary.getRefNumber())));
    System.out.println(generateFormattedMultiLine(("Total activities: " + wordOrNumeric(itinerary.getTotalActivities())), ("Total attendees: " + wordOrNumeric(itinerary.getTotalAttendees()))));
    System.out.println(generateFormattedSingleLine("Date: " + itinerary.getDate()));
  }

  /**
   * Prints the itinerary add ons section of the itinerary.
   * @param itinerary The itinerary to print.
   */
  private void itineraryAddOnsSection(Itinerary itinerary) {
    System.out.println(generateFormattedSingleLine("Itinerary add ons:"));
    int itineraryAddOnCount = 1;

    for (ItineraryAddOn addOnItinerary : itinerary.getItineraryAddOnsList()) {
      System.out.println(generateFormattedSingleLine((itineraryAddOnCount++) + ". Add on: " + addOnItinerary.getName() + " @ £" + String.format("%.2f", (addOnItinerary.getBaseCost() / 100.0)) + " x " + itinerary.getTotalAttendees()));
    }
    System.out.println(generateFormattedCostLine("Sub total: £" + String.format("%.2f", (itinerary.getTotalItineraryAddOnsCost() / 100.0))));

  }

  /**
   * Prints the activities section of the itinerary.
   * @param itinerary The itinerary to print.
   */
  private void activitySection(Itinerary itinerary) {
    System.out.println(generateFormattedSingleLine("Activities"));
    int activityCount = 1;
    int activityAddOnCount = 1;

    for(Activity activity : itinerary.getActivitiesList()) {
      System.out.println(generateFormattedSingleLine((activityCount++) + ". " + activity.getTitle() + " @ £" + String.format("%.2f", (activity.getBaseCost() / 100.0)) + " x " + itinerary.getTotalAttendees()));
      for(ActivityAddOn addOnActivity : activity.getActivityAddOnsList()) {
        System.out.println(generateFormattedSingleLine("     " + (activityAddOnCount++) + ". Add on: " + addOnActivity.getName() + " @ £" + String.format("%.2f", (addOnActivity.getBaseCost() / 100.0)) + " x " + itinerary.getTotalAttendees()));
      }
      activityAddOnCount = 1;
      System.out.println(generateFormattedCostLine("     Sub total: £" + String.format("%.2f", (activity.getTotalCost() / 100.0))));
    }
  }

  private void totalCostsSection(Itinerary itinerary) {
    System.out.println(generateFormattedCostLine("Total cost before discount applied: £" + String.format("%.2f", ((itinerary.getItineraryCost() / 100.0) / (1 - itinerary.getDiscountDecimal())))));

    System.out.println(generateFormattedCostLine("Discount: " + String.format("%.2f", itinerary.getDiscountDecimal() * 100.0) + "%"));

    System.out.println(generateFormattedCostLine("Total cost after discount applied: £" + String.format("%.2f", (itinerary.getItineraryCost() / 100.0))));
  }

  /**
   * Prints the full itinerary.
   * @param itinerary The itinerary to print.
   */
  public void fullItinerary(Itinerary itinerary) {

    System.out.println(topBottomBorder());

    clientInfoSection(itinerary);

    System.out.println(topBottomBorder());

    itineraryAddOnsSection(itinerary);
    activitySection(itinerary);

    System.out.println(topBottomBorder());

    totalCostsSection(itinerary);

    System.out.println(topBottomBorder());
  }

  /**
   * Prints the menu.
   */
  public void menu(){
    System.out.println("New itinerary \n" + "1. New itinerary \n" + "2. View itineraries \n" + "3. Exit \n");
  }

}