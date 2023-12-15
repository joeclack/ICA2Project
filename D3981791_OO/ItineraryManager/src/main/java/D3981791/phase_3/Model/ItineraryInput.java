/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package D3981791.phase_3.Model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class ItineraryInput {

  public Itinerary generateItinerary(List<Activity> availableActivities, List<ActivityAddOn> availableActivitiyAddOns, List<ItineraryAddOn> availableItineraryAddOns) {

    Itinerary itinerary = new Itinerary();

    Scanner scanner = new Scanner(System.in);

    String Divider = "--------------------";

    String firstName = "";
    while (firstName.trim().isEmpty()) {
      System.out.print("Enter first name: ");
      firstName = scanner.nextLine().trim();

      if (firstName.isEmpty()) {
        System.out.println("First name cannot be empty. Please enter again.");
      }
    }

    itinerary.setLeadAttendeeFirstName(firstName.toUpperCase());

    String lastName = "";
    while (lastName.trim().isEmpty()) {
      System.out.print("Enter last name: ");
      lastName = scanner.nextLine().trim();

      if (lastName.isEmpty()) {
        System.out.println("Last name cannot be empty. Please enter again.");
      }
    }

    itinerary.setLeadAttendeeLastName(lastName.toUpperCase());

    int totalAttendees = Validation.intOnly("Enter total attendees: ");
    itinerary.setTotalAttendees(totalAttendees);

    int totalActivities = 0;

    while (true) {
      totalActivities = Validation.intOnly("Enter total activities: ");

      if (totalActivities <= 0) {
        System.out.println("Please enter a number greater than 0.");
      } else if (totalActivities >= availableActivities.size()) {
        System.out.println("Please enter a number less than the total available activities.");
      } else {
        break;
      }
    }

    itinerary.setTotalActivities(totalActivities);

    LocalDate inputDate;

    while (true) {
      try {
        System.out.print("Enter a date (YYYY-MM-DD): ");
        String userInput = scanner.nextLine();
        inputDate = LocalDate.parse(userInput);
        itinerary.setDate(inputDate);
        break;
      } catch (DateTimeParseException e) {
        System.out.println("Invalid input! Please enter a date in the format YYYY-MM-DD.");
      }
    }

    System.out.println(Divider);
    System.out.println();

    for (int i = 0; i < totalActivities; i++) {

      System.out.println("Available activities");
      System.out.println(Divider);

      for (int count = 0; count < availableActivities.size(); count++) {
        Activity activity = availableActivities.get(count);
        System.out.println(count + 1 + ". " + activity.getTitle());
      }

      System.out.println();

      int activityNumber = Validation.intOnly("Enter activity number to add: ");

      Activity selectedActivity = availableActivities.get(activityNumber - 1);

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
      for (int j = 0; j < availableActivitiyAddOns.size(); j++) {
        System.out.println((j + 1) + ". " + availableActivitiyAddOns.get(j).getName());
      }

      System.out.println();

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
              if (index >= 0 && index < availableActivitiyAddOns.size()) {
                selectedActivity.getActivityAddOnsList().add(availableActivitiyAddOns.get(index));
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

      selectedActivity.calculateTotalCost();
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

}
