/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package D3981791.phase_1.Model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Itinerary class that stores information about an itinerary.
 */

public class Itinerary implements Serializable {

  private int totalAttendees;
  private int totalActivities;
  private String refNumber;
  private String leadAttendeeFirstName;
  private String leadAttendeeLastName;
  private final List<Activity> activitiesList;
  private final List<ItineraryAddOn> itineraryAddOnsList;
  private LocalDate date;
  private int totalItineraryAddOnsCost;
  private int itineraryCost;
  private double discountDecimal;
  private String numberInWordFormat;

  /**
   * Constructor for Itinerary class.
   * @param leadAttendeeFirstName The first name of the lead attendee.
   * @param leadAttendeeLastName The last name of the lead attendee.
   * @param totalAttendees The total number of attendees.
   * @param totalActivities The total number of activities.
   * @param date The date of the itinerary.
   */

  public Itinerary(String leadAttendeeFirstName, String leadAttendeeLastName, int totalAttendees, int totalActivities, LocalDate date) {
    this.totalAttendees = totalAttendees;
    this.totalActivities = totalActivities;
    this.leadAttendeeFirstName = leadAttendeeFirstName;
    this.leadAttendeeLastName = leadAttendeeLastName;
    this.date = date;
    this.activitiesList = new ArrayList<>();
    this.itineraryAddOnsList = new ArrayList<>();
  }

  /**
   * Calculates the total cost of the itinerary add-ons.
   */
  public void calculateTotalItineraryAddOnsCost() {
    int cost = 0;

    for (ItineraryAddOn addOnItinerary : itineraryAddOnsList) {
      cost += addOnItinerary.getBaseCost();
    }

    this.totalItineraryAddOnsCost = cost * this.totalAttendees;
  }

  /**
   * Calculates the total cost of the itinerary.
   */
  public void calculateItineraryCost() {
    int cost = 0;

    calculateTotalItineraryAddOnsCost();

    calculateDiscount();

    for (Activity activity : activitiesList) {
      cost += activity.getTotalCost();
    }

    cost += totalItineraryAddOnsCost;

    this.itineraryCost = (int) (cost * (1.0 - this.discountDecimal));
  }

  /**
   * Generates a reference number for the itinerary.
   */
  public void generateRefNum() {
    Random random = new Random();
    String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    int threeNums = random.nextInt(999) + 100;
    int randLetterIndex = random.nextInt(alphabet.length());
    char lastChar = alphabet.charAt(randLetterIndex);

    this.refNumber = this.leadAttendeeFirstName.charAt(0) + String.valueOf(this.leadAttendeeLastName.charAt(0)) + threeNums + lastChar;
  }

    /**
     * Calculates the discount for the itinerary.
     */
  public void calculateDiscount() {
    double[][] discountTable = {
      {0.0, 0.05, 0.08},
      {0.05, 0.08, 0.12},
      {0.10, 0.12, 0.14}
    };

    int activities = this.totalActivities;
    int attendees = this.totalAttendees;

    int rowIndex;
    int colIndex;
    if (activities >= 1 && activities <= 2) {
      colIndex = 0;
    } else if (activities <= 5) {
      colIndex = 1;
    } else {
      colIndex = 2;
    }

    if (attendees < 10) {
      rowIndex = 0;
    } else if (attendees <= 20) {
      rowIndex = 1;
    } else {
      rowIndex = 2;
    }

    this.discountDecimal = discountTable[colIndex][rowIndex];
  }

  /**
   * Converts the itinerary cost to a word format.
   */
  public static String numberToWordConverter(int numToConvert) {

    String wordVersionNumber = "";

    String[] onesDictionary = {"One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine"};
    String[] teensDictionary = {"Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
    String[] tensDictionary = {"Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};

    String numAsString = String.valueOf(numToConvert);

    int length = numAsString.length();

    if (length == 1) {

      int index = numToConvert - 1;

      wordVersionNumber = onesDictionary[index];

    } else if (length == 2) {

      if (numToConvert >= 10 && numToConvert < 20) {

        int secondDigit = Character.getNumericValue(numAsString.charAt(1));

        wordVersionNumber = teensDictionary[secondDigit];
      } else {

        int firstDigit = Character.getNumericValue(numAsString.charAt(0));
        int secondDigit = Character.getNumericValue(numAsString.charAt(1));

        wordVersionNumber = tensDictionary[firstDigit - 2] + " " + onesDictionary[secondDigit - 1];
      }
    }

    return wordVersionNumber;
  }

  public LocalDate getDate() {
    return date;
  }

  public void setDate(LocalDate date) {
    this.date = date;
  }

  public int getTotalAttendees() {
    return totalAttendees;
  }

  public void setTotalAttendees(int totalAttendees) {
    if(totalAttendees < 1 || totalAttendees > 100) {
      throw new IllegalArgumentException("Total attendees must be greater than 0 and less than 100.");
    }
    this.totalAttendees = totalAttendees;
  }

  public int getTotalActivities() {
    return totalActivities;
  }

  public void setTotalActivities(int totalActivities) {
    if(totalActivities < 1 || totalActivities > new PreBuiltItems().getAvailableActivities().size()) {
      throw new IllegalArgumentException("Total activities must be between 1 and " + new PreBuiltItems().getAvailableActivities().size() + " inclusive.");
    }
    this.totalActivities = totalActivities;
  }

  public String getRefNumber() {
    return refNumber;
  }

  public String getLeadAttendeeFirstName() {
    return leadAttendeeFirstName;
  }

  public void setLeadAttendeeFirstName(String leadAttendeeFirstName) { this.leadAttendeeFirstName = leadAttendeeFirstName; }

  public String getLeadAttendeeLastName() {
    return leadAttendeeLastName;
  }

  public void setLeadAttendeeLastName(String leadAttendeeLastName) {
    this.leadAttendeeLastName = leadAttendeeLastName;
  }

  public List<Activity> getActivitiesList() {
    return activitiesList;
  }

  public List<ItineraryAddOn> getItineraryAddOnsList() {
    return itineraryAddOnsList;
  }

  public int getItineraryCost() {
    return itineraryCost;
  }

  public double getTotalItineraryAddOnsCost() {
    return totalItineraryAddOnsCost;
  }

  public double getDiscountDecimal() {
    return discountDecimal;
  }

}