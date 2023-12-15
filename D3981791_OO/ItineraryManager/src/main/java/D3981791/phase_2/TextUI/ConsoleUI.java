/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package D3981791.phase_2.TextUI;

import D3981791.phase_1.Model.*;

public class ConsoleUI {

  private String topBottomBorder() {
    return "+------------------------------------------------+";
  }

  private String generateFormattedMultiLine(String line1, String line2) {
    int firstStringLength = line1.length();
    int secondStringLength = line2.length();

    int MAX_WIDTH = 50;
    String LEFT_BORDER = "| ";
    String RIGHT_BORDER = " |";
    String whiteSpace = "";

    int allCharLengthIncludingBorders = LEFT_BORDER.length() + firstStringLength + secondStringLength + RIGHT_BORDER.length();
    int whiteSpaceToFillLength = MAX_WIDTH - allCharLengthIncludingBorders;
    for (int i = 0; i < whiteSpaceToFillLength; i++) {
      whiteSpace += " ";
    }

    String formattedMultiLine = LEFT_BORDER + line1 + whiteSpace + line2 + RIGHT_BORDER;
    return formattedMultiLine;
  }

  private String generateFormattedSingleLine(String line) {
    int lineLength = line.length();
    int MAX_WIDTH = 50;
    String LEFT_BORDER = "| ";
    String RIGHT_BORDER = " |";
    String whiteSpace = "";

    int allCharLengthIncludingBorders = LEFT_BORDER.length() + lineLength + RIGHT_BORDER.length();
    int whiteSpaceToFillLength = MAX_WIDTH - allCharLengthIncludingBorders;
    for (int i = 0; i < whiteSpaceToFillLength; i++) {
      whiteSpace += " ";
    }

    String formattedLine = LEFT_BORDER + line + whiteSpace + RIGHT_BORDER;
    return formattedLine;
  }

  private String generateFormattedCostLine(String line) {
    int lineLength = line.length();
    int MAX_WIDTH = 50;
    String LEFT_BORDER = "| ";
    String RIGHT_BORDER = " |";
    String whiteSpace = "";

    int allCharLengthIncludingBorders = LEFT_BORDER.length() + lineLength + RIGHT_BORDER.length();
    int whiteSpaceToFillLength = MAX_WIDTH - allCharLengthIncludingBorders;
    for (int i = 0; i < whiteSpaceToFillLength; i++) {
      whiteSpace += " ";
    }

    String formattedLine = LEFT_BORDER + whiteSpace + line + RIGHT_BORDER;
    return formattedLine;
  }

  private String WordOrNumeric(int number) {
    String value;

    if (number <= 5) {
      value = Itinerary.numberToWordConverter(number);
    } else {
      return String.valueOf(number);
    }

    return value;
  }

