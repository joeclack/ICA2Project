/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package d3981791.phase2.model;

import d3981791.phase1.model.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * ItineraryInput class that handles user input for creating an itinerary.
 */
public class ItineraryInput implements Serializable {

    private static final PreBuiltItems preBuiltItems = new PreBuiltItems();
    private static final Scanner scanner = new Scanner(System.in);
    private static List<Activity> filteredActivities;

    /**
     * Generates an itinerary based on user input.
     *
     * @return The generated itinerary.
     */
    public static Itinerary generateItinerary() {

        Itinerary itinerary = enterClientInfo();

        String divider = "--------------------";

        System.out.println(divider);
        System.out.println();

        filteredActivities = preBuiltItems.getAvailableActivities();

        chooseActivity(itinerary, divider);

        chooseItineraryAddOns(divider, itinerary);

        itinerary.calculateItineraryCost();
        itinerary.generateRefNum();

        return itinerary;
    }

    /**
     * Allows the user to enter the client information for an itinerary.
     *
     * @return The itinerary with the client information.
     */
    private static Itinerary enterClientInfo() {
        String firstName = Validation.stringOnly("Enter first name: ");
        String lastName = Validation.stringOnly("Enter last name: ");
        int totalAttendees = Validation.intOnly("Enter total attendees: ", 1, 100);
        int totalActivities = Validation.intOnly("Enter total activities: ", 1, preBuiltItems.getAvailableActivities().size());
        LocalDate date = Validation.getDateInput("Enter a date (dd-mm-yy): ");

        return new Itinerary(firstName, lastName, totalAttendees, totalActivities, date);
    }

    /**
     * Allows the user to choose the add-ons for an itinerary.
     *
     * @param divider   The divider to display.
     * @param itinerary The itinerary to add the add-ons to.
     */
    private static void chooseItineraryAddOns(String divider, Itinerary itinerary) {
        System.out.println("Available add-ons for the itinerary:");
        System.out.println(divider);
        for (int num = 0; num < preBuiltItems.getAvailableItineraryAddOns().size(); num++) {
            System.out.println((num + 1) + ". " + preBuiltItems.getAvailableItineraryAddOns().get(num).getName());
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
                boolean validInputNumbers = validItineraryAddOnInput(itinerary, itineraryAddOnNumbers);

                if (validInputNumbers) {
                    validInput = true;
                }
            }
        }
    }

    private static boolean validItineraryAddOnInput(Itinerary itinerary, String[] itineraryAddOnNumbers) {
        boolean validInputNumbers = true;

        for (String addOnNumber : itineraryAddOnNumbers) {
            try {
                int index = Integer.parseInt(addOnNumber) - 1;
                if (index >= 0 && index < preBuiltItems.getAvailableItineraryAddOns().size()) {
                    itinerary.getItineraryAddOnsList().add(preBuiltItems.getAvailableItineraryAddOns().get(index));
                } else {
                    System.out.println("Invalid add-on number: " + addOnNumber);
                    validInputNumbers = false;

                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input: " + addOnNumber + " is not a valid number.");
                validInputNumbers = false;

            }
        }
        return validInputNumbers;
    }

    /**
     * Allows the user to choose the activities for an itinerary.
     *
     * @param itinerary The itinerary to add the activities to.
     * @param divider   The divider to display.
     */
    private static void chooseActivity(Itinerary itinerary, String divider) {
        for (int i = 0; i < itinerary.getTotalActivities(); i++) {
            System.out.println("Activity number " + (i + 1));

            System.out.println("Available activities");
            System.out.println(divider);

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

            LocalTime inputTime = Validation.getTimeInput("Enter a time (HH:MM): ");
            selectedActivity.setTime(inputTime);


            chooseActivityAddOns(divider, selectedActivity);

            System.out.println();


            selectedActivity.calculateTotalCost(itinerary.getTotalAttendees());
            itinerary.getActivitiesList().add(selectedActivity);
        }
    }

    /**
     * Allows the user to choose the add-ons for an activity.
     *
     * @param divider          The divider to display.
     * @param selectedActivity The activity to add the add-ons to.
     */
    private static void chooseActivityAddOns(String divider, Activity selectedActivity) {
        System.out.println("Available add-ons for " + selectedActivity.getTitle());
        System.out.println(divider);

        if (selectedActivity.isRequireInsurance()) {
            System.out.println("** Insurance is required for this activity **");
            boolean validChoice = false;
            while (!validChoice) {
                int insuranceResult = Validation.intOnly("Which insurance is required? \n 1. Exciting Activities insurance \n 2. Third party insurance \n> ", 1, 2);
                switch (insuranceResult) {
                    case 1:
                        selectedActivity.getActivityAddOnsList().add(preBuiltItems.getAvailableActivityAddOns().get(1));
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
            List<ActivityAddOn> filteredAddOns = preBuiltItems.getAvailableActivityAddOns().stream()
                    .filter(addOn -> !addOn.getName().equals("Insurance"))
                    .collect(Collectors.toList());

            for (int j = 0; j < filteredAddOns.size(); j++) {
                System.out.println((j + 1) + ". " + filteredAddOns.get(j).getName());
            }
            addActivityAddOns(filteredAddOns, selectedActivity, scanner);
        } else {
            for (int j = 0; j < preBuiltItems.getAvailableActivityAddOns().size(); j++) {
                System.out.println((j + 1) + ". " + preBuiltItems.getAvailableActivityAddOns().get(j).getName());
            }
            addActivityAddOns(preBuiltItems.getAvailableActivityAddOns(), selectedActivity, scanner);
        }
    }

    /**
     * Allows the user to enter the add-on numbers for an activity.
     *
     * @param listToAddFrom    The list of add-ons to add from.
     * @param selectedActivity The activity to add the add-ons to.
     * @param scanner          The scanner to use for user input.
     */
    public static void addActivityAddOns(List<ActivityAddOn> listToAddFrom, Activity selectedActivity, Scanner scanner) {
        boolean validInput = false;

        while (!validInput) {
            System.out.print("Enter add-on numbers separated by spaces (Enter if none are required): ");
            String activityAddOnNumbersInput = scanner.nextLine();
            System.out.println();

            String[] activityAddOnNumbers = activityAddOnNumbersInput.split(" ");

            if (activityAddOnNumbersInput.isEmpty()) {

                validInput = true;
            } else {
                boolean validInputNumbers = validItineraryAddOnInput(listToAddFrom, selectedActivity, activityAddOnNumbers);

                if (validInputNumbers) {
                    validInput = true;
                }

            }
        }
    }

    /**
     * Validates the add-on numbers entered by the user.
     *
     * @param listToAddFrom       The list of add-ons to add from.
     * @param selectedActivity    The activity to add the add-ons to.
     * @param activityAddOnNumbers The add-on numbers entered by the user.
     * @return Whether the add-on numbers are valid.
     */
    private static boolean validItineraryAddOnInput(List<ActivityAddOn> listToAddFrom, Activity selectedActivity, String[] activityAddOnNumbers) {
        boolean validInputNumbers = true;

        for (String addOnNumber : activityAddOnNumbers) {
            try {
                int index = Integer.parseInt(addOnNumber) - 1;
                if (index >= 0 && index < listToAddFrom.size()) {
                    selectedActivity.getActivityAddOnsList().add(listToAddFrom.get(index));
                } else {
                    System.out.println("Invalid add-on number: " + addOnNumber);
                    validInputNumbers = false;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input: " + addOnNumber + " is not a valid number.");
                validInputNumbers = false;
            }
        }
        return validInputNumbers;
    }

}