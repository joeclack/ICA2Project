/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package D3981791.phase_2.Model;

import D3981791.phase_1.Model.*;

import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ItineraryInput {

  public static Itinerary generateItinerary(List<Activity> availableActivities, List<ActivityAddOn> availableActivityAddOns, List<ItineraryAddOn> availableItineraryAddOns) {

    Itinerary itinerary = new Itinerary();

    Scanner scanner = new Scanner(System.in);

    String Divider = "--------------------";

    itinerary.setLeadAttendeeFirstName(Validation.stringOnly("Enter first name: "));

    itinerary.setLeadAttendeeLastName(Validation.stringOnly("Enter last name: "));

    itinerary.setTotalAttendees(Validation.intOnly("Enter total attendees: ", 1, 100));

    int totalActivities = Validation.intOnly("Enter total activities: ", 1, availableActivities.size());
    itinerary.setTotalActivities(totalActivities);

    itinerary.setDate(Validation.getDateInput("Enter a date (DD-MM-YY): "));

    System.out.println(Divider);
    System.out.println();
    
    List<Activity> filteredActivities = availableActivities;

    for (int i = 0; i < totalActivities; i++) {
      System.out.println("Activity number " + (i+1));

      System.out.println("Available activities");
      System.out.println(Divider);
      
      for (int count = 0; count < filteredActivities.size(); count++) {
        Activity activity = filteredActivities.get(count);
        System.out.println((count + 1) + ". " + activity.getTitle());
      }

      System.out.println();

      int activityNumber = Validation.intOnly("Enter activity number to add: ", 1, filteredActivities.size());

      
      Activity selectedActivity = filteredActivities.get(activityNumber - 1);

      filteredActivities = filteredActivities.stream()
                .filter(activity -> !activity.getTitle().equals(selectedActivity.getTitle()))
                .collect(Collectors.toList());

      LocalTime inputTime;

      while (true) {
        try {
          System.out.print("Enter time for " + selectedActivity.getTitle() + " in the format HH:MM: ");
          String userInput = scanner.nextLine();
          inputTime = LocalTime.parse(userInput);
          selectedActivity.setTime(inputTime);
          break;
        } catch (DateTimeParseException e) {
          System.out.println("Invalid input! Please enter a time in the format HH:MM.");
        }
      }

      System.out.println("Available add-ons for " + selectedActivity.getTitle());
      System.out.println(Divider);

      if (selectedActivity.isRequireInsurance()) {
        System.out.println("** Insurance is required for this activity **");
        boolean validChoice = false;
        while (!validChoice) {
          int insuranceResult = Validation.intOnly("Which insurance is required? \n 1. Exciting Activities insurance \n 2. Third party insurance \n> ", 1, 2);
          switch (insuranceResult) {
            case 1:
              selectedActivity.getActivityAddOnsList().add(availableActivityAddOns.get(1)); 
              selectedActivity.setThirdPartyInsurance(false);
              validChoice = true;
              break;
            case 2:
              selectedActivity.setThirdPartyInsurance(true);
              validChoice = true;
              break;
            default:
          }
        }
        List<ActivityAddOn> filteredAddOns = availableActivityAddOns.stream()
                .filter(addOn -> !addOn.getName().equals("Insurance"))
                .collect(Collectors.toList());

        for (int j = 0; j < filteredAddOns.size(); j++) {
          System.out.println((j + 1) + ". " + filteredAddOns.get(j).getName());
        }
        enterActivityAddOnNumbers(filteredAddOns, selectedActivity, scanner);
      } else {
        for (int j = 0; j < availableActivityAddOns.size(); j++) {
          System.out.println((j + 1) + ". " + availableActivityAddOns.get(j).getName());
        }
        enterActivityAddOnNumbers(availableActivityAddOns, selectedActivity, scanner);
      }

      System.out.println();
      

      selectedActivity.calculateTotalCost(itinerary.getTotalAttendees());
      itinerary.getActivitiesList().add(selectedActivity);
    }

    System.out.println("Available add-ons for the itinerary:");
    System.out.println(Divider);
    for (int num = 0; num < availableItineraryAddOns.size(); num++) {
      System.out.println((num + 1) + ". " + availableItineraryAddOns.get(num).getName());
    }

    System.out.println();
    boolean validInput = false;

    while (!validInput) {
      System.out.print("Enter add-on numbers separated by spaces (Enter if none are required): ");
      String itineraryAddOnNumbersInput = scanner.nextLine();
      System.out.println();

      String[] itineraryAddOnNumbers = itineraryAddOnNumbersInput.split(" ");

      if (itineraryAddOnNumbersInput.isEmpty()) {
        validInput = true;
      } else {
        boolean validInputNumbers = true;

        for (String addOnNumber : itineraryAddOnNumbers) {
          try {
            int index = Integer.parseInt(addOnNumber) - 1;
            if (index >= 0 && index < availableItineraryAddOns.size()) {
              itinerary.getItineraryAddOnsList().add(availableItineraryAddOns.get(index));
            } else {
              System.out.println("Invalid add-on number: " + addOnNumber);
              validInputNumbers = false;
              break;
            }
          } catch (NumberFormatException e) {
            System.out.println("Invalid input: " + addOnNumber + " is not a valid number.");
            validInputNumbers = false;
            break;
          }
        }

        if (validInputNumbers) {
          validInput = true;
        }
      }
    }

    itinerary.calculateItineraryCost();
    itinerary.generateRefNum();

    return itinerary;
  }

  public static void enterActivityAddOnNumbers(List<ActivityAddOn> listToAddFrom, Activity selectedActivity, Scanner scanner) {
    boolean validInput = false;

    while (!validInput) {
      System.out.print("Enter add-on numbers separated by spaces (Enter if none are required): ");
      String activityAddOnNumbersInput = scanner.nextLine();
      System.out.println();

      String[] activityAddOnNumbers = activityAddOnNumbersInput.split(" ");

      if (activityAddOnNumbersInput.isEmpty()) {

        validInput = true;
      } else {
        boolean validInputNumbers = true;

        for (String addOnNumber : activityAddOnNumbers) {
          try {
            int index = Integer.parseInt(addOnNumber) - 1;
            if (index >= 0 && index < listToAddFrom.size()) {
              selectedActivity.getActivityAddOnsList().add(listToAddFrom.get(index));
            } else {
              System.out.println("Invalid add-on number: " + addOnNumber);
              validInputNumbers = false;
              break;
            }
          } catch (NumberFormatException e) {
            System.out.println("Invalid input: " + addOnNumber + " is not a valid number.");
            validInputNumbers = false;
            break;
          }
        }
        
        if (validInputNumbers) {
          validInput = true;
        }
      }
    }
  }

}