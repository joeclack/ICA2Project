/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package D3981791.phase_3.View;

import D3981791.phase_3.Model.Itinerary;
import D3981791.phase_3.Model.ItineraryAddOn;

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

  private String wordOrNumeric(int number) {
    String value;

    if (number <= 5) {
      value = Itinerary.numberToWordConverter(number);
    } else {
      return String.valueOf(number);
    }

    return value;
  }

  private void clientInfoSection(Itinerary itinerary) {
    System.out.println(generateFormattedMultiLine(("Client name: " + itinerary.getLeadAttendeeFirstName().charAt(0) + " " + itinerary.getLeadAttendeeLastName()), ("Ref: " + itinerary.getRefNumber())));
    System.out.println(generateFormattedMultiLine(("Total activities: " + wordOrNumeric(itinerary.getTotalActivities())), ("Total attendees: " + wordOrNumeric(itinerary.getTotalAttendees()))));
    System.out.println(generateFormattedSingleLine("Date: " + itinerary.getDate()));
  }

  private void itineraryAddOnsSection(Itinerary itinerary) {
    System.out.println(generateFormattedSingleLine("Itinerary add ons:"));

    for (ItineraryAddOn addOnItinerary : itinerary.getItineraryAddOnsList()) {
      double baseCost = addOnItinerary.getBaseCost() / 100;
      String formattedBaseCost = String.format("%.2f", baseCost);
      String addOnDetails = "     Add on: " + addOnItinerary.getName() + " @ £" + formattedBaseCost + " x " + itinerary.getTotalAttendees();
      System.out.println(generateFormattedSingleLine(addOnDetails));
    }

    double totalItineraryAddOnsCost = (itinerary.getTotalItineraryAddOnsCost() / 100.0) * itinerary.getTotalAttendees();
    String formattedTotalItineraryAddOnsCost = String.format("%.2f", totalItineraryAddOnsCost);
    System.out.println(generateFormattedCostLine("Sub total: £" + formattedTotalItineraryAddOnsCost));
  }

  private void activitySection(Itinerary itinerary) {
    System.out.println(generateFormattedSingleLine("Activities"));

    for (int l = 0; l < itinerary.getTotalActivities(); l++) {
      double activityBaseCost = itinerary.getActivitiesList().get(l).getBaseCost() / 100.0;
      String formattedActivityBaseCost = String.format("%.2f", activityBaseCost);
      String activityDetails = (l + 1) + ". " + itinerary.getActivitiesList().get(l).getTitle() + " @ £" + formattedActivityBaseCost + " x " + itinerary.getTotalAttendees();
      System.out.println(generateFormattedSingleLine(activityDetails));

      for (int m = 0; m < itinerary.getActivitiesList().get(l).getActivityAddOnsList().size(); m++) {
        double activityAddOnBaseCost = itinerary.getActivitiesList().get(l).getActivityAddOnsList().get(m).getBaseCost() / 100.0;
        String formattedActivityAddOnBaseCost = String.format("%.2f", activityAddOnBaseCost);
        String addOnDetails = "     Add on: " + itinerary.getActivitiesList().get(l).getActivityAddOnsList().get(m).getName() + " @ £" + formattedActivityAddOnBaseCost + " x " + itinerary.getTotalAttendees();
        System.out.println(generateFormattedSingleLine(addOnDetails));
      }

      double totalActivityCost = (itinerary.getActivitiesList().get(l).getTotalCost() / 100.0) * itinerary.getTotalAttendees();
      String formattedTotalActivityCost = String.format("%.2f", totalActivityCost);
      System.out.println(generateFormattedCostLine("Sub total: £" + formattedTotalActivityCost));
    }
  }

  private void totalCostsSection(Itinerary itinerary) {
    double totalCostBeforeDiscount = ((itinerary.getItineraryCost() / 100.0) * itinerary.getTotalAttendees() / (1 - itinerary.getDiscountDecimal()));
    String formattedTotalCost = String.format("%.2f", totalCostBeforeDiscount);
    System.out.println(generateFormattedCostLine("Total cost before discount applied: £" + formattedTotalCost));

    double discountPercentage = itinerary.getDiscountDecimal() * 100.0;
    String formattedDiscountPercentage = String.format("%.2f", discountPercentage);
    System.out.println(generateFormattedCostLine("Discount: " + formattedDiscountPercentage + "%"));

    double totalCostAfterDiscount = (itinerary.getItineraryCost() / 100.0) * itinerary.getTotalAttendees();
    String formattedTotalCostAfterDiscount = String.format("%.2f", totalCostAfterDiscount);
    System.out.println(generateFormattedCostLine("Total cost after discount applied: £" + formattedTotalCostAfterDiscount));
  }

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

  public void menu() {
    System.out.println("");
    System.out.println("Menu: ");
    System.out.println("1. Create itinerary");
    System.out.println("2. View itinerary");
    System.out.println("3. Exit");
    System.out.print(">");
  }

}
