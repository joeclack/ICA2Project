/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package D3981791.phase_2;

import D3981791.phase_1.Library.*;
import D3981791.phase_1.Model.*;
import D3981791.phase_2.Model.*;
import D3981791.phase_2.TextUI.*;

import java.util.List;
import java.util.Scanner;

public class Phase2Run {

  public static void main(String[] args) {

    Scanner scanner = new Scanner(System.in);
    String Divider = "--------------------";

    Itinerary itinerary = new Itinerary();

    ActivityRepository activityRepository = new ActivityRepository();
    List<Activity> availableActivities = activityRepository.getAllActivities();

    ActivityAddOnRepository activityAddOnRepository = new ActivityAddOnRepository();
    List<ActivityAddOn> availableActivitiyAddOns = activityAddOnRepository.getAllActivityAddOns();

    ItineraryAddonRepository itineraryAddOnRepository = new ItineraryAddonRepository();
    List<ItineraryAddOn> availableItineraryAddOns = itineraryAddOnRepository.getAllItineraryAddOns();


    ItineraryInput input = new ItineraryInput();

    itinerary = input.generateItinerary(availableActivities, availableActivitiyAddOns, availableItineraryAddOns);


    itinerary.calculateItineraryCost();
    itinerary.generateRefNum();

    ConsoleUI display = new ConsoleUI();
    display.fullItinerary(itinerary);
  }
}