  public void printItinerary(Itinerary itinerary) {

    String topBottomBorder = topBottomBorder();
    String printClientAndRef = generateFormattedMultiLine(("Client name: " + itinerary.getLeadAttendeeFirstName().charAt(0) + " " + itinerary.getLeadAttendeeLastName()), ("Ref: " + itinerary.getRefNumber()));

    String printTotalAttendeesAndActivities = generateFormattedMultiLine(("Total activities: " + WordOrNumeric(itinerary.getTotalActivities())), ("Total attendees: " + WordOrNumeric(itinerary.getTotalAttendees())));
    String printDate = generateFormattedSingleLine("Date: " + itinerary.getDate());
    String printCostBreakDown = generateFormattedSingleLine("Cost break down");
    String printItineraryAddOn = generateFormattedSingleLine("Itinerary add ons:");

    String PRINT_EMPTY_LINE = generateFormattedSingleLine("");

    System.out.println(topBottomBorder);
    System.out.println(printClientAndRef);
    System.out.println(printTotalAttendeesAndActivities);
    System.out.println(printDate);
    System.out.println(PRINT_EMPTY_LINE);
    System.out.println(topBottomBorder);
    System.out.println(PRINT_EMPTY_LINE);
    System.out.println(printCostBreakDown);

    System.out.println(PRINT_EMPTY_LINE);
    System.out.println(printItineraryAddOn);

    for (ItineraryAddOn addOnItinerary : itinerary.getItineraryAddOnsList()) {
      double baseCost = addOnItinerary.getBaseCost() / 100;
      String formattedBaseCost = String.format("%.2f", baseCost);
      String addOnDetails = "     Add on: " + addOnItinerary.getName() + " @ £" + formattedBaseCost + " x " + itinerary.getTotalAttendees();
      String formattedAddOnDetails = generateFormattedSingleLine(addOnDetails);
      System.out.println(formattedAddOnDetails);
    }

    double totalItineraryAddOnsCost = (itinerary.getTotalItineraryAddOnsCost() / 100.0) * itinerary.getTotalAttendees();
    String formattedTotalItineraryAddOnsCost = String.format("%.2f", totalItineraryAddOnsCost);
    String printTotalItineraryAddOnsCost = generateFormattedCostLine("Sub total: £" + formattedTotalItineraryAddOnsCost);
    System.out.println(printTotalItineraryAddOnsCost);

    String printActivities = generateFormattedSingleLine("Activities");
    System.out.println(printActivities);

    System.out.println(PRINT_EMPTY_LINE);

    for (int l = 0; l < itinerary.getTotalActivities(); l++) {
      double activityBaseCost = itinerary.getActivitiesList().get(l).getBaseCost() / 100.0;
      String formattedActivityBaseCost = String.format("%.2f", activityBaseCost);
      String activityDetails = (l + 1) + ". " + itinerary.getActivitiesList().get(l).getTitle() + " @ £" + formattedActivityBaseCost + " x " + itinerary.getTotalAttendees();
      String printActivityDetails = generateFormattedSingleLine(activityDetails);
      System.out.println(printActivityDetails);

      for (int m = 0; m < itinerary.getActivitiesList().get(l).getActivityAddOnsList().size(); m++) {
        double activityAddOnBaseCost = itinerary.getActivitiesList().get(l).getActivityAddOnsList().get(m).getBaseCost() / 100.0;
        String formattedActivityAddOnBaseCost = String.format("%.2f", activityAddOnBaseCost);
        String addOnDetails = "     Add on: " + itinerary.getActivitiesList().get(l).getActivityAddOnsList().get(m).getName() + " @ £" + formattedActivityAddOnBaseCost + " x " + itinerary.getTotalAttendees();
        String printAddOnDetails = generateFormattedSingleLine(addOnDetails);
        System.out.println(printAddOnDetails);
      }

      double totalActivityCost = (itinerary.getActivitiesList().get(l).getTotalCost() / 100.0) * itinerary.getTotalAttendees();
      String formattedTotalActivityCost = String.format("%.2f", totalActivityCost);
      String printTotalActivityCost = generateFormattedCostLine("Sub total: £" + formattedTotalActivityCost);
      System.out.println(printTotalActivityCost);
    }

    System.out.println(PRINT_EMPTY_LINE);
    System.out.println(topBottomBorder);
    System.out.println(PRINT_EMPTY_LINE);

    double totalCostBeforeDiscount = ((itinerary.getItineraryCost() / 100.0) * itinerary.getTotalAttendees() / (1 - itinerary.getDiscountDecimal()));
    String formattedTotalCost = String.format("%.2f", totalCostBeforeDiscount);
    String printTotalCostBeforeDiscount = generateFormattedCostLine("Total cost before discount applied: £" + formattedTotalCost);
    System.out.println(printTotalCostBeforeDiscount);

    System.out.println(PRINT_EMPTY_LINE);

    double discountPercentage = itinerary.getDiscountDecimal() * 100.0;
    String formattedDiscountPercentage = String.format("%.2f", discountPercentage);
    String printDiscountPercentage = generateFormattedCostLine("Discount: " + formattedDiscountPercentage + "%");
    System.out.println(printDiscountPercentage);

    System.out.println(PRINT_EMPTY_LINE);

    double totalCostAfterDiscount = (itinerary.getItineraryCost() / 100.0) * itinerary.getTotalAttendees();
    String formattedTotalCostAfterDiscount = String.format("%.2f", totalCostAfterDiscount);
    String printTotalCostAfterDiscount = generateFormattedCostLine("Total cost after discount applied: £" + formattedTotalCostAfterDiscount);
    System.out.println(printTotalCostAfterDiscount);

    System.out.println(topBottomBorder);
  }


  public StringBuilder menu(){
    StringBuilder menuString = new StringBuilder();
    menuString.append(" \n").append("New itinerary \n").append("1. New itinerary \n").append("2. View itineraries \n").append("3. Exit \n").append("");
    return menuString;
  }

